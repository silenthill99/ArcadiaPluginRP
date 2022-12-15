package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class RankUpSuperHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public RankUpSuperHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
