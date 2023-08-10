package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin.BanAdminInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class BanAdminHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public BanAdminHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Bannir> bannir = new HashMap<>();
}
