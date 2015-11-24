/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/


import java.util.*;

// line 62 "ScoreKeeper.ump"
public class League
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static League theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //League Attributes
  private List<int> playerScoreRanking;
  private List<int> playerCombinedPenaltyRanking;
  private List<int> playerRedPenaltyRanking;
  private List<int> playerYellowPenaltyRanking;
  private List<int> teamPointRanking;
  private List<int> teamGoalsRanking;
  private List<int> teamCombinedPenaltyRanking;
  private List<int> teamRedPenaltyRanking;
  private List<int> teamYellowPenaltyRanking;

  //League Associations
  private List<Team> tracks-t;
  private List<Game> tracks-g;
  private List<Player> tracks-p;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private League()
  {
    playerScoreRanking = new ArrayList<int>();
    playerCombinedPenaltyRanking = new ArrayList<int>();
    playerRedPenaltyRanking = new ArrayList<int>();
    playerYellowPenaltyRanking = new ArrayList<int>();
    teamPointRanking = new ArrayList<int>();
    teamGoalsRanking = new ArrayList<int>();
    teamCombinedPenaltyRanking = new ArrayList<int>();
    teamRedPenaltyRanking = new ArrayList<int>();
    teamYellowPenaltyRanking = new ArrayList<int>();
    tracks-t = new ArrayList<Team>();
    tracks-g = new ArrayList<Game>();
    tracks-p = new ArrayList<Player>();
  }

  public static League getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new League();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean addPlayerScoreRanking(int aPlayerScoreRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerScoreRanking.add(aPlayerScoreRanking);
    return wasAdded;
  }

  public boolean removePlayerScoreRanking(int aPlayerScoreRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerScoreRanking.remove(aPlayerScoreRanking);
    return wasRemoved;
  }

  public boolean addPlayerCombinedPenaltyRanking(int aPlayerCombinedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerCombinedPenaltyRanking.add(aPlayerCombinedPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerCombinedPenaltyRanking(int aPlayerCombinedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerCombinedPenaltyRanking.remove(aPlayerCombinedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addPlayerRedPenaltyRanking(int aPlayerRedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerRedPenaltyRanking.add(aPlayerRedPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerRedPenaltyRanking(int aPlayerRedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerRedPenaltyRanking.remove(aPlayerRedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addPlayerYellowPenaltyRanking(int aPlayerYellowPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = playerYellowPenaltyRanking.add(aPlayerYellowPenaltyRanking);
    return wasAdded;
  }

  public boolean removePlayerYellowPenaltyRanking(int aPlayerYellowPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = playerYellowPenaltyRanking.remove(aPlayerYellowPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamPointRanking(int aTeamPointRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamPointRanking.add(aTeamPointRanking);
    return wasAdded;
  }

  public boolean removeTeamPointRanking(int aTeamPointRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamPointRanking.remove(aTeamPointRanking);
    return wasRemoved;
  }

  public boolean addTeamGoalsRanking(int aTeamGoalsRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamGoalsRanking.add(aTeamGoalsRanking);
    return wasAdded;
  }

  public boolean removeTeamGoalsRanking(int aTeamGoalsRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamGoalsRanking.remove(aTeamGoalsRanking);
    return wasRemoved;
  }

  public boolean addTeamCombinedPenaltyRanking(int aTeamCombinedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamCombinedPenaltyRanking.add(aTeamCombinedPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamCombinedPenaltyRanking(int aTeamCombinedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamCombinedPenaltyRanking.remove(aTeamCombinedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamRedPenaltyRanking(int aTeamRedPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamRedPenaltyRanking.add(aTeamRedPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamRedPenaltyRanking(int aTeamRedPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamRedPenaltyRanking.remove(aTeamRedPenaltyRanking);
    return wasRemoved;
  }

  public boolean addTeamYellowPenaltyRanking(int aTeamYellowPenaltyRanking)
  {
    boolean wasAdded = false;
    wasAdded = teamYellowPenaltyRanking.add(aTeamYellowPenaltyRanking);
    return wasAdded;
  }

  public boolean removeTeamYellowPenaltyRanking(int aTeamYellowPenaltyRanking)
  {
    boolean wasRemoved = false;
    wasRemoved = teamYellowPenaltyRanking.remove(aTeamYellowPenaltyRanking);
    return wasRemoved;
  }

  public int getPlayerScoreRanking(int index)
  {
    int aPlayerScoreRanking = playerScoreRanking.get(index);
    return aPlayerScoreRanking;
  }

  public int[] getPlayerScoreRanking()
  {
    int[] newPlayerScoreRanking = playerScoreRanking.toArray(new int[playerScoreRanking.size()]);
    return newPlayerScoreRanking;
  }

  public int numberOfPlayerScoreRanking()
  {
    int number = playerScoreRanking.size();
    return number;
  }

  public boolean hasPlayerScoreRanking()
  {
    boolean has = playerScoreRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerScoreRanking(int aPlayerScoreRanking)
  {
    int index = playerScoreRanking.indexOf(aPlayerScoreRanking);
    return index;
  }

  public int getPlayerCombinedPenaltyRanking(int index)
  {
    int aPlayerCombinedPenaltyRanking = playerCombinedPenaltyRanking.get(index);
    return aPlayerCombinedPenaltyRanking;
  }

  public int[] getPlayerCombinedPenaltyRanking()
  {
    int[] newPlayerCombinedPenaltyRanking = playerCombinedPenaltyRanking.toArray(new int[playerCombinedPenaltyRanking.size()]);
    return newPlayerCombinedPenaltyRanking;
  }

  public int numberOfPlayerCombinedPenaltyRanking()
  {
    int number = playerCombinedPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerCombinedPenaltyRanking()
  {
    boolean has = playerCombinedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerCombinedPenaltyRanking(int aPlayerCombinedPenaltyRanking)
  {
    int index = playerCombinedPenaltyRanking.indexOf(aPlayerCombinedPenaltyRanking);
    return index;
  }

  public int getPlayerRedPenaltyRanking(int index)
  {
    int aPlayerRedPenaltyRanking = playerRedPenaltyRanking.get(index);
    return aPlayerRedPenaltyRanking;
  }

  public int[] getPlayerRedPenaltyRanking()
  {
    int[] newPlayerRedPenaltyRanking = playerRedPenaltyRanking.toArray(new int[playerRedPenaltyRanking.size()]);
    return newPlayerRedPenaltyRanking;
  }

  public int numberOfPlayerRedPenaltyRanking()
  {
    int number = playerRedPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerRedPenaltyRanking()
  {
    boolean has = playerRedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerRedPenaltyRanking(int aPlayerRedPenaltyRanking)
  {
    int index = playerRedPenaltyRanking.indexOf(aPlayerRedPenaltyRanking);
    return index;
  }

  public int getPlayerYellowPenaltyRanking(int index)
  {
    int aPlayerYellowPenaltyRanking = playerYellowPenaltyRanking.get(index);
    return aPlayerYellowPenaltyRanking;
  }

  public int[] getPlayerYellowPenaltyRanking()
  {
    int[] newPlayerYellowPenaltyRanking = playerYellowPenaltyRanking.toArray(new int[playerYellowPenaltyRanking.size()]);
    return newPlayerYellowPenaltyRanking;
  }

  public int numberOfPlayerYellowPenaltyRanking()
  {
    int number = playerYellowPenaltyRanking.size();
    return number;
  }

  public boolean hasPlayerYellowPenaltyRanking()
  {
    boolean has = playerYellowPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfPlayerYellowPenaltyRanking(int aPlayerYellowPenaltyRanking)
  {
    int index = playerYellowPenaltyRanking.indexOf(aPlayerYellowPenaltyRanking);
    return index;
  }

  public int getTeamPointRanking(int index)
  {
    int aTeamPointRanking = teamPointRanking.get(index);
    return aTeamPointRanking;
  }

  public int[] getTeamPointRanking()
  {
    int[] newTeamPointRanking = teamPointRanking.toArray(new int[teamPointRanking.size()]);
    return newTeamPointRanking;
  }

  public int numberOfTeamPointRanking()
  {
    int number = teamPointRanking.size();
    return number;
  }

  public boolean hasTeamPointRanking()
  {
    boolean has = teamPointRanking.size() > 0;
    return has;
  }

  public int indexOfTeamPointRanking(int aTeamPointRanking)
  {
    int index = teamPointRanking.indexOf(aTeamPointRanking);
    return index;
  }

  public int getTeamGoalsRanking(int index)
  {
    int aTeamGoalsRanking = teamGoalsRanking.get(index);
    return aTeamGoalsRanking;
  }

  public int[] getTeamGoalsRanking()
  {
    int[] newTeamGoalsRanking = teamGoalsRanking.toArray(new int[teamGoalsRanking.size()]);
    return newTeamGoalsRanking;
  }

  public int numberOfTeamGoalsRanking()
  {
    int number = teamGoalsRanking.size();
    return number;
  }

  public boolean hasTeamGoalsRanking()
  {
    boolean has = teamGoalsRanking.size() > 0;
    return has;
  }

  public int indexOfTeamGoalsRanking(int aTeamGoalsRanking)
  {
    int index = teamGoalsRanking.indexOf(aTeamGoalsRanking);
    return index;
  }

  public int getTeamCombinedPenaltyRanking(int index)
  {
    int aTeamCombinedPenaltyRanking = teamCombinedPenaltyRanking.get(index);
    return aTeamCombinedPenaltyRanking;
  }

  public int[] getTeamCombinedPenaltyRanking()
  {
    int[] newTeamCombinedPenaltyRanking = teamCombinedPenaltyRanking.toArray(new int[teamCombinedPenaltyRanking.size()]);
    return newTeamCombinedPenaltyRanking;
  }

  public int numberOfTeamCombinedPenaltyRanking()
  {
    int number = teamCombinedPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamCombinedPenaltyRanking()
  {
    boolean has = teamCombinedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamCombinedPenaltyRanking(int aTeamCombinedPenaltyRanking)
  {
    int index = teamCombinedPenaltyRanking.indexOf(aTeamCombinedPenaltyRanking);
    return index;
  }

  public int getTeamRedPenaltyRanking(int index)
  {
    int aTeamRedPenaltyRanking = teamRedPenaltyRanking.get(index);
    return aTeamRedPenaltyRanking;
  }

  public int[] getTeamRedPenaltyRanking()
  {
    int[] newTeamRedPenaltyRanking = teamRedPenaltyRanking.toArray(new int[teamRedPenaltyRanking.size()]);
    return newTeamRedPenaltyRanking;
  }

  public int numberOfTeamRedPenaltyRanking()
  {
    int number = teamRedPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamRedPenaltyRanking()
  {
    boolean has = teamRedPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamRedPenaltyRanking(int aTeamRedPenaltyRanking)
  {
    int index = teamRedPenaltyRanking.indexOf(aTeamRedPenaltyRanking);
    return index;
  }

  public int getTeamYellowPenaltyRanking(int index)
  {
    int aTeamYellowPenaltyRanking = teamYellowPenaltyRanking.get(index);
    return aTeamYellowPenaltyRanking;
  }

  public int[] getTeamYellowPenaltyRanking()
  {
    int[] newTeamYellowPenaltyRanking = teamYellowPenaltyRanking.toArray(new int[teamYellowPenaltyRanking.size()]);
    return newTeamYellowPenaltyRanking;
  }

  public int numberOfTeamYellowPenaltyRanking()
  {
    int number = teamYellowPenaltyRanking.size();
    return number;
  }

  public boolean hasTeamYellowPenaltyRanking()
  {
    boolean has = teamYellowPenaltyRanking.size() > 0;
    return has;
  }

  public int indexOfTeamYellowPenaltyRanking(int aTeamYellowPenaltyRanking)
  {
    int index = teamYellowPenaltyRanking.indexOf(aTeamYellowPenaltyRanking);
    return index;
  }

  public Team getTracks-t(int index)
  {
    Team aTracks-t = tracks-t.get(index);
    return aTracks-t;
  }

  public List<Team> getTracks-t()
  {
    List<Team> newTracks-t = Collections.unmodifiableList(tracks-t);
    return newTracks-t;
  }

  public int numberOfTracks-t()
  {
    int number = tracks-t.size();
    return number;
  }

  public boolean hasTracks-t()
  {
    boolean has = tracks-t.size() > 0;
    return has;
  }

  public int indexOfTracks-t(Team aTracks-t)
  {
    int index = tracks-t.indexOf(aTracks-t);
    return index;
  }

  public Game getTracks-g(int index)
  {
    Game aTracks-g = tracks-g.get(index);
    return aTracks-g;
  }

  public List<Game> getTracks-g()
  {
    List<Game> newTracks-g = Collections.unmodifiableList(tracks-g);
    return newTracks-g;
  }

  public int numberOfTracks-g()
  {
    int number = tracks-g.size();
    return number;
  }

  public boolean hasTracks-g()
  {
    boolean has = tracks-g.size() > 0;
    return has;
  }

  public int indexOfTracks-g(Game aTracks-g)
  {
    int index = tracks-g.indexOf(aTracks-g);
    return index;
  }

  public Player getTracks-p(int index)
  {
    Player aTracks-p = tracks-p.get(index);
    return aTracks-p;
  }

  public List<Player> getTracks-p()
  {
    List<Player> newTracks-p = Collections.unmodifiableList(tracks-p);
    return newTracks-p;
  }

  public int numberOfTracks-p()
  {
    int number = tracks-p.size();
    return number;
  }

  public boolean hasTracks-p()
  {
    boolean has = tracks-p.size() > 0;
    return has;
  }

  public int indexOfTracks-p(Player aTracks-p)
  {
    int index = tracks-p.indexOf(aTracks-p);
    return index;
  }

  public static int minimumNumberOfTracks-t()
  {
    return 0;
  }

  public boolean addTracks-t(Team aTracks-t)
  {
    boolean wasAdded = false;
    if (tracks-t.contains(aTracks-t)) { return false; }
    tracks-t.add(aTracks-t);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTracks-t(Team aTracks-t)
  {
    boolean wasRemoved = false;
    if (tracks-t.contains(aTracks-t))
    {
      tracks-t.remove(aTracks-t);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTracks-tAt(Team aTracks-t, int index)
  {  
    boolean wasAdded = false;
    if(addTracks-t(aTracks-t))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-t()) { index = numberOfTracks-t() - 1; }
      tracks-t.remove(aTracks-t);
      tracks-t.add(index, aTracks-t);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTracks-tAt(Team aTracks-t, int index)
  {
    boolean wasAdded = false;
    if(tracks-t.contains(aTracks-t))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-t()) { index = numberOfTracks-t() - 1; }
      tracks-t.remove(aTracks-t);
      tracks-t.add(index, aTracks-t);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTracks-tAt(aTracks-t, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTracks-g()
  {
    return 0;
  }

  public boolean addTracks-g(Game aTracks-g)
  {
    boolean wasAdded = false;
    if (tracks-g.contains(aTracks-g)) { return false; }
    tracks-g.add(aTracks-g);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTracks-g(Game aTracks-g)
  {
    boolean wasRemoved = false;
    if (tracks-g.contains(aTracks-g))
    {
      tracks-g.remove(aTracks-g);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTracks-gAt(Game aTracks-g, int index)
  {  
    boolean wasAdded = false;
    if(addTracks-g(aTracks-g))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-g()) { index = numberOfTracks-g() - 1; }
      tracks-g.remove(aTracks-g);
      tracks-g.add(index, aTracks-g);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTracks-gAt(Game aTracks-g, int index)
  {
    boolean wasAdded = false;
    if(tracks-g.contains(aTracks-g))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-g()) { index = numberOfTracks-g() - 1; }
      tracks-g.remove(aTracks-g);
      tracks-g.add(index, aTracks-g);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTracks-gAt(aTracks-g, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTracks-p()
  {
    return 0;
  }

  public boolean addTracks-p(Player aTracks-p)
  {
    boolean wasAdded = false;
    if (tracks-p.contains(aTracks-p)) { return false; }
    tracks-p.add(aTracks-p);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTracks-p(Player aTracks-p)
  {
    boolean wasRemoved = false;
    if (tracks-p.contains(aTracks-p))
    {
      tracks-p.remove(aTracks-p);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTracks-pAt(Player aTracks-p, int index)
  {  
    boolean wasAdded = false;
    if(addTracks-p(aTracks-p))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-p()) { index = numberOfTracks-p() - 1; }
      tracks-p.remove(aTracks-p);
      tracks-p.add(index, aTracks-p);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTracks-pAt(Player aTracks-p, int index)
  {
    boolean wasAdded = false;
    if(tracks-p.contains(aTracks-p))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracks-p()) { index = numberOfTracks-p() - 1; }
      tracks-p.remove(aTracks-p);
      tracks-p.add(index, aTracks-p);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTracks-pAt(aTracks-p, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    tracks-t.clear();
    tracks-g.clear();
    tracks-p.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]"
     + outputString;
  }
}