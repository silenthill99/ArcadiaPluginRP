package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Moderateur implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cVous avez oublié d'activer le mode Modérateur");
            return false;
        }

        if (args.length > 1) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cLa commande contient trop d'arguments !");
            return false;
        }

        if(!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off")) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c\"" + args[0] + "§c\" n'est pas un argument valable !");
        } else if (args[0].equalsIgnoreCase("on")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set moderateur");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §eVous venez de prendre votre service en tant que Modérateur d'Arcadia héberge");
        } else if (args[0].equalsIgnoreCase("off")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cVous avez fini votre service en tant que Staff !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + player.getName() + " off");
            Bukkit.getOnlinePlayers().forEach(players -> player.showPlayer(Main.getInstance(), players));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "god " + player.getName() + " off");
        }

        return false;
    }
}
