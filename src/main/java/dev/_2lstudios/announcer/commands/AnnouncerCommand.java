package dev._2lstudios.announcer.commands;

import dev._2lstudios.announcer.Announcer;

public class AnnouncerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String message = Announcer.getInstance().getConfig().getString("messages.from-command");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }
}
