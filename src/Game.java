/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 39 "ScoreKeeper.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int startTime;
  private int endTime;
  private String location;
  private List<int> score;
  private List<boolean> isVictor;

  //Game Associations
  private List<Team> matches;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aStartTime, int aEndTime, String aLocation, Team... allMatches)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    location = aLocation;
    score = new ArrayList<int>();
    isVictor = new ArrayList<boolean>();
    matches = new ArrayList<Team>();
    boolean didAddMatches = setMatches(allMatches);
    if (!didAddMatches)
    {
      throw new RuntimeException("Unable to create Game, must have 2 matches");
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

  public boolean addScore(int aScore)
  {
    boolean wasAdded = false;
    wasAdded = score.add(aScore);
    return wasAdded;
  }

  public boolean removeScore(int aScore)
  {
    boolean wasRemoved = false;
    wasRemoved = score.remove(aScore);
    return wasRemoved;
  }

  public boolean addIsVictor(boolean aIsVictor)
  {
    boolean wasAdded = false;
    wasAdded = isVictor.add(aIsVictor);
    return wasAdded;
  }

  public boolean removeIsVictor(boolean aIsVictor)
  {
    boolean wasRemoved = false;
    wasRemoved = isVictor.remove(aIsVictor);
    return wasRemoved;
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

  public int getScore(int index)
  {
    int aScore = score.get(index);
    return aScore;
  }

  public int[] getScore()
  {
    int[] newScore = score.toArray(new int[score.size()]);
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

  public int indexOfScore(int aScore)
  {
    int index = score.indexOf(aScore);
    return index;
  }

  public boolean getIsVictor(int index)
  {
    boolean aIsVictor = isVictor.get(index);
    return aIsVictor;
  }

  public boolean[] getIsVictor()
  {
    boolean[] newIsVictor = isVictor.toArray(new boolean[isVictor.size()]);
    return newIsVictor;
  }

  public int numberOfIsVictor()
  {
    int number = isVictor.size();
    return number;
  }

  public boolean hasIsVictor()
  {
    boolean has = isVictor.size() > 0;
    return has;
  }

  public int indexOfIsVictor(boolean aIsVictor)
  {
    int index = isVictor.indexOf(aIsVictor);
    return index;
  }

  public Team getMatche(int index)
  {
    Team aMatche = matches.get(index);
    return aMatche;
  }

  public List<Team> getMatches()
  {
    List<Team> newMatches = Collections.unmodifiableList(matches);
    return newMatches;
  }

  public int numberOfMatches()
  {
    int number = matches.size();
    return number;
  }

  public boolean hasMatches()
  {
    boolean has = matches.size() > 0;
    return has;
  }

  public int indexOfMatche(Team aMatche)
  {
    int index = matches.indexOf(aMatche);
    return index;
  }

  public static int requiredNumberOfMatches()
  {
    return 2;
  }

  public static int minimumNumberOfMatches()
  {
    return 2;
  }

  public static int maximumNumberOfMatches()
  {
    return 2;
  }

  public boolean setMatches(Team... newMatches)
  {
    boolean wasSet = false;
    ArrayList<Team> verifiedMatches = new ArrayList<Team>();
    for (Team aMatche : newMatches)
    {
      if (verifiedMatches.contains(aMatche))
      {
        continue;
      }
      verifiedMatches.add(aMatche);
    }

    if (verifiedMatches.size() != newMatches.length || verifiedMatches.size() != requiredNumberOfMatches())
    {
      return wasSet;
    }

    matches.clear();
    matches.addAll(verifiedMatches);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    matches.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "location" + ":" + getLocation()+ "]"
     + outputString;
  }
}