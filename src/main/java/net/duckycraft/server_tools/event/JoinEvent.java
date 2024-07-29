package net.duckycraft.server_tools.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinEvent {
    Plugin plugin;

    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
    }
    class Vanish implements Listener {

        @EventHandler
        public void VanishOnline(PlayerJoinEvent event) {
            Player player = event.getPlayer();

            for (Player p : player.getServer().getOnlinePlayers()) {
                if (player.hasMetadata("vanished") && !p.isOp() && !p.hasMetadata("vanished")) {
                    p.hidePlayer(plugin, player);
                } else {
                    p.showPlayer(plugin, player);
                }
            }
        }
    }
}
