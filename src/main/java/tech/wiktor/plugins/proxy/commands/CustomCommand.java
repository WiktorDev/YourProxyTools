package tech.wiktor.plugins.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import tech.wiktor.plugins.proxy.Main;
import tech.wiktor.plugins.proxy.utils.MessageUtil;
import java.util.Iterator;
import java.util.List;

public class CustomCommand extends Command {
    private String name;
    private Main instance;

    public CustomCommand(String name, Main instance){
        super(name);
        this.name = name;
        this.instance = instance;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (instance.getCommands().containsKey(name)){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            Iterator command = ((List)instance.getCommands().get(name)).iterator();
            while (command.hasNext()){
                String line = (String)command.next();
                if (line.startsWith("[say]")){
                    MessageUtil.sendMessage(player, line.replace("[say] ", ""));
                    continue;
                }
                if (line.startsWith("[send]")){
                    ServerInfo target = ProxyServer.getInstance().getServerInfo(line.replace("[send] ", ""));
                    player.connect(target);
                    continue;
                }
            }
        }
    }
}
