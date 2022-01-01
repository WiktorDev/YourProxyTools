package tech.wiktor.plugins.proxy;

import lombok.SneakyThrows;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import tech.wiktor.plugins.proxy.managers.CommandsManager;
import tech.wiktor.plugins.proxy.managers.ConfigManager;
import java.io.File;
import java.util.*;

public final class Main extends Plugin {
    private Configuration config;
    private Map<String, List<String>> commands;

    @Override
    @SneakyThrows
    public void onEnable() {
        this.commands = new HashMap();
        new ConfigManager(this).loadConfiguration();
        Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        this.config = configuration;
        new CommandsManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Configuration getConfig() {
        return this.config;
    }

    public Map<String, List<String>> getCommands(){
        return commands;
    }
}