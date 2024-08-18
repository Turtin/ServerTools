package net.duckycraft.server_tools.tasks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RestartSchedule {

    private Plugin plugin;

    private class RestartProcess extends TimerTask {
         public void run() {
             plugin.getServer().broadcastMessage(ChatColor.GREEN + "Server is restarting in 1 hour!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.GREEN + "Server is restarting in 1 hour!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.GREEN + "Server is restarting in 1 hour!")));

             try {
                 Thread.sleep(1800000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.GREEN + "Server is restarting in 30 minutes!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.GREEN + "Server is restarting in 30 minutes!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.GREEN + "Server is restarting in 30 minutes!")));

             try {
                    Thread.sleep(1200000);
             } catch (InterruptedException e) {
                    e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Server is restarting in 30 minutes!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.YELLOW + "Server is restarting in 30 minutes!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.YELLOW + "Server is restarting in 30 minutes!")));

         }
    }

    public void scheduleRestart(Date date, Plugin plugin, Configuration config) {
        this.plugin = plugin;

        Date startTime = date;
        startTime.setHours(date.getHours()-1);

        Timer timer = new Timer();
        timer.schedule(new RestartSchedule.RestartProcess(), startTime);
    }
}
