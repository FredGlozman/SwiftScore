/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 48 "ScoreKeeper.ump"
// line 102 "ScoreKeeper.ump"
public class League
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static League theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //League Attributes
  private List<Integer> playerScoreRanking;
  private List<Integer> playerCombinedPenaltyRanking;
  private List<Integer> playerRedPenaltyRanking;
  private List<Integer> playerYellowPenaltyRanking;
  private List<Integer> teamPointRanking;
  private List<Integer> teamGoalsRanking;
  private List<Integer> teamCombinedPenaltyRanking;
  private List<Integer> teamRedPenaltyRanking;
  private List<Integer> teamYellowPenaltyRanking;

  //League Associations
  private List<Team> teams;
  private List<Game> games;
  private List<Player> players;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private League()
  {
    playerScoreRanking = new ArrayList<Integer>();
    playerCombinedPenaltyRanking = new ArrayList<Integer>();
    playerRedPenaltyRanking = new ArrayList<Integer>();
    playerYellowPenaltyRanking = new ArrayList<Integer>();
    teamPointRanking = new ArrayList<Integer>();
    teamGoalsRanking = new ArrayList<Integer>();
    teamCombinedPenaltyRanking = new ArrayList<Integer>();
    teamRedPenaltyRanking = new ArrayList<Integer>();
    teamYellowPenaltyRanking = new ArrayList<Integer>();
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

  public boolean addPlayerScoreRanking(Integer aPlayerScoreRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerScoreRanking.add(aPlayerScoreRanking);
    return wasAdded;
  }

  public boolean removePlayerScoreRanking(Integer aPlayerScoreRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerScoreRanking.remove(aPlayerScoreRanking);
    return wasRemoved;
  }

  public boolean addPlayerCombinedPenaltyRanking(Integer aPlayerCombinedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerCombinedPenaltyRanking.add(aPlayerCombinedPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerCombinedPenaltyRanking(Integer aPlayerCombinedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerCombinedPenaltyRanking.remove(aPlayerCombinedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addPlayerRedPenaltyRanking(Integer aPlayerRedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerRedPenaltyRanking.add(aPlayerRedPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerRedPenaltyRanking(Integer aPlayerRedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerRedPenaltyRanking.remove(aPlayerRedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addPlayerYellowPenaltyRanking(Integer aPlayerYellowPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerYellowPenaltyRanking.add(aPlayerYellowPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerYellowPenaltyRanking(Integer aPlayerYellowPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerYellowPenaltyRanking.remove(aPlayerYellowPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamPointRanking(Integer aTeamPointRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamPointRanking.add(aTeamPointRanking);
    return wasAdded;
  }

  public boolean removeTeamPointRanking(Integer aTeamPointRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamPointRanking.remove(aTeamPointRanking);
    return wasRemoved;
  }

  public boolean addTeamGoalsRanking(Integer aTeamGoalsRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamGoalsRanking.add(aTeamGoalsRanking);
    return wasAdded;
  }

  public boolean removeTeamGoalsRanking(Integer aTeamGoalsRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamGoalsRanking.remove(aTeamGoalsRanking);
    return wasRemoved;
  }

  public boolean addTeamCombinedPenaltyRanking(Integer aTeamCombinedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamCombinedPenaltyRanking.add(aTeamCombinedPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamCombinedPenaltyRanking(Integer aTeamCombinedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamCombinedPenaltyRanking.remove(aTeamCombinedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamRedPenaltyRanking(Integer aTeamRedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamRedPenaltyRanking.add(aTeamRedPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamRedPenaltyRanking(Integer aTeamRedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamRedPenaltyRanking.remove(aTeamRedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamYellowPenaltyRanking(Integer aTeamYellowPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamYellowPenaltyRanking.add(aTeamYellowPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamYellowPenaltyRanking(Integer aTeamYellowPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamYellowPenaltyRanking.remove(aTeamYellowPenaltyRanking);
    return wasRemoved;
  }

  public Integer getPlayerScoreRanking(int index)
  {
    Integer aPlayerScoreRanking = playerScoreRanking.get(index);
    return aPlayerScoreRanking;
  }

  public Integer[] getPlayerScoreRanking()
  {
    Integer[] newPlayerScoreRanking = playerScoreRanking.toArray(new Integer[playerScoreRanking.size()]);
    return newPlayerScoreRanking;
  }

  public int numberOfPlayerScoreRanking()
  {
    int number = playerScoreRanking.size();
    return number;
  }

  public boolean hasPlayerScoreRanking()
  {
    boolean has = playerScoreRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerScoreRanking(Integer aPlayerScoreRanking)
  {
    int index = playerScoreRanking.indexOf(aPlayerScoreRanking);
    return index;
  }

  public Integer getPlayerCombinedPenaltyRanking(int index)
  {
    Integer aPlayerCombinedPenaltyRanking = playerCombinedPenaltyRanking.get(index);
    return aPlayerCombinedPenaltyRanking;
  }

  public Integer[] getPlayerCombinedPenaltyRanking()
  {
    Integer[] newPlayerCombinedPenaltyRanking = playerCombinedPenaltyRanking.toArray(new Integer[playerCombinedPenaltyRanking.size()]);
    return newPlayerCombinedPenaltyRanking;
  }

  public int numberOfPlayerCombinedPenaltyRanking()
  {
    int number = playerCombinedPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerCombinedPenaltyRanking()
  {
    boolean has = playerCombinedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerCombinedPenaltyRanking(Integer aPlayerCombinedPenaltyRanking)
  {
    int index = playerCombinedPenaltyRanking.indexOf(aPlayerCombinedPenaltyRanking);
    return index;
  }

  public Integer getPlayerRedPenaltyRanking(int index)
  {
    Integer aPlayerRedPenaltyRanking = playerRedPenaltyRanking.get(index);
    return aPlayerRedPenaltyRanking;
  }

  public Integer[] getPlayerRedPenaltyRanking()
  {
    Integer[] newPlayerRedPenaltyRanking = playerRedPenaltyRanking.toArray(new Integer[playerRedPenaltyRanking.size()]);
    return newPlayerRedPenaltyRanking;
  }

  public int numberOfPlayerRedPenaltyRanking()
  {
    int number = playerRedPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerRedPenaltyRanking()
  {
    boolean has = playerRedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerRedPenaltyRanking(Integer aPlayerRedPenaltyRanking)
  {
    int index = playerRedPenaltyRanking.indexOf(aPlayerRedPenaltyRanking);
    return index;
  }

  public Integer getPlayerYellowPenaltyRanking(int index)
  {
    Integer aPlayerYellowPenaltyRanking = playerYellowPenaltyRanking.get(index);
    return aPlayerYellowPenaltyRanking;
  }

  public Integer[] getPlayerYellowPenaltyRanking()
  {
    Integer[] newPlayerYellowPenaltyRanking = playerYellowPenaltyRanking.toArray(new Integer[playerYellowPenaltyRanking.size()]);
    return newPlayerYellowPenaltyRanking;
  }

  public int numberOfPlayerYellowPenaltyRanking()
  {
    int number = playerYellowPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerYellowPenaltyRanking()
  {
    boolean has = playerYellowPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerYellowPenaltyRanking(Integer aPlayerYellowPenaltyRanking)
  {
    int index = playerYellowPenaltyRanking.indexOf(aPlayerYellowPenaltyRanking);
    return index;
  }

  public Integer getTeamPointRanking(int index)
  {
    Integer aTeamPointRanking = teamPointRanking.get(index);
    return aTeamPointRanking;
  }

  public Integer[] getTeamPointRanking()
  {
    Integer[] newTeamPointRanking = teamPointRanking.toArray(new Integer[teamPointRanking.size()]);
    return newTeamPointRanking;
  }

  public int numberOfTeamPointRanking()
  {
    int number = teamPointRanking.size();
    return number;
  }

  public boolean hasTeamPointRanking()
  {
    boolean has = teamPointRanking.size() > 0;
    return has;
  }

  public int indexOfTeamPointRanking(Integer aTeamPointRanking)
  {
    int index = teamPointRanking.indexOf(aTeamPointRanking);
    return index;
  }

  public Integer getTeamGoalsRanking(int index)
  {
    Integer aTeamGoalsRanking = teamGoalsRanking.get(index);
    return aTeamGoalsRanking;
  }

  public Integer[] getTeamGoalsRanking()
  {
    Integer[] newTeamGoalsRanking = teamGoalsRanking.toArray(new Integer[teamGoalsRanking.size()]);
    return newTeamGoalsRanking;
  }

  public int numberOfTeamGoalsRanking()
  {
    int number = teamGoalsRanking.size();
    return number;
  }

  public boolean hasTeamGoalsRanking()
  {
    boolean has = teamGoalsRanking.size() > 0;
    return has;
  }

  public int indexOfTeamGoalsRanking(Integer aTeamGoalsRanking)
  {
    int index = teamGoalsRanking.indexOf(aTeamGoalsRanking);
    return index;
  }

  public Integer getTeamCombinedPenaltyRanking(int index)
  {
    Integer aTeamCombinedPenaltyRanking = teamCombinedPenaltyRanking.get(index);
    return aTeamCombinedPenaltyRanking;
  }

  public Integer[] getTeamCombinedPenaltyRanking()
  {
    Integer[] newTeamCombinedPenaltyRanking = teamCombinedPenaltyRanking.toArray(new Integer[teamCombinedPenaltyRanking.size()]);
    return newTeamCombinedPenaltyRanking;
  }

  public int numberOfTeamCombinedPenaltyRanking()
  {
    int number = teamCombinedPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamCombinedPenaltyRanking()
  {
    boolean has = teamCombinedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamCombinedPenaltyRanking(Integer aTeamCombinedPenaltyRanking)
  {
    int index = teamCombinedPenaltyRanking.indexOf(aTeamCombinedPenaltyRanking);
    return index;
  }

  public Integer getTeamRedPenaltyRanking(int index)
  {
    Integer aTeamRedPenaltyRanking = teamRedPenaltyRanking.get(index);
    return aTeamRedPenaltyRanking;
  }

  public Integer[] getTeamRedPenaltyRanking()
  {
    Integer[] newTeamRedPenaltyRanking = teamRedPenaltyRanking.toArray(new Integer[teamRedPenaltyRanking.size()]);
    return newTeamRedPenaltyRanking;
  }

  public int numberOfTeamRedPenaltyRanking()
  {
    int number = teamRedPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamRedPenaltyRanking()
  {
    boolean has = teamRedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamRedPenaltyRanking(Integer aTeamRedPenaltyRanking)
  {
    int index = teamRedPenaltyRanking.indexOf(aTeamRedPenaltyRanking);
    return index;
  }

  public Integer getTeamYellowPenaltyRanking(int index)
  {
    Integer aTeamYellowPenaltyRanking = teamYellowPenaltyRanking.get(index);
    return aTeamYellowPenaltyRanking;
  }

  public Integer[] getTeamYellowPenaltyRanking()
  {
    Integer[] newTeamYellowPenaltyRanking = teamYellowPenaltyRanking.toArray(new Integer[teamYellowPenaltyRanking.size()]);
    return newTeamYellowPenaltyRanking;
  }

  public int numberOfTeamYellowPenaltyRanking()
  {
    int number = teamYellowPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamYellowPenaltyRanking()
  {
    boolean has = teamYellowPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamYellowPenaltyRanking(Integer aTeamYellowPenaltyRanking)
  {
    int index = teamYellowPenaltyRanking.indexOf(aTeamYellowPenaltyRanking);
    return index;
  }

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

  public boolean addTeam(Team aTeam)
  {
    boolean wasAdded = false;
    if (teams.contains(aTeam)) { return false; }
    teams.add(aTeam);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTeam(Team aTeam)
  {
    boolean wasRemoved = false;
    if (teams.contains(aTeam))
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

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    games.add(aGame);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    if (games.contains(aGame))
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

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    players.add(aPlayer);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (players.contains(aPlayer))
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
    teams.clear();
    games.clear();
    players.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]"
     + outputString;
  }
}