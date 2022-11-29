package fr.silenthill99.ArcadiaPluginRP.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peux pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Cette commande n'as pas d'arguments !");
            return false;
        }

        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        list.remove(player);

        if (list.size() == 0) {
            player.sendMessage(ChatColor.RED + "Il n'y a pas d'autres joueurs en ligne !");
            return false;
        }

        Player target = list.get(new Random().nextInt(list.size()));
        player.teleport(target.getLocation());
        player.sendMessage(ChatColor.AQUA + "Vous avez été téléporté à " + ChatColor.YELLOW);

        return false;
    }
}
