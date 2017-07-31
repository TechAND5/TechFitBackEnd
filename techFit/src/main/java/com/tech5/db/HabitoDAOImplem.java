package com.tech5.db;


import java.util.logging.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.tech5.models.Habito;

public class HabitoDAOImplem extends HabitoDAO {
private static Logger logger = Logger.getLogger("UsuarioBBDAOImpl");
	
	private static HabitoDAOImplem instance = null;

	public static HabitoDAOImplem getInstance() {
		if (instance == null) {
			instance = new HabitoDAOImplem();
		}
		return instance;
	}
	
	//Obtener dlista de habitos del usuario:
	public List<Habito> getUserHabito(int uid) {
		List<Habito> listADevolver = new ArrayList<Habito>();
		
		listADevolver.add(new Habito(1, "correr", "correr 10km al dia", 30, 30, null, uid));
		listADevolver.add(new Habito(2, "verdura", "comer 5 verduras al dia", 50, 30, null, uid));
		listADevolver.add(new Habito(3, "agua", "beber 2L de agua", 40, 40, null, uid));
		
//		try {
//			Connection conn = this.datasource.getConnection();
//
//			String sql = "WHERE h.Usuario_usuario=?";
//			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, uid);
//
//			ResultSet rs = pstm.executeQuery();
//
//			while (rs.next()) {
//				listADevolver.add(new Habito(rs.getInt("hId"),
//						rs.getString("titulo"),
//						rs.getString("descripcion"),
//						rs.setDate("fechaI"),
//						rs.getInt("progreso"), 
//						rs.getInt("estado"), 
//						null, 
//						rs.getInt("uid")));
//			}
//
//			pstm.close();
//
//			conn.close();
//
//			logger.info("Conexión exitosa:" + listADevolver);
//
//		} catch (Exception e) {
//			logger.severe("Error en la conexión de BBDD:" + e);
//			listADevolver = null;
//		}

		return listADevolver;
	}

}
