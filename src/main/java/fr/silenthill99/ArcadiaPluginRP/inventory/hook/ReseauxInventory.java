package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.ReseauxHolder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ReseauxInventory extends AbstractInventory<ReseauxHolder>
{
    public ReseauxInventory()
    {
        super(ReseauxHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        ReseauxHolder holder = new ReseauxHolder();

        Inventory inv = createInventory(holder, 27, "Réseaux sociaux");
        int slot = 0;
        for (Reseaux reseaux : Reseaux.values())
        {
            holder.reseaux.put(slot, reseaux);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(reseaux.getName()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ReseauxHolder holder)
    {
        Reseaux reseaux = holder.reseaux.get(event.getSlot());
        switch (current.getType())
        {
            case PAPER:
            {
                player.closeInventory();
                TextComponent text = new TextComponent("\n" + reseaux.getMessage() + "\n");
                text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(reseaux.getMessage()).create()));
                text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, reseaux.getMessage()));

                player.spigot().sendMessage(text);
            }
        }
    }

    public enum Reseaux
    {
        DISCORD(ChatColor.YELLOW + "Discord", "https://discord.gg/kqSssvKDce", ChatColor.BLUE + "Pour rejoindre le discord clique ici")
        ;
        private final String name;
        private final String link;
        private final String message;
        //A ne pas confondre avec The Legend of Zelda

        Reseaux(String name, String link, String message)
        {
            this.name = name;
            this.link = link;
            this.message = message;
        }

        public String getName()
        {
            return this.name;
        }

        public String getLink()
        {
            return this.link;
        }

        public String getMessage()
        {
            return this.message;
        }
    }
}
