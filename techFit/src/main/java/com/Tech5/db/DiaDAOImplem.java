package com.tech5.db;

import java.util.logging.Logger;

public class DiaDAOImplem {
private static Logger logger = Logger.getLogger("UsuarioBBDAOImpl");
	
	private static DiaDAOImplem instance = null;

	public static DiaDAOImplem getInstance() {
		if (instance == null) {
			instance = new DiaDAOImplem();
		}
		return instance;
	}
	

}
