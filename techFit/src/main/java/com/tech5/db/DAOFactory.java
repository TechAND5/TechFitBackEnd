package com.tech5.db;

public class DAOFactory {
	private static DAOFactory instance = null;
	
	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public Object getDAO(String daoType) {
		switch (daoType) {
		case "usuario":
			return (Object) UsuarioDAOImplem.getInstance();
		case "proyecto":
			return (Object) HabitoDAOImplem.getInstance();
		case "tarea":
			return (Object) DiaDAOImplem.getInstance();
		default:
			return null;
		}
	}

}