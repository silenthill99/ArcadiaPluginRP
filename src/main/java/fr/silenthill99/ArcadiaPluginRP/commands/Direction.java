package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Direction implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peux pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cVous avez oublié le mode Directeur");
            return false;
        }

        if (args.length > 1 ) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cLa commande contient trop d'arguments !");
            return false;
        }

        if (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off")) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c" + args[0] + " §cn'est pas un argument valable !");
        } else if (args[0].equalsIgnoreCase("on")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set directeur");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §eVous avez pris votre service en tant que Directeur de Arcadia RP !");
        } else if (args[0].equalsIgnoreCase("off")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cVous avez fini votre service en tant que Staff !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "god " + player.getName() + " off");
            Bukkit.getOnlinePlayers().forEach(players -> player.showPlayer(Main.getInstance(), players));
            player.setFlying(false);
            player.setAllowFlight(false);
            if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
                player.setGameMode(GameMode.ADVENTURE);
            }
            if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        }

        return false;
    }
}