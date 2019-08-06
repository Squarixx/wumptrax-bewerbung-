package de.squarix.testproject.punktekonto.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.squarix.testproject.punktekonto.PunkteKonto;
import de.squarix.testproject.punktekonto.utils.PunkteKontoManager;

public class Events implements Listener {
	
	
  public  ArrayList<Player> waitCooldown = new ArrayList<>();
  public  List list = new ArrayList<>();
  //public HashMap map = new HashMap();
  
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    PunkteKontoManager.createPlayer(p.getUniqueId().toString());
  }
  	
  
  

  
  //3 Aufgabe
  
  /*
  Diese Aufgabe ist meiner Meinung nach nicht lösbar.
  Man kann eben nicht für jeden Command eine eigene HashMap erstellen.
  Falls man nur eine HashMap etc. benutzt funktioniert das ganze bei
  meheren Commands oder Spielern nicht, da dann die Commands überschrieben werden.
 
  Ich habe 3h an dieser Aufgabe gearbeitet und es  ist nichts dabei rumgekommen(Außer man ist allein auf dem Server). xd
  
  Der Cooldown ist mit meiner Lösung nur mit einem Spieler möglich. 
  */
  
  @EventHandler
  public void onCmd(PlayerCommandPreprocessEvent e) {
      Player p = e.getPlayer();
      if(waitCooldown.contains(p)) {
          if(list.contains(e.getMessage())) {
              e.setCancelled(true);
              p.sendMessage("§cWarte 15 Sekunden zwischen diesem Commando!");
          }  else {
              list.add(e.getMessage());
          }
      } else {
          waitCooldown.add(p);
          list.add(e.getMessage());
          
          Bukkit.getScheduler().runTaskLater(PunkteKonto.getInstance(), new Runnable() {
              public void run() {
                  waitCooldown.remove(p);
                  p.sendMessage("§7Du kannst nun den Command wieder nutzen!");
                  list.clear();
              }
          }, 20*15);
      }
  }
	
}
	   
     
	
