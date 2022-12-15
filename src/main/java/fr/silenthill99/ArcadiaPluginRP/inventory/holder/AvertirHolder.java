package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo.AvertirInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class AvertirHolder extends SilenthillHolder
{

    public Map<Integer, Warns> warns = new HashMap<>();
    private final OfflinePlayer target;

    public AvertirHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
