package io.github.eddiediamondfire.bedwars.arenadata;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {

    private String teamName;
    private String teamDisplayName;
    private ChatColor teamColor;
    private List<UUID> playersInTeam;

    public Team(String teamName, String teamDisplayName, ChatColor teamColour){
        setTeamName(teamName);
        setTeamDisplayName(teamDisplayName);
        setTeamColor(teamColour);
        setPlayersInTeam(new ArrayList<>());
    }
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDisplayName() {
        return teamDisplayName;
    }

    public void setTeamDisplayName(String teamDisplayName) {
        this.teamDisplayName = teamDisplayName;
    }

    public ChatColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(ChatColor teamColor) {
        this.teamColor = teamColor;
    }

    public List<UUID> getPlayersInTeam() {
        return playersInTeam;
    }

    public void setPlayersInTeam(List<UUID> playersInTeam) {
        this.playersInTeam = playersInTeam;
    }
}
