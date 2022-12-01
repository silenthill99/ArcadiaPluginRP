package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.ErreurStaffInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class ErreurStaffHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public ErreurStaffHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public Map<Integer, ErreurStaff> erreur_staff = new HashMap<>();
}
