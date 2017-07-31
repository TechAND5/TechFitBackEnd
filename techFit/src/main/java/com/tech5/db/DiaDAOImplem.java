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


public class DiaDAOImplem extends DiaDAO{

private static Logger logger = Logger.getLogger("DiaDAOImplem");
	
	private static DiaDAOImplem instance = null;

	public static DiaDAOImplem getInstance() {
		if (instance == null) {
			instance = new DiaDAOImplem();
		}
		return instance;
	}
		
		@Override
		public List<Dia> getDiaList(){
			List<Dia> listADevolver =new ArrayList<Dia>();

			try {

				Connection conn = datasource.getConnection();
				PreparedStatement pstm =null;
				// ordenes sql
				//if (password == null) {
					String sql = "SELECT d.* FROM techfit.dia; d WHERE u.nickname=? LIMIT 1;";
					pstm = conn.prepareStatement(sql);
					int dia;
					pstm.setInt(1, dia);
				}
			else {
					String sql = "SELECT u.* FROM techfit.dia d WHERE d.dId=? AND password=? LIMIT 1;";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, did);
					// pstm.setString(2, password);				
				}
		

				ResultSet rs = pstm.executeQuery();
			
				if (rs.next()) {
					usuarioADevolver = new Usuario(
							rs.getInt("dId"),
							rs.getString("diaSemana"),
							//(password == null)?"":rs.getString("password"),
							rs.getInt("fechaActual"),
							rs.getInt("estado"),
							rs.getInt("habito"),
							rs.getInt("usuario"),
							);
					
				}

				pstm.close();
				conn.close();

			} catch (Exception e) 			{
				logger.severe("Error en la conexión de BBDD:" + e);
				Object diaADevolver = null;
			
			
			return diaADevolver;
		
			
	/*	@Override
		public did getD(int did) {
			Dia diaADevolver = null;
			try {
				Connection conn = datasource.getConnection();
				PreparedStatement pstm =null;
				
				// ordenes sql
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.uId=? LIMIT 1";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, did);

				ResultSet rs = pstm.executeQuery();

				

				if (rs.next()) {
					diaADevolver = new Dia(
							rs.getInt("dId"),
							rs.getString("diaSemana"),
							rs.getDate("fechaActual"),
							rs.getInt("estado"),
							rs.getInt("habito"),
							rs.getInt("usuario")
							
							);
				}

				pstm.close();
				conn.close();

			} catch (Exception e) {
				logger.severe("Error en la conexión de BBDD:" + e);
				diaADevolver = null;
			}
			return diaADevolver;
		}
		*/

	
		



		@Override
		public List<Dia> getDiaList(Habito habit) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Dia getDia(int did) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean updateDia(Dia dia) {
			// TODO Auto-generated method stub
			return false;
		}