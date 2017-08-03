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

import javax.ws.rs.core.Response.Status;



import com.tech5.models.Dia;
import com.tech5.models.Habito;
import com.tech5.models.Message;
import com.tech5.models.Usuario;
import com.tech5.db.DAOFactory;
import com.tech5.db.DiaDAO;
import com.tech5.db.HabitoDAO;
import com.tech5.db.UsuarioDAO;

@Path("/habito")
public class HabitoResource extends JSONService{
	//public static HabitoDAO hDAO = (HabitoDAO) DAOFactory.getInstance().getDAO("habito");
	
	private static List<Habito> misHabitos;
	static {
		HabitoResource.misHabitos = new ArrayList<Habito>();
	}
	
	//GETS y POST 
		
		//Mostrar lista de habitos del usuario por token
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUserHabitList(@HeaderParam("token") String token)throws Exception{
			
			
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMensaje = null;
			if (userEmail == null) {
				statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}
			try {
				// obtener el objeto usuario completo
				Usuario user = new Usuario();
				UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getDAO("usuario");
				user = userDAO.getUsuario(userEmail, null);
				// existe usuario uid??
				if (user != null) {
				// Otener la lista de los habitos de ese usuario
					//Habito elHabito = new Habito();
					HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
					HabitoResource.misHabitos = habDAO.getHabitoxUser(user);
				} else {
					throw new RuntimeException("- El usuario (" + userEmail + ") es desconocido.");
				}
				mResponse = Response.status(200).entity(HabitoResource.misHabitos).build();
				return mResponse;
			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
				return mResponse;
			}
			
			
		}
		
		//Añadir un habito a la lista
		@Path("/")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response insertHabito(Habito nuevoHab, @HeaderParam("token") String token)throws Exception {
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse=null;
			
			try {
				// obtener el objeto usuario completo
				Usuario elUsuario = new Usuario();
				UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getDAO("usuario");
				elUsuario = userDAO.getUsuario(userEmail, null);
				if (elUsuario != null) {
					nuevoHab = new Habito();
					HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
					if (habDAO.insertHabito(nuevoHab)) {
						mResponse = Response.status(200).entity("El habito esta añadido").build();
					} else {
						mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("Operacion sin actualizar").build();
					}
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("El usuario no existe").build();
				}

			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Habito.\nLease API")
						.build();
				return mResponse;
			}
			/*if (userEmail == null) {
				Message statusMessage = new Message(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
				mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
			}else  {
				HabitoResource.misHabitos.add(nuevoHab);
				Message statusMessage = new Message(Status.CREATED.getStatusCode(),"Habito Añadido!!!");
				mResponse=Response.status(200).entity(statusMessage).build();
			}*/

			return  mResponse;

		}
		
		
		
	//GET{hid}, PUT{hid}, DELETE{hid}:
		
		//GET obtener un habito por su id
		@GET
		@Path("/{hid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getHabito(@PathParam("hid")int hid, @HeaderParam("token") String token)throws Exception{
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMensaje = null;
			if (userEmail == null) {
				statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}
			try {
				// Existe habito que se pide?
				Habito elHabito = new Habito();
				HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
				elHabito = habDAO.getHabito(hid);
			
				// existe habito
				if (elHabito != null) {
					return Response.status(200).entity(elHabito).build();
				} else {
					throw new RuntimeException("- El Habito (" + hid + ") es desconocido.");
				}
			} catch (Exception e) {
				statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Habito.\nLease API");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			}
			
			return mResponse;
		}
		
		
		
		
		//Modifica un habito de la lista
		@Path("/{hid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateHabito(@PathParam("hid") int hid,Habito elHabito, @HeaderParam("token") String token) throws Exception{
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMensaje = null;
			if (userEmail == null) {
				statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}
			try {
				// Existe habito que se pide?
				elHabito = new Habito();
				HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
				elHabito = habDAO.getHabito(hid);
				//si existe habito
				if (elHabito != null) {					
					if (habDAO.updateHabito(hid,elHabito)) {
						Message statusMessage = new Message(Status.CREATED.getStatusCode(),"Habito modificado!!!");
						mResponse=Response.status(200).entity(statusMessage).build();
					} else {
						mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- La modificacion del habito ha sido cancelada.").build();
					}
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- El habito (" + hid + ") es inexistente").build();
				}
			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto habito.\nLease API").build();
				return mResponse;
			}			
			
			return mResponse;
		}

		@Path("/{hid}")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteHabito(@PathParam("hid") int hid, @HeaderParam("token") String token)throws Exception {
			String userEmail = this.getUserEmailFromToken(token);
			Response mResponse = null;
			Message statusMensaje = null;
			if (userEmail == null) {
				statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}
			try {
				
				Habito elHabito = new Habito();
				HabitoDAO habDAO = (HabitoDAO) DAOFactory.getDAO("habito");
				elHabito = habDAO.getHabito(hid);
				if ((habDAO.delHabito(hid))) {
					statusMensaje = new Message(Status.FORBIDDEN.getStatusCode(), "Habito borrado");
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
					return mResponse;
				}			
			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto habito.\nLease API").build();
				return mResponse;
			}
			mResponse = Response.status(200).entity("\n- Se ha borrado un habito de la lista.").build();	
			return mResponse;
			
		}
		@GET
		@Path("/{hid}/dias")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getDiaList(@PathParam("hid") int hid,@HeaderParam("token") String token)throws Exception{
			
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
					elDia = (Dia) diaDAO.getDiaListxHabito(hid);
					return Response.status(200).entity(elDia).build();
				} else {
					throw new RuntimeException("- El habito (" + hid + ") es desconocido.");
				}
				
			} catch (Exception e) {
				mResponse = Response.status(499)
						.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto dia.\nLease API").build();
				return mResponse;
			}
			
			
		}
		
}
