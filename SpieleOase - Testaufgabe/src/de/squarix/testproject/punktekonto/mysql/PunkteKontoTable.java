package de.squarix.testproject.punktekonto.mysql;

import de.squarix.testproject.punktekonto.PunkteKonto;

public class PunkteKontoTable {
	
	/*
	Erstellt, falls noch nicht vorhanden, einen Table für die Punkte.
	*/
	
	
	public static void createKontoTable() {
		PunkteKonto.mysql.update("CREATE TABLE IF NOT EXISTS Punkte(UUID varchar(64), punkte int);");
	}

}
