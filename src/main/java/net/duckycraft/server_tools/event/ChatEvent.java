package net.duckycraft.server_tools.event;

import net.duckycraft.server_tools.bot.BotHandeler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class ChatEvent implements Listener {

    BotHandeler BOT;
    FileConfiguration CONFIG;

    public ChatEvent(BotHandeler bot, FileConfiguration config) {
        BOT = bot; // saving the bot instance to a field so it can be accessed later
        CONFIG = config; // saving the config instance to a field so it can be accessed later
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException {
        if (!CONFIG.getBoolean("chat-forwarding")) { // Checking if chat forwarding is enabled
            return;
        }

        String message = event.getMessage(); // Getting the message
        BOT.sendMessage(message, event.getPlayer()); // Sending the message to discord
    }
}
