package fr.silenthill99.ArcadiaPluginRP.inventory.holder.admin;

import fr.silenthill99.ArcadiaPluginRP.inventory.SilenthillHolder;
import fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin.PlayerAdminInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class PlayerAdminHolder extends SilenthillHolder {
    private final OfflinePlayer target;

    public PlayerAdminHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Books> books = new HashMap<>();
}
