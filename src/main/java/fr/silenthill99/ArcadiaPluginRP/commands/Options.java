package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Options implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("La console ne peut pas exécuter cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 1)
        {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Cette commande n'as pas d'arguments !");
            return false;
        }

        InventoryManager.openInventory(player, InventoryType.OPTION );

        return false;
    }
}
