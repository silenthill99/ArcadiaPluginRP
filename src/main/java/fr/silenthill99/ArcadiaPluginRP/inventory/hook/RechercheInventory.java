package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.commands.Recherche;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.RechercheHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RechercheInventory extends AbstractInventory<RechercheHolder>
{
    public RechercheInventory()
    {
        super(RechercheHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        RechercheHolder holder = new RechercheHolder();
        Inventory inv = createInventory(holder, 54, "Avis de recherche");
        int slot = 0;
        for (Player target : Recherche.recherche)
        {
            holder.recherche.put(slot, target);
            inv.setItem(slot++, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).setName(target.getName()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, RechercheHolder holder)
    {
        Player target = holder.recherche.get(event.getSlot());
    }
}
