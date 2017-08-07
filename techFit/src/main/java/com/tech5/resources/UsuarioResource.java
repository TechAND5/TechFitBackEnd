package com.tech5.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tech5.db.DAOFactory;
import com.tech5.db.UsuarioDAO;
import com.tech5.models.Message;
import com.tech5.models.Usuario;

import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/usuarios")
public class UsuarioResource extends JSONService {

	private static List<Usuario> misUsuarios;
	static {
		misUsuarios = new ArrayList<Usuario>();
	}

	// POST inserta un usuario a la lista total de usuarios de la BBDD
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUser, @HeaderParam("token") String token) {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		if (userEmail == null) {
			Message statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		} else {
			UsuarioResource.misUsuarios.add(nuevoUser);
			Message statusMensaje = new Message(Status.CREATED.getStatusCode(), "Usuario Añadido!!!");
			mResponse = Response.status(200).entity(statusMensaje).build();
		}
		return mResponse;

	}

	// GET{uid}: obtiene un usuario por su id
	
	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("uid") int uid, @HeaderParam("token") String token) {

		String userEmail =  this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}


		try {
			// Existe usuario que se pide?
			Usuario elUsuario = new Usuario();
			UsuarioDAO uDAO = (UsuarioDAO) DAOFactory.getDAO("usuario");
			elUsuario = uDAO.getUsuario(uid);
		  
		  // existe usuario if (elUsuario != null) { return
			if (elUsuario != null) {
				return Response.status(200).entity(elUsuario).build();
			} else {
				throw new RuntimeException("- El usuario (" + uid + ") es desconocido.");
			}
		} catch (Exception e) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto usuario.\nLease API");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}
		
	return mResponse;

	}

	// PUT{uid} actualiza datos de un usuario
	@Path("/{uid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("uid") int uid, Usuario updateUser, @HeaderParam("token") String token) {

		String userEmail =  this.getUserEmailFromToken(token);
		Response mResponse = null;

		if (userEmail == null) {
			Message statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
			}

		try {
			// Existe usuario que se pide?
			Usuario elUsuario = new Usuario();
			UsuarioDAO uDAO = (UsuarioDAO) DAOFactory.getDAO("usuario");
			elUsuario = uDAO.getUsuario(uid);
			// si existe el usuario entonces:
			if (elUsuario != null) {
				if (uDAO.updateUsuario(elUsuario)) {
					Message statusMessage = new Message(Status.CREATED.getStatusCode(), "usuario modificado!!!");
					mResponse = Response.status(200).entity(statusMessage).build();
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
							.entity(" La modificacion del usuario ha sido cancelada.").build();
				}
			} else {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
						.entity(" El usuario (" + uid + ") es inexistente").build();
			}
		} catch (Exception e) {
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
					.entity(e.getMessage() + "Formato erroneo en el cuerpo del objeto usuario. Lease API").build();
			return mResponse;
		}

		return mResponse;

		
	}
}
