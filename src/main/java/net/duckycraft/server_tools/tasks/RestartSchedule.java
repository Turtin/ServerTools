package net.duckycraft.server_tools.tasks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RestartSchedule extends Thread {

    private Plugin plugin; // The plugin instance

    private class RestartProcess extends TimerTask {
         public void run() { // This really badly made, but for some reason throwing the exception doesn't work
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

             plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Server is restarting in 10 minutes!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.YELLOW + "Server is restarting in 10 minutes!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.YELLOW + "Server is restarting in 10 minutes!")));

             try {
                 Thread.sleep(300000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Server is restarting in 5 minutes!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.YELLOW + "Server is restarting in 5 minutes!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.YELLOW + "Server is restarting in 5 minutes!")));

             try {
                 Thread.sleep(240000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.GOLD + "Server is restarting in 1 minutes!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.GOLD + "Server is restarting in 1 minutes!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.GOLD + "Server is restarting in 1 minutes!")));

             try {
                 Thread.sleep(30000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.GOLD + "Server is restarting in 30 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.GOLD + "Server is restarting in 30 seconds!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.GOLD + "Server is restarting in 30 seconds!")));

             try {
                 Thread.sleep(20000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 10 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.RED + "Server is restarting in 10 seconds!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 10 seconds!")));

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 5 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.RED + "Server is restarting in 5 seconds!", "", 10, 70, 20));
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 5 seconds!")));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 4 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 4 seconds!")));

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 3 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 3 seconds!")));

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 2 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 2 seconds!")));

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             plugin.getServer().broadcastMessage(ChatColor.RED + "Server is restarting in 1 seconds!");
             plugin.getServer().getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.RED + "Server is restarting in 1 seconds!")));

             plugin.getServer().spigot().restart();
         }
    }

    public void scheduleRestart(Date date, Plugin plugin) {
        this.plugin = plugin;

        Date startTime = date;
        startTime.setHours(date.getHours()-1);

        Timer timer = new Timer();
        timer.schedule(new RestartSchedule.RestartProcess(), startTime);
    }

    public void run() {
        new RestartSchedule.RestartProcess().run();
    }
}
