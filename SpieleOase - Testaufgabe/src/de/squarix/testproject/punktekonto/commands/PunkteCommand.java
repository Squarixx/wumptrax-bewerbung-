package de.squarix.testproject.punktekonto.commands;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.squarix.testproject.punktekonto.PunkteKonto;
import de.squarix.testproject.punktekonto.utils.PunkteKontoManager;

public class PunkteCommand implements CommandExecutor {

  /*
  Punkte Command. :D
  */
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (args.length == 0)
      {
        int coins = PunkteKontoManager.getPunkte(p.getUniqueId().toString()).intValue();
        p.sendMessage(PunkteKonto.getInstance().prefix + "§7Deine Punktanzahl beträgt§8: §e" + coins + " §7.");
      }
      else if (args.length == 1)
      {
    	  OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

          if (!target.isOnline()) {
        	p.sendMessage(PunkteKonto.getInstance().prefix + "§cDieser Spieler ist noch nicht registriert!");
            return false; 
          }
          if (target.isOnline())
          {
            int coins = PunkteKontoManager.getPunkte(target.getUniqueId().toString()).intValue();
            p.sendMessage(PunkteKonto.getInstance().prefix + "§7Der Spieler " + target.getName() + " §7hat §e" + coins + " §7Punkte.");
          }

      }
      else if (args.length == 3)
      {
        if (p.hasPermission("PunkteSystem.punkte"))
        {
        	 OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        	 if (!target.isOnline()) {
             	p.sendMessage(PunkteKonto.getInstance().prefix + "§cDieser Spieler ist noch nicht registriert!");
                 return false; 
               }
            if (target.isOnline()) {
            try
            {
              int toAdd = Integer.parseInt(args[2]);
              if (args[1].equalsIgnoreCase("pay"))
              {
            	PunkteKontoManager.addPunkte(target.getUniqueId().toString(), Integer.valueOf(toAdd));
            	PunkteKontoManager.removePunkte(p.getUniqueId().toString(), Integer.valueOf(toAdd));
                p.sendMessage(PunkteKonto.getInstance().prefix + "§7Du hast " + toAdd + " §7Punkte an §e" + target + " §7überwiesen.");
                p.sendMessage(PunkteKonto.getInstance().prefix + "§7Dir wurden §e" + toAdd + " §7Punkte abgezogen!");
              }
            }
            catch (NumberFormatException ex)
            {
              p.sendMessage(PunkteKonto.getInstance().prefix + "§cBitte gebe eine Zahl an.");
              return false;
            }
          }
        }
      }
      
      else if (args.length == 3) {
    	 if (p.hasPermission("PunkteSystem.punkte")){
    	  OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

          if (!target.isOnline()) {
        	p.sendMessage(PunkteKonto.getInstance().prefix + "§cDieser Spieler ist noch nicht registriert!");
            return false; 
          }
          if (target.isOnline())
          {
            try
            {
              int toAdd = Integer.parseInt(args[2]);
              if (args[1].equalsIgnoreCase("add"))
              {
            	PunkteKontoManager.addPunkte(target.getUniqueId().toString(), Integer.valueOf(toAdd));
                p.sendMessage(PunkteKonto.getInstance().prefix + "§7Du hast die Coins von §e" + target.getName() + " §7erfolgriech geändert.");
              }
            }
            catch (NumberFormatException ex)
            {
              p.sendMessage(PunkteKonto.getInstance().prefix + "§cBitte gebe eine Zahl an.");
              return false;
            }
          }
          }
          
        }
    }
    return true;
  }
}

