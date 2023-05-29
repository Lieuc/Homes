package fr.crabii.homes.managers;

import fr.crabii.homes.Main;
import fr.crabii.homes.commands.*;


public class CommandsManager {
    public static void register(Main instance) {
        instance.getCommand("sethome").setExecutor(new CommandSethome());
        instance.getCommand("delhome").setExecutor(new CommandDelhome());
        instance.getCommand("gethome").setExecutor(new CommandGethome());
        instance.getCommand("home").setExecutor(new CommandHome());
        instance.getCommand("gohome").setExecutor(new CommandGohome());


        instance.getCommand("home").setTabCompleter(new CommandHome());
    }


}
