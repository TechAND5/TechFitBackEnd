
package com.tech5.db;


import java.util.List;


import com.tech5.models.Habito;
import com.tech5.models.Usuario;

public abstract class HabitoDAO extends DAO{
	public abstract List<Habito> getUserHabito(Usuario user); 
	public abstract List<Habito> getHabitoList();
	public abstract boolean delHabito(int hid);
	public abstract boolean insertHabito(Habito habito) throws Exception;
	public abstract boolean updateProyecto(Habito habito);

}

