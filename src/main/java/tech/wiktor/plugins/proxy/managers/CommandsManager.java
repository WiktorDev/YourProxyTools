package tech.wiktor.plugins.proxy.managers;

import net.md_5.bungee.api.ProxyServer;
import tech.wiktor.plugins.proxy.Main;
import tech.wiktor.plugins.proxy.commands.CustomCommand;

import java.util.Iterator;

public class CommandsManager {
    public CommandsManager(Main instance){
        Iterator iterator = instance.getConfig().getSection("commands").getKeys().iterator();
        while (iterator.hasNext()){
            String commandName = (String) iterator.next();
            instance.getCommands().put(commandName.toLowerCase(), instance.getConfig().getStringList("commands."+commandName));
            ProxyServer.getInstance().getPluginManager().registerCommand(instance, new CustomCommand(commandName, instance));
        }
    }
}