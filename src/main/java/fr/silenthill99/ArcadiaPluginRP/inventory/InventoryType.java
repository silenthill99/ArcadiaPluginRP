package fr.silenthill99.ArcadiaPluginRP.inventory;

import fr.silenthill99.ArcadiaPluginRP.inventory.hook.*;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo.*;

import java.util.Arrays;
import java.util.List;

public enum InventoryType
{
    ADMIN_OPTION(new AdminOptionInventory()),
    ADMIN_SYSTEM_JOB(new AdminJobInventory()),
    ATM(new DistributeurInventory()),
    AVERTIR(new AvertirInventory()),
    BAN(new BanInventory()),
    BAN_ADMIN(new BanAdminInventory()),
    DIRECTION(new DirectionInventory()),
    ERREUR_STAFF(new ErreurStaffInventory()),
    EXPULSER(new ExpulserInventory()),
    GRADE(new GradeInventory()),
    INVENTAIRE(new InventaireInventory()),
    MACDO(new MacDoInventory()),
    PLAYER_OPTIONS(new PlayerOptionInventory()),
    PLAYER_ADMIN_MENU(new PlayerAdminInventory()),
    PLAYER_SANCTION_INVENTORY(new PlayerSanctionTypeInventory()),
    PLAYER_SANCTION_MENU(new PlayerSanctionMenuInventory()),
    POLICIER(new PolicierInventory()),
    RANK_UP(new RankUpInventory()),
    RANK_UP_SUPER(new RankUpSuperinventory()),
    SANCTION_ADMIN(new SanctionAdminInventory()),
    TELEPORTATION(new TeleportationInventory());
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv)
    {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv()
    {
        return this.inv;
    }

    public static List<InventoryType> getValues()
    {
        return Arrays.asList(values());
    }
}
