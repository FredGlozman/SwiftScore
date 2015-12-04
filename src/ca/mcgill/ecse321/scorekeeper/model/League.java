/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;
import ca.mcgill.ecse321.scorekeeper.persistence.PersistenceXStream;
import java.util.*;

/**
 * 
 * Domain object that stores data relating to the League.
 * Teams, Games, and Players are all tracked by the Leage.
 * Their statistics are amalgamated by the League.
 * 
 * @see Team
 * @see Game
 * @see Player
 */
// line 677 "../../../../../ScoreKeeper.ump"
// line 745 "../../../../../ScoreKeeper.ump"
// line 758 "../../../../../ScoreKeeper.ump"
public class League
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static League theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //League Associations
  private List<Team> teams;
  private List<Game> games;
  private List<Player> players;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private League()
  {
    teams = new ArrayList<Team>();
    games = new ArrayList<Game>();
    players = new ArrayList<Player>();
  }

  public static League getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new League();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Team getTeam(int index)
  {
    Team aTeam = teams.get(index);
    return aTeam;
  }

  public List<Team> getTeams()
  {
    List<Team> newTeams = Collections.unmodifiableList(teams);
    return newTeams;
  }

  public int numberOfTeams()
  {
    int number = teams.size();
    return number;
  }

  public boolean hasTeams()
  {
    boolean has = teams.size() > 0;
    return has;
  }

  public int indexOfTeam(Team aTeam)
  {
    int index = teams.indexOf(aTeam);
    return index;
  }

  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

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

  public static int minimumNumberOfTeams()
  {
    return 0;
  }

  public Team addTeam(String aName)
  {
    return new Team(aName, this);
  }

  public boolean addTeam(Team aTeam)
  {
    boolean wasAdded = false;
    if (teams.contains(aTeam)) { return false; }
    League existingLeague = aTeam.getLeague();
    boolean isNewLeague = existingLeague != null && !this.equals(existingLeague);
    if (isNewLeague)
    {
      aTeam.setLeague(this);
    }
    else
    {
      teams.add(aTeam);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTeam(Team aTeam)
  {
    boolean wasRemoved = false;
    //Unable to remove aTeam, as it must always have a league
    if (!this.equals(aTeam.getLeague()))
    {
      teams.remove(aTeam);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTeamAt(Team aTeam, int index)
  {  
    boolean wasAdded = false;
    if(addTeam(aTeam))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeams()) { index = numberOfTeams() - 1; }
      teams.remove(aTeam);
      teams.add(index, aTeam);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTeamAt(Team aTeam, int index)
  {
    boolean wasAdded = false;
    if(teams.contains(aTeam))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeams()) { index = numberOfTeams() - 1; }
      teams.remove(aTeam);
      teams.add(index, aTeam);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTeamAt(aTeam, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfGames()
  {
    return 0;
  }

  public Game addGame(int aStartTime, int aEndTime, String aLocation, Team... allCompetitors)
  {
    return new Game(aStartTime, aEndTime, aLocation, this, allCompetitors);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    League existingLeague = aGame.getLeague();
    boolean isNewLeague = existingLeague != null && !this.equals(existingLeague);
    if (isNewLeague)
    {
      aGame.setLeague(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a league
    if (!this.equals(aGame.getLeague()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPlayers()
  {
    return 0;
  }

  public Player addPlayer(String aName, int aJerseyNumber, Team aTeam)
  {
    return new Player(aName, aJerseyNumber, aTeam, this);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    League existingLeague = aPlayer.getLeague();
    boolean isNewLeague = existingLeague != null && !this.equals(existingLeague);
    if (isNewLeague)
    {
      aPlayer.setLeague(this);
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
    //Unable to remove aPlayer, as it must always have a league
    if (!this.equals(aPlayer.getLeague()))
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

  public void delete()
  {
    for(int i=teams.size(); i > 0; i--)
    {
      Team aTeam = teams.get(i - 1);
      aTeam.delete();
    }
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
  }


  /**
   * Java Code //
   */
  // line 691 "../../../../../ScoreKeeper.ump"
   public static  boolean save(League l){
    PersistenceXStream.setFilename("data.xml");
    PersistenceXStream.setAlias("league", League.class);
    PersistenceXStream.setAlias("team", Team.class);
    PersistenceXStream.setAlias("player", Player.class);
    PersistenceXStream.setAlias("goalie", Goalie.class);
    PersistenceXStream.setAlias("game", Game.class);
    PersistenceXStream.setAlias("shot", Shot.class);
    PersistenceXStream.setAlias("infraction", Infraction.class);
    return PersistenceXStream.saveToXMLwithXStream(l);
  }

  // line 704 "../../../../../ScoreKeeper.ump"
   public static  League load(){
    return (League) PersistenceXStream.loadFromXMLwithXStream();
  }

}