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
  private String infractionType;
  private boolean penaltyShot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Infraction(String aInfractionType, boolean aPenaltyShot)
  {
    infractionType = aInfractionType;
    penaltyShot = aPenaltyShot;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInfractionType(String aInfractionType)
  {
    boolean wasSet = false;
    infractionType = aInfractionType;
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

  public String getInfractionType()
  {
    return infractionType;
  }

  public boolean getPenaltyShot()
  {
    return penaltyShot;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "infractionType" + ":" + getInfractionType()+ "," +
            "penaltyShot" + ":" + getPenaltyShot()+ "]"
     + outputString;
  }
}