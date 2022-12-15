package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.TeleportationHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TeleportationInventory extends AbstractInventory<TeleportationHolder>
{
    public TeleportationInventory()
    {
        super(TeleportationHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        TeleportationHolder holder = new TeleportationHolder();
        Inventory inv = createInventory(holder,54, "Se téléporter");
        int slot = 0;
        for (Teleportation teleportation : Teleportation.values())
        {
            holder.teleportation.put(slot, teleportation);
            inv.setItem(slot++, new ItemBuilder(Material.FILLED_MAP).setName(teleportation.getName()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, TeleportationHolder holder)
    {
        Teleportation teleportation = holder.teleportation.get(event.getSlot());
        switch (current.getType())
        {
            case FILLED_MAP:
            {
                player.teleport(teleportation.getLoc());
                break;
            }
            default:
            {
                break;
            }
        }
    }

    public enum Teleportation
    {
        AEROPORT("aéroport", new Location(Bukkit.getWorld("world"), 2114, 4.5, -115, -90f, 0f)),
        MDS("Bâtiment staff", new Location(Bukkit.getWorld("world"), -59, 4.5, 522, 90f, 0f)),
        COMMISSARIAT("Commissariat ville n°2", new Location(Bukkit.getWorld("world"), 44.59, 4, -220.474, -179.6f, 0)),
        PRISON("Prison", new Location(Bukkit.getWorld("world"), 532.632, 4, 909.268, -4.7f, 0)),
        TRIBUNAL("Tribunal", new Location(Bukkit.getWorld("world"), 419.091, 4, 90.694, 180f, 0))
        ;
        private final String name;
        private final Location loc;

        Teleportation(String name, Location loc)
        {
            this.name = name;
            this.loc = loc;
        }

        public String getName()
        {
            return this.name;
        }

        public Location getLoc()
        {
            return this.loc;
        }
    }
}
