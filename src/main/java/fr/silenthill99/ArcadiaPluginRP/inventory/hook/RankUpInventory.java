package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.RankUpHolder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
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

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, RankUpHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        operateur op = holder.operateur.get(event.getSlot());
        Grade grade = holder.gd.get(event.getSlot());
        switch (current.getType())
        {
            case LIME_WOOL:
            {
                role(player, target, "moderateur", "Arcadia.moderation");
                break;
            }
            case BLUE_WOOL:
            {
                role(player, target, "développeur", "Arcadia.developpeur");
                break;
            }
            case LIGHT_BLUE_WOOL:
            {
                role(player, target, "administrateur", "Arcadia.administrateur");
                break;
            }
            case ORANGE_WOOL:
            {
                role(player, target, "secretaire", "Arcadia.secretaire");
                break;
            }
            case RED_DYE:
            {
                if (op.equals(operateur.METTRE_OP))
                {
                    if (target.isOp())
                    {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c" + target.getName()+" est déjà opérateur du serveur !");
                        return;
                    }
                    target.setOp(true);
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais opérateur !");
                }
                else
                {
                    if (!target.isOp())
                    {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c" + player.getName() + " n'est pas opérateur !");
                        return;
                    }
                    target.setOp(false);
                    player.sendMessage("§4" + target.getName() + " a perdu ses privilèges d'opérateur !");
                }
                break;
            }
            case PAPER:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                Bukkit.dispatchCommand(player, "vanish " + target.getName() + " off");
                Bukkit.dispatchCommand(player, "fly " + target.getName() + " off");
                Bukkit.dispatchCommand(player, "god " + target.getName() + " off");
                if (!target.getPlayer().getGameMode().equals(GameMode.ADVENTURE))
                    target.getPlayer().setGameMode(GameMode.ADVENTURE);
                Bukkit.dispatchCommand(player, "kick " + target.getName() + " unrank");
                break;
            }
            case BOOK:
            {
                if (grade.equals(Grade.JOUEUR))
                    InventoryManager.openInventory(player, InventoryType.GRADE, target);
                else
                    InventoryManager.openInventory(player, InventoryType.RANK_UP_SUPER, target);
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
            default:
                break;
        }
    }

    public void role(Player player, OfflinePlayer target, String grade, String permission)
    {
        player.closeInventory();
        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set " + grade);
        Bukkit.dispatchCommand(player, "list");
        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set " + permission);
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
