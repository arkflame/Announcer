package dev._2lstudios.announcer.tasks;

import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class AnnouncerTask implements Runnable {
    private final List<String> announcements;
    private int index = 0;

    public AnnouncerTask(final List<String> announcements) {
        this.announcements = announcements;
    }

    @Override
    public void run() {
        if (index >= announcements.size()) {
            index = 0;
        }

        if (index < announcements.size()) {
            ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', announcements.get(index))));
        }

        index++;
    }
}