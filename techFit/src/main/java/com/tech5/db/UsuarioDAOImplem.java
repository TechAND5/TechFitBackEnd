
package com.tech5.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.tech5.models.Usuario;

public class UsuarioDAOImplem extends UsuarioDAO {

	private static Logger logger = Logger.getLogger("UsuarioDAOImplem");

	private static UsuarioDAOImplem instance = null;

	public static UsuarioDAOImplem getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImplem();
		}
		return instance;
	}

	@Override
	public Usuario getUsuario(String email, String password) {
		Usuario aUser = null;

		try {

			Connection conn = datasource.getConnection();
			PreparedStatement pstm = null;
			// ordenes sql
			if (password == null) {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.email=? LIMIT 1;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, email);
			} else {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.email=? AND password=? LIMIT 1;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, email);
				pstm.setString(2, password);
			}

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				aUser = new Usuario();
				aUser.setUid(rs.getInt("uId"));
				aUser.setEmail(rs.getString("email"));
				aUser.setPassword(rs.getString("password"));
				aUser.setUsername(rs.getString("username"));
				aUser.setNombre(rs.getString("nombre"));
				aUser.setApellido(rs.getString("apellido"));
				break;
			}

			pstm.close();
			conn.close();

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			aUser = null;
		}

		return aUser;
	}
	
	@Override
	public Usuario getUsuarioByMail(String email) throws Exception {
		Usuario aUser = null;
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement pstm = null;
			
			String sql = "SELECT u.* FROM techfit.usuario u WHERE email=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				aUser = new Usuario();
				aUser.setUid(rs.getInt("uId"));
				aUser.setEmail(rs.getString("email"));
				aUser.setPassword(rs.getString("password"));
				aUser.setUsername(rs.getString("username"));
				aUser.setNombre(rs.getString("nombre"));
				aUser.setApellido(rs.getString("apellido"));
				break;
			}

			pstm.close();
			conn.close();
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			aUser = null;
		}

		return aUser;
	}


	@Override
	public Usuario getUsuario(int uid) {
		Usuario usuarioADevolver = null;
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement pstm = null;

			// ordenes sql
			String sql = "SELECT u.* FROM techfit.usuario u WHERE u.uId=? LIMIT 1";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				usuarioADevolver = new Usuario(
						rs.getInt("uId"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("username"),
						rs.getString("nombre"), 
						rs.getString("apellido"));
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
	public boolean insertUsuario(Usuario nuevoUsuario) {
		boolean exito = false;

		try {

			Connection conn = this.datasource.getConnection();
			PreparedStatement pstm = null;
			// ordenes sql
			String sql = "INSERT INTO u.* FROM techfit.usuario u VALUES(NULL,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nuevoUsuario.getEmail());
			pstm.setString(2, nuevoUsuario.getPassword());
			pstm.setString(3, nuevoUsuario.getUsername());
			pstm.setString(4, nuevoUsuario.getNombre());
			pstm.setString(5, nuevoUsuario.getApellido());

			int rs = pstm.executeUpdate();

			pstm.close();
			conn.close();
			logger.info("Inserción de usuario exitosa");
			exito = rs > 0 ? true : false;

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD desde insertUsuario:" + e.getMessage());
			exito = false;
		}
		return exito;
	}

	@Override
	public boolean updateUsuario(Usuario usuario) {
		boolean exito = false;

		try {

			Connection conn = this.datasource.getConnection();
			PreparedStatement pstm = null;
			// ordenes sql
			String sql = "UPDATE techfit.usuario u SET u.email=?, u.password=?, u.username=?, u.nombre=?, u.apellido=? WHERE u.uId=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "email");
			pstm.setString(2, "password");
			pstm.setString(3, "username");
			pstm.setString(4, "nombre");
			pstm.setString(5, "apellido");
			pstm.setString(6, "uId");

			int rs = pstm.executeUpdate();

			pstm.close();
			conn.close();
			logger.info("Combio en usuario exitoso");
			exito = rs > 0 ? true : false;

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD desde updateUsuario:" + e.getMessage());
			exito = false;
		}
		return exito;
	}

}
