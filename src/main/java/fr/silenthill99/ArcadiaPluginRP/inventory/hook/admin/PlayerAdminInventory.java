package fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.admin.PlayerAdminHolder;
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
        ItemBuilder Sanction_admin = new ItemBuilder(Material.RED_DYE).setName("§aSanction Administrative");
        ItemBuilder Direction = new ItemBuilder(Material.RED_WOOL).setName("§aEspace direction");
        ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

        Inventory inv = createInventory(holder, 9, "§4§lADMIN | "+target.getName());
        int slot = 1;
        for (Books book : Books.values())
        {
            holder.books.put(slot, book);
            inv.setItem(slot++, new ItemBuilder(Material.BOOK).setName(book.getName()).toItemStack());
        }
        inv.setItem(3, Sanction_admin.toItemStack());
        inv.setItem(7, Direction.toItemStack());
        inv.setItem(8, Retour.toItemStack());
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, PlayerAdminHolder holder) {

        OfflinePlayer target = holder.getTarget();
        Books books = holder.books.get(event.getSlot());
        switch (current.getType())
        {
            case BOOK:
            {
                InventoryManager.openInventory(player, books.getType(), target);
                break;
            }
            case RED_DYE:
            {
                InventoryManager.openInventory(player, InventoryType.SANCTION_ADMIN, target);
                break;
            }
            case RED_WOOL:
            {
                if (!player.hasPermission("Arcadia.direction"))
                    player.sendMessage("§a[Direction] §cTu n'as pas accès à ceci");
                else
                    InventoryManager.openInventory(player, InventoryType.DIRECTION);
                break;
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
        }
    }

    public enum Books
    {
        OPTION_ADMIN("§eOption Administrateur", InventoryType.ADMIN_OPTION),
        SET_TEAM("§aSet team", InventoryType.ADMIN_SYSTEM_JOB);

        private final String name;
        private final InventoryType type;
        Books(String name, InventoryType type)
        {
            this.name = name;
            this.type = type;
        }

        public String getName()
        {
            return this.name;
        }

        public InventoryType getType()
        {
            return this.type;
        }
    }
}
