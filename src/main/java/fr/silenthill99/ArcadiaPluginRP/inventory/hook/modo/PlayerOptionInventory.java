package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.modo.PlayerOptionHolder;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerOptionInventory extends AbstractInventory<PlayerOptionHolder> {
    public PlayerOptionInventory() {
        super(PlayerOptionHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerOptionHolder holder = new PlayerOptionHolder(target);

        ItemBuilder Inventaire = new ItemBuilder(Material.CHEST).setName("§eInventaire du joueur");
        ItemBuilder EnderChest = new ItemBuilder(Material.ENDER_CHEST).setName("§eEc du joueur");
        ItemBuilder Tuer = new ItemBuilder(Material.DIAMOND_SWORD).setName("§eKill le joueur");
        ItemBuilder Se_teleporter = new ItemBuilder(Material.ENDER_PEARL).setName("§eSe téléporter");
        ItemBuilder Tp_le_joueur = new ItemBuilder(Material.ENDER_EYE).setName("§eTéléporter ici");
        ItemBuilder Retourner_le_joueur = new ItemBuilder(Material.TORCH).setName("§eRetourner le joueur");
        ItemBuilder Avertissements = new ItemBuilder(Material.WRITABLE_BOOK).setName("§eAvertissements du joueur");
        ItemBuilder Logs = new ItemBuilder(Material.BLUE_WOOL).setName("§eLogs du joueur");
        ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

        Inventory inv = createInventory(holder, 9, "§2Option pour "+target.getName());
        inv.setItem(0, Inventaire.toItemStack());
        inv.setItem(1, EnderChest.toItemStack());
        inv.setItem(2, Tuer.toItemStack());
        inv.setItem(3, Se_teleporter.toItemStack());
        inv.setItem(4, Tp_le_joueur.toItemStack());
        inv.setItem(5, Retourner_le_joueur.toItemStack());
        inv.setItem(6, Avertissements.toItemStack());
        inv.setItem(7, Logs.toItemStack());
        inv.setItem(8, Retour.toItemStack());
        player.openInventory(inv);
    }
}
