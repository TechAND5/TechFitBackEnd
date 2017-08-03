package com.tech5.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.tech5.models.Habito;
import com.tech5.models.Message;
import com.tech5.models.Usuario;
import com.tech5.db.DAOFactory;
import com.tech5.db.DiaDAO;
import com.tech5.db.HabitoDAO;
import com.tech5.db.UsuarioDAO;

@Path("/dias")
public class DiaResource extends JSONService {

	private static List<Dia> misDias;
	static {
		 DiaResource.misDias = new ArrayList<Dia>();
	}

	//GET mostrar lista de dias de un habito
	@GET
	@Path("/{hid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiaList(@PathParam("hid") int hid,@HeaderParam("token") String token)throws IOException{
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		Message statusMensaje = null;
		if (userEmail == null) {
			statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
	
		}
		try {
			// obtener el objeto Habito completo
			Habito habito= new Habito();
			HabitoDAO habDAO=(HabitoDAO) DAOFactory.getDAO("habito");
			habito= habDAO.getHabito(hid);
			// existe usuario uid??
			if (habito != null) {
			// Obtener la lista de los dias de ese habito de un usuario
				Dia elDia=new Dia();
				DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
				DiaResource.misDias = diaDAO.getDiaListxHabito(hid);
				return Response.status(200).entity(DiaResource.misDias).build();
			} else {
				throw new RuntimeException("- El habito (" + hid + ") es desconocido.");
			}
			
		} catch (Exception e) {
			mResponse = Response.status(499)
					.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto dia.\nLease API").build();
			return mResponse;
		}
		
		
	}
	
	
	//GET{id} Mostrar un dia por id
	
		@GET
		@Path("/{did}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getDia(@PathParam("did") int did, @HeaderParam("token") String token){
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMessage = null;
			if (userEmail == null) {
				statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),
						"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
				return mResponse;
			}
			try {
				Dia elDia=new Dia();
				DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
				elDia = diaDAO.getDia(did);	
				if(elDia!=null) {
					return Response.status(200).entity(elDia).build();
				}else {
					throw new RuntimeException("- El Dia (" + did + ") es desconocido.");
				}
			}catch(Exception e){
				statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),	"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Dia.\nLease API");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();

			}
			
			return mResponse;
		}
		
		
	//PUT{id} Modifica un dia de la lista por id
		@Path("/{did}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateDia(@PathParam("did") int did,Dia updateDia, @HeaderParam("token") String token) {
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMessage = null;
			if (userEmail == null) {
				statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
				return mResponse;
			}
			
			try {
				Dia elDia=new Dia();
				DiaDAO diaDAO = (DiaDAO) DAOFactory.getDAO("dia");
				elDia = diaDAO.getDia(did);	
				if(elDia!=null) {
					if(diaDAO.updateDia(elDia)) {
						statusMessage=new Message(Status.CREATED.getStatusCode(),"Dia modificado!!!");
						mResponse=Response.status(200).entity(statusMessage).build();
					}else {
						statusMessage=new Message(Status.FORBIDDEN.getStatusCode(),"la modificación del dia ha sido cancelada");
						mResponse=Response.status(400).entity(statusMessage).build();
					}
				}else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- El dia (" + did + ") es inexistente").build();				
					}

				
			}catch(Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto habito.\nLease API").build();
				return mResponse;
			}
			
			
			return mResponse;
		}
}
