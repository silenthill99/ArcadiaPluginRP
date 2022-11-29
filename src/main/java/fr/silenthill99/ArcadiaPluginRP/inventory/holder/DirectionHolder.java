package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.DirectionInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class DirectionHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public DirectionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public Map<Integer, Livres> livres = new HashMap<>();
}
