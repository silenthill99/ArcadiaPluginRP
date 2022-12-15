package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
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
        switch (current.getType()) {
            case PAPER:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " " + warns.getName());
                break;
            /*
            case "§2NoFearRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " NoFearRP");
                break;
            case "§2NoPainRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " NoPainRP");
                break;
            case "§2New Life Rules":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " NLR");
                break;
            case "§2Serious RP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Serious RP Non respecté");
                break;
            case "§2Conduite NoRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Conduite non RolePlay");
                break;
            case "§2Métagaming":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Métagaming");
                break;
            case "§2Hrp en RP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Hrp en RP");
                break;
            case "§2Hrp en RP (vocal)":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Hrp en RP vocal");
                break;
            case "§2NoRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " NoRP");
                break;
            case "§2Braquage rue":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Braquage rue");
                break;
            case "§2Braquage solo":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Braquage solo");
                break;
            case "§2Occupation quartier ou bâtiment":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Occupation d'un quartier ou bâtiment");
                break;
            case "§2Objet illégal en métier légal":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Objet illégal en métier légal");
                break;
            case "§2Déco scène RP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Déco en scène RP");
                break;
            case "§2FreePunch":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " FreePunch");
                break;
            case "§2TP scène RP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Tp en scène RP");
                break;
            case "§2Déco combat":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Déco en combat");
                break;
            case "§2Faux ticket":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Faux ticket");
                break;
            case "§2Insulte HRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Insultes HRP");
                break;
            case "§2FreeShot":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " FreeShot");
                break;
            case "§2FreeJail":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " FreeJail");
                break;
            case "§2FreeTaze":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " FreeTaze");
                break;
            case "§2Freekill":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Freekill");
                break;
            case "§2Bunny hop":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Bunny hop");
                break;
            case "§2Braquage banque sans FDO":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Braquage banque sans FDO");
                break;
            case "§2Véhicule bâtiment":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + "Véhicule bâtiment");
                break;
            case "§2Arme en métier légal":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Armes en métier légal");
                break;
            case "§2Provocation":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Provocation HRP");
                break;
            case "§2Skin NoRP":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Skin NoRP");
                break;
            case "§2No FairPlay":
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " No FairPlay");
                break;

             */
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            default:
                break;
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
