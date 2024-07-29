package net.duckycraft.server_tools.bot;

import net.duckycraft.server_tools.file.FileHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.managers.WebhookManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class BotHandeler {
    FileConfiguration config;
    String TOKEN;
    JDA bot;

    HashMap<Player, Webhook> webhooks = new HashMap<>();

    public void createBot(FileConfiguration configFile) throws InterruptedException {
        config = configFile; // Saving the config file to a field so it can be accessed later
        TOKEN = config.getString("bot-token"); // Getting the bot token from the config file
        JDABuilder builder = JDABuilder.createDefault(TOKEN); // Creating the bot
        bot = builder/*.enableIntents(EnumSet.allOf(GatewayIntent.class))*/.build().awaitReady();
    }

    private Webhook getWebhookByName(String name) { // Subject to removal
        for (Webhook webhook : bot.getTextChannelById(config.getString("channel-id")).retrieveWebhooks().complete()) {
            if (Objects.equals(webhook.getName(), name)) {
                return webhook;
            }
        }
        return null;
    }

    private Icon getFace(Player player) throws IOException { // Gets the players face
        return Icon.from(new FileHandler().getImage(player)); // Getting the players face
    }

    private void createWebhook(String name, Player player) throws IOException { // Creates a webhook for the player
        bot.getTextChannelById(config.getString("channel-id")).createWebhook(name).complete(); // Creating the webhook
        webhooks.put(player, getWebhookByName(name)); // Saving the webhook to a hashmap
        WebhookManager manager = webhooks.get(player).getManager(); // Getting the webhook manager
        manager.setAvatar(getFace(player)); // Setting the webhook avatar
        manager.complete(); // Applying the changes
    }
    public void sendMessage(String message, Player player) throws IOException { // Sends a message to discord
        if (!webhooks.containsKey(player)) { // Checking if the player has a webhook
            createWebhook(player.getName(), player);
        }
        webhooks.get(player).sendMessage(message).queue(); // Sending the message
    }

    public void clearWebhooks() { // Clears all webhooks
        for (Webhook webhook : bot.getTextChannelById(config.getString("channel-id")).retrieveWebhooks().complete()) { // Getting all webhooks
            webhook.delete().queue(); // Deleting the webhook
        }
    }
}
