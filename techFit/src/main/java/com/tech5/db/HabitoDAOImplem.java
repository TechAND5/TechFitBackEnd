package com.tech5.db;

import java.sql.Connection;
import java.sql.ResultSet;
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

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "SQL";
			java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Habito(rs.getInt("hid"),
						rs.getString("titulo"),
						rs.getString("descripcion"), 
						rs.getInt("progreso"), 
						rs.getInt("estado"), 
						null, 
						rs.getInt("uid")));
			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa:" + listADevolver);

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

}
