package dev._2lstudios.announcer;

import java.util.concurrent.TimeUnit;

import dev._2lstudios.announcer.commands.AnnouncerCommand;
import dev._2lstudios.announcer.config.BungeeConfiguration;
import dev._2lstudios.announcer.tasks.AnnouncerTask;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class Announcer extends Plugin {
    private Configuration config;
    
    public Configuration getConfig() {
        return config;
    }

    @Override
    public void onEnable () {
        final BungeeConfiguration bungeeConfiguration = new BungeeConfiguration(this, "config.yml");

        bungeeConfiguration.saveDefaults();
        config = bungeeConfiguration.load();

        Announcer.instance = this;

        this.getCommand("example").setExecutor(new AnnouncerCommand());

        final long interval = config.getInt("interval");

        this.getProxy().getScheduler().schedule(this, new AnnouncerTask(), interval, interval, TimeUnit.SECONDS);
    }

    private static Announcer instance;

    public static Announcer getInstance () {
        return Announcer.instance;
    }
}