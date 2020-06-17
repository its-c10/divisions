package net.dohaw.play.divisions;

import net.dohaw.play.divisions.playerData.PlayerData;
import net.dohaw.play.divisions.rank.Permission;
import net.dohaw.play.divisions.rank.Rank;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public class Division {

    final private String NAME;
    final private FileConfiguration CONFIG;
    final private PlayerData LEADER;

    private String bankName;
    private double goldAmount, power, heartsDestroyed;
    private Location garrisonLocation;
    private List<PlayerData> players;
    private EnumMap<Rank, EnumMap<Permission, Object>> rankPermissions;
    private int kills, casualties, shrinesConquered;

    public Division(final String DIVISION_NAME, final FileConfiguration DIVISION_CONFIG, final PlayerData LEADER){
        this.NAME = DIVISION_NAME;
        this.CONFIG = DIVISION_CONFIG;
        this.LEADER = LEADER;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

    public Location getGarrisonLocation() {
        return garrisonLocation;
    }

    public void setGarrisonLocation(Location garrisonLocation) {
        this.garrisonLocation = garrisonLocation;
    }

    public int getCasualties() {
        return casualties;
    }

    public void setCasualties(int casualties) {
        this.casualties = casualties;
    }

    public double getHeartsDestroyed() {
        return heartsDestroyed;
    }

    public void setHeartsDestroyed(double heartsDestroyed) {
        this.heartsDestroyed = heartsDestroyed;
    }

    public double getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        this.goldAmount = goldAmount;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getShrinesConquered() {
        return shrinesConquered;
    }

    public void setShrinesConquered(int shrinesConquered) {
        this.shrinesConquered = shrinesConquered;
    }

    public PlayerData getMember(UUID u){
        for(PlayerData data : players){
            if(data.getPlayerUUID().equals(u)){
                return data;
            }
        }
        return null;
    }

    public String getName() {
        return NAME;
    }

    public FileConfiguration getConfig() {
        return CONFIG;
    }

    public PlayerData getLeader() {
        return LEADER;
    }

    public EnumMap<Rank, EnumMap<Permission, Object>> getRankPermissions() {
        return rankPermissions;
    }

    public void setRankPermissions(EnumMap<Rank, EnumMap<Permission, Object>> rankPermissions) {
        this.rankPermissions = rankPermissions;
    }
}