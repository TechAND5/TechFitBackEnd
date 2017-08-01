
package com.tech5.db;


import java.sql.SQLException;
import java.util.List;


import com.tech5.models.Habito;
import com.tech5.models.Usuario;

public abstract class HabitoDAO extends DAO{
	public abstract Habito getHabito(int hid);
	public abstract List<Habito> getHabitoxUser(Usuario user); 
	/*public abstract List<Habito> getHabitoList();*/
	public abstract boolean insertHabito(Habito newHabito) throws Exception;
	public abstract boolean updateHabito(int hid,Habito habito) throws  Exception;
	public abstract boolean delHabito(int hid) throws Exception;

}

