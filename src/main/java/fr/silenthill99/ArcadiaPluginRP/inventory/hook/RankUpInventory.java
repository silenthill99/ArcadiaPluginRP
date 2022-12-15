package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.RankUpHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RankUpInventory extends AbstractInventory<RankUpHolder>
{
    public RankUpInventory() {
        super(RankUpHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        RankUpHolder holder = new RankUpHolder(target);

        ItemStack Moderateur = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Modérateur" + ChatColor.DARK_GREEN + "]").toItemStack();
        ItemStack Developpeur = new ItemBuilder(Material.BLUE_WOOL).setName(ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "Développeur" + ChatColor.DARK_BLUE + "]").toItemStack();
        ItemStack Administrateur = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "[Administrateur]").toItemStack();
        ItemStack Srt_general = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "[Srt. général]").toItemStack();
        ItemStack UnRank = new ItemBuilder(Material.PAPER).setName("Démettre de ses fonctions").toItemStack();


        Inventory inv = createInventory(holder, 18, "★-Rank | " + target.getName() + "★");
        inv.setItem(1, Moderateur);
        inv.setItem(2, Developpeur);
        inv.setItem(3, Administrateur);
        inv.setItem(4, Srt_general);
        int slot = 7;
        for (Grade gd : Grade.values())
        {
            holder.gd.put(slot, gd);
            inv.setItem(slot++, new ItemBuilder(Material.BOOK).setName(ChatColor.YELLOW + gd.getName()).toItemStack());
        }
        inv.setItem(10, RETOUR);
        for (operateur op : operateur.values())
        {
            holder.operateur.put(op.getSlot(), op);
            inv.setItem(op.getSlot(), new ItemBuilder(Material.RED_DYE).setName(op.getName()).toItemStack());
        }
        inv.setItem(15, UnRank);
        player.openInventory(inv);
    }
    public enum operateur
    {
        METTRE_OP(14, ChatColor.YELLOW + "Mettre opérateur"),
        DEOP(16, ChatColor.YELLOW + "Enlever l'opérateur");

        private final int slot;
        private final String name;

        operateur(int slot, String name)
        {
            this.slot = slot;
            this.name = name;
        }

        public int getSlot()
        {
            return this.slot;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public enum Grade
    {
        JOUEUR("Grade Joueur"),
        DIRECTION("Grade Direction");

        private final String name;

        Grade(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
