package net.duckycraft.server_tools;

import net.duckycraft.server_tools.bot.BotHandeler;
import net.duckycraft.server_tools.event.ChatEvent;
import net.duckycraft.server_tools.file.FirstInit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTools extends JavaPlugin {

    BotHandeler BOT;

    @Override
    public void onEnable() {
        new FirstInit(); // Creating the necessary directories
        saveDefaultConfig(); // Creating the config file if it doesn't exist
        BotHandeler bot = new BotHandeler(); // Preparing the bot
        try { // Trying to create the bot
            bot.createBot(getConfig());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getServer().getPluginManager().registerEvents(new ChatEvent(bot, getConfig()), this);

        BOT = bot; // saving the bot instance to a field so it can be accessed later
        // Notifying the console that the plugin has been enabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been enabled!");
    }

    @Override
    public void onDisable() {
        BOT.clearWebhooks(); // Clearing all webhooks when the server stops
        // Notifying the console that the plugin has been disabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been enabled!");
    }
}
