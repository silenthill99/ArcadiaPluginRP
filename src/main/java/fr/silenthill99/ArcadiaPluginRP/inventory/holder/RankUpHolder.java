package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.RankUpInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class RankUpHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public RankUpHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, operateur> operateur = new HashMap<>();
    public HashMap<Integer, Grade> gd = new HashMap<>();
}
