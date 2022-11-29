package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stafftchat implements CommandExecutor {

    LuckPerms api = LuckPermsProvider.get();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peux pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        User user = api.getUserManager().getUser(player.getName());

        if (args.length == 0) {
            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Veuillez faire /stafftchat <message>");
            return false;
        }

        StringBuilder bc = new StringBuilder();

        for (String part : args) {
            bc.append(part + " ");
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("Arcadia.staff")) {
                players.sendMessage("§6[STAFF] " +  user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName() + " §a: "+bc);
            }
        }

        return false;
    }
}
