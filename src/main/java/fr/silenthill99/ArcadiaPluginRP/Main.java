package fr.silenthill99.ArcadiaPluginRP;

import fr.silenthill99.ArcadiaPluginRP.commands.*;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Main extends JavaPlugin {

    public static Map<Player, ArrayList<String>> logs = new HashMap<>();

    private static Main instance;

    public Economy economy = null;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new InventoryManager(), this);
        setupEconomy();
        getCommand("disconnect").setExecutor(new Disconnect());
        getCommand("connect").setExecutor(new Connect());
        getCommand("tchatlist").setExecutor(new Tchat());
        getCommand("menu").setExecutor(new Menu());
        getCommand("moderateur").setExecutor(new Moderateur());
        getCommand("moderateur").setTabCompleter(new Staff());
        getCommand("direction").setExecutor(new Direction());
        getCommand("direction").setTabCompleter(new Staff());
        getCommand("recherche").setExecutor(new Recherche());
        getCommand("recherche").setTabCompleter(new Recherche());
        getCommand("carte").setExecutor(new Carte());
        getCommand("reseaux").setExecutor(new Reseaux());
        getCommand("stafftchat").setExecutor(new Stafftchat());
        getCommand("options").setExecutor(new Options());
        getCommand("randomtp").setExecutor(new RandomTP());
        getCommand("administrateur").setExecutor(new Administrateur());
        getCommand("administrateur").setTabCompleter(new Staff());
        getCommand("developpeur").setExecutor(new Developpeur());
        getCommand("developpeur").setTabCompleter(new Staff());
        getCommand("secretaire").setExecutor(new Secretaire());
        getCommand("responsable").setExecutor(new Responsable());
        getCommand("responsable").setTabCompleter(new Staff());
        Bukkit.getScheduler().runTaskTimer(this, new TimerTask() {
            @Override
            public void run() {
                List<String> list = getConfig().getStringList("messages.auto");
                String a = list.get(ThreadLocalRandom.current().nextInt(list.size())).replace("&", "§");
                Bukkit.broadcastMessage(getConfig().getString("options.prefix").replace("&", "§") + " " + a);
            }
        }, 0, 12000);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean setupEconomy(){
        RegisteredServiceProvider<Economy> eco = getServer().getServicesManager().getRegistration(Economy.class);
        if (eco != null) {
            economy = eco.getProvider();
        }
        return economy != null;
    }

}
