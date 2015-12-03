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
// line 311 "../../../../../ScoreKeeper.ump"
// line 700 "../../../../../ScoreKeeper.ump"
public class Team
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Team Attributes
  private String name;

  //Team Associations
  private List<Player> players;
  private List<Game> games;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Team(String aName, League aLeague)
  {
    name = aName;
    players = new ArrayList<Player>();
    games = new ArrayList<Game>();
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

  public static int minimumNumberOfGames()
  {
    return 0;
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    games.add(aGame);
    if (aGame.indexOfCompetitor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGame.addCompetitor(this);
      if (!wasAdded)
      {
        games.remove(aGame);
      }
    }
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    if (!games.contains(aGame))
    {
      return wasRemoved;
    }

    int oldIndex = games.indexOf(aGame);
    games.remove(oldIndex);
    if (aGame.indexOfCompetitor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGame.removeCompetitor(this);
      if (!wasRemoved)
      {
        games.add(oldIndex,aGame);
      }
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
    ArrayList<Game> copyOfGames = new ArrayList<Game>(games);
    games.clear();
    for(Game aGame : copyOfGames)
    {
      if (aGame.numberOfCompetitors() <= Game.minimumNumberOfCompetitors())
      {
        aGame.delete();
      }
      else
      {
        aGame.removeCompetitor(this);
      }
    }
    League placeholderLeague = league;
    this.league = null;
    placeholderLeague.removeTeam(this);
  }


  /**
   * Java Code //
   * 
   * Method returning the total number of Shots made by Players in the Team.
   * 
   * @return total number of Shots
   * 
   * @see Shot
   * @see Player
   */
  // line 330 "../../../../../ScoreKeeper.ump"
   public int getTotalShotCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.numberOfShots();
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of successful Shots made by Players in the Team.
   * 
   * @return total number of successful shots
   * 
   * @see Shot
   * @see Player
   */
  // line 348 "../../../../../ScoreKeeper.ump"
   public int getSuccessfulShotCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.getSuccessfulShotCount();
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of points earned by the Team
   * 
   * @return total number of points earned by the Team
   * 
   * @see Game
   */
  // line 365 "../../../../../ScoreKeeper.ump"
   public int getPoints(){
    int res = 0;
    for(Game game : this.getGames())
    {
      if(game.getVictor() == null)
      {
        res += 1;
      }
      else if(game.getVictor() == this)
      {
        res += 3;
      }
      else
      {
      }
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of Infractions made by Players in the Team.
   * 
   * @return total number of Infractions
   * 
   * @see Infraction
   * @see Player
   */
  // line 393 "../../../../../ScoreKeeper.ump"
   public int getTotalInfractionCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.numberOfInfractions();
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of penalty shots caused by Players in the Team.
   * 
   * @return total number of penalty shots caused by Players in the Team
   * 
   * @see Infraction
   * @see Player
   */
  // line 411 "../../../../../ScoreKeeper.ump"
   public int getPenaltyShotCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.getPenaltyShotCount();
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of red cards caused by Players in the Team.
   * 
   * @return total number of red cards caused by Players in the Team
   * 
   * @see Infraction
   * @see Player
   */
  // line 429 "../../../../../ScoreKeeper.ump"
   public int getRedInfractionCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.getRedInfractionCount();
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of yellow cards caused by Players in the Team.
   * 
   * @return total number of yellow cards caused by Players in the Team
   * 
   * @see Infraction
   * @see Player
   */
  // line 447 "../../../../../ScoreKeeper.ump"
   public int getYellowInfractionCount(){
    int res = 0;
    for(Player player : this.getPlayers())
    {
      res += player.getYellowInfractionCount();
    }
    return res;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 460 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_NAME = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
  		return other.name.compareTo(one.name);
  	}};
// line 474 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_SHOTS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getTotalShotCount() - other.getTotalShotCount();
    }};
// line 488 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_SUCCESSFUL_SHOTS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getSuccessfulShotCount() - other.getSuccessfulShotCount();
    }};
// line 502 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_POINTS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getPoints() - other.getPoints(); 
    }};
// line 516 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_TOTAL_INFRACTIONS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getTotalInfractionCount() - other.getTotalInfractionCount();
    }};
// line 530 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_PENALTY_SHOTS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getPenaltyShotCount() - other.getPenaltyShotCount();
    }};
// line 544 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_RED_CARDS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getRedInfractionCount() - other.getRedInfractionCount();
    }};
// line 558 ../../../../../ScoreKeeper.ump
  public static Comparator<Team> COMPARE_BY_YELLOW_CARDS = new Comparator<Team>() {public int compare(Team one, Team other)
  	{
      return one.getYellowInfractionCount() - other.getYellowInfractionCount();
    }};

  
}