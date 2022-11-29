package fr.silenthill99.ArcadiaPluginRP.commands;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Reseaux implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            Main.getInstance().getLogger().info("Cette commande ne peux pas être exécutée via la console !");
            return false;
        }

        Player player = (Player) sender;

        /*
        ItemBuilder discord = new ItemBuilder(Material.PLAYER_HEAD).setCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ0MjMzN2JlMGJkY2EyMTI4MDk3ZjFjNWJiMTEwOWU1YzYzM2MxNzkyNmFmNWZiNmZjMjAwMDAwMTFhZWI1MyJ9fX0=").setName("§eDiscord");
        ItemBuilder reddit = new ItemBuilder(Material.PLAYER_HEAD).setCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ5YmQ0YjJmYThkYTgyNDdhODJjM2QxZmEyNDY3MTVmOWI2ZDk4Yzc3ODM3NGRhNmVmYzEwYzg5Y2Q2NCJ9fX0=").setName("§6Reddit");
         */
        Inventory reseaux = Bukkit.createInventory(null, 27, "Réseaux sociaux");
        /*
        reseaux.setItem(0, discord.toItemStack());
        reseaux.setItem(1, reddit.toItemStack());
         */
        player.openInventory(reseaux);

        return false;
    }
}
