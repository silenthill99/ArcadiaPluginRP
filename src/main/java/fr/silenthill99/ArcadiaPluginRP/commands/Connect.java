package fr.silenthill99.ArcadiaPluginRP.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Connect implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        LuckPerms api = LuckPermsProvider.get();
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peux pas être exécutée via la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            player.sendMessage("§4Cette commande n'a pas d'arguments !");
            return false;
        }
        User user = api.getUserManager().getUser(player.getName());
        Bukkit.broadcastMessage("§2[+] "+user.getCachedData().getMetaData().getPrefix().replace("&", "§")+player.getName());
        Bukkit.dispatchCommand(player, "vanish off");
        return false;
    }
}
