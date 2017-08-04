package com.tech5.db;


import java.util.List;

import com.tech5.models.Dia;



public abstract class DiaDAO extends DAO {

	public abstract List<Dia> getDiaListxHabito(int hid);//get lista
	public abstract Dia getDia(int did) throws Exception;//get dia por id
	public abstract boolean updateDia(Dia dia) throws Exception; //put - actualizar dia

}
