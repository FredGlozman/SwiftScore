package ca.mcgill.ecse321.scorekeeper.search_utils;

import ca.mcgill.ecse321.scorekeeper.model.*;
import java.util.List;

public class Find {

    //returns a team if team1 is a team in the league, returns null otherwise
    public static Team getTeam(String team1)
    {
        //check if teams exist
        List<Team> teams = League.getInstance().getTeams();
        for(Team team : teams)
        {
            if(team.getName().matches(team1))
            {
                return team;
            }
        }
        return null;
    }

    //returns a player if player is in team, returns null otherwise
    public static Player getPlayer(String player, Team team)
    {
        for(Player p : team.getPlayers())
        {
            if(p.getName().matches(player))
            {
                return p;
            }
        }
        return null;
    }

    //returns a player if player is in team, returns null otherwise
    public static Player getPlayer(int playerNumber, Team team)
    {
        for(Player p : team.getPlayers())
        {
            if(p.getJerseyNumber() == playerNumber)
            {
                return p;
            }
        }
        return null;
    }

    public static Player inferPlayer(String playerID, Team team1, Team team2)
    {
        Player player;
    
        if(isInt(playerID))
        {
            player = getPlayer(Integer.parseInt(playerID), team1);
            if(player==null)
            {
                player = getPlayer(Integer.parseInt(playerID), team2);
            }
        }
        else
        {
            player = getPlayer(playerID, team1);
            if(player==null)
            {
                player = getPlayer(playerID, team2);
            }
        }
    
        return player;
    }
    
    public static Player inferPlayer(String playerID, Team team1)
    {
        Player player;
    
        if(isInt(playerID))
        {
            player = getPlayer(Integer.parseInt(playerID), team1);
        }
        else
        {
            player = getPlayer(playerID, team1);
        }
    
        return player;
    }

    private static boolean isInt(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    //returns true if team1 is a team in the league, returns false otherwise
    public static boolean teamExists(String team1)
    {
        //check if teams exist
        List<Team> teams = League.getInstance().getTeams();
        for(Team team : teams)
        {
            if(team.getName().matches(team1))
            {
                return true;
            }
        }
        return false;
    }

    //returns true if team1 is a team in the league, returns false otherwise
    public static boolean playerExists(String playerName)
    {
        //check if player exist
        List<Player> players = League.getInstance().getPlayers();
        for(Player p : players)
        {
            if(p.getName().matches(playerName))
            {
                return true;
            }
        }
        return false;
    }
}
