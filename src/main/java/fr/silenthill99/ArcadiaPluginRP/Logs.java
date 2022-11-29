package fr.silenthill99.ArcadiaPluginRP;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Logs {

    private ArrayList<String> msg;

    public Logs() {
    }

    public void addLog(String message) {
        msg.add(message);
    }

}
