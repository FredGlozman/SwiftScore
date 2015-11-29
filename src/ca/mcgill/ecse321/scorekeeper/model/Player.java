/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;

/**
 * 
 * Domain object that stores data relating to players.
 * Each Player has multiple Shots an Infractions.
 * Each Player also belongs to a Team and a League.
 * This object is also able to calculate statistics
 * about the players in the team (e.g. their total
 * number of successful goals). Note that a Goalie
 * is also a kind of Player.
 * 
 * @param name          the name of the Player
 * @param jerseyNumber  the Player's jersey number
 * 
 * @see Team
 * @see Shot
 * @see Infraction
 * @see Goalie
 * @see League
 */
// line 21 "../../../../../ScoreKeeper.ump"
// line 614 "../../../../../ScoreKeeper.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String name;
  private int jerseyNumber;

  //Player Associations
  private List<Shot> shots;
  private List<Infraction> infractions;
  private Team team;
  private League league;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName, int aJerseyNumber, Team aTeam, League aLeague)
  {
    name = aName;
    jerseyNumber = aJerseyNumber;
    shots = new ArrayList<Shot>();
    infractions = new ArrayList<Infraction>();
    boolean didAddTeam = setTeam(aTeam);
    if (!didAddTeam)
    {
      throw new RuntimeException("Unable to create player due to team");
    }
    boolean didAddLeague = setLeague(aLeague);
    if (!didAddLeague)
    {
      throw new RuntimeException("Unable to create player due to league");
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

  public boolean setJerseyNumber(int aJerseyNumber)
  {
    boolean wasSet = false;
    jerseyNumber = aJerseyNumber;
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

  public Shot getShot(int index)
  {
    Shot aShot = shots.get(index);
    return aShot;
  }

  /**
   * Umple Code//
   */
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

  public Team getTeam()
  {
    return team;
  }

  public League getLeague()
  {
    return league;
  }

  public static int minimumNumberOfShots()
  {
    return 0;
  }

  public Shot addShot(boolean aGoal, int aTime, Goalie aGoalie)
  {
    return new Shot(aGoal, aTime, this, aGoalie);
  }

  public boolean addShot(Shot aShot)
  {
    boolean wasAdded = false;
    if (shots.contains(aShot)) { return false; }
    if (shots.contains(aShot)) { return false; }
    Player existingPlayer = aShot.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aShot.setPlayer(this);
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
    //Unable to remove aShot, as it must always have a player
    if (!this.equals(aShot.getPlayer()))
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

  public Infraction addInfraction(Color aColor, boolean aPenaltyShot, int aTime)
  {
    return new Infraction(aColor, aPenaltyShot, aTime, this);
  }

  public boolean addInfraction(Infraction aInfraction)
  {
    boolean wasAdded = false;
    if (infractions.contains(aInfraction)) { return false; }
    if (infractions.contains(aInfraction)) { return false; }
    Player existingPlayer = aInfraction.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aInfraction.setPlayer(this);
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
    //Unable to remove aInfraction, as it must always have a player
    if (!this.equals(aInfraction.getPlayer()))
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

  public boolean setTeam(Team aTeam)
  {
    boolean wasSet = false;
    if (aTeam == null)
    {
      return wasSet;
    }

    Team existingTeam = team;
    team = aTeam;
    if (existingTeam != null && !existingTeam.equals(aTeam))
    {
      existingTeam.removePlayer(this);
    }
    team.addPlayer(this);
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
      existingLeague.removePlayer(this);
    }
    league.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
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
    Team placeholderTeam = team;
    this.team = null;
    placeholderTeam.removePlayer(this);
    League placeholderLeague = league;
    this.league = null;
    placeholderLeague.removePlayer(this);
  }


  /**
   * Java Code //
   * 
   * Method returning the total number of successful Shots.
   * 
   * @return total number of successful Shots
   * 
   * @see Shot
   */
  // line 41 "../../../../../ScoreKeeper.ump"
   public int getSuccessfulShotCount(){
    int res = 0;
    for(Shot shot : this.getShots())
    {
      if(shot.getGoal())
      {
        res++;
      }
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of Infractions resulting in penalty kicks.
   * 
   * @return total number of Infractions resulting in penalty kicks
   * 
   * @see Infraction
   */
  // line 61 "../../../../../ScoreKeeper.ump"
   public int getPenaltyShotCount(){
    int res = 0;
    for(Infraction inf : this.getInfractions())
    {
      if(inf.getPenaltyShot())
      {
        res++;
      }
    }
    return res;
  }


  /**
   * 
   * Method returning the total number of Infractions resulting in red cards.
   * 
   * @return total number of Infractions resulting in red cards
   * 
   * @see Infraction
   */
  // line 81 "../../../../../ScoreKeeper.ump"
   public int getRedInfractionCount(){
    return this.getColorInfractionCount(Color.RED);
  }


  /**
   * 
   * Method returning the total number of Infractions resulting in yellow cards.
   * 
   * @return total number of Infractions resulting in yellow cards
   * 
   * @see Infraction
   */
  // line 93 "../../../../../ScoreKeeper.ump"
   public int getYellowInfractionCount(){
    return this.getColorInfractionCount(Color.YELLOW);
  }


  /**
   * 
   * Method returning the total number of Infractions resulting in a specified color of card .
   * 
   * @param color color of the card to count infractions for
   * 
   * @return total number of Infractions resulting in a specified color of card
   * 
   * @see Infraction
   */
  // line 107 "../../../../../ScoreKeeper.ump"
   private int getColorInfractionCount(Color color){
    int res = 0;
    for(Infraction inf : this.getInfractions())
    {
      if(inf.getColor() == color)
      {
        res++;
      }
    }
    return res;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "jerseyNumber" + ":" + getJerseyNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "team = "+(getTeam()!=null?Integer.toHexString(System.identityHashCode(getTeam())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 123 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_NAME = new Comparator<Player>() {public int compare(Player one, Player other)
  	{
  		return one.name.compareTo(other.name);
  	}};
// line 136 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_JERSEY = new Comparator<Player>() {public int compare(Player one, Player other)
  	{
  		return one.jerseyNumber - other.jerseyNumber;
  	}};
// line 150 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_SHOTS = new Comparator<Player>() {public int compare(Player one, Player other)
     {
       return one.numberOfShots() - other.numberOfShots();
     }};
// line 164 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_SUCCESSFUL_SHOTS = new Comparator<Player>() {public int compare(Player one, Player other)
    {
      return one.getSuccessfulShotCount() - other.getSuccessfulShotCount();
    }};
// line 178 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_TOTAL_INFRACTIONS = new Comparator<Player>() {public int compare(Player one, Player other)
    {
      return one.numberOfShots() - other.numberOfShots();
    }};
// line 192 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_PENALTY_SHOTS = new Comparator<Player>() {public int compare(Player one, Player other)
    {
      return one.getPenaltyShotCount() - other.getPenaltyShotCount();
    }};
// line 206 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_RED_CARDS = new Comparator<Player>() {public int compare(Player one, Player other)
    {
      return one.getRedInfractionCount() - other.getRedInfractionCount();
    }};
// line 220 ../../../../../ScoreKeeper.ump
  public static Comparator<Player> COMPARE_BY_YELLOW_CARDS = new Comparator<Player>() {public int compare(Player one, Player other)
    {
      return one.getYellowInfractionCount() - other.getYellowInfractionCount();
    }};

  
}