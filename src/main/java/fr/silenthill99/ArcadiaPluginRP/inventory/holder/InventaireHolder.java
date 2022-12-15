package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class InventaireHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public InventaireHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }
}
