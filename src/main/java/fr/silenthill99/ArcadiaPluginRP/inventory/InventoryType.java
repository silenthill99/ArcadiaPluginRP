package fr.silenthill99.ArcadiaPluginRP.inventory;

import fr.silenthill99.ArcadiaPluginRP.inventory.hook.*;

import java.util.Arrays;
import java.util.List;

public enum InventoryType
{
    ADMIN_OPTION(new AdminOptionInventory()),
    ADMIN_SYSTEM_JOB(new AdminJobInventory()),
    AVERTIR(new AvertirInventory()),
    BAN(new BanInventory()),
    DIRECTION(new DirectionInventory()),
    PLAYER_OPTIONS(new PlayerOptionInventory()),
    PLAYER_ADMIN_MENU(new PlayerAdminInventory()),
    PLAYER_SANCTION_INVENTORY(new PlayerSanctionTypeInventory()),
    PLAYER_SANCTION_MENU(new PlayerSanctionMenuInventory()),
    SANCTION_ADMIN(new SanctionAdminInventory());
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
