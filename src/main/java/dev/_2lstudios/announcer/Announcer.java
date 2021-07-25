package dev._2lstudios.announcer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev._2lstudios.announcer.commands.AnnouncerCommand;
import dev._2lstudios.announcer.config.BungeeConfiguration;
import dev._2lstudios.announcer.tasks.AnnouncerTask;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class Announcer extends Plugin {
    private Configuration config;

    public Configuration getConfig() {
        return config;
    }

    public void unload() {
        final ProxyServer proxyServer = this.getProxy();

        proxyServer.getPluginManager().unregisterCommands(this);
        proxyServer.getScheduler().cancel(this);
    }

    public void load() throws IOException {
        final ProxyServer proxyServer = this.getProxy();
        final BungeeConfiguration bungeeConfiguration = new BungeeConfiguration(this, "config.yml");

        bungeeConfiguration.saveDefaults();
        config = bungeeConfiguration.load();

        proxyServer.getPluginManager().registerCommand(this,
                new AnnouncerCommand("announcer", "announcer.admin", ""));

        final long interval = config.getInt("interval");
        final List<String> announcements = config.getStringList("announcements");

        proxyServer.getScheduler().schedule(this, new AnnouncerTask(announcements), interval, interval,
                TimeUnit.SECONDS);
    }

    public void reload() throws IOException {
        unload();
        load();
    }

    @Override
    public void onEnable() {
        Announcer.instance = this;

        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Announcer instance;

    public static Announcer getInstance() {
        return Announcer.instance;
    }
}