package fr.silenthill99.ArcadiaPluginRP.inventory.holder.modo;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class PlayerOptionHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public PlayerOptionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
