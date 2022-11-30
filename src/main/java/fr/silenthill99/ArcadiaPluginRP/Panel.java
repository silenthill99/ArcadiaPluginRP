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
        if (type == Type.BANNIR) {

            ItemBuilder Insultes_staff = new ItemBuilder(Material.PAPER).setName("§6Insulte staff").setLore("Durée : 3 jours");
            ItemBuilder Freekill_involontaire = new ItemBuilder(Material.PAPER).setName("§6Freekill involontaire").setLore("Durée : 24 heures");
            ItemBuilder Freekill_volontaire = new ItemBuilder(Material.PAPER).setName("§6Freekill Volontaire").setLore("Durée : 15 jours");
            ItemBuilder Freekill_Aveu = new ItemBuilder(Material.PAPER).setName("§6Freekill - Aveu").setLore("Durée : 10 jours");
            ItemBuilder Freekill_Stuff_Rendu = new ItemBuilder(Material.PAPER).setName("§6Freekill - Stuff rendu").setLore("Durée : 1 semaine");
            ItemBuilder Deco_inter = new ItemBuilder(Material.PAPER).setName("§6Déco inter").setLore("Durée : 24 heures");
            ItemBuilder Mensonge_staff = new ItemBuilder(Material.PAPER).setName("§6Mensonge Staff").setLore("Durée : 2 jours");
            ItemBuilder Refus_d_inter = new ItemBuilder(Material.PAPER).setName("§6Refus d'inter").setLore("Durée : 3 jours");
            ItemBuilder Faux_ticket_massif = new ItemBuilder(Material.PAPER).setName("§6Faux ticket massif").setLore("Durée : 5 jours");
            ItemBuilder Spam_massif = new ItemBuilder(Material.PAPER).setName("§6Spam massif").setLore("Durée : 10 minutes");
            ItemBuilder Intrusion_batiment_staff = new ItemBuilder(Material.PAPER).setName("§6Intrusion bâtiment staff").setLore("Durée : 1 heure");
            ItemBuilder Cheat = new ItemBuilder(Material.PAPER).setName("§6Cheat").setLore("Durée : 3 mois");
            ItemBuilder UseBug = new ItemBuilder(Material.PAPER).setName("§6UseBug").setLore("Durée : 3 jours");
            ItemBuilder Anti_AFK = new ItemBuilder(Material.PAPER).setName("§6Anti-AFK").setLore("Durée : 2 jours");
            ItemBuilder Tentative_fk_modo = new ItemBuilder(Material.PAPER).setName("§6Tentative FK modo").setLore("Durée : 2 jours");
            ItemBuilder NoRP_en_masse = new ItemBuilder(Material.PAPER).setName("§cNoRP en masse");
            ItemBuilder Freekill_en_masse = new ItemBuilder(Material.PAPER).setName("§cFreekill en masse");
            ItemBuilder Menace_DDOS = new ItemBuilder(Material.PAPER).setName("§cMenace DDoS");
            ItemBuilder Troll = new ItemBuilder(Material.PAPER).setName("§cTroll");
            ItemBuilder Duplication = new ItemBuilder(Material.PAPER).setName("§cSuspicion de duplication");
            ItemBuilder Coup_detat = new ItemBuilder(Material.PAPER).setName("§cCoup d'état serveur");
            ItemBuilder Denigrement = new ItemBuilder(Material.PAPER).setName("§cDénigrement de serveur");
            ItemBuilder Insulte_serveur = new ItemBuilder(Material.PAPER).setName("§cInsulte serveur");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");
            ItemBuilder NoRP_recidive_600_min = new ItemBuilder(Material.PAPER).setName("§6NoRP [Récidive | 600 min]");
            ItemBuilder NoRP_recidive_120_min = new ItemBuilder(Material.PAPER).setName("§6NoRP [Récidive | 120 min]");
            ItemBuilder NoRP_recidive_60_min = new ItemBuilder(Material.PAPER).setName("§6NoRP [Récidive | 60 min]");
            ItemBuilder Propos_obscenes = new ItemBuilder(Material.PAPER).setName("§6Propos obscènes (Grave)").setLore("Durée : 8 heures");
            ItemBuilder Actes_obscenes = new ItemBuilder(Material.PAPER).setName("§6Actes obscènes (Grave)").setLore("Durée : 8 heures");
            ItemBuilder Bannissements_temporaires = new ItemBuilder(Material.ORANGE_DYE).setName("§6Bannissement temporaire");
            ItemBuilder Bannissements_permanents = new ItemBuilder(Material.RED_DYE).setName("§cBannissement permanent");

            Inventory bannissements = Bukkit.createInventory(new AdminOptionHolder(pl), 54, "§4Bannir " + pl.getName());
            // bannissements.setItem(1, Insultes_staff.toItemStack());
            // bannissements.setItem(2, Freekill_involontaire.toItemStack());
            // bannissements.setItem(3, Freekill_volontaire.toItemStack());
            // bannissements.setItem(4, Freekill_Aveu.toItemStack());
            // bannissements.setItem(5, Freekill_Stuff_Rendu.toItemStack());
            // bannissements.setItem(6, Deco_inter.toItemStack());
            // bannissements.setItem(7, Mensonge_staff.toItemStack());
            // bannissements.setItem(9, Refus_d_inter.toItemStack());
            // bannissements.setItem(10, Faux_ticket_massif.toItemStack());
            // bannissements.setItem(11, Spam_massif.toItemStack());
            // bannissements.setItem(12, Intrusion_batiment_staff.toItemStack());
            // bannissements.setItem(13, Cheat.toItemStack());
            // bannissements.setItem(14, UseBug.toItemStack());
            // bannissements.setItem(15, Anti_AFK.toItemStack());
            //bannissements.setItem(16, Tentative_fk_modo.toItemStack());
            bannissements.setItem(27, NoRP_en_masse.toItemStack());
            bannissements.setItem(28, Freekill_en_masse.toItemStack());
            bannissements.setItem(29, Menace_DDOS.toItemStack());
            bannissements.setItem(30, Troll.toItemStack());
            bannissements.setItem(31, Duplication.toItemStack());
            bannissements.setItem(32, Coup_detat.toItemStack());
            bannissements.setItem(33, Denigrement.toItemStack());
            bannissements.setItem(34, Insulte_serveur.toItemStack());
            bannissements.setItem(35, Retour.toItemStack());
            bannissements.setItem(44, Bannissements_temporaires.toItemStack());
            bannissements.setItem(45, NoRP_recidive_600_min.toItemStack());
            bannissements.setItem(46, NoRP_recidive_120_min.toItemStack());
            bannissements.setItem(47, NoRP_recidive_60_min.toItemStack());
            bannissements.setItem(48, Propos_obscenes.toItemStack());
            bannissements.setItem(49, Actes_obscenes.toItemStack());
            bannissements.setItem(53, Bannissements_permanents.toItemStack());
            sn.openInventory(bannissements);
        }
        if (type == Type.EXPULSER) {

            ItemBuilder Spam = new ItemBuilder(Material.PAPER).setName("§bSpam");
            ItemBuilder Flood = new ItemBuilder(Material.PAPER).setName("§bFlood");
            ItemBuilder Spam_micro = new ItemBuilder(Material.PAPER).setName("§bSpam micro");
            ItemBuilder Joueur_AFK = new ItemBuilder(Material.PAPER).setName("§bJoueur AFK");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

            Inventory expulsions = Bukkit.createInventory(new AdminOptionHolder(pl), 9, "§3Expulser " + pl.getName());
            expulsions.setItem(0, Spam.toItemStack());
            expulsions.setItem(1, Flood.toItemStack());
            expulsions.setItem(2, Spam_micro.toItemStack());
            expulsions.setItem(3, Joueur_AFK.toItemStack());
            expulsions.setItem(8, Retour.toItemStack());
            sn.openInventory(expulsions);
        }
        if (type == Type.BAN_ADMIN) {

            ItemBuilder Contournement_de_sanctions = new ItemBuilder(Material.PAPER).setName("§cContournement de sanctions");
            ItemBuilder Usurpation_d_identite = new ItemBuilder(Material.PAPER).setName("§cUsurpation d'identité");
            ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

            Inventory ban_admin = Bukkit.createInventory(new AdminOptionHolder(pl), 9, "§4Bannir ");
            ban_admin.setItem(1, Contournement_de_sanctions.toItemStack());
            ban_admin.setItem(2, Usurpation_d_identite.toItemStack());
            ban_admin.setItem(8, Retour.toItemStack());
            sn.openInventory(ban_admin);
        }
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