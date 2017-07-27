package com.tech5.db;

import java.util.logging.Logger;

public class HabitoDAOImplem {
private static Logger logger = Logger.getLogger("UsuarioBBDAOImpl");
	
	private static HabitoDAOImplem instance = null;

	public static HabitoDAOImplem getInstance() {
		if (instance == null) {
			instance = new HabitoDAOImplem();
		}
		return instance;
	}

}
