/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;

/**
 * 
 * Domain object that stores data relating to Teams.
 * Teams contain Players, and two Teams play a Game.
 * Teams also belong to a League
 * 
 * @param name  the name of the Team
 * 
 * @see Players
 * @see Game
 * @see League 
 * 
 */
// line 175 "../../../../../ScoreKeeper.ump"
// line 292 "../../../../../ScoreKeeper.ump"
public class Team
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Team Attributes
  private String name;

  //Team Associations
  private List<Player> players;
  private Game game;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Team(String aName, Game aGame, League aLeague)
  {
    name = aName;
    players = new ArrayList<Player>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create game due to game");
    }
    boolean didAddLeague = setLeague(aLeague);
    if (!didAddLeague)
    {
      throw new RuntimeException("Unable to create team due to league");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  /**
   * Umple Code//
   */
  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }

  public Game getGame()
  {
    return game;
  }

  public League getLeague()
  {
    return league;
  }

  public static int minimumNumberOfPlayers()
  {
    return 0;
  }

  public Player addPlayer(String aName, int aJerseyNumber, League aLeague)
  {
    return new Player(aName, aJerseyNumber, this, aLeague);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    Team existingTeam = aPlayer.getTeam();
    boolean isNewTeam = existingTeam != null && !this.equals(existingTeam);
    if (isNewTeam)
    {
      aPlayer.setTeam(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a team
    if (!this.equals(aPlayer.getTeam()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to game
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (2)
    if (aGame.numberOfGames() >= Game.maximumNumberOfGames())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeGame(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setLeague(League aLeague)
  {
    boolean wasSet = false;
    if (aLeague == null)
    {
      return wasSet;
    }

    League existingLeague = league;
    league = aLeague;
    if (existingLeague != null && !existingLeague.equals(aLeague))
    {
      existingLeague.removeTeam(this);
    }
    league.addTeam(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removeGame(this);
    League placeholderLeague = league;
    this.league = null;
    placeholderLeague.removeTeam(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 189 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_NAME = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
  		return one.name.compareTo(other.name);
  	}};

  
}