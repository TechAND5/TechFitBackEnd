package com.tech5.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tech5.models.Message;
import com.tech5.models.Usuario;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.tech5.db.DAOFactory;
import com.tech5.db.UsuarioDAO;

@Path("/usuario")
public class UsuarioResource extends JSONService {
	public static UsuarioDAO uDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO("usuario");
	private static List<Usuario> misUsuarios;//actua como un singleton todas las llamadas responden a este atributo
	static{misUsuarios=new ArrayList<Usuario>();}
	
	
	//POST
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUser,@HeaderParam("token") String token){
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;
		if (userEmail == null) {
			Message statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
			this.misUsuarios.add(nuevoUser);
			Message statusMensaje = new Message(Status.CREATED.getStatusCode(),"Usuario Añadido!!!");
			mResponse=Response.status(200).entity(statusMensaje).build();
		}
		return mResponse;

	}
	
	//GET y PUT {uid}:
		@GET
		@Path("/{uid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUsuario(int uid, @HeaderParam("token") String token){
			Response elUsuario = null;
			return elUsuario;
		}
		
		
		
		
		@Path("/{uid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateUsuario(@PathParam("uid") int uid,Usuario updateUser, @HeaderParam("token") String token) {
			Response updUsuario = null;
			return updUsuario;
		}

		/*@Path("/{uid}")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteUsuario(@PathParam("uid") int uid, @HeaderParam("token") String token) {
			Response delUsuario = null;
			return delUsuario;
		}*/


}
