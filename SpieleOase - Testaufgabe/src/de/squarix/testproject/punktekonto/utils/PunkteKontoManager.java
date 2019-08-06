package de.squarix.testproject.punktekonto.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.squarix.testproject.punktekonto.PunkteKonto;
import de.squarix.testproject.punktekonto.mysql.DatabaseConnection;

public class PunkteKontoManager {
	
	 /*
	 Hier sind alle Methoden zur Abfragung, Hinzufügung, Erstellung von den
	 Punkten der Spieler.
	 */
	 

	  public static boolean playerExists(String uuid)
	  {
		try{
		ResultSet rs = PunkteKonto.mysql.query("SELECT * FROM Punkte WHERE UUID= '" + uuid + "'");
		 if (rs.next()) {
		     return rs.getString("UUID") != null;
		    }
		    return false;
		    }
			catch (SQLException e)
		    {
		      e.printStackTrace();
		    }
		    return false;
		  }
		  
	  public static void createPlayer(String uuid)
	  {
	    if (!playerExists(uuid)) {
	    	PunkteKonto.mysql.update("INSERT INTO Punkte(UUID, punkte) VALUES ('" + uuid + "', '0');");
	    }
	  }
	  
	  public static Integer getPunkte(String uuid)
	  {
	    Integer i = Integer.valueOf(0);
	    if (playerExists(uuid))
	    {
	      try
	      {
	        ResultSet rs = PunkteKonto.mysql.query("SELECT punkte FROM Punkte WHERE UUID= '" + uuid + "'");
	        if (rs.next()) Integer.valueOf(rs.getInt("punkte"));
	        i = Integer.valueOf(rs.getInt("punkte"));
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    else
	    {
	      createPlayer(uuid);
	      getPunkte(uuid);
	    }
	    return i;
	  }
	  
	  public static void setPunkte(String uuid, Integer punkte)
	  {
	    if (playerExists(uuid))
	    {
	      PunkteKonto.mysql.update("UPDATE Punkte SET punkte= '" + punkte + "'WHERE UUID= '" + uuid + "';");
	    }
	    else
	    {
	      createPlayer(uuid);
	      setPunkte(uuid, punkte);
	    }
	    
	  }
	  
	  public static void addPunkte(String uuid, Integer Punkte)
	  {
	    if (playerExists(uuid))
	    {
	      setPunkte(uuid, Integer.valueOf(getPunkte(uuid).intValue() + Punkte.intValue()));
	    }
	    else
	    {
	      createPlayer(uuid);
	      addPunkte(uuid, Punkte);
	    }
	  }
	  
	  public static void removePunkte(String uuid, Integer Punkte)
	  {
	    if (playerExists(uuid))
	    {
	      setPunkte(uuid, Integer.valueOf(getPunkte(uuid).intValue() - Punkte.intValue()));
	    }
	    else
	    {
	      createPlayer(uuid);
	      removePunkte(uuid, Punkte);
	    }
	  }
	}
