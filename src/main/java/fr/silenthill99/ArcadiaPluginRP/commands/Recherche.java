package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class  Recherche implements CommandExecutor, TabCompleter {

    ArrayList<String> arguments = new ArrayList<>();
    ArrayList<Player> recherche = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0 || args[0].equalsIgnoreCase("liste")) {
            Inventory inv = Bukkit.createInventory(null, 54, "Avis de recherche");
            int slot = 0;
            for (Player players : recherche) {
                ItemBuilder Tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(players.getName()).setName(players.getName());
                inv.setItem(slot, Tete.toItemStack());
                slot += 1;
            }
            player.openInventory(inv);
            return false;
        }
        if (args[0].equalsIgnoreCase("ajouter")) {
            if (args.length < 2) {
                player.sendMessage("§aVeuillez saisir le pseudo d'un joueur");
                return false;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (recherche.contains(target)) {
                player.sendMessage("§4Cette personne est déjà recherchée !");
                return false;
            }

            recherche.add(target);
            Bukkit.broadcastMessage("§4[Attention] §cl'individu §6" + target.getName() + " §cest actuellement recherché par la police !");
            return false;
        }
        if (args[0].equalsIgnoreCase("retirer")) {
            if (args.length < 2) {
                player.sendMessage("§aVeuillez saisir le pseudo d'un joueur");
                return false;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (!recherche.contains(target)) {
                player.sendMessage("§aLe joueur n'a pas d'avis de recherche en cours");
                return false;
            }

            recherche.remove(target);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("Arcadia.fdl")) {
                    players.sendMessage("§1[Info police] §e" + target.getName() + " §an'as plus d'avis de recherche !");
                }
            }
            return false;
        }
        if (!args[0].equalsIgnoreCase("liste") && !args[0].equalsIgnoreCase("ajouter") && !args[0].equalsIgnoreCase("retirer")) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix") + " §c\"" + args[0] + "§c\" n'est pas un argument valable !");
            return false;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
        String prefix = args[args.length - 1].toLowerCase(Locale.ROOT);
        for (String s : Arrays.asList("ajouter", "liste", "retirer")) {
            if (prefix.isEmpty() || s.startsWith(prefix)) {
                arguments.add(s);
            }
        }
        return arguments;
    }
}
