package net.duckycraft.server_tools.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MessageVanisher implements CommandExecutor, TabCompleter {
    boolean servercom = false;

    public MessageVanisher(boolean servercom) {
        this.servercom = servercom;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length < 2) { // If the command is incomplete
            StringBuilder command = new StringBuilder(); // Create a string to store the command
            command.append(s);
            for (String arg : args) { // Loop through the arguments
                command.append(" " + arg); // Add the argument to the message
            }
            sender.sendMessage(ChatColor.RED + "Unknown or incomplete command, see bellow for error\n" + command + "<--[HERE]");
            return true;
        } else if ((!sender.getServer().getOnlinePlayers().contains(sender.getServer().getPlayer(args[0])) || sender.getServer().getPlayer(args[0]).hasMetadata("vanished")) && !(args[0].equalsIgnoreCase("server") && servercom)) { // If the player is not online or vanished
            sender.sendMessage(ChatColor.RED + "No player was found");
            return true;
        }

        StringBuilder message = new StringBuilder(); // Create a string to store the message

        for (int i = 1; i < args.length; i++) { // Loop through the arguments
            message.append(args[i]).append(" "); // Add the argument to the message
        }

        if (servercom && args[0].equalsIgnoreCase("server")) { // If the player is trying to message the server
            sender.sendMessage(ChatColor.GRAY + "You whisper to the server: " + message); // Send the message to the sender
            sender.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + sender.getName() + " whispers to you: " + message); // Send the message to the console
            return true;
        }

        sender.sendMessage(ChatColor.GRAY + "You whisper to " + sender.getServer().getPlayer(args[0]).getName() + ": " + message); // Send the message to the sender
        sender.getServer().getPlayer(args[0]).sendMessage(ChatColor.GRAY + sender.getName() + " whispers to you: " + message); // Send the message to the player
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) { // If they are trying to tab complete the first argument
            List<String> players = new ArrayList<>(); // Create a list to store the players

            for (Player player: sender.getServer().getOnlinePlayers()) {
                if (!player.hasMetadata("vanished")) { // If the player is not vanished
                    players.add(player.getName()); // Add the player to the list
                }
            }
            players.add("server"); // Add the server to the list
            return players; // Return the list
        } else {
            return List.of("<message>"); // Return an empty list
        }
    }
}
