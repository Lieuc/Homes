package fr.crabii.homes;

import fr.crabii.homes.managers.Managers;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    Managers managers = new Managers();

    @Override
    public void onEnable() {
        managers.load(this);
    }
}
