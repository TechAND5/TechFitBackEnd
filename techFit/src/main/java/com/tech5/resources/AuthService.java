package com.tech5.resources;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.tech5.db.DAOFactory;
import com.tech5.db.UsuarioDAO;
import com.tech5.models.Message;
import com.tech5.models.Usuario;



@Path("/authenticate")
public class AuthService extends JSONService{
	
	private static Logger logger = Logger.getLogger("JSONService");
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(@HeaderParam("username") String username,
			@HeaderParam("password") String password)
			throws JsonGenerationException, JsonMappingException, IOException {
		logger.info("Authenticating User Credentials...:"+username+":"+password);

		if (username == null) {
			Message statusMessage = new Message();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setBody("Username value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
			
					}

		if (password == null) {
			Message statusMessage = new Message();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setBody("Password value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}
		
		Usuario user = null;
		UsuarioDAO usuarioDAO;
		try {
			usuarioDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO("usuario");
			user = usuarioDAO.getUsuario(username, password);
			logger.log(Level.INFO, "user:" + user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			Message statusMessage = new Message();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setBody("Access Denied for this functionality !!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);

		senderJwk.setKeyId("1");
		logger.info("JWK (1) ===> " + senderJwk.toJson());

		// Create the Claims, which will be the content of the JWT
		JwtClaims claims = new JwtClaims();
		claims.setIssuer("tech5.com"); // who creates the token and signs it
		claims.setExpirationTimeMinutesInTheFuture(20); // token will expire (10
														// minutes from now)
		claims.setGeneratedJwtId(); // a unique identifier for the token
		claims.setIssuedAtToNow(); // when the token was issued/created (now)
		claims.setNotBeforeMinutesInThePast(2); // time before which the token
												// is not yet valid (2 minutes
												// ago)
		claims.setSubject(user.getEmail()); // the subject/principal is whom
											// the token is about
		claims.setStringListClaim("roles", "client"); //
		// multi-valued claims for roles
		JsonWebSignature jws = new JsonWebSignature();

		jws.setPayload(claims.toJson());

		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(jwt).build();
	}
}
