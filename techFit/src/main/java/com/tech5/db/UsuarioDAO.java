package com.tech5.db;

import com.tech5.models.Usuario;

public abstract class UsuarioDAO extends DAO {

	public abstract Usuario getUsuario(String email, String password) throws Exception;
	public abstract Usuario getUsuarioByMail(String email) throws Exception;
	public abstract Usuario getUsuario(int uid)throws Exception;
	public abstract boolean insertUsuario(Usuario usuario)throws Exception;
	public abstract boolean updateUsuario(Usuario usuario)throws Exception;

}
