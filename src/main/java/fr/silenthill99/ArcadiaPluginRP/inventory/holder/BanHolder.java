package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo.BanInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class BanHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public BanHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public Map<Integer, BanTemp> ban_temp = new HashMap<>();
    public Map<Integer, Ban> ban = new HashMap<>();
}
