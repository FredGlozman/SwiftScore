/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 25 "ScoreKeeper.ump"
// line 77 "ScoreKeeper.ump"
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
  private Team victor;

  //Game Associations
  private List<Team> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aStartTime, int aEndTime, String aLocation, Team aVictor, Team... allGames)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    location = aLocation;
    score = new ArrayList<Integer>();
    victor = aVictor;
    games = new ArrayList<Team>();
    boolean didAddGames = setGames(allGames);
    if (!didAddGames)
    {
      throw new RuntimeException("Unable to create Game, must have 2 games");
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

  public boolean setVictor(Team aVictor)
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

  public Team getVictor()
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

  public boolean setGames(Team... newGames)
  {
    boolean wasSet = false;
    ArrayList<Team> verifiedGames = new ArrayList<Team>();
    for (Team aGame : newGames)
    {
      if (verifiedGames.contains(aGame))
      {
        continue;
      }
      verifiedGames.add(aGame);
    }

    if (verifiedGames.size() != newGames.length || verifiedGames.size() != requiredNumberOfGames())
    {
      return wasSet;
    }

    games.clear();
    games.addAll(verifiedGames);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    games.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "victor" + "=" + (getVictor() != null ? !getVictor().equals(this)  ? getVictor().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}