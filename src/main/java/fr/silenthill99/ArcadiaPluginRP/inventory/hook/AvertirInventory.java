package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.AvertirHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Currency;

public class AvertirInventory extends AbstractInventory<AvertirHolder>
{
    public AvertirInventory()
    {
        super(AvertirHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AvertirHolder holder = new AvertirHolder(target);

        ItemStack Avertissement_permanent = new ItemBuilder(Material.GREEN_DYE).setName(ChatColor.GREEN + "Avertissement Permanent").toItemStack();
        Inventory inv = createInventory(holder, 54, ChatColor.DARK_GREEN + "Avertir " + target.getName());
        for (Warns warns : Warns.values())
        {
            holder.warns.put(warns.getSlot(), warns);
            inv.setItem(warns.getSlot(), new ItemBuilder(Material.PAPER).setName(ChatColor.GREEN + warns.getName()).toItemStack());
        }
        inv.setItem(44, Avertissement_permanent);
        inv.setItem(53, RETOUR);
        player.openInventory(inv);
    }


    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, AvertirHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Warns warns = holder.warns.get(event.getSlot());
        switch (current.getType())
        {
            case PAPER:
            {
                switch (warns)
                {
                    default:
                    {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " " + warns.getName());
                        break;
                    }
                }
            }
            default:
            {
                break;
            }
        }
    }

    public enum Warns
    {
        NO_FEAR(0, "NoFear"),
        NO_PAIN(1, "NoPainRP"),
        NLR(2, "New Life Rules"),
        SERIOUS_RP(3, "Serious RP"),
        CONDUITE_NO_RP(4, "Conduite NoRP"),
        METAGAMING(5, "Métagaming"),
        HRP_EN_RP(6, "Hrp en RP"),
        HRP_EN_RP_VOCAL(7, "Hrp en RP (vocal)"),
        NORP(8, "NoRP"),
        BRAQUAGE_RUE(9, "Braquage rue"),
        BRAQUAGE_SOLO(10, "Braquage solo"),
        OCCUPATION(11, "Occupation quartier ou bâtiment"),
        OBJET_ILLEGAL_EN_METIER_LEGAL(12, "Objet illégal en métier légal"),
        DECO_SCENE_RP(14, "Déco scène RP"),
        FREEPUNCH(15, "FreePunch"),
        FAIRPLAY(16, "No FairPlay"),
        TP_SCENE_RP(27, "TP Scène RP"),
        DECO_COMBAT(28, "Déco combat"),
        FAUX_TICKET(29, "Faux ticket"),
        INSULTE_HRP(30, "Insulte HRP"),
        FREESHOT(32, "FreeShot"),
        FREEJAIL(33, "FreeJail"),
        FREETAZE(34, "FreeTaze"),
        FREEKILL(45, "Freekill"),
        BUNNY_HOP(46, "Bunny hop"),
        BRAQUAGE_BANQUE(47, "Braquage banque sans FDO"),
        VEHICULE(48, "Véhicule bâtiment"),
        ARME(49, "Armes en métier légal"),
        PROVOCATION(50, "Provocation"),
        SKIN(51, "Skin NoRP")
        ;
        private final int slot;
        private final String name;

        Warns(int slot, String name)
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
}
