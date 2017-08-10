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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.Response.Status;

import com.tech5.models.Dia;
import com.tech5.models.Habito;
import com.tech5.models.Message;
import com.tech5.models.Usuario;

import com.tech5.db.DAOFactory;
import com.tech5.db.DiaDAO;
import com.tech5.db.HabitoDAO;
import com.tech5.db.UsuarioDAO;

@Path("/habitos")
public class HabitoResource extends JSONService {
	private static Logger logger = Logger.getLogger("HabitoResource");

	private static List<Habito> misHabitos = new ArrayList<Habito>();



	// POST Añadir un habito a la lista

	@POST
	@Path("/")

	@Produces(MediaType.APPLICATION_JSON)
	public Response insertHabito(Habito nuevoHab, @HeaderParam("token") String token) throws Exception {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}
		try {

			nuevoHab = new Habito();
			HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
			habDAO.insertHabito(nuevoHab);

			mResponse = Response.status(200).entity("El habito esta añadido").build();
		} catch (Exception e) {
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
					.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Habito.\nLease API").build();

		}

		return mResponse;

	}

	// GET{hid} obtener un habito por su id
	@GET
	@Path("/{hid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHabito(@PathParam("hid") int hid, @HeaderParam("token") String token) {

		String userEmail = this.getUserEmailFromToken(token);
		System.out.println("***Email..." + userEmail);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();

		}
		try {
			// Existe habito que se pide?
			Habito elHabito = new Habito();
			HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
			elHabito = habDAO.getHabito(hid);
			System.out.println("***user..." + elHabito);

			// existe habito
			if (elHabito != null) {
				
				mResponse = Response.status(200).entity(elHabito).build();
				System.out.println("habito obtenido!!!!");
			} else {
				throw new RuntimeException("- El Habito (" + hid + ") es desconocido.");
			}
		} catch (Exception e) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),
					"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Habito.\nLease API");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}

		return mResponse;
	}

	// PUT{hid} Modifica un habito de la lista
	@Path("/{hid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateHabito(@PathParam("hid") int hid, Habito elHabito, @HeaderParam("token") String token)
			throws Exception {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();

		} else {
			try {
				elHabito = new Habito();
				HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
				habDAO.updateHabito(hid, elHabito);
				Message statusMessage = new Message(Status.CREATED.getStatusCode(), "Habito modificado!!!");
				mResponse = Response.status(200).entity(statusMessage).build();

			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
						.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto habito.\nLease API")
						.build();
				return mResponse;
			}
		}
		return mResponse;
	}

	// DELETE{hid} borrar habito por id
	@Path("/{hid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteHabito(@PathParam("hid") int hid, @HeaderParam("token") String token) throws Exception {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();

		} else {
			try {
				HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
				if (habDAO != null) {
					habDAO.delHabito(hid);

					statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Habito borrado");
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				} else {
					statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),
							"el habito ya ha sido borrado o no existe");
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				}
			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode())
						.entity(e.getMessage() + "el habito no se ha borrado").build();
				return mResponse;
			}
			mResponse = Response.status(200).entity("\n- el habito" + hid + "Se ha borrado un habito de la lista.")
					.build();
		}
		return mResponse;

	}

	// Dias
	// GET{hid}/dias lista de dias por habito
	@GET
	@Path("/{hid}/dias")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getDiaList(@PathParam("hid") int hid, @HeaderParam("token") String token) throws Exception {

		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}
		try {
			// obtener el objeto Habito completo
			Habito habito = new Habito();
			HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
			habito = habDAO.getHabito(hid);
			//List<Dia> elDia = habito.getListaDias();
			// existe usuario uid??
			if (habito != null) {
				List<Dia> elDia = new ArrayList<Dia>();
				DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
				elDia =diaDAO.getDiaListxHabito(habito);
				
				//elDia .add(new Dia(1));
				//elDia .add(new Dia(2));
				//elDia .add(new Dia(3));
				
				
				
				statusMensaje = new Message(Status.CREATED.getStatusCode(),"lista de dias obtenida!!!");
				mResponse = Response.status(200).entity(elDia).build();
			} 
			
		} catch (Exception e) {
			mResponse = Response.status(499)
					.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto dia.\nLease API").build();

		}

		return mResponse;
	}

}
