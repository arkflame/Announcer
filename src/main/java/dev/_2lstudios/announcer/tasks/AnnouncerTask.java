package dev._2lstudios.announcer.tasks;

import dev._2lstudios.announcer.Announcer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class AnnouncerTask implements Runnable {
    @Override
    public void run() {
        final String message = Announcer.getInstance().getConfig().getString("messages.from-task");
        ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
    }
}