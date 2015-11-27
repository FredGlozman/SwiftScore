/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;

/**
 * 
 * Domain object that stores data relating a Game.
 * Two teams play a Game. Each Game takes place in a League.
 * 
 * @param statTime  start time of the game in seconds since the Unix epoch 
 * @param endTime   start time of the game in seconds since the Unix epoch
 * @param location  location of the game
 * @param score     two dimensional array containing the game's score 
 * (uses indices from the Game's Team array)
 * @param victor    array index of the game's victor
 * 
 * @see Team 
 * @see League
 */
// line 298 "../../../../../ScoreKeeper.ump"
// line 377 "../../../../../ScoreKeeper.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int startTime;
  private int endTime;
  private String location;
  private List<Integer> score;
  private int victor;

  //Game Associations
  private List<Team> games;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aStartTime, int aEndTime, String aLocation, int aVictor, League aLeague)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    location = aLocation;
    score = new ArrayList<Integer>();
    victor = aVictor;
    games = new ArrayList<Team>();
    boolean didAddLeague = setLeague(aLeague);
    if (!didAddLeague)
    {
      throw new RuntimeException("Unable to create game due to league");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(int aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(int aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean addScore(Integer aScore)
  {
    boolean wasAdded = false;
    wasAdded = score.add(aScore);
    return wasAdded;
  }

  public boolean removeScore(Integer aScore)
  {
    boolean wasRemoved = false;
    wasRemoved = score.remove(aScore);
    return wasRemoved;
  }

  public boolean setVictor(int aVictor)
  {
    boolean wasSet = false;
    victor = aVictor;
    wasSet = true;
    return wasSet;
  }

  public int getStartTime()
  {
    return startTime;
  }

  public int getEndTime()
  {
    return endTime;
  }

  public String getLocation()
  {
    return location;
  }

  public Integer getScore(int index)
  {
    Integer aScore = score.get(index);
    return aScore;
  }

  public Integer[] getScore()
  {
    Integer[] newScore = score.toArray(new Integer[score.size()]);
    return newScore;
  }

  public int numberOfScore()
  {
    int number = score.size();
    return number;
  }

  public boolean hasScore()
  {
    boolean has = score.size() > 0;
    return has;
  }

  public int indexOfScore(Integer aScore)
  {
    int index = score.indexOf(aScore);
    return index;
  }

  public int getVictor()
  {
    return victor;
  }

  public Team getGame(int index)
  {
    Team aGame = games.get(index);
    return aGame;
  }

  public List<Team> getGames()
  {
    List<Team> newGames = Collections.unmodifiableList(games);
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

  public int indexOfGame(Team aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }

  public League getLeague()
  {
    return league;
  }

  public boolean isNumberOfGamesValid()
  {
    boolean isValid = numberOfGames() >= minimumNumberOfGames() && numberOfGames() <= maximumNumberOfGames();
    return isValid;
  }

  public static int requiredNumberOfGames()
  {
    return 2;
  }

  public static int minimumNumberOfGames()
  {
    return 2;
  }

  public static int maximumNumberOfGames()
  {
    return 2;
  }

  public Team addGame(String aName, League aLeague)
  {
    if (numberOfGames() >= maximumNumberOfGames())
    {
      return null;
    }
    else
    {
      return new Team(aName, this, aLeague);
    }
  }

  public boolean addGame(Team aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    if (numberOfGames() >= maximumNumberOfGames())
    {
      return wasAdded;
    }

    Game existingGame = aGame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfGames() <= minimumNumberOfGames())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aGame.setGame(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Team aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a game
    if (this.equals(aGame.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfGames() <= minimumNumberOfGames())
    {
      return wasRemoved;
    }
    games.remove(aGame);
    wasRemoved = true;
    return wasRemoved;
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
      existingLeague.removeGame(this);
    }
    league.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=games.size(); i > 0; i--)
    {
      Team aGame = games.get(i - 1);
      aGame.delete();
    }
    League placeholderLeague = league;
    this.league = null;
    placeholderLeague.removeGame(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "location" + ":" + getLocation()+ "," +
            "victor" + ":" + getVictor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null")
     + outputString;
  }
}