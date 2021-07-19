package io.github.eddiediamondfire.bedwars.game.team;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.Team;
import io.github.eddiediamondfire.bedwars.game.GameManager;

import java.util.List;

public class TeamManager {

    private final Bedwars plugin;
    public TeamManager(GameManager gameManager){
        this.plugin = gameManager.getPlugin();
    }

    public boolean teamExist(String teamName, int id){
        GameInstance gameInstance = plugin.getGameManager().getGameInstances().get(id);

        List<Team> teams = gameInstance.getTeams();

        for(Team team: teams){
            return team.getTeamName().equals(teamName);
        }
        return false;
    }

    public Team getTeam(int id, String teamName){
        GameInstance gameInstance = plugin.getGameManager().getGameInstances().get(id);

        List<Team> teams = gameInstance.getTeams();

        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return team;
            }
        }
        return null;
    }
}
