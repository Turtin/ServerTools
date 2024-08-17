package net.duckycraft.server_tools.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BotCommands extends ListenerAdapter {
    JDA bot;
    Configuration config;
    Plugin plugin;

    public BotCommands(JDA bot, Configuration config, Plugin plugin) {
        this.bot = bot;
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        plugin.getServer().getConsoleSender().sendMessage("Command: " + command);
        if (command.equals("join")) {
            event.reply("joining minecraft chat").queue();
            plugin.getServer().getConsoleSender().sendMessage("Joining minecraft chat");
        }
    }

    // Registers the commands

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().upsertCommand(getCommands().getFirst()).queue();
        plugin.getServer().getConsoleSender().sendMessage("Commands registered");
    }

    public List<CommandData> getCommands() {
        List<CommandData> commands = new ArrayList<>();

        commands.add(Commands.slash("join", "Joins the minecraft chat").setDefaultPermissions(DefaultMemberPermissions.ENABLED));

        return commands;
    }
}
