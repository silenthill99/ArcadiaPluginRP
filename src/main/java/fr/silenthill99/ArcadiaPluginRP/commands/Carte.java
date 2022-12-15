package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.Panel;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Carte implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Cette commande ne peut pas être effectuée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §cCette commande n'a pas d'arguments !");
            return false;
        }

        InventoryManager.openInventory(player, InventoryType.TELEPORTATION);

        return false;
    }
}
