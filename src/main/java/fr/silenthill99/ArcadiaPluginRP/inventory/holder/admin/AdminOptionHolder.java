package fr.silenthill99.ArcadiaPluginRP.inventory.holder.admin;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class AdminOptionHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public AdminOptionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
