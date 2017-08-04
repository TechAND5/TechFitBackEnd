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

import com.tech5.models.Message;
import com.tech5.models.Usuario;

import com.sun.jersey.api.client.ClientResponse.Status;


@Path("/usuarios")
public class UsuarioResource extends JSONService {
	//public static UsuarioDAO uDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO("usuario");
	private static List<Usuario> misUsuarios;
	//actua como un singleton todas las llamadas responden a este atributo
	
	
	static{
		misUsuarios=new ArrayList<Usuario>();
		misUsuarios.add(new Usuario(1, "diana@es.com", "dianacom", "diana","diana","Ramon"));
		misUsuarios.add(new Usuario(2, "Juana@es.com", "Juanacom", "juana","juana","juanason"));
	
	
	}
	
	
	//POST
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUser,@HeaderParam("token") String token){
		String userEmail = "diana@es.com";//this.getUserEmailFromToken(token);
		Response mResponse=null;
		if (userEmail == null) {
			Message statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
			UsuarioResource.misUsuarios.add(nuevoUser);
			Message statusMensaje = new Message(Status.CREATED.getStatusCode(),"Usuario Añadido!!!");
			mResponse=Response.status(200).entity(statusMensaje).build();
		}
		return mResponse;

	}
	
	//GET y PUT {uid}:
		@GET
		@Path("/{uid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUsuario(@PathParam("uid")int uid, @HeaderParam("token") String token){
			
			String userEmail = "diana@es.com";//this.getUserEmailFromToken(token);
			Response mResponse=null;
			
			if (userEmail == null) {
				Message statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
				mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			}else  {
				Usuario unUsuario = new Usuario();
				for (Usuario user : misUsuarios) {
					if(user.getUid()==uid){
						unUsuario=user;
						break;
					}
				}
			mResponse=Response.status(200).entity(unUsuario).build();
			}
			return mResponse;
		}
		
		
		
		
		@Path("/{uid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateUsuario(@PathParam("uid") int uid,Usuario updateUser, @HeaderParam("token") String token) {
			
			String userEmail = "diana@es.com";//this.getUserEmailFromToken(token);
			Response mResponse=null;
			
			if (userEmail == null) {
				Message statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
				mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
			}else  {
			
				for (Usuario user : misUsuarios) {
					if(user.getUid()==uid){
						misUsuarios.remove(user);
						misUsuarios.add(updateUser);
						break;
					}
				}
				Message statusMessage = new Message(Status.CREATED.getStatusCode(),"Usuario modificado!!!");
				mResponse=Response.status(200).entity(statusMessage).build();
			}
			
			return mResponse;
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
