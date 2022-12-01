package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.ExpulserInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class ExpulserHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public ExpulserHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public HashMap<Integer, Expulser> expulser = new HashMap<>();

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
