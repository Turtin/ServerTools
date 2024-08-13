package net.duckycraft.server_tools.command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ActionBarHandeler extends Thread{
    Plugin plugin;

    public ActionBarHandeler(Plugin plugin) {
        this.plugin = plugin;
    }

    public void run() {
        try {
            while (true) {
                for (Player p : plugin.getServer().getOnlinePlayers()) {
                    if (p.hasMetadata("vanished")) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "You are currently invisible!"));
                    }
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
