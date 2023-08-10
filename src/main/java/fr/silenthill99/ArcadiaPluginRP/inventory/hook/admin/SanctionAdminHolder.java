package fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class SanctionAdminHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public SanctionAdminHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
