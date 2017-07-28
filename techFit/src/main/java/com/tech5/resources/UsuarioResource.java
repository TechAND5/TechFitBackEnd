package com.tech5.resources;

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

import com.tech5.models.Usuario;
import com.tech5.db.DAOFactory;
import com.tech5.db.UsuarioDAO;

@Path("/usuario")
public class UsuarioResource extends JSONService {
	public static UsuarioDAO uDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO("usuario");
	
	//GETS:
		@GET
		@Path("/{uid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUsuario(int uid, @HeaderParam("token") String token){
			Response elUsuario = null;
			return elUsuario;
		}
		
		//POST, PUT, DELETE:
		@Path("/")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response insertUsuario(Usuario nuevoUser, @HeaderParam("token") String token) {
			Response newUsuario = null;
			return newUsuario;
		}
		
		@Path("/{uid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateUsuario(@PathParam("uid") int uid,Usuario updateUser, @HeaderParam("token") String token) {
			Response updUsuario = null;
			return updUsuario;
		}

		@Path("/{uid}")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteUsuario(@PathParam("uid") int uid, @HeaderParam("token") String token) {
			Response delUsuario = null;
			return delUsuario;
		}


}
