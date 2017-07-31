package com.tech5.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.tech5.models.Usuario;



public class UsuarioDAOImplem extends UsuarioDAO{

private static Logger logger = Logger.getLogger("UsuarioDAOImplem");
	
	private static UsuarioDAOImplem instance = null;

	public static UsuarioDAOImplem getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImplem();
		}
		return instance;
	}

	@Override
	public Usuario getUsuario(String nickname, String password) {
		Usuario usuarioADevolver = null;

		try {

			Connection conn = datasource.getConnection();
			PreparedStatement pstm =null;
			// ordenes sql
			if (password == null) {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.nickname=? LIMIT 1;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, nickname);
			} else {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.nickname=? AND password=? LIMIT 1;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, nickname);
				pstm.setString(2, password);				
			}

			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				usuarioADevolver = new Usuario(
						rs.getInt("uId"),
						rs.getString("email"),
						(password == null)?"":rs.getString("password"),
						rs.getString("nickname"),
						rs.getString("nombre"),
						rs.getString("apellido")
						);
			}

			pstm.close();
			conn.close();

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}

		return usuarioADevolver;
	}

	@Override
	public Usuario getUsuario(int uid) {
		Usuario usuarioADevolver = null;
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement pstm =null;
			
			// ordenes sql
			String sql = "SELECT u.* FROM techfit.usuario u WHERE u.uId=? LIMIT 1";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			

			if (rs.next()) {
				usuarioADevolver = new Usuario(
						rs.getInt("uId"),
						rs.getString("email"),
						rs.getString("nickname"),
						rs.getString("nombre"),
						rs.getString("apellido")
						);
			}

			pstm.close();
			conn.close();

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}
		return usuarioADevolver;
	}

	@Override
	public boolean insertUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
