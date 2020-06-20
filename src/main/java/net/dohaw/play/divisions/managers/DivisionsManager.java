package net.dohaw.play.divisions.managers;

import net.dohaw.play.divisions.Division;
import net.dohaw.play.divisions.DivisionsPlugin;
import net.dohaw.play.divisions.files.DivisionsConfigHandler;
import net.dohaw.play.divisions.files.DivisionsListConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DivisionsManager implements Manager {

    private HashMap<String, Division> divisions = new HashMap<>();
    private DivisionsPlugin plugin;
    private DivisionsListConfig divisionsListConfig;
    private Economy e;

    public DivisionsManager(DivisionsPlugin plugin){
        this.plugin = plugin;
        this.e = DivisionsPlugin.getEconomy();
        this.divisionsListConfig = new DivisionsListConfig(plugin);
    }

    @Override
    public Object getContents() {
        return divisions;
    }

    @Override
    public boolean hasContent(Object obj) {
        return divisions.get(obj) != null;
    }

    @Override
    public void saveContents() {
        DivisionsConfigHandler dch = new DivisionsConfigHandler(plugin);
        dch.saveDivisionsData(divisions);
    }

    @Override
    public void loadContents() {
        DivisionsConfigHandler dch = new DivisionsConfigHandler(plugin);
        this.divisions = dch.loadDivisions();
    }

    @Override
    public boolean addContent(Object content) {
        return false;
    }

    public boolean addContent(String divisionName, Object content){
        return divisions.put(divisionName, (Division) content) != null;
    }

    public void createNewDivision(String divisionName, Player creator){
        DivisionsConfigHandler dch = new DivisionsConfigHandler(plugin);
        FileConfiguration newDivisionConfig = dch.createDivisionsConfig(divisionName, creator.getUniqueId());
        Division newDivision = dch.loadDivision(divisionName, newDivisionConfig);

        divisions.put(divisionName, newDivision);
        e.createBank(divisionName + "_Bank", Bukkit.getOfflinePlayer(creator.getUniqueId()));
        divisionsListConfig.addDivision(divisionName);
    }

    @Override
    public boolean removeContent(Object content) {
        return false;
    }

    public Division getDivision(String divisionName){
        return divisions.get(divisionName);
    }

    public void setDivision(String divisionName, Division division){
        divisions.replace(divisionName, division);
    }

    public Division getByLeader(UUID leaderUUID){
        for(Map.Entry<String, Division> division : divisions.entrySet()){
            Division div = division.getValue();
            if(div.getLeader().getPlayerUUID().equals(leaderUUID)){
                return div;
            }
        }
        return null;
    }

}
