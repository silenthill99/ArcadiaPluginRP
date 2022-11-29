package fr.silenthill99.ArcadiaPluginRP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Staff implements TabCompleter {

    ArrayList<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String prefix = args[args.length - 1].toLowerCase(Locale.ROOT);
        for (String s : Arrays.asList("on", "off")) {
            if (prefix.isEmpty() || s.startsWith(prefix)) {
                arguments.add(s);
            }
        }
        return arguments;
    }
}
