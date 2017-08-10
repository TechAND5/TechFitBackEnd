
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
	public Usuario getUsuario(String email, String password)throws Exception {
		Usuario usuarioADevolver = null;
		
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement pstm = null;
			
			// ordenes sql
			if (password == null) {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.email=? LIMIT 1";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, email);
				System.out.println("***email..." + email);
			} else {
				String sql = "SELECT u.* FROM techfit.usuario u WHERE u.email=? AND u.password=? LIMIT 1";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, email);
				pstm.setString(2, password);
				System.out.println("***email+pass..." + email + password);
			}

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
	public Usuario getUsuarioByMail(String email) throws Exception {
		Usuario usuarioADevolver = null;
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement pstm = null;

			// ordenes sql
			String sql = "SELECT u.* FROM techfit.usuario u WHERE u.email=? LIMIT 1";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);

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
	public Usuario getUsuario(int uid)throws Exception {
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
	public boolean insertUsuario(Usuario nuevoUsuario)throws Exception {
		boolean exito = false;
		nuevoUsuario=new Usuario();
		Connection conn = this.datasource.getConnection();
		
		try {
			PreparedStatement pstm = null;
			
			// ordenes sql
			String sql = "INSERT INTO techfit.usuario (uId, email, password, username, nombre, apellido) VALUES (?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, nuevoUsuario.getUid());
			pstm.setString(2, nuevoUsuario.getEmail());
			pstm.setString(3, nuevoUsuario.getPassword());
			pstm.setString(4, nuevoUsuario.getUsername());
			pstm.setString(5, nuevoUsuario.getNombre());
			pstm.setString(6, nuevoUsuario.getApellido());

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
	public boolean updateUsuario(Usuario usuario)throws Exception {
		boolean exito = false;
		usuario=new Usuario();
		try {

			Connection conn = this.datasource.getConnection();
			PreparedStatement pstm = null;
			// ordenes sql
			String sql = "UPDATE techfit.usuario u SET u.email=?, u.password=?, u.username=?, u.nombre=?, u.apellido=? WHERE u.uId=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getPassword());
			pstm.setString(3, usuario.getUsername());
			pstm.setString(4, usuario.getNombre());
			pstm.setString(5, usuario.getApellido());
			pstm.setInt(6, usuario.getUid());

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
