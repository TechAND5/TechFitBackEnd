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
import javax.ws.rs.core.Response.Status;

import com.tech5.models.Dia;

import com.tech5.models.Message;

import com.tech5.db.DAOFactory;
import com.tech5.db.DiaDAO;

@Path("/dias")
public class DiaResource extends JSONService {


	// GET{id} Mostrar un dia por id

	@GET
	@Path("/{did}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDia(@PathParam("did") int did, @HeaderParam("token") String token) {

		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMessage = null;
		if (userEmail == null) {
			statusMessage = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}
		try {
			Dia elDia = new Dia();
			DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
			elDia = diaDAO.getDia(did);
			if (elDia != null) {
				return Response.status(200).entity(elDia).build();
			} else {
				throw new RuntimeException("- El Dia (" + did + ") es desconocido.");
			}
		} catch (Exception e) {
			statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),
					"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Dia.\nLease API");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();

		}

		return mResponse;
	}

	// PUT{id} Modifica un dia de la lista por id
	@Path("/{did}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDia(@PathParam("did") int did, Dia updateDia, @HeaderParam("token") String token) {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMessage = null;
		if (userEmail == null) {
			statusMessage = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();

		} else {

			try {
				updateDia = new Dia();
				DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
				diaDAO.updateDia(updateDia);
				statusMessage = new Message(Status.CREATED.getStatusCode(), "Dia modificado!!!");
				mResponse = Response.status(200).entity(statusMessage).build();

			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
						.entity(e.getMessage() + "El dia no se ha modificado").build();
				return mResponse;
			}
		}
		return mResponse;
	}
}
