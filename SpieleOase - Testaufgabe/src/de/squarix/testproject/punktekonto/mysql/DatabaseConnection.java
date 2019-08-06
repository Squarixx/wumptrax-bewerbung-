package de.squarix.testproject.punktekonto.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import de.squarix.testproject.punktekonto.PunkteKonto;
import net.md_5.bungee.api.ChatColor;

public class DatabaseConnection  {
 
  public String HOST = "";
  public String DATABASE = "";
  public String USER = "";
  public String PASSWORD = "";
  static Connection con;
  
  public DatabaseConnection(String host, String database, String user, String password)
  {
    HOST = host;
    DATABASE = database;
    USER = user;
    PASSWORD = password;
    
    connect();
  }
  
  public static void connect()
  {
    try
    {
      con = DriverManager.getConnection("jdbc:mysql://" + PunkteKonto.mysql.HOST + ":3306/" + PunkteKonto.mysql.DATABASE + "?autoReconnect=true", PunkteKonto.mysql.USER, PunkteKonto.mysql.PASSWORD);
      Bukkit.getConsoleSender().sendMessage(PunkteKonto.getInstance().prefix + ChatColor.DARK_GREEN + "Die Verbindung für die Datenbank wurde hergestellt! :)");
      
    }
    catch (SQLException e)
    {
      Bukkit.getConsoleSender().sendMessage(PunkteKonto.getInstance().prefix +  ChatColor.RED + "Fehler beim verbinden zur Datenbank: " + e.getMessage() + " :C");
    }
  }
  
  public void close()
  {
    try
    {
      if (con != null) {
        con.close();
      }
    }
    catch (SQLException localSQLException) {}
  }
  
  public void update(String qry)
  {
    try
    {
      Statement st = con.createStatement();
      st.executeUpdate(qry);
      st.close();
    }
    catch (SQLException e)
    {
      connect();
      System.err.println(e);
    }
  }


  public ResultSet query(String qry)
  {
    ResultSet rs = null;
    try
    {
      Statement st = con.createStatement();
      rs = st.executeQuery(qry);
    }
    catch (SQLException e)
    {
      connect();
      System.err.println(e);
    }
    return rs;
  }
}
