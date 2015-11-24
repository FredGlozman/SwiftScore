/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/



// line 49 "ScoreKeeper.ump"
public class Shot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shot Attributes
  private boolean isGoal;
  private int shotTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shot(boolean aIsGoal, int aShotTime)
  {
    isGoal = aIsGoal;
    shotTime = aShotTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsGoal(boolean aIsGoal)
  {
    boolean wasSet = false;
    isGoal = aIsGoal;
    wasSet = true;
    return wasSet;
  }

  public boolean setShotTime(int aShotTime)
  {
    boolean wasSet = false;
    shotTime = aShotTime;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsGoal()
  {
    return isGoal;
  }

  public int getShotTime()
  {
    return shotTime;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "isGoal" + ":" + getIsGoal()+ "," +
            "shotTime" + ":" + getShotTime()+ "]"
     + outputString;
  }
}