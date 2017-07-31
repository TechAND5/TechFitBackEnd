package com.tech5.db;

import java.util.Date;

import com.tech5.models.Usuario;

public abstract class DiaDAO extends DAO {

	public abstract DiaDAO getDia(int dId, String diaSemana, Date fechaActual, int estado, int habito, int usuario);
	public abstract DiaDAO getDia(int uid);
	public abstract boolean insertDia(DiaDAO dia);
	public abstract boolean updateDia(DiaDAO dia);

}
