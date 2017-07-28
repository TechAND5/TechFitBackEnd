package com.tech5.db;

import java.util.logging.Logger;


public class UsuarioDAOImplem extends UsuarioDAO{
private static Logger logger = Logger.getLogger("UsuarioBBDAOImpl");
	
	private static UsuarioDAOImplem instance = null;

	public static UsuarioDAOImplem getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImplem();
		}
		return instance;
	}

}
