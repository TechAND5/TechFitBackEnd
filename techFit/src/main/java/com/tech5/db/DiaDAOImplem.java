package com.tech5.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.tech5.models.Dia;
import com.tech5.models.Habito;
import com.tech5.models.Usuario;

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
		public List<Dia> getDiaListxHabito(int unHid){
			List<Dia> dialistADevolver =new ArrayList<Dia>();
			try {
			
				Connection conn =  this.datasource.getConnection();
				// ordenes sql
				String sql = "SELECT d.* FROM techfit.dia d LEFT JOIN techfit.habito h ON d.habito=h.hId WHERE h.hId=?";
				java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, unHid);
					ResultSet rs = pstm.executeQuery();
				
					while  (rs.next()) {

						dialistADevolver.add( new Dia(
								rs.getInt("did"), 
								rs.getDate("fechaActual"),
								rs.getInt("estado"),
								rs.getInt("hid")
								));
					}
					pstm.close();

					conn.close();
					logger.info("Conexión exitosa: Lista Dias de Habito");	
				
			} catch (Exception e) {
				logger.severe("Error en la conexión de BBDD en getDiaList:" + e);
				dialistADevolver=null;
			}
			
			return dialistADevolver;
		}
			

	@Override
	public Dia getDia(int unDid) {
		Dia diaADevolver =null;
		try {
		
			Connection conn =  this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT d.* FROM techfit.dia d  WHERE d.dId=?";
			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, unDid);
				ResultSet rs = pstm.executeQuery();
			
				if  (rs.next()) {

					diaADevolver= new Dia(
							rs.getInt("did"), 
							rs.getDate("fechaActual"),
							rs.getInt("estado"),
							rs.getInt("hid")
							);
				}
				pstm.close();

				conn.close();
				logger.info("Conexión exitosa: Dia pedido");	
			
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD en getDia:" + e);
			diaADevolver=null;
		}
		
		return diaADevolver;
	}

	@Override
	public boolean updateDia(Dia unDia) {
		Dia diaADevolver =null;
		try {
		
			Connection conn =  this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT d.* FROM techfit.dia d  WHERE d.dId=?";
			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, unDid);
				ResultSet rs = pstm.executeQuery();
			
				if  (rs.next()) {

					diaADevolver= new Dia(
							rs.getInt("did"), 
							rs.getDate("fechaActual"),
							rs.getInt("estado"),
							rs.getInt("hid")
							);
				}
				pstm.close();

				conn.close();
				logger.info("Conexión exitosa: Dia pedido");	
			
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD en getDia:" + e);
			diaADevolver=null;
		}
		
		return diaADevolver;
		
		
		
		
		return false;
	}
}