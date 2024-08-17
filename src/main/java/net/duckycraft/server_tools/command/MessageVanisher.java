package net.duckycraft.server_tools.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MessageVanisher implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length < 2) { // If the command is incomplete
            for (int i = 1; i < args.length; i++) { // Loop through the arguments
                s += " " + args[i]; // Add the argument to the message
            }
            sender.sendMessage(ChatColor.RED + "Unknown or incomplete command, see bellow for error\n" + s + "<--[HERE]");
            return true;
        } else if (!sender.getServer().getOnlinePlayers().contains(sender.getServer().getPlayer(args[0])) || sender.getServer().getPlayer(args[0]).hasMetadata("vanished")) { // If the player is not online or vanished
            sender.sendMessage(ChatColor.RED + "No player was found");
            return true;
        }

        StringBuilder message = new StringBuilder(); // Create a string to store the message

        for (int i = 1; i < args.length; i++) { // Loop through the arguments
            message.append(args[i]).append(" "); // Add the argument to the message
        }

        sender.sendMessage(ChatColor.GRAY + "You whisper to " + sender.getServer().getPlayer(args[0]).getName() + ": " + message); // Send the message to the sender
        sender.getServer().getPlayer(args[0]).sendMessage(ChatColor.GRAY + sender.getName() + "whispers to you: " + message); // Send the message to the player
        return true;
    }
}
