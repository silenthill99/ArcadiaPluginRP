package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class Menu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peux pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("/menu <offline player>");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
        return false;
    }
}
