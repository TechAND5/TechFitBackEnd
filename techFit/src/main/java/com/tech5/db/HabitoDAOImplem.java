package com.tech5.db;


import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;


import com.tech5.models.Habito;
import com.tech5.models.Usuario;

public class HabitoDAOImplem extends HabitoDAO {
	private static Logger logger = Logger.getLogger("HabitoDAOImplem");
	
	private static HabitoDAOImplem instance = null;

	public static HabitoDAOImplem getInstance() {
		if (instance == null) {
			instance = new HabitoDAOImplem();
		}
		return instance;
	}
	
	
	
	//get{id}-obtener un habito por hid
	@Override
	public Habito getHabito(int hid) {
		Habito habitoADevolver = null;
		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT h.* FROM techfit.habito h WHERE h.hId=? LIMIT 1 "; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, hid);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				habitoADevolver = new Habito();
				habitoADevolver.setHid(rs.getInt("hId")); 
				habitoADevolver.setTitulo(rs.getString("titulo"));
				habitoADevolver.setDescripcion(rs.getString("descripcion"));
				habitoADevolver.setFechaI(rs.getDate("fechaI"));
				habitoADevolver.setFechaF(rs.getDate("fechaF"));
				habitoADevolver.setProgreso(rs.getInt("progreso"));
				habitoADevolver.setEstado(rs.getInt("estado"));
				habitoADevolver.setUsuario(rs.getInt("usuario"));
				break;	
						
			}

			pstm.close();
			conn.close();
			
			logger.info("Conexión exitosa getHabito");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			habitoADevolver = null;
		}

		return habitoADevolver;

	}
	
	//GET -Obtener lista de habitos x id usuario:
	@Override
	public List<Habito> getHabitoxUser(Usuario user) {
		List<Habito> habitListADevolver = new ArrayList<Habito>();
		
	try {
		Connection conn = this.datasource.getConnection();
		String sql = "SELECT h.* FROM techfit.habito h LEFT JOIN techfit.usuario u ON h.usuario=u.uId WHERE u.uId=?";
		java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, user.getUid());
			ResultSet rs = pstm.executeQuery();

			while  (rs.next()) {

				habitListADevolver.add( new Habito(
						rs.getInt("hId"), 
						rs.getString("titulo"),
						rs.getString("descripcion"),
						rs.getDate("fechaI"),
						rs.getDate("fechaF"),
						rs.getInt("progreso"),
						rs.getInt("estado"),
						rs.getInt("usuario")
						));
			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa: getUserHabito");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			habitListADevolver= null;
	}

		return habitListADevolver;
	}

	
	//POST insertar nuevo habito a la lista de habitos de un usuario
	@Override
	public boolean insertHabito(Habito nuevoHab) throws Exception {
		boolean estaInsertado = false;
		PreparedStatement pstm = null;
		Connection conn = null;
		
		try {
			conn = this.datasource.getConnection();
			String sql = "INSERT INTO techfit.habito('hId', 'titulo', 'descripcion', 'fechaI', 'fechaF', 'progreso', 'estado', 'usuario') VALUES (?, ?, ?, ?, ?, ?, ?, ? );";
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1,nuevoHab.getHid()); 
			pstm.setString(2,nuevoHab.getTitulo());
			pstm.setString(3,nuevoHab.getDescripcion());
			pstm.setDate(4,(Date)nuevoHab.getFechaI());
			pstm.setDate(5,(Date)nuevoHab.getFechaF());
			pstm.setInt(6,nuevoHab.getProgreso());
			pstm.setInt(7,nuevoHab.getEstado());
			pstm.setInt(8,nuevoHab.getUsuario());

			
			// execute the preparedstatement
			int rows = pstm.executeUpdate();
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Nigun Objeto insertado \"{0}\"", sql));
			} else {
				estaInsertado = true;
				logger.info("Conexión exitosa insertHabito");
			}
			pstm.close();
			conn.close();
			logger.info("Inserción exitosa");
			estaInsertado = rows > 0 ? true : false;
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
		} 
		return estaInsertado;

	}

	
	//PUT{id} actualizar datos de un habito
	@Override
	public boolean updateHabito(int hid,Habito elHabito) throws Exception {
		boolean estaActualizado = false;
		PreparedStatement pstm = null;
		Connection conn = null;

		conn = this.datasource.getConnection();
		String sql ="UPDATE techfit.habito SET titulo=?, descripcion=?, fechaI=?, fechaF=?, progreso=?, estado=? WHERE hId=?";
		
		pstm = conn.prepareStatement(sql);
		pstm.setString(1,elHabito.getTitulo());
		pstm.setString(2,elHabito.getDescripcion());
		pstm.setDate(3,(Date) elHabito.getFechaI());
		pstm.setDate(4,(Date) elHabito.getFechaF());
		pstm.setInt(5,elHabito.getProgreso());
		pstm.setInt(6,elHabito.getEstado());
		
		
		
		
		pstm.executeUpdate();
		
		try {
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Nigun Objeto esta actualizado \"{0}\"", sql));
			} else {
				estaActualizado = true;
				logger.info("Conexión exitosa updatehabito");
			}
			pstm.close();
			conn.close();
		} catch (

		Exception e) {
			logger.severe("Error en la conexiï¿½n de BBDD:" + e);
		}

		return estaActualizado;
	}
	
	
	//DELETE{id} borrar un habito
	@Override
	public boolean delHabito(int hid) throws Exception {
		boolean estaBorrado = false;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.datasource.getConnection();
			String sql = "DELETE h.* FROM techfit.habito WHERE hId=?;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, hid);

			pstm.executeUpdate();
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Objeto sin borrar", sql));
			} else {
				estaBorrado = true;
				logger.info("conexion exitosa borrar proyecto");
			}

		} catch (Exception e) {
			logger.severe("Error en la conexion" + e);
		} finally {
			pstm.close();

			conn.close();
		};		
		return estaBorrado;
	}

	
	

}
