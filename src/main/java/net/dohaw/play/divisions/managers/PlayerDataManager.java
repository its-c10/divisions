package net.dohaw.play.divisions.managers;

import net.dohaw.play.divisions.DivisionsPlugin;
import net.dohaw.play.divisions.files.PlayerDataHandler;
import net.dohaw.play.divisions.playerData.PlayerData;
import net.dohaw.play.divisions.rank.Rank;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDataManager implements Manager{

    private DivisionsPlugin plugin;
    private PlayerDataHandler playerDataHandler;
    private List<PlayerData> playerDataList;

    public PlayerDataManager(DivisionsPlugin plugin){
        this.plugin = plugin;
        this.playerDataHandler = new PlayerDataHandler(plugin);
    }

    @Override
    public Object getContents() {
        return playerDataList;
    }

    public PlayerData getPlayerByUUID(UUID playerUUID){
        for(PlayerData data : playerDataList){
            if(data.getPlayerUUID().equals(playerUUID)){
                return data;
            }
        }
        return null;
    }

    public PlayerData getByPlayerObj(Player player){
        for(PlayerData data : playerDataList){
            if(data.getPlayer().getPlayer().equals(player)){
                return data;
            }
        }
        return null;
    }

    public List<PlayerData> getByRank(Rank rank){
        List<PlayerData> playersWithRank = new ArrayList<>();
        for(PlayerData data : playerDataList){
            if(data.getRank().equals(rank)){
                playersWithRank.add(data);
            }
        }
        return null;
    }

    public List<PlayerData> getDivisionName(String divisionName){
        List<PlayerData> membersData = new ArrayList<>();
        for(PlayerData data : playerDataList){
            if(data.getDivision().getName().equalsIgnoreCase(divisionName)){
                membersData.add(data);
            }
        }
        return membersData;
    }

    @Override
    public boolean hasContent(Object obj) {
        return playerDataList.contains(obj);
    }

    @Override
    public void saveContents() {
        playerDataHandler.saveData(playerDataList);
    }

    @Override
    public void loadContents() {
        this.playerDataList = playerDataHandler.loadData();
    }

    @Override
    public boolean addContent(Object content) { return false; }

    public void addPlayerData(UUID u){
        playerDataList.add(playerDataHandler.loadPlayerData(u));
    }

    public void removePlayerData(UUID u){
        playerDataHandler.saveData(getPlayerByUUID(u));
        playerDataList.remove(getPlayerByUUID(u));
    }

    @Override
    public boolean removeContent(Object content) {
        return false;
    }
}