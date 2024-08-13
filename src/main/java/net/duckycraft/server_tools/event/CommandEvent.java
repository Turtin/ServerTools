package net.duckycraft.server_tools.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/msg") || event.getMessage().startsWith("/tell") || event.getMessage().startsWith("/w") || event.getMessage().startsWith("/minecraft:msg") || event.getMessage().startsWith("/minecraft:tell") || event.getMessage().startsWith("/minecraft:w")) {
            for (Player p: event.getPlayer().getServer().getOnlinePlayers()) {
                if (event.getMessage().split(" ")[1].equalsIgnoreCase(p.getName()) && p.hasMetadata("vanished")) {
                    event.getPlayer().sendMessage(ChatColor.RED + "Player not found");
                    event.setCancelled(true);
                }
            }
        }
    }
}
