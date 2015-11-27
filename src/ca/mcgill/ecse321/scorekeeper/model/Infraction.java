/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;

/**
 * 
 * Domain object that stores data relating to an Infraction.
 * Infractions are commited by Players.
 * 
 * @param color        indicates whether the infraction resulted in a red or yellow card
 * @param penaltyShot  indicates whether the infraction resulted in a penalty shot
 * @param time         time of the infraction in milliseconds since the start of the game
 */
// line 253 "../../../../../ScoreKeeper.ump"
// line 309 "../../../../../ScoreKeeper.ump"
public class Infraction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Infraction Attributes
  private Color color;
  private boolean penaltyShot;
  private int time;

  //Infraction Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer)
  {
    color = aColor;
    penaltyShot = aPenaltyShot;
    time = aTime;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create infraction due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(Color aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenaltyShot(boolean aPenaltyShot)
  {
    boolean wasSet = false;
    penaltyShot = aPenaltyShot;
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

  public Color getColor()
  {
    return color;
  }

  public boolean getPenaltyShot()
  {
    return penaltyShot;
  }

  public int getTime()
  {
    return time;
  }

  public Player getPlayer()
  {
    return player;
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
      existingPlayer.removeInfraction(this);
    }
    player.addInfraction(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player placeholderPlayer = player;
    this.player = null;
    placeholderPlayer.removeInfraction(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "penaltyShot" + ":" + getPenaltyShot()+ "," +
            "time" + ":" + getTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null")
     + outputString;
  }
}