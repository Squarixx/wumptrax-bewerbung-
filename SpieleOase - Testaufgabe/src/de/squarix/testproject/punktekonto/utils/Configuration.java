package de.squarix.testproject.punktekonto.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import de.squarix.testproject.punktekonto.PunkteKonto;
import de.squarix.testproject.punktekonto.mysql.DatabaseConnection;

public class Configuration {

  /*
  Erstellt falls noch nicht vorhanden eine neue Config mit Musterdaten.
  Anderenfalls werden die Daten aus der Config ausgelesen und den jeweiligen
  Werten zugetragen.
   */
	
public static void getMySQLDaten() {
	
 File file = new File("plugins//TestProject//database.yml");
 File ordner = new File("plugins//TestProject");
 if (!file.exists()) {
   try {
	  file.createNewFile();
	   }
	   catch (IOException e)
	   {
	   e.printStackTrace();
	  }
	 }
	  if (!ordner.exists()) {
	    ordner.mkdirs();
	   }
	   YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	    
	   cfg.options().header("MySQL Daten");
	    
	   cfg.addDefault("host", "localhost");
	   cfg.addDefault("database", "database");
	   cfg.addDefault("user", "Squarix");
	   cfg.addDefault("password", "passwort");
	    
	   PunkteKonto.mysql.HOST = cfg.getString("host");
	   PunkteKonto.mysql.DATABASE = cfg.getString("database");
	   PunkteKonto.mysql.USER = cfg.getString("user");
	   PunkteKonto.mysql.PASSWORD = cfg.getString("password");
	    
	   cfg.options().copyDefaults(true);
	   try {
	    cfg.save(file);
	   }
	   catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
   }
