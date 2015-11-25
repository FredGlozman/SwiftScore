/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/



// line 41 "ScoreKeeper.ump"
// line 88 "ScoreKeeper.ump"
public class Infraction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Infraction Attributes
  private String type;
  private boolean penaltyShot;
  private int time;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Infraction(String aType, boolean aPenaltyShot, int aTime)
  {
    type = aType;
    penaltyShot = aPenaltyShot;
    time = aTime;
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

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "type" + ":" + getType()+ "," +
            "penaltyShot" + ":" + getPenaltyShot()+ "," +
            "time" + ":" + getTime()+ "]"
     + outputString;
  }
}