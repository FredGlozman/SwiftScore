/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;

/**
 * 
 * Domain object that stores data relating to a Shot.
 * Shots are taken by Players and saved by Goalies.
 * 
 * @param goal  indicates whether the goal was successful
 * @param time  time of the goal in milliseconds since the start of the Game
 * 
 * @see Player
 * @see Goalie
 */
// line 576 "../../../../../ScoreKeeper.ump"
// line 641 "../../../../../ScoreKeeper.ump"
public class Shot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shot Attributes
  private boolean goal;
  private int time;

  //Shot Associations
  private Player player;
  private Goalie goalie;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie)
  {
    goal = aGoal;
    time = aTime;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create shot due to player");
    }
    boolean didAddGoalie = setGoalie(aGoalie);
    if (!didAddGoalie)
    {
      throw new RuntimeException("Unable to create save due to goalie");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGoal(boolean aGoal)
  {
    boolean wasSet = false;
    goal = aGoal;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(int aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean getGoal()
  {
    return goal;
  }

  public int getTime()
  {
    return time;
  }

  public Player getPlayer()
  {
    return player;
  }

  public Goalie getGoalie()
  {
    return goalie;
  }

  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeShot(this);
    }
    player.addShot(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setGoalie(Goalie aGoalie)
  {
    boolean wasSet = false;
    if (aGoalie == null)
    {
      return wasSet;
    }

    Goalie existingGoalie = goalie;
    goalie = aGoalie;
    if (existingGoalie != null && !existingGoalie.equals(aGoalie))
    {
      existingGoalie.removeSave(this);
    }
    goalie.addSave(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player placeholderPlayer = player;
    this.player = null;
    placeholderPlayer.removeShot(this);
    Goalie placeholderGoalie = goalie;
    this.goalie = null;
    placeholderGoalie.removeSave(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "goal" + ":" + getGoal()+ "," +
            "time" + ":" + getTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "goalie = "+(getGoalie()!=null?Integer.toHexString(System.identityHashCode(getGoalie())):"null")
     + outputString;
  }
}