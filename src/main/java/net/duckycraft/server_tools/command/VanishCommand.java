package net.duckycraft.server_tools.command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {
    Plugin plugin;

    public VanishCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setActionBar () throws InterruptedException {
        while (true) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                if (p.hasMetadata("vanished")) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("You are currently invisible!"));
                }
            }
            wait(500);
        }
    }

    private void activateVanish(Player player) {
        if (player.hasMetadata("vanished")) { // If they are vanished
            player.removeMetadata("vanished", plugin);
            plugin.getServer().broadcastMessage(player.getName() + " has joined the game!");
        } else { // If they are not vanished
            player.setMetadata("vanished", new FixedMetadataValue(plugin, true));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("You are currently invisible!"));
            plugin.getServer().broadcastMessage(player.getName() + " has left the game!");
        }

        for (Player p : player.getServer().getOnlinePlayers()) {
            if (player.hasMetadata("vanished") && (!p.isOp() || !p.hasMetadata("vanished"))) {
                p.hidePlayer(plugin, player);
            } else {
                p.showPlayer(plugin, player);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!sender.isOp()) return true;
        if (args.length == 0) {
            if (sender instanceof Player) {
                activateVanish(((Player) sender).getPlayer());
                return true;
            } else {
                sender.sendMessage("You must be a player to use this command!");
                return true;
            }
        } else if (args.length == 1) {
            activateVanish(sender.getServer().getPlayer(args[0]));
            return true;
        }
        return false;
    }
}
