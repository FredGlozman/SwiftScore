/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;

/**
 * 
 * Domain object that stores data relating to Goalies (extends Player).
 * Players shoot on goal but goalies can also save goals (in addition to
 * doing everything else a Player can do). This is reflected in the
 * inheritance structure.
 * 
 * @see Player
 * @see Team
 * @see Shot
 * @see Infraction 
 * @see League
 */
// line 241 "../../../../../ScoreKeeper.ump"
// line 693 "../../../../../ScoreKeeper.ump"
public class Goalie extends Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Goalie Associations
  private List<Shot> saves;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Goalie(String aName, int aJerseyNumber, Team aTeam, League aLeague)
  {
    super(aName, aJerseyNumber, aTeam, aLeague);
    saves = new ArrayList<Shot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Shot addSave(boolean aGoal, int aTime, Player aPlayer, Game aGame)
  {
    return new Shot(aGoal, aTime, aPlayer, this, aGame);
  }

  public boolean addSave(Shot aSave)
  {
    boolean wasAdded = false;
    if (saves.contains(aSave)) { return false; }
    Goalie existingGoalie = aSave.getGoalie();
    boolean isNewGoalie = existingGoalie != null && !this.equals(existingGoalie);
    if (isNewGoalie)
    {
      aSave.setGoalie(this);
    }
    else
    {
      saves.add(aSave);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSave(Shot aSave)
  {
    boolean wasRemoved = false;
    //Unable to remove aSave, as it must always have a goalie
    if (!this.equals(aSave.getGoalie()))
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
    for(int i=saves.size(); i > 0; i--)
    {
      Shot aSave = saves.get(i - 1);
      aSave.delete();
    }
    super.delete();
  }


  /**
   * Java Code //
   * 
   * Method returning the total number of successful Saves.
   * 
   * @return total number of successful Saves
   * 
   * @see Shot
   */
  // line 259 "../../../../../ScoreKeeper.ump"
   public int getSuccessfulSaveCount(){
    int res = 0;
    for(Shot save : this.getSaves())
    {
      if(!save.getGoal())
      {
        res++;
      }
    }
    return res;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 276 ../../../../../ScoreKeeper.ump
  public static Comparator<Goalie> COMPARE_BY_SAVES = new Comparator<Goalie>() {public int compare(Goalie one, Goalie other)
    {
      return one.numberOfSaves() - other.numberOfSaves();
    }};
// line 290 ../../../../../ScoreKeeper.ump
  public static Comparator<Goalie> COMPARE_BY_SUCCESSFUL_SAVES = new Comparator<Goalie>() {public int compare(Goalie one, Goalie other)
    {
      return one.getSuccessfulSaveCount() - other.getSuccessfulSaveCount();
    }};

  
}