/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 22 "ScoreKeeper.ump"
public class Team
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Team Attributes
  private String name;
  private int points;
  private int wins;
  private int losses;
  private int ties;
  private int successfulShots;
  private int failedShots;
  private int redCards;
  private int yellowCards;
  private int penaltyKicksCaused;
  private int successfulSaves;
  private int failedSaves;

  //Team Associations
  private List<Player> contains;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Team(String aName, int aPoints, int aWins, int aLosses, int aTies, int aSuccessfulShots, int aFailedShots, int aRedCards, int aYellowCards, int aPenaltyKicksCaused, int aSuccessfulSaves, int aFailedSaves)
  {
    name = aName;
    points = aPoints;
    wins = aWins;
    losses = aLosses;
    ties = aTies;
    successfulShots = aSuccessfulShots;
    failedShots = aFailedShots;
    redCards = aRedCards;
    yellowCards = aYellowCards;
    penaltyKicksCaused = aPenaltyKicksCaused;
    successfulSaves = aSuccessfulSaves;
    failedSaves = aFailedSaves;
    contains = new ArrayList<Player>();
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

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setWins(int aWins)
  {
    boolean wasSet = false;
    wins = aWins;
    wasSet = true;
    return wasSet;
  }

  public boolean setLosses(int aLosses)
  {
    boolean wasSet = false;
    losses = aLosses;
    wasSet = true;
    return wasSet;
  }

  public boolean setTies(int aTies)
  {
    boolean wasSet = false;
    ties = aTies;
    wasSet = true;
    return wasSet;
  }

  public boolean setSuccessfulShots(int aSuccessfulShots)
  {
    boolean wasSet = false;
    successfulShots = aSuccessfulShots;
    wasSet = true;
    return wasSet;
  }

  public boolean setFailedShots(int aFailedShots)
  {
    boolean wasSet = false;
    failedShots = aFailedShots;
    wasSet = true;
    return wasSet;
  }

  public boolean setRedCards(int aRedCards)
  {
    boolean wasSet = false;
    redCards = aRedCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setYellowCards(int aYellowCards)
  {
    boolean wasSet = false;
    yellowCards = aYellowCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenaltyKicksCaused(int aPenaltyKicksCaused)
  {
    boolean wasSet = false;
    penaltyKicksCaused = aPenaltyKicksCaused;
    wasSet = true;
    return wasSet;
  }

  public boolean setSuccessfulSaves(int aSuccessfulSaves)
  {
    boolean wasSet = false;
    successfulSaves = aSuccessfulSaves;
    wasSet = true;
    return wasSet;
  }

  public boolean setFailedSaves(int aFailedSaves)
  {
    boolean wasSet = false;
    failedSaves = aFailedSaves;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPoints()
  {
    return points;
  }

  public int getWins()
  {
    return wins;
  }

  public int getLosses()
  {
    return losses;
  }

  public int getTies()
  {
    return ties;
  }

  public int getSuccessfulShots()
  {
    return successfulShots;
  }

  public int getFailedShots()
  {
    return failedShots;
  }

  public int getRedCards()
  {
    return redCards;
  }

  public int getYellowCards()
  {
    return yellowCards;
  }

  public int getPenaltyKicksCaused()
  {
    return penaltyKicksCaused;
  }

  public int getSuccessfulSaves()
  {
    return successfulSaves;
  }

  public int getFailedSaves()
  {
    return failedSaves;
  }

  public Player getContain(int index)
  {
    Player aContain = contains.get(index);
    return aContain;
  }

  public List<Player> getContains()
  {
    List<Player> newContains = Collections.unmodifiableList(contains);
    return newContains;
  }

  public int numberOfContains()
  {
    int number = contains.size();
    return number;
  }

  public boolean hasContains()
  {
    boolean has = contains.size() > 0;
    return has;
  }

  public int indexOfContain(Player aContain)
  {
    int index = contains.indexOf(aContain);
    return index;
  }

  public static int minimumNumberOfContains()
  {
    return 0;
  }

  public boolean addContain(Player aContain)
  {
    boolean wasAdded = false;
    if (contains.contains(aContain)) { return false; }
    contains.add(aContain);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContain(Player aContain)
  {
    boolean wasRemoved = false;
    if (contains.contains(aContain))
    {
      contains.remove(aContain);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addContainAt(Player aContain, int index)
  {  
    boolean wasAdded = false;
    if(addContain(aContain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContains()) { index = numberOfContains() - 1; }
      contains.remove(aContain);
      contains.add(index, aContain);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainAt(Player aContain, int index)
  {
    boolean wasAdded = false;
    if(contains.contains(aContain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContains()) { index = numberOfContains() - 1; }
      contains.remove(aContain);
      contains.add(index, aContain);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainAt(aContain, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    contains.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "points" + ":" + getPoints()+ "," +
            "wins" + ":" + getWins()+ "," +
            "losses" + ":" + getLosses()+ "," +
            "ties" + ":" + getTies()+ "," +
            "successfulShots" + ":" + getSuccessfulShots()+ "," +
            "failedShots" + ":" + getFailedShots()+ "," +
            "redCards" + ":" + getRedCards()+ "," +
            "yellowCards" + ":" + getYellowCards()+ "," +
            "penaltyKicksCaused" + ":" + getPenaltyKicksCaused()+ "," +
            "successfulSaves" + ":" + getSuccessfulSaves()+ "," +
            "failedSaves" + ":" + getFailedSaves()+ "]"
     + outputString;
  }
}