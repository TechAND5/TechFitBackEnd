package com.tech5.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tech5.models.Dia;
import com.tech5.db.DAOFactory;
import com.tech5.db.DiaDAO;


public class DiaResource {

	public static DiaDAO dDAO = (DiaDAO) DAOFactory.getInstance().getDAO("dia");
	
	//GETS:
		@GET
		@Path("/{did}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getDia(int did, @HeaderParam("token") String token){
			Response elDia = null;
			return elDia;
		}
		
		@GET
		@Path("/{hid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getDiaList(int hid, @HeaderParam("token") String token){
			Response elDia = null;
			return elDia;
		}
		
		//POST, PUT, DELETE:

		@Path("/{did}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateDia(@PathParam("did") int did,Dia updateDia, @HeaderParam("token") String token) {
			Response updDia = null;
			return updDia;
		}
}
