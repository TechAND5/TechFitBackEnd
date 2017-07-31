package com.tech5.db;


import java.util.List;

import com.tech5.models.Habito;

public abstract class HabitoDAO extends DAO{
	public abstract List<Habito> getUserHabito(int uid); 


}
