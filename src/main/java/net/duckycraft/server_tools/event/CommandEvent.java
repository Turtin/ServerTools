package net.duckycraft.server_tools.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;

public class CommandEvent implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/minecraft:msg") || event.getMessage().startsWith("/minecraft:tell") || event.getMessage().startsWith("/minecraft:w")) {
            StringBuilder command = new StringBuilder(); // Preparing to rebuild the players command
            for (String part : event.getMessage().split(" ")) { // Loop through the command parts
                if (part.startsWith("/minecraft:msg") || part.startsWith("/minecraft:tell") || part.startsWith("/minecraft:w")) {// removes the part that stops the plugin from handling the command instead
                    command.append(part.replace("minecraft:", ""));
                } else {
                    command.append(" ").append(part); // Rebuilding their command
                }
            }

            event.getPlayer().chat(command.toString()); // Forcing the player to run the remade command
            event.setCancelled(true); // Cancelling the original command
        }
    }
}
