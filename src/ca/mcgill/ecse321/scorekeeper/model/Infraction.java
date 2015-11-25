/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;

// line 43 "../../../../../ScoreKeeper.ump"
// line 90 "../../../../../ScoreKeeper.ump"
public class Infraction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Infraction Attributes
  private String type;
  private boolean penaltyShot;
  private int time;

  //Infraction Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Infraction(String aType, boolean aPenaltyShot, int aTime, Player aPlayer)
  {
    type = aType;
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

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
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

  public String getType()
  {
    return type;
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
            "type" + ":" + getType()+ "," +
            "penaltyShot" + ":" + getPenaltyShot()+ "," +
            "time" + ":" + getTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null")
     + outputString;
  }
}