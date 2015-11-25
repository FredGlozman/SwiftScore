/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/



// line 35 "ScoreKeeper.ump"
// line 83 "ScoreKeeper.ump"
public class Shot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shot Attributes
  private boolean goal;
  private int time;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shot(boolean aGoal, int aTime)
  {
    goal = aGoal;
    time = aTime;
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

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "goal" + ":" + getGoal()+ "," +
            "time" + ":" + getTime()+ "]"
     + outputString;
  }
}