package fr.silenthill99.ArcadiaPluginRP;

import fr.silenthill99.ArcadiaPluginRP.commands.Options;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.net.ContentHandler;

@SuppressWarnings("deprecation")
public class Events implements Listener {

    LuckPerms api = LuckPermsProvider.get();
    ItemBuilder Argent = new ItemBuilder(Material.GOLD_INGOT).setName("100 €");

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block current = event.getClickedBlock();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            switch (current.getType()){
                case CAULDRON:
                    Inventory poubelle = Bukkit.createInventory(null, 9, "Poubelle");
                    player.openInventory(poubelle);
                    break;
                /**
                case DROPPER:
                    event.setCancelled(true);

                    ItemBuilder deposer = new ItemBuilder(Material.GOLD_INGOT).setName("Déposer de l'argent");
                    ItemBuilder retirer = new ItemBuilder(Material.GOLD_INGOT).setName("Retirer de l'argent");

                    Inventory atm = Bukkit.createInventory(null, 27, "Distributeur");
                    atm.setItem(10, deposer.toItemStack());
                    atm.setItem(16, retirer.toItemStack());
                    player.openInventory(atm);
                 */
                default:
                    break;
            }
        } else if (event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (!player.getItemInHand().equals(Material.BRICK)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = api.getUserManager().getUser(player.getName());
        try {
            event.setJoinMessage("§2[+] " + user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName());
        } catch (IllegalArgumentException exception) {
            event.setJoinMessage("§2[+] " + player.getName());
        }
        if (!player.hasPlayedBefore()) {
            Bukkit.broadcastMessage("\n"+Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + "  " + player.getName() + " §bVient de débarquer à AnamaraCity. Veuillez lui souhaiter la bienvenue !\n");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco set " + player.getName() + " 3000");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = api.getUserManager().getUser(player.getName());
        try {
            event.setQuitMessage("§4[-] " + user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName());
        } catch (IllegalArgumentException exception) {
            event.setQuitMessage("§4[-] " + player.getName());
        }
    }

    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity.getName().equalsIgnoreCase("MacDo")){

            ItemBuilder steak = new ItemBuilder(Material.COOKED_BEEF).setLore("Prix : 15€");
            ItemBuilder tarte = new ItemBuilder(Material.PUMPKIN_PIE).setLore("Prix : 20€");

            Inventory menu = Bukkit.createInventory(null, 9, "Acheter à manger");
            menu.setItem(0, steak.toItemStack());
            menu.setItem(1, tarte.toItemStack());
            player.openInventory(menu);
            return;
        }

        if (player.hasPermission("group.policier") && !player.isOp()) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                if (!target.getName().equalsIgnoreCase("Emrolehero") && !target.getName().equalsIgnoreCase("dealer") && !target.getName().equalsIgnoreCase("Secrétaire") && !target.getName().equalsIgnoreCase("pôle emploi")) {

                    ItemBuilder Fouiller = new ItemBuilder(Material.BLAZE_ROD).setName("§aFouiller");

                    //Inventory policier = Bukkit.createInventory(new AdminOptionHolder(target), 27, "Menu policier");
                    //policier.setItem(0, Fouiller.toItemStack());
                    //player.openInventory(policier);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();

        /**
        if (event.getView().getTitle().startsWith("Distributeur")) {
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "Déposer de l'argent":
                    if (player.getInventory().contains(Argent.toItemStack())) {
                        player.getInventory().removeItem(Argent.toItemStack());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " 100");
                    } else {
                        player.sendMessage("§4Vous n'avez pas d'argent sur vous !");
                    }
                    break;
                case "Retirer de l'argent":
                    if (Main.getInstance().economy.has(player, 100)) {
                        player.getInventory().addItem(Argent.toItemStack());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " 100");
                    } else {
                        player.sendMessage("§4Tu n'as pas assez d'argent sur ton compte !");
                    }
                    break;
            }
        }
         */
        if (event.getView().getTitle().startsWith("Panel de ")) {
            event.setCancelled(true);

            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();

            switch (current.getType()) {
                case BLAZE_POWDER:
                    if (!player.hasPermission("admin.staff")) {
                        player.sendMessage("&a[Administration] &cVous n'avez pas accès à ceci");
                    } else {
                        Panel.panel_modo(target, player, "admin");
                    }
                    break;
                case STONE:
                    Panel.panel_modo(target, player, "sanction");
                    break;
                case BLAZE_ROD:
                    Panel.panel_modo(target, player, "options");
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§4§lADMIN | ")) {
            event.setCancelled(true);

            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();

            switch (current.getType()) {
                case BOOK:
                    if (current.getItemMeta().getDisplayName().equals("§eOption Administrateur")) {
                        Panel.panel_modo(target, player, "admin_option");
                    } else if (current.getItemMeta().getDisplayName().equals("§aSet team")) {
                        Panel.panel_modo(target, player, "admin_system_job");
                    }
                    break;
                case RED_DYE:
                    Panel.panel_modo(target, player, "sanction_admin");
                    break;
                case RED_WOOL:
                    if (!player.hasPermission("Arcadia.direction")) {
                        player.sendMessage("§a[Direction] §cTu n'as pas accès à ceci");
                    } else {
                        Panel.panel_modo(target, player, "espace direction");
                    }
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§2Sanctionner ")) {

            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();

            event.setCancelled(true);
            switch (current.getType()){
                case GREEN_DYE:
                    Panel.panel_modo(target, player, "avertir");
                    break;
                case RED_DYE:
                    Panel.panel_modo(target, player, "bannir");
                    break;
                case BLUE_DYE:
                    Panel.panel_modo(target, player, "expulser");
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§2Option pour ")) {

            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();

            event.setCancelled(true);
            switch (current.getType()) {
                case CHEST:
                    Bukkit.dispatchCommand(player, "invsee "+target.getName());
                    break;
                case ENDER_CHEST:
                    Bukkit.dispatchCommand(player, "ec "+target.getName());
                    break;
                case DIAMOND_SWORD:
                    if (!target.isOnline()) {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix") + " §cLe joueur n'est pas en ligne !");
                    } else {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "kill "+target.getName());
                    }
                    break;
                case ENDER_PEARL:
                    if (!target.isOnline()) {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix") + " §cLe joueur n'est pas en ligne !");
                    } else {
                        player.closeInventory();
                        Location tloc = target.getPlayer().getLocation();
                        player.teleport(tloc);
                    }
                    break;
                case ENDER_EYE:
                    if (!target.isOnline()) {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix") + " §cLe joueur n'est pas en ligne !");
                    } else {
                        player.closeInventory();
                        Location ploc = player.getLocation();
                        target.getPlayer().teleport(ploc);
                    }
                    break;
                case TORCH:
                    if (!target.isOnline()) {
                        player.sendMessage(Main.getInstance().getConfig().getString("options.prefix") + " §cLe joueur n'est pas en ligne !");
                    } else {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "spawn "+target.getName());
                    }
                    break;
                case WRITABLE_BOOK:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "warns "+target.getName());
                    break;
                case BLUE_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "logs "+target.getName());
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§4Option Admin | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case BOOK:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "history " + target.getName());
                    break;
                case BLAZE_POWDER:
                    player.closeInventory();
                    target.getPlayer().getInventory().clear();
                    break;
                case ENDER_PEARL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "check " + target.getName());
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Set team ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "§aActualiser la liste des jobs":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "list");
                    break;
                case "§cRetour":
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§dSanctionner ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case BOOK:
                    Panel.panel_modo(target, player, "bannissements administratifs");
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Direction | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case BOOK:
                    if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eErreur staff - Avertissement permanent")) {
                        Panel.panel_modo(target, player, "erreurs staff");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eRankUp un joueur")) {
                        Panel.panel_modo(target, player, "RankUP");
                    }
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                case PAPER:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "warn " + target.getName() + " Discord non-homologué");
                    break;
                case GREEN_DYE:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "warn " + target.getName() + " Blacklist staff");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§2Avertir ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
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
                case "§cRetour":
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§4Bannir ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "§6Insulte staff":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 3d Insulte Staff");
                    break;
                case "§6Freekill involontaire":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 24h Freekill involontaire");
                    break;
                case "§6Freekill Volontaire":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 15d Freekill volontaire");
                    break;
                case "§6Freekill - Aveu":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 10d Freekill avoué à la modération");
                    break;
                case "§6Freekill - Stuff rendu":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 7d Freekill - Stuff rendu");
                    break;
                case "§6Déco inter":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 24h Déco Inter");
                    break;
                case "§6Mensonge Staff":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 48h Mensonge staff");
                    break;
                case "§6Refus d'inter":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 72h Refus d'interaction avec le staff");
                    break;
                case "§6Faux ticket massif":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 5d Modération | Faux ticket en masse");
                    break;
                case "§6Spam massif":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 10m Spam massif - Nous vous avons prévenu");
                    break;
                case "§6Intrusion bâtiment staff":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 1h Intrusion bâtiment staff");
                    break;
                case "§6Cheat":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 3mo Cheat interdit !");
                    break;
                case "§6UseBug":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 3d Utilisation d'un usebug interdit");
                    break;
                case "§6Anti-AFK":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 48h Utilisation d'un UseBug interdit");
                    break;
                case "§6Tentative FK modo":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 48h Tentative de Freekill envers un staff");
                    break;
                case "§cNoRP en masse":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " NoRP en masse");
                    break;
                case "§cFreekill en masse":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Freekill en masse");
                    break;
                case "§cMenace DDoS":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Menace DDoS");
                    break;
                case "§cTroll":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Troll");
                    break;
                case "§cSuspicion de duplication":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Suspicion de duplication");
                    break;
                case "§cCoup d'état serveur":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Menace de coup d'état envers l'infrastructure");
                    break;
                case "§cDénigrement de serveur":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Dénigrement de serveur");
                    break;
                case "§cInsulte serveur":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Insultes serveur");
                    break;
                case "§cRetour":
                    Panel.panel_modo(target, player, "menu");
                    break;
                case "§6NoRP [Récidive | 600 min]":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 600min Action non Rôle-Play (Récidive)");
                    break;
                case "§6NoRP [Récidive | 120 min]":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 120min Action non Rôle-Play (Récidive)");
                    break;
                case "§6NoRP [Récidive | 60 min]":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 60min Action non Rôle-Play (Récidive)");
                    break;
                case "§6Propos obscènes (Grave)":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 8h Propos Obscènes");
                    break;
                case "§6Actes obscènes (Grave)":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 8h Actes Obscènes");
                    break;
                case "§cContournement de sanctions":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Contournement de sanctions - Double compte");
                    break;
                case "§cUsurpation d'identité":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Usurpation d'identité");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("§3Expulser " )) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "§bSpam":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " Spam | Prochaine fois, vous serez banni pendant 10 minutes");
                    break;
                case "§bFlood":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " Flood en masse");
                    break;
                case "§bSpam micro":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " Spam Micro interdit");
                    break;
                case "§bJoueur AFK":
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " Joueur AFK depuis 5 minutes");
                    break;
                case "§cRetour":
                    player.closeInventory();
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Erreur | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case PAPER:
                    player.closeInventory();
                    if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aFly sans vanish")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Fly sans le vanish");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("erreurs staff")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | God en WZ");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aAbus de pouvoir")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Abus de pouvoir");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aAbus de permissions")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Abus de permissions");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aHiérarchie")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Non respect de la hiérarchie");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aFreeBan")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | FreeBan");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aFreeWarn")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | FreeWarn");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aNon-Respect | Règlement Staff")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Non respect du règlement staff");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eAbsence non justifiée")) {
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | Absence non justifiée");
                    }
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("★-Rank | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case LIME_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set moderateur");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.moderation");
                    break;
                case BLUE_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set développeur");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.developpeur");
                    break;
                case RED_DYE:
                    if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eMettre opérateur")) {
                        if (target.isOp()) {
                            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c" + target.getName()+" est déjà opérateur du serveur !");
                            return;
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + target.getName());
                        player.sendMessage("§a" + target.getName() + " est désormais opérateur !");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eEnlever l'opérateur")) {
                        if (!target.isOp()) {
                            player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §c" + player.getName() + " n'est pas opérateur !");
                            return;
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "deop " + target.getName());
                        player.sendMessage("§4" + target.getName() + " a perdu ses privilèges d'opérateur !");
                    }
                    break;
                case LIGHT_BLUE_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set administrateur");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.administrateur");
                    break;
                case ORANGE_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set secretaire");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.secretaire");
                    break;
                case PAPER:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                    Bukkit.dispatchCommand(player, "vanish " + target.getName() + " off");
                    Bukkit.dispatchCommand(player, "fly " + target.getName() + " off");
                    Bukkit.dispatchCommand(player, "god " + target.getName() + " off");
                    if (!target.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
                        target.getPlayer().setGameMode(GameMode.ADVENTURE);
                    }
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " unrank");
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                case BOOK:
                    if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§aGrade Joueur")) {
                        Panel.panel_modo(target, player, "direction system grade");
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§eGrade direction")) {
                        Panel.panel_modo(target, player, "direction système rank");
                    }
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Grade | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case YELLOW_DYE:
                    player.closeInventory();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Rank | ")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case YELLOW_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set responsables");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.responsable");
                    break;
                case RED_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set directeur");
                    Bukkit.dispatchCommand(player, "list");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set Arcadia.direction");
                    break;
                case NETHER_STAR:
                    Panel.panel_modo(target, player, "menu");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Acheter à manger")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case COOKED_BEEF:
                    if (Main.getInstance().economy.has(player, 15)){
                        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " 15");
                    } else {
                        player.sendMessage("§cVous n'avez pas assez d'argent !");
                    }
                    break;
                case PUMPKIN_PIE:
                    if (Main.getInstance().economy.has(player, 20)) {
                        player.getInventory().addItem(new ItemStack(Material.PUMPKIN_PIE));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " 20");
                    } else {
                        player.sendMessage("§cVous n'avez pas assez d'argent !");
                    }
                    break;
                default:
                    break;
            }
            return;
        }
        if (event.getView().getTitle().startsWith("Se téléporter")) {
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "Aéroport":
                    Location aeroport = new Location(player.getWorld(), 2114, 4.5, -115, -90f, 0f);
                    player.teleport(aeroport);
                    break;
                case "Bâtiment staff":
                    Location MDS = new Location(player.getWorld(), -59, 4.5, 522, 90f, 0f);
                    player.teleport(MDS);
                    break;
                case "Commissariat ville n°2":
                    Location Commissariat_2 = new Location(player.getWorld(), 444.59, 4, -220.474, -179.6f, -1.4f);
                    player.teleport(Commissariat_2);
                    break;
                case "Prison":
                    Location prison = new Location(player.getWorld(), 532.632, 4, 909.268, -4.7f, -11.7f);
                    player.teleport(prison);
                    break;
                case "Tribunal":
                    Location tribunal = new Location(player.getWorld(), 419.091, 4, 90.694, 179.9f, -16.7f);
                    player.teleport(tribunal);
                    break;
                case "Mairie":
                    Bukkit.dispatchCommand(player, "spawn");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Réseaux sociaux")) {
            event.setCancelled(true);
            switch(current.getItemMeta().getDisplayName()) {
                case "§eDiscord":
                    player.closeInventory();
                    TextComponent discord = new TextComponent(ChatColor.BLUE + "Pour rejoindre le discord clique ici");
                    discord.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique ici pour rejoindre le discord").create()));
                    discord.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/Nq62557DUb"));

                    player.sendMessage("\n");
                    player.spigot().sendMessage(discord);
                    player.sendMessage("\n");
                    break;
                case "§6Reddit":
                    player.closeInventory();
                    TextComponent reddit = new TextComponent("§6Pour rejoindre le subreddit clique ici");
                    reddit.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique ici pour rejoindre le subReddit").create()));
                    reddit.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.reddit.com/r/ArcadiaHeberge/"));

                    player.sendMessage("\n");
                    player.spigot().sendMessage(reddit);
                    player.sendMessage("\n");
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Options")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case RED_DYE:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist off");
                    player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §aVous avez désactivé la vhitelist");
                    event.getClickedInventory().setItem(0, Options.whitelist_off.toItemStack());
                    break;
                case GREEN_DYE:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist on");
                    player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Vous avez activé la whitelist");
                    event.getClickedInventory().setItem(0, Options.whitelist_on.toItemStack());
                    break;
                case RED_WOOL:
                    player.closeInventory();
                    Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §aArrêt imminent du serveur dans 30 secondes !");
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                            if (!Bukkit.getServer().hasWhitelist()) {
                                Bukkit.getServer().setWhitelist(true);
                            }
                        }
                    }, 600);
                    break;
                default:
                    break;
            }
        }
        if (event.getView().getTitle().startsWith("Menu policier")) {
            AdminOptionHolder holder = (AdminOptionHolder) event.getClickedInventory().getHolder();
            Player target = (Player) holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType()) {
                case BLAZE_ROD:
                    Inventory inventaire = Bukkit.createInventory(target, 36, "Fouiller " + target.getName());
                    for (int slot = 0; slot <= 35; slot++) {
                        if (!target.getInventory().getItem(slot).equals(Material.AIR)) {
                            inventaire.setItem(slot, target.getInventory().getItem(slot));
                        }
                    }
                    player.openInventory(inventaire);
                    break;
                default:
                    break;
            }
        }
    }
}