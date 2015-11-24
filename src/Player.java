/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 1 "ScoreKeeper.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String name;
  private int jerseyNumber;
  private int successfulShots;
  private int failedShots;
  private int redCards;
  private int yellowCards;
  private int penaltyKicksCaused;

  //Player Associations
  private List<Shot> takes;
  private List<Infraction> commits;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName, int aJerseyNumber, int aSuccessfulShots, int aFailedShots, int aRedCards, int aYellowCards, int aPenaltyKicksCaused)
  {
    name = aName;
    jerseyNumber = aJerseyNumber;
    successfulShots = aSuccessfulShots;
    failedShots = aFailedShots;
    redCards = aRedCards;
    yellowCards = aYellowCards;
    penaltyKicksCaused = aPenaltyKicksCaused;
    takes = new ArrayList<Shot>();
    commits = new ArrayList<Infraction>();
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

  public boolean setJerseyNumber(int aJerseyNumber)
  {
    boolean wasSet = false;
    jerseyNumber = aJerseyNumber;
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

  public String getName()
  {
    return name;
  }

  public int getJerseyNumber()
  {
    return jerseyNumber;
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

  public Shot getTake(int index)
  {
    Shot aTake = takes.get(index);
    return aTake;
  }

  public List<Shot> getTakes()
  {
    List<Shot> newTakes = Collections.unmodifiableList(takes);
    return newTakes;
  }

  public int numberOfTakes()
  {
    int number = takes.size();
    return number;
  }

  public boolean hasTakes()
  {
    boolean has = takes.size() > 0;
    return has;
  }

  public int indexOfTake(Shot aTake)
  {
    int index = takes.indexOf(aTake);
    return index;
  }

  public Infraction getCommit(int index)
  {
    Infraction aCommit = commits.get(index);
    return aCommit;
  }

  public List<Infraction> getCommits()
  {
    List<Infraction> newCommits = Collections.unmodifiableList(commits);
    return newCommits;
  }

  public int numberOfCommits()
  {
    int number = commits.size();
    return number;
  }

  public boolean hasCommits()
  {
    boolean has = commits.size() > 0;
    return has;
  }

  public int indexOfCommit(Infraction aCommit)
  {
    int index = commits.indexOf(aCommit);
    return index;
  }

  public static int minimumNumberOfTakes()
  {
    return 0;
  }

  public boolean addTake(Shot aTake)
  {
    boolean wasAdded = false;
    if (takes.contains(aTake)) { return false; }
    if (takes.contains(aTake)) { return false; }
    takes.add(aTake);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTake(Shot aTake)
  {
    boolean wasRemoved = false;
    if (takes.contains(aTake))
    {
      takes.remove(aTake);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTakeAt(Shot aTake, int index)
  {  
    boolean wasAdded = false;
    if(addTake(aTake))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTakes()) { index = numberOfTakes() - 1; }
      takes.remove(aTake);
      takes.add(index, aTake);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTakeAt(Shot aTake, int index)
  {
    boolean wasAdded = false;
    if(takes.contains(aTake))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTakes()) { index = numberOfTakes() - 1; }
      takes.remove(aTake);
      takes.add(index, aTake);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTakeAt(aTake, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCommits()
  {
    return 0;
  }

  public boolean addCommit(Infraction aCommit)
  {
    boolean wasAdded = false;
    if (commits.contains(aCommit)) { return false; }
    if (commits.contains(aCommit)) { return false; }
    commits.add(aCommit);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCommit(Infraction aCommit)
  {
    boolean wasRemoved = false;
    if (commits.contains(aCommit))
    {
      commits.remove(aCommit);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCommitAt(Infraction aCommit, int index)
  {  
    boolean wasAdded = false;
    if(addCommit(aCommit))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCommits()) { index = numberOfCommits() - 1; }
      commits.remove(aCommit);
      commits.add(index, aCommit);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCommitAt(Infraction aCommit, int index)
  {
    boolean wasAdded = false;
    if(commits.contains(aCommit))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCommits()) { index = numberOfCommits() - 1; }
      commits.remove(aCommit);
      commits.add(index, aCommit);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCommitAt(aCommit, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    takes.clear();
    commits.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "jerseyNumber" + ":" + getJerseyNumber()+ "," +
            "successfulShots" + ":" + getSuccessfulShots()+ "," +
            "failedShots" + ":" + getFailedShots()+ "," +
            "redCards" + ":" + getRedCards()+ "," +
            "yellowCards" + ":" + getYellowCards()+ "," +
            "penaltyKicksCaused" + ":" + getPenaltyKicksCaused()+ "]"
     + outputString;
  }
}