package fr.silenthill99.ArcadiaPluginRP.inventory.holder;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.GradeInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class GradeHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public GradeHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Grades> grades = new HashMap<>();
}
