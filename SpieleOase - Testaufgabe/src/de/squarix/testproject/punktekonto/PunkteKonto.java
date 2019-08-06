package de.squarix.testproject.punktekonto;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.squarix.testproject.punktekonto.commands.PunkteCommand;
import de.squarix.testproject.punktekonto.listener.Events;
import de.squarix.testproject.punktekonto.mysql.DatabaseConnection;
import de.squarix.testproject.punktekonto.mysql.PunkteKontoTable;
import de.squarix.testproject.punktekonto.utils.Configuration;

public class PunkteKonto extends JavaPlugin{
	
  public static PunkteKonto main;
  public static Configuration cfg;
  public static DatabaseConnection mysql;

  public String prefix = "§8[§ePunkte§8] §7";
  
     /*
     In dem Formular stand folgenes: "Solche Überweisungen nennen wir auch Transaktionen. Jede Veränderung am Punktestand wird durch eine sogennante Transaktion dokumentiert. "
     Jedoch wurde keine Funktion dieser Transaktionen angegeben, weshalb ich das ganze nicht verstanden hab? xd
     Aus diesem Grund habe ich dann das ganze nicht eingebaut, da das Ganze ja nirgendwo benutzt wird. xD
     */
	 
  
  public void onEnable() {
    main = this;
	  
    Configuration.getMySQLDaten();
    connectMySQL();
    PunkteKontoTable.createKontoTable();
	  
    register();
  }
  
  
  public void onDisable() {  
     mysql.close();
  }
  
  private void connectMySQL() {
    mysql = new DatabaseConnection(PunkteKonto.mysql.HOST, PunkteKonto.mysql.DATABASE, PunkteKonto.mysql.USER, PunkteKonto.mysql.PASSWORD);
  }
  
  
  public void register()
  {
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new Events(), this);
    
    getCommand("punkte").setExecutor(new PunkteCommand());
  }

  
  public static PunkteKonto getInstance() {
   return main;
  }


  public static DatabaseConnection getMySQL() {
    return mysql;
  }


}
