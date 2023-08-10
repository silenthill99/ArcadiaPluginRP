package fr.silenthill99.ArcadiaPluginRP.inventory.holder.admin;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin.AdminJobInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class AdminJobHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public AdminJobHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public Map<Integer, Metiers> metiers = new HashMap<>();
}
