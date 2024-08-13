package net.duckycraft.server_tools.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (event.getPlayer().hasMetadata("vanished")) {
            event.setQuitMessage(null); // Prevents leave message from happening
            return;
        }
    }
}
