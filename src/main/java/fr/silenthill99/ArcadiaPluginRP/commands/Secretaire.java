package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Secretaire implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peut être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1)
        {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Veuillez faire /secretaire <on|off>");
            return false;
        }

        if (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))
        {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " \"" + args[0] + "\" n'est pas un argument valable !");
        } else if (args[0].equalsIgnoreCase("on"))
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set secretaire");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + ChatColor.YELLOW + " Vous venez de prendre votre service en tant que Secrétaire général d'Arcadia héberge");
        } else
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Vous avez fini votre service en tant que staff !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + player.getName() + " off");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + player.getName() + " off");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "god " + player.getName() + " off");
            if (!player.getGameMode().equals(GameMode.ADVENTURE))
            {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }

        return false;
    }
}
