package fr.crabii.homes.managers;

import fr.crabii.homes.Main;
import fr.crabii.homes.commands.CommandDelhome;
import fr.crabii.homes.commands.CommandGethome;
import fr.crabii.homes.commands.CommandHome;
import fr.crabii.homes.commands.CommandSethome;


public class CommandsManager {
    public static void register(Main instance) {
        instance.getCommand("sethome").setExecutor(new CommandSethome());
        instance.getCommand("delhome").setExecutor(new CommandDelhome());
        instance.getCommand("gethome").setExecutor(new CommandGethome());
        instance.getCommand("home").setExecutor(new CommandHome());
        instance.getCommand("home").setTabCompleter(new CommandHome());
    }


}
