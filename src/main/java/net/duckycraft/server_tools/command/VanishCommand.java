package net.duckycraft.server_tools.command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.tools.jconsole.Tab;

import java.util.List;

public class VanishCommand implements CommandExecutor, TabCompleter {

    Plugin plugin;

    public VanishCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    private void activateVanish(Player player) {
        if (player.hasMetadata("vanished")) { // If they are vanished
            player.removeMetadata("vanished", plugin);
            plugin.getServer().broadcastMessage( ChatColor.YELLOW + player.getName() + " joined the game");
        } else { // If they are not vanished
            player.setMetadata("vanished", new FixedMetadataValue(plugin, true));
            plugin.getServer().broadcastMessage( ChatColor.YELLOW + player.getName() + " left the game");
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
        if (!sender.isOp()) return true; // If the sender is not an operator
        //vanishing players
        if (args.length == 0) {
            if (sender instanceof Player) {
                activateVanish(((Player) sender).getPlayer());
                return true;
            } else {
                sender.sendMessage("You must be a player to use this command!");
                return true;
            }
        } else if (args.length == 1) {
            // If first arg is "List"
            if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage("Vanished Players:");
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    if (player.hasMetadata("vanished")) {
                        sender.sendMessage("  " + player.getName());
                    }
                }
                return true;
            }
            // If first arg is a player
            activateVanish(sender.getServer().getPlayer(args[0]));
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> completions = new java.util.ArrayList<>();
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            completions.add(player.getName());
        }
        completions.add("list");
        return completions;
    }
}
