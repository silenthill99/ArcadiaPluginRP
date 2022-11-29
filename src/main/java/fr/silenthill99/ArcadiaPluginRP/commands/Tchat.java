package fr.silenthill99.ArcadiaPluginRP.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Tchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        sender.sendMessage("&e/hrp &a: Commande pour parler das un contexte HRP");
        sender.sendMessage("&eRP normal &a: Tchat pour parler dans un contexte RP");
        sender.sendMessage("&e/radio &a: Si vous êtes des forces de l'ordre ou secouristes, vous pouvez utiliser cette commande pour communiquer avec vos collègues ou votre hiérarchie");
        sender.sendMessage("&e/anonymemsg &a: Commande pour envoyer des messages anonymes dans un contexte RP");
        return false;
    }
}
