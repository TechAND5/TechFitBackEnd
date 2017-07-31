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

import com.tech5.models.Habito;
import com.tech5.db.DAOFactory;
import com.tech5.db.HabitoDAO;

@Path("/habito")
public class HabitoResource extends JSONService{
	public static HabitoDAO hDAO = (HabitoDAO) DAOFactory.getInstance().getDAO("habito");
	
	//GETS:
		@GET
		@Path("/{hid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getHabito(int hid, @HeaderParam("token") String token){
			Response elHabito = null;
			return elHabito;
		}
		
		@GET
		@Path("/{uid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getHabitoList(int uid, @HeaderParam("token") String token){
			Response elHabito = null;
			return elHabito;
		}
		
		//POST, PUT, DELETE:
		@Path("/")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response insertHabito(Habito nuevoHab, @HeaderParam("token") String token) {
			Response newHabito = null;
			return newHabito;
		}
		
		@Path("/{hid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateHabito(@PathParam("hid") int hid,Habito updateHabito, @HeaderParam("token") String token) {
			Response updHabito = null;
			return updHabito;
		}

		@Path("/{hid}")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteHabito(@PathParam("hid") int hid, @HeaderParam("token") String token) {
			Response delHabito = null;
			return delHabito;
		}
}
