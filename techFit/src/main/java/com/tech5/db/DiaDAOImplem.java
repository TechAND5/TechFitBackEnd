package com.tech5.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.tech5.models.Dia;
import com.tech5.models.Habito;


public class DiaDAOImplem extends DiaDAO {

	private static Logger logger = Logger.getLogger("DiaDAOImplem");

	private static DiaDAOImplem instance = null;

	public static DiaDAOImplem getInstance() {
		if (instance == null) {
			instance = new DiaDAOImplem();
		}
		return instance;
	}

	@Override
	public List<Dia> getDiaListxHabito(Habito hab) {
		List<Dia> dialistADevolver = new ArrayList<Dia>();
		try {

			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT d.* FROM techfit.dia d LEFT JOIN techfit.habito h ON d.habito=h.hId WHERE h.hId=?";
			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, hab.getHid());
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				dialistADevolver.add(new Dia(
								rs.getInt("did"), 
								rs.getString("diaSemana"), 
								rs.getDate("fechaActual"), 
								rs.getInt("estado"), 
								rs.getInt("habito")));
			}
			pstm.close();

			conn.close();
			logger.info("Conexión exitosa: Lista Dias de un Habito");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD en getDiaList:" + e);
			dialistADevolver = null;
		}

		return dialistADevolver;
	}

	@Override
	public Dia getDia(int unDid) throws Exception {
		Dia diaADevolver = null;
		try {

			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT d.* FROM techfit.dia d  WHERE d.dId=?";
			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, unDid);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				diaADevolver = new Dia(
						rs.getInt("did"), 
						rs.getString("diaSemana"), 
						rs.getDate("fechaActual"), 
						rs.getInt("estado"),
						rs.getInt("habito"));
			}
			pstm.close();

			conn.close();
			logger.info("Conexión exitosa: Dia pedido");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD en getDia:" + e);
			diaADevolver = null;
		}

		return diaADevolver;
	}

	@Override
	public boolean updateDia(Dia updateDia) throws Exception {
		boolean diaActualizado = false;
		
		PreparedStatement pstm = null;
		Connection conn = this.datasource.getConnection();

		// ordenes sql
		String sql = "UPDATE techfit.dia d SET  d.fechaActual=?, d.estado=? WHERE d.dId=?";
		
		pstm = conn.prepareStatement(sql);

		
		pstm.setDate(1, (Date)updateDia.getFechaActual());  
		pstm.setInt(2, updateDia.getEstado());
		pstm.setInt(3, updateDia.getDid());
		

		pstm.executeUpdate();

		try {
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Nigun ObjetoDia esta actualizado \"{0}\"", sql));
			} else {
				diaActualizado = true;
				logger.info("Conexión exitosa updateDia");
			}
			pstm.close();
			conn.close();
		} catch (

		Exception e) {
			logger.severe("Error en la conexiï¿½n de BBDD en updateDia:" + e);
		}

		return diaActualizado;

	}

}