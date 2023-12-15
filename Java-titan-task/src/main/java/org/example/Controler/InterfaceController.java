package org.example.Controler;

import java.sql.SQLException;

public interface InterfaceController {

    	 void add() throws SQLException;
	    
	     void update() throws SQLException;
	    
	     void delete();

	     void getAll();
		 Object saisie() throws SQLException;

}
