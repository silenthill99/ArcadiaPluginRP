package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.DirectionHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DirectionInventory extends AbstractInventory<DirectionHolder> {
    public DirectionInventory() {
        super(DirectionHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        DirectionHolder holder = new DirectionHolder(target);

        ItemStack Discord = new ItemBuilder(Material.PAPER).setName(ChatColor.GREEN + "Discord non-homologué").toItemStack();
        ItemStack Blacklist_staff = new ItemBuilder(Material.GREEN_DYE).setName(ChatColor.GREEN + "Blacklist Staff").toItemStack();
        ItemStack TETE = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).toItemStack();

        Inventory inv = createInventory(holder, 9, "Direction | " + target.getName());
        int slot = 0;
        for (Livres livres : Livres.values())
        {
            holder.livres.put(slot, livres);
            inv.setItem(slot++, new ItemBuilder(Material.BOOK).setName(ChatColor.YELLOW + livres.getName()).toItemStack());
        }
        inv.setItem(5, RETOUR);
        inv.setItem(6, Discord);
        inv.setItem(7, Blacklist_staff);
        inv.setItem(8, TETE);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, DirectionHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Livres livres = holder.livres.get(event.getSlot());

        switch (current.getType())
        {
            case BOOK:
            {
                if (livres.equals(Livres.ERREURS_STAFF))
                    InventoryManager.openInventory(player, InventoryType.ERREUR_STAFF, target);
                else
                    InventoryManager.openInventory(player, InventoryType.RANK_UP);
                break;
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
            case PAPER:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Discord non-homologué");
                break;
            }
            case GREEN_DYE:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Blacklist staff");
                break;
            }
            default:
                break;
        }
    }

    public enum Livres
    {
        ERREURS_STAFF("Erreur staff"),
        RANK_UP("RankUp un joueur")
        ;
        private final String name;

        Livres(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
