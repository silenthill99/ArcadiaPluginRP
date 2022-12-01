package fr.silenthill99.ArcadiaPluginRP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Panel {


    public static void panel_modo(OfflinePlayer pl, Player sn, Type type){
        sn.closeInventory();
        if (type == Type.ERREURS_STAFF) {

            ItemBuilder Fly_sans_vanish = new ItemBuilder(Material.PAPER).setName("§aFly sans vanish");
            ItemBuilder God_en_WZ = new ItemBuilder(Material.PAPER).setName("§aGod en WZ");
            ItemBuilder Abus_de_pouvoir = new ItemBuilder(Material.PAPER).setName("§aAbus de pouvoir");
            ItemBuilder Abus_de_perms = new ItemBuilder(Material.PAPER).setName("§aAbus de permissions");
            ItemBuilder Hierarchie = new ItemBuilder(Material.PAPER).setName("§aHiérarchie");
            ItemBuilder Freeban = new ItemBuilder(Material.PAPER).setName("§aFreeBan");
            ItemBuilder Freewarn = new ItemBuilder(Material.PAPER).setName("§aFreeWarn");
            ItemBuilder Non_respect_reglement_staff = new ItemBuilder(Material.PAPER).setName("§aNon-Respect | Règlement Staff");
            ItemBuilder Abscence_non_justifiee = new ItemBuilder(Material.PAPER).setName("§eAbsence non justifiée");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

            Inventory erreurs_staff = Bukkit.createInventory(new AdminOptionHolder(pl), 18, "Erreur | " + pl.getName() + "★");
            erreurs_staff.setItem(1, Fly_sans_vanish.toItemStack());
            erreurs_staff.setItem(2, God_en_WZ.toItemStack());
            erreurs_staff.setItem(3, Abus_de_pouvoir.toItemStack());
            erreurs_staff.setItem(4, Abus_de_perms.toItemStack());
            erreurs_staff.setItem(5, Hierarchie.toItemStack());
            erreurs_staff.setItem(6, Freeban.toItemStack());
            erreurs_staff.setItem(7, Freewarn.toItemStack());
            erreurs_staff.setItem(8, Non_respect_reglement_staff.toItemStack());
            erreurs_staff.setItem(10, Abscence_non_justifiee.toItemStack());
            erreurs_staff.setItem(17, Retour.toItemStack());
            sn.openInventory(erreurs_staff);
        }
        if (type == Type.RANK_UP) {

            ItemBuilder Moderateur = new ItemBuilder(Material.LIME_WOOL).setName("§2[§aModérateur§2]");
            ItemBuilder Developpeur = new ItemBuilder(Material.BLUE_WOOL).setName("§1[§9Développeur§1]");
            ItemBuilder Administrateur = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "[Administrateur]");
            ItemBuilder Srt_general = new ItemBuilder(Material.ORANGE_WOOL).setName("§6[Srt. général]");
            ItemBuilder Mettre_op = new ItemBuilder(Material.RED_DYE).setName("§eMettre opérateur");
            ItemBuilder UnRank = new ItemBuilder(Material.PAPER).setName("Démettre de ses fonctions");
            ItemBuilder DeOP = new ItemBuilder(Material.RED_DYE).setName("§eEnlever l'opérateur");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");
            ItemBuilder Joueur = new ItemBuilder(Material.BOOK).setName("§aGrade Joueur");
            ItemBuilder Direction = new ItemBuilder(Material.BOOK).setName("§eGrade direction");

            Inventory rank_up = Bukkit.createInventory(new AdminOptionHolder(pl), 18, "★-Rank | " + pl.getName() + "★");
            rank_up.setItem(1, Moderateur.toItemStack());
            rank_up.setItem(2, Developpeur.toItemStack());
            rank_up.setItem(3, Administrateur.toItemStack());
            rank_up.setItem(4, Srt_general.toItemStack());
            rank_up.setItem(7, Direction.toItemStack());
            rank_up.setItem(8, Joueur.toItemStack());
            rank_up.setItem(10, Retour.toItemStack());
            rank_up.setItem(14, Mettre_op.toItemStack());
            rank_up.setItem(15, UnRank.toItemStack());
            rank_up.setItem(16, DeOP.toItemStack());
            sn.openInventory(rank_up);
        }
        if (type == Type.DIRECTION_SYSTEME_GRADE) {

            ItemBuilder Youtube = new ItemBuilder(Material.PAPER).setName("§cYou§fTube");
            ItemBuilder Vip = new ItemBuilder(Material.PAPER).setName("§eVIP");
            ItemBuilder VipPlus = new ItemBuilder(Material.PAPER).setName("§eVIP+");
            ItemBuilder VipPlusPlus = new ItemBuilder(Material.PAPER).setName("§eVIP++");
            ItemBuilder Liste = new ItemBuilder(Material.YELLOW_DYE).setName("§aActualiser la liste des jobs");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

            Inventory direction_system_grade = Bukkit.createInventory(new AdminOptionHolder(pl), 9, "Grade | " + pl.getName());
            direction_system_grade.setItem(1, Youtube.toItemStack());
            direction_system_grade.setItem(2, Vip.toItemStack());
            direction_system_grade.setItem(3, VipPlus.toItemStack());
            direction_system_grade.setItem(4, VipPlusPlus.toItemStack());
            direction_system_grade.setItem(7, Liste.toItemStack());
            direction_system_grade.setItem(8, Retour.toItemStack());
            sn.openInventory(direction_system_grade);
        }
        if (type == Type.DIRECTION_SYSTEME_RANK) {

            ItemBuilder Resp_equipe = new ItemBuilder(Material.YELLOW_WOOL).setName("§6[§eResp. équipe§6]");
            ItemBuilder Directeur = new ItemBuilder(Material.RED_WOOL).setName("§4[§cDirection§4]");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

            Inventory direction_systeme_rank = Bukkit.createInventory(new AdminOptionHolder(pl), 9, "Rank | " + pl.getName());
            direction_systeme_rank.setItem(1, Resp_equipe.toItemStack());
            direction_systeme_rank.setItem(2, Directeur.toItemStack());
            direction_systeme_rank.setItem(3, Retour.toItemStack());
            sn.openInventory(direction_systeme_rank);
        }
    }
    public static void teleportation(Player player) {

        ItemBuilder Aeroport = new ItemBuilder(Material.FILLED_MAP).setName("Aéroport");
        ItemBuilder MDS = new ItemBuilder(Material.FILLED_MAP).setName("Bâtiment staff");
        ItemBuilder Commissariat_2 = new ItemBuilder(Material.FILLED_MAP).setName("Commissariat ville n°2");
        ItemBuilder Mairie = new ItemBuilder(Material.FILLED_MAP).setName("Mairie");
        ItemBuilder Prison = new ItemBuilder(Material.FILLED_MAP).setName("Prison");
        ItemBuilder Tribunal = new ItemBuilder(Material.FILLED_MAP).setName("Tribunal");

        Inventory teleportation = Bukkit.createInventory(null, 54, "Se téléporter");
        teleportation.setItem(0, Aeroport.toItemStack());
        teleportation.setItem(1, MDS.toItemStack());
        teleportation.setItem(2, Commissariat_2.toItemStack());
        teleportation.setItem(3, Mairie.toItemStack());
        teleportation.setItem(4, Prison.toItemStack());
        teleportation.setItem(5, Tribunal.toItemStack());
        player.openInventory(teleportation);

    }
    public enum Type
    {
        AVERTIR, BANNIR, EXPULSER, BAN_ADMIN, ERREURS_STAFF, RANK_UP, DIRECTION_SYSTEME_GRADE, DIRECTION_SYSTEME_RANK;
    }
}