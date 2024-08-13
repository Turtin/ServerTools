package net.duckycraft.server_tools.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinEvent implements Listener {

    Plugin plugin;

    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void VanishOnline(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player p : player.getServer().getOnlinePlayers()) {
            if (p.hasMetadata("vanished") && (!player.isOp() || !player.hasMetadata("vanished"))) {
                player.hidePlayer(plugin, p);
            } else {
                player.showPlayer(plugin, p);
            }
        }

        if (player.hasMetadata("vanished")) {
            event.setJoinMessage(null); // Prevents join message from happening
        }
    }
}
