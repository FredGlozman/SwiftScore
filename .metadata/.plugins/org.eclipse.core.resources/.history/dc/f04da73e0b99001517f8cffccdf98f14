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
 * @param victor    array index of the game's victor. -1 if there is a tie
 * 
 * @see Team 
 * @see League
 */
// line 581 "../../../../../ScoreKeeper.ump"
// line 706 "../../../../../ScoreKeeper.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int startTime;
  private int endTime;
  private String location;

  //Game Associations
  private List<Team> competitors;
  private List<Shot> shots;
  private List<Infraction> infractions;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    location = aLocation;
    competitors = new ArrayList<Team>();
    boolean didAddCompetitors = setCompetitors(allCompetitors);
    if (!didAddCompetitors)
    {
      throw new RuntimeException("Unable to create Game, must have 2 competitors");
    }
    shots = new ArrayList<Shot>();
    infractions = new ArrayList<Infraction>();
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

  public Team getCompetitor(int index)
  {
    Team aCompetitor = competitors.get(index);
    return aCompetitor;
  }

  /**
   * Umple Code //
   */
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

  public Shot getShot(int index)
  {
    Shot aShot = shots.get(index);
    return aShot;
  }

  public List<Shot> getShots()
  {
    List<Shot> newShots = Collections.unmodifiableList(shots);
    return newShots;
  }

  public int numberOfShots()
  {
    int number = shots.size();
    return number;
  }

  public boolean hasShots()
  {
    boolean has = shots.size() > 0;
    return has;
  }

  public int indexOfShot(Shot aShot)
  {
    int index = shots.indexOf(aShot);
    return index;
  }

  public Infraction getInfraction(int index)
  {
    Infraction aInfraction = infractions.get(index);
    return aInfraction;
  }

  public List<Infraction> getInfractions()
  {
    List<Infraction> newInfractions = Collections.unmodifiableList(infractions);
    return newInfractions;
  }

  public int numberOfInfractions()
  {
    int number = infractions.size();
    return number;
  }

  public boolean hasInfractions()
  {
    boolean has = infractions.size() > 0;
    return has;
  }

  public int indexOfInfraction(Infraction aInfraction)
  {
    int index = infractions.indexOf(aInfraction);
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

  public static int minimumNumberOfShots()
  {
    return 0;
  }

  public Shot addShot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie)
  {
    return new Shot(aGoal, aTime, aPlayer, aGoalie, this);
  }

  public boolean addShot(Shot aShot)
  {
    boolean wasAdded = false;
    if (shots.contains(aShot)) { return false; }
    Game existingGame = aShot.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aShot.setGame(this);
    }
    else
    {
      shots.add(aShot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShot(Shot aShot)
  {
    boolean wasRemoved = false;
    //Unable to remove aShot, as it must always have a game
    if (!this.equals(aShot.getGame()))
    {
      shots.remove(aShot);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addShotAt(Shot aShot, int index)
  {  
    boolean wasAdded = false;
    if(addShot(aShot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShots()) { index = numberOfShots() - 1; }
      shots.remove(aShot);
      shots.add(index, aShot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShotAt(Shot aShot, int index)
  {
    boolean wasAdded = false;
    if(shots.contains(aShot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShots()) { index = numberOfShots() - 1; }
      shots.remove(aShot);
      shots.add(index, aShot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShotAt(aShot, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfInfractions()
  {
    return 0;
  }

  public Infraction addInfraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer)
  {
    return new Infraction(aColor, aPenaltyShot, aTime, aPlayer, this);
  }

  public boolean addInfraction(Infraction aInfraction)
  {
    boolean wasAdded = false;
    if (infractions.contains(aInfraction)) { return false; }
    Game existingGame = aInfraction.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aInfraction.setGame(this);
    }
    else
    {
      infractions.add(aInfraction);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInfraction(Infraction aInfraction)
  {
    boolean wasRemoved = false;
    //Unable to remove aInfraction, as it must always have a game
    if (!this.equals(aInfraction.getGame()))
    {
      infractions.remove(aInfraction);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addInfractionAt(Infraction aInfraction, int index)
  {  
    boolean wasAdded = false;
    if(addInfraction(aInfraction))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInfractions()) { index = numberOfInfractions() - 1; }
      infractions.remove(aInfraction);
      infractions.add(index, aInfraction);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInfractionAt(Infraction aInfraction, int index)
  {
    boolean wasAdded = false;
    if(infractions.contains(aInfraction))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInfractions()) { index = numberOfInfractions() - 1; }
      infractions.remove(aInfraction);
      infractions.add(index, aInfraction);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInfractionAt(aInfraction, index);
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
    for(int i=shots.size(); i > 0; i--)
    {
      Shot aShot = shots.get(i - 1);
      aShot.delete();
    }
    for(int i=infractions.size(); i > 0; i--)
    {
      Infraction aInfraction = infractions.get(i - 1);
      aInfraction.delete();
    }
    League placeholderLeague = league;
    this.league = null;
    placeholderLeague.removeGame(this);
  }


  /**
   * Java Code //
   */
  // line 596 "../../../../../ScoreKeeper.ump"
   public Team getVictor(){
    int[] score = this.getScore();
    if(score[0] == score[1])
    {
      return null;
    }
    else if(score[0] > score[1])
    {
      return this.getCompetitor(0);
    }
    else
    {
      return this.getCompetitor(1);
    }
  }

  // line 613 "../../../../../ScoreKeeper.ump"
   public int[] getScore(){
    int res0 = 0;
    int res1 = 0;
    for(Shot shot : this.getShots())
    {
      if(shot.getGame() == this && shot.getGoal())
      {
        if(this.getCompetitor(0).getPlayers().contains(shot.getPlayer()))
        {
          res0++;
        }
        else if(this.getCompetitor(1).getPlayers().contains(shot.getPlayer()))
        {
          res1++;
        }
        else
        {
        }
      }
    }
    int[] res = {res0, res1};
    return res;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null")
     + outputString;
  }
}