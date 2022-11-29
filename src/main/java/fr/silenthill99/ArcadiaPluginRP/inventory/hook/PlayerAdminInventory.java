package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.PlayerAdminHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerAdminInventory extends AbstractInventory<PlayerAdminHolder> {
    public PlayerAdminInventory()
    {
        super(PlayerAdminHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerAdminHolder holder = new PlayerAdminHolder(target);
        ItemBuilder Options_administrateur = new ItemBuilder(Material.BOOK).setName("§eOption Administrateur");
        ItemBuilder Set_team = new ItemBuilder(Material.BOOK).setName("§aSet team");
        ItemBuilder Sanction_admin = new ItemBuilder(Material.RED_DYE).setName("§aSanction Administrative");
        ItemBuilder Direction = new ItemBuilder(Material.RED_WOOL).setName("§aEspace direction");
        ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

        Inventory inv = createInventory(holder, 9, "§4§lADMIN | "+target.getName());
        inv.setItem(1, Options_administrateur.toItemStack());
        inv.setItem(2, Set_team.toItemStack());
        inv.setItem(3, Sanction_admin.toItemStack());
        inv.setItem(7, Direction.toItemStack());
        inv.setItem(8, Retour.toItemStack());
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, PlayerAdminHolder holder) {
        OfflinePlayer target = holder.getTarget();
    }
}
