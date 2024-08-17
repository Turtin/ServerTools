package net.duckycraft.server_tools;

import net.duckycraft.server_tools.bot.BotHandeler;
import net.duckycraft.server_tools.command.ActionBarHandeler;
import net.duckycraft.server_tools.command.MessageVanisher;
import net.duckycraft.server_tools.command.VanishCommand;
import net.duckycraft.server_tools.event.ChatEvent;
import net.duckycraft.server_tools.event.CommandEvent;
import net.duckycraft.server_tools.event.JoinEvent;
import net.duckycraft.server_tools.file.FirstInit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTools extends JavaPlugin {

    BotHandeler BOT;

    @Override
    public void onEnable() {
        // Discord integration setup
        new FirstInit(); // Creating the necessary directories
        saveDefaultConfig(); // Creating the config file if it doesn't exist
        BotHandeler bot = new BotHandeler(); // Preparing the bot
        try { // Trying to create the bot
            bot.createBot(getConfig(), this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Try to start up the vanish action bar
        ActionBarHandeler actionBar = new ActionBarHandeler(this);
        actionBar.start();

        // Event Registry
        getServer().getPluginManager().registerEvents(new ChatEvent(bot, getConfig()), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new CommandEvent(), this);

        // Command Registry
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("vanish").setTabCompleter(new VanishCommand(this));

        // hiding vanished players from /w
        getCommand("msg").setExecutor(new MessageVanisher());
        getCommand("w").setExecutor(new MessageVanisher());
        getCommand("tell").setExecutor(new MessageVanisher());

        BOT = bot; // saving the bot instance to a field so it can be accessed later
        // Notifying the console that the plugin has been enabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been enabled!");
    }

    @Override
    public void onDisable() {
        BOT.clearWebhooks(); // Clearing all webhooks when the server stops
        // Notifying the console that the plugin has been disabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been disabled!");
    }
}
