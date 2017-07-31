package com.tech5.db;

import java.util.logging.Logger;


public class DiaDAOImplem extends DiaDAO{

private static Logger logger = Logger.getLogger("DiaDAOImplem");
	
	private static DiaDAOImplem instance = null;

	public static DiaDAOImplem getInstance() {
		if (instance == null) {
			instance = new DiaDAOImplem();
		}
		return instance;
	}
	

}
