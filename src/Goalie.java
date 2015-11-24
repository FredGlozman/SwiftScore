/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 14 "ScoreKeeper.ump"
public class Goalie extends Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Goalie Attributes
  private int successfulSaves;
  private int failedSaves;

  //Goalie Associations
  private List<Shot> saves;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Goalie(String aName, int aJerseyNumber, int aSuccessfulShots, int aFailedShots, int aRedCards, int aYellowCards, int aPenaltyKicksCaused, int aSuccessfulSaves, int aFailedSaves)
  {
    super(aName, aJerseyNumber, aSuccessfulShots, aFailedShots, aRedCards, aYellowCards, aPenaltyKicksCaused);
    successfulSaves = aSuccessfulSaves;
    failedSaves = aFailedSaves;
    saves = new ArrayList<Shot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSuccessfulSaves(int aSuccessfulSaves)
  {
    boolean wasSet = false;
    successfulSaves = aSuccessfulSaves;
    wasSet = true;
    return wasSet;
  }

  public boolean setFailedSaves(int aFailedSaves)
  {
    boolean wasSet = false;
    failedSaves = aFailedSaves;
    wasSet = true;
    return wasSet;
  }

  public int getSuccessfulSaves()
  {
    return successfulSaves;
  }

  public int getFailedSaves()
  {
    return failedSaves;
  }

  public Shot getSave(int index)
  {
    Shot aSave = saves.get(index);
    return aSave;
  }

  public List<Shot> getSaves()
  {
    List<Shot> newSaves = Collections.unmodifiableList(saves);
    return newSaves;
  }

  public int numberOfSaves()
  {
    int number = saves.size();
    return number;
  }

  public boolean hasSaves()
  {
    boolean has = saves.size() > 0;
    return has;
  }

  public int indexOfSave(Shot aSave)
  {
    int index = saves.indexOf(aSave);
    return index;
  }

  public static int minimumNumberOfSaves()
  {
    return 0;
  }

  public boolean addSave(Shot aSave)
  {
    boolean wasAdded = false;
    if (saves.contains(aSave)) { return false; }
    saves.add(aSave);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSave(Shot aSave)
  {
    boolean wasRemoved = false;
    if (saves.contains(aSave))
    {
      saves.remove(aSave);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSaveAt(Shot aSave, int index)
  {  
    boolean wasAdded = false;
    if(addSave(aSave))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSaves()) { index = numberOfSaves() - 1; }
      saves.remove(aSave);
      saves.add(index, aSave);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSaveAt(Shot aSave, int index)
  {
    boolean wasAdded = false;
    if(saves.contains(aSave))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSaves()) { index = numberOfSaves() - 1; }
      saves.remove(aSave);
      saves.add(index, aSave);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSaveAt(aSave, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    saves.clear();
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "successfulSaves" + ":" + getSuccessfulSaves()+ "," +
            "failedSaves" + ":" + getFailedSaves()+ "]"
     + outputString;
  }
}