package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.Panel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Options implements CommandExecutor {

    public static ItemBuilder whitelist_off = new ItemBuilder(Material.GREEN_DYE).setName("§a§lActiver la whitelist").setLore("La whitelist est désactivée !");
    public static ItemBuilder whitelist_on = new ItemBuilder(Material.RED_DYE).setName("§c§lDésactiver la whitelist").setLore("La whitelist est activée !");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("La console ne peut pas exécuter cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Cette commande n'as pas d'arguments !");
            return false;
        }

        ItemBuilder stop = new ItemBuilder(Material.RED_WOOL).setName("§4Arrêter le serveur").setLore("§4Uniquement en cas d'urgence !");

        Inventory options = Bukkit.createInventory(null, 27, "Options");

        if (Bukkit.getServer().hasWhitelist()) {
            options.setItem(0, whitelist_on.toItemStack());
        } else {
            options.setItem(0, whitelist_off.toItemStack());
        }
        options.setItem(1, stop.toItemStack());

        player.openInventory(options);

        return false;
    }
}
