package com.tech5.db;

import com.tech5.models.Usuario;

public abstract class UsuarioDAO extends DAO {

	public abstract Usuario getUsuario(String nickname, String password);
	public abstract Usuario getUsuario(int uid);
	public abstract boolean insertUsuario(Usuario usuario);
	public abstract boolean updateUsuario(Usuario usuario);

}
