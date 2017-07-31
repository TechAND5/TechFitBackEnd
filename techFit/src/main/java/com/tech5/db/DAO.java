package com.tech5.db;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class DAO {
		private static Logger logger = Logger.getLogger("DAO");

		public DataSource datasource;

		protected DAO() {
			try{
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env"); 

				this.datasource = (DataSource)envContext.lookup("jdbc/techFit");
			}catch (Exception e) {
				logger.info("Error al instanciar Datasource!!!!");
				e.printStackTrace();
			}

		}
	}
