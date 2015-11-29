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
// line 555 "../../../../../ScoreKeeper.ump"
// line 634 "../../../../../ScoreKeeper.ump"
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
  private List<Team> competitors;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aStartTime, int aEndTime, String aLocation, int aVictor, League aLeague, Team... allCompetitors)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    location = aLocation;
    score = new ArrayList<Integer>();
    victor = aVictor;
    competitors = new ArrayList<Team>();
    boolean didAddCompetitors = setCompetitors(allCompetitors);
    if (!didAddCompetitors)
    {
      throw new RuntimeException("Unable to create Game, must have 2 competitors");
    }
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

  public Team getCompetitor(int index)
  {
    Team aCompetitor = competitors.get(index);
    return aCompetitor;
  }

  public List<Team> getCompetitors()
  {
    List<Team> newCompetitors = Collections.unmodifiableList(competitors);
    return newCompetitors;
  }

  public int numberOfCompetitors()
  {
    int number = competitors.size();
    return number;
  }

  public boolean hasCompetitors()
  {
    boolean has = competitors.size() > 0;
    return has;
  }

  public int indexOfCompetitor(Team aCompetitor)
  {
    int index = competitors.indexOf(aCompetitor);
    return index;
  }

  public League getLeague()
  {
    return league;
  }

  public boolean isNumberOfCompetitorsValid()
  {
    boolean isValid = numberOfCompetitors() >= minimumNumberOfCompetitors() && numberOfCompetitors() <= maximumNumberOfCompetitors();
    return isValid;
  }

  public static int requiredNumberOfCompetitors()
  {
    return 2;
  }

  public static int minimumNumberOfCompetitors()
  {
    return 2;
  }

  public static int maximumNumberOfCompetitors()
  {
    return 2;
  }

  public boolean addCompetitor(Team aCompetitor)
  {
    boolean wasAdded = false;
    if (competitors.contains(aCompetitor)) { return false; }
    if (numberOfCompetitors() >= maximumNumberOfCompetitors())
    {
      return wasAdded;
    }

    competitors.add(aCompetitor);
    if (aCompetitor.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCompetitor.addGame(this);
      if (!wasAdded)
      {
        competitors.remove(aCompetitor);
      }
    }
    return wasAdded;
  }

  public boolean removeCompetitor(Team aCompetitor)
  {
    boolean wasRemoved = false;
    if (!competitors.contains(aCompetitor))
    {
      return wasRemoved;
    }

    if (numberOfCompetitors() <= minimumNumberOfCompetitors())
    {
      return wasRemoved;
    }

    int oldIndex = competitors.indexOf(aCompetitor);
    competitors.remove(oldIndex);
    if (aCompetitor.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCompetitor.removeGame(this);
      if (!wasRemoved)
      {
        competitors.add(oldIndex,aCompetitor);
      }
    }
    return wasRemoved;
  }

  public boolean setCompetitors(Team... newCompetitors)
  {
    boolean wasSet = false;
    ArrayList<Team> verifiedCompetitors = new ArrayList<Team>();
    for (Team aCompetitor : newCompetitors)
    {
      if (verifiedCompetitors.contains(aCompetitor))
      {
        continue;
      }
      verifiedCompetitors.add(aCompetitor);
    }

    if (verifiedCompetitors.size() != newCompetitors.length || verifiedCompetitors.size() < minimumNumberOfCompetitors() || verifiedCompetitors.size() > maximumNumberOfCompetitors())
    {
      return wasSet;
    }

    ArrayList<Team> oldCompetitors = new ArrayList<Team>(competitors);
    competitors.clear();
    for (Team aNewCompetitor : verifiedCompetitors)
    {
      competitors.add(aNewCompetitor);
      if (oldCompetitors.contains(aNewCompetitor))
      {
        oldCompetitors.remove(aNewCompetitor);
      }
      else
      {
        aNewCompetitor.addGame(this);
      }
    }

    for (Team anOldCompetitor : oldCompetitors)
    {
      anOldCompetitor.removeGame(this);
    }
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
      existingLeague.removeGame(this);
    }
    league.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Team> copyOfCompetitors = new ArrayList<Team>(competitors);
    competitors.clear();
    for(Team aCompetitor : copyOfCompetitors)
    {
      aCompetitor.removeGame(this);
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