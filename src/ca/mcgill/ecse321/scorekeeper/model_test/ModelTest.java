package ca.mcgill.ecse321.scorekeeper.model_test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.ArrayList;

import ca.mcgill.ecse321.scorekeeper.model.*;

/**
 * Set of test cases for the ca.mcgill.ecse321.scorekeeper.model package.
 */
public class ModelTest
{
  League league;

  Team quarks;
  Player up;
  Player down;
  Player top;
  Player bottom;
  Player charm;
  Goalie strange;

  Team leptons;
  Player electron;
  Player muon;
  Player tau;
  Player elecNeutrino;
  Player muonNeutrino;
  Goalie tauNeutrino;

  Team bosons;
  Player gluon;
  Player photon;
  Player zBoson;
  Player wBoson;
  Goalie higgsBoson;

	/**
	 * JUnit setUp() method.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
    league = League.getInstance();

    quarks = new Team("Quarks", league);
    up = new Player("Up Quark", 1, quarks, league);
    down = new Player("Down Quark", 2, quarks, league);
    top = new Player("Top Quark", 3, quarks, league);
    bottom = new Player("Bottom Quark", 4, quarks, league);
    charm = new Player("Charm Quark", 5, quarks, league);
    strange = new Goalie("Strange Quark", 6, quarks, league);

    leptons = new Team("Leptons", league);
    electron = new Player("Electron", 10, leptons, league);
    muon = new Player("Muon", 20, leptons, league);
    tau = new Player("Tau", 30, leptons, league);
    elecNeutrino = new Player("Electron Neutrino", 40, leptons, league);
    muonNeutrino = new Player("Muon Neutrino", 50, leptons, league);
    tauNeutrino = new Goalie("Tau Neutrino", 60, leptons, league);

    bosons = new Team("Bosons", league);
    gluon = new Player("Gluon", 100, bosons, league);
    photon = new Player("Photon", 200, bosons, league);
    zBoson = new Player("Z Boson", 300, bosons, league);
    wBoson = new Player("W Boson", 400, bosons, league);
    higgsBoson = new Goalie("Higgs Boson", 500, bosons, league);
	}

	/**
	 * JUnit tearDown() method.
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		league.delete();
	}

	@Test
	public void setupTest()
	{
	  // Check that number of teams in league is right
	  assertEquals(league.numberOfTeams(), 3);
	  
	  // Check that number of players in team is right
	  assertEquals(quarks.numberOfPlayers(), 6);
	  assertEquals(leptons.numberOfPlayers(), 6);
	  assertEquals(bosons.numberOfPlayers(), 5);
	  
	  // Check a sample league-team association (both ways)
	  assertTrue(quarks.getLeague() == league);
	  assertTrue(league.getTeams().contains(quarks));
	  
	  // Check a sample team-player association(both ways)
	  assertTrue(tau.getTeam() == leptons);
	  assertTrue(leptons.getPlayers().contains(tau));
	}
	
	@Test
	public void goalTest()
	{
		Game g1 = new Game(1449002076, 1449002076+3600, "Mars", league, quarks, leptons);
		assertTrue(g1.getCompetitors().contains(quarks));
		assertTrue(g1.getCompetitors().contains(leptons));
		
		new Shot(true, 1449002076 + 1000, charm, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1001, charm, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1140, top, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1500, up, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1040, top, tauNeutrino, g1);
		
		// Test game data
		assertEquals(g1.numberOfShots(), 5);
		assertEquals(g1.getScore()[0], 3);
		assertEquals(g1.getScore()[1], 0);
		assertTrue(g1.getVictor() == quarks);
		
		// Test player data
		assertEquals(charm.getSuccessfulShotCount(), 2);
		assertEquals(charm.numberOfShots(), 2);
		assertEquals(tauNeutrino.getSuccessfulSaveCount(), 2); //TODO Flaky!!
		assertEquals(tauNeutrino.numberOfShots(), 0);
		assertEquals(tauNeutrino.numberOfSaves(), 5);
		
		// Test team data
		assertEquals(quarks.getSuccessfulShotCount(), 3);
		assertEquals(quarks.getPoints(), 3);
		
		Game g2 = new Game(1429002076, 1429002076 + 10000, "Pluto", league, quarks, bosons);
		assertTrue(g2.getCompetitors().contains(quarks));
		assertTrue(g2.getCompetitors().contains(bosons));
		
		new Shot(true, 1429002076 + 1000, charm, higgsBoson, g2);
		new Shot(true, 1429002076 + 1001, charm, higgsBoson, g2);
		new Shot(false, 1429002076 + 1140, top, higgsBoson, g2);
		new Shot(true, 1429002076 + 1500, wBoson, strange, g2);
		new Shot(true, 1429002076 + 1040, zBoson, strange, g2);
		
		// Test game data
		assertEquals(g2.numberOfShots(), 5);
		assertEquals(g2.getScore()[0], 2);
		assertEquals(g2.getScore()[1], 2);
		assertTrue(g2.getVictor() == null);
		
		// Test player data
		assertEquals(charm.getSuccessfulShotCount(), 2 + 2);
		assertEquals(charm.numberOfShots(), 2 + 2);
		assertEquals(up.getSuccessfulShotCount(), 1);
		assertEquals(up.numberOfShots(), 1);
		assertEquals(strange.getSuccessfulSaveCount(), 0);
		assertEquals(higgsBoson.getSuccessfulSaveCount(), 1);
		assertEquals(strange.numberOfShots(), 0);
		assertEquals(strange.numberOfSaves(), 2);
		assertEquals(higgsBoson.numberOfShots(), 0);
		assertEquals(higgsBoson.numberOfSaves(), 3);
		
		// Test team data
		assertEquals(quarks.getSuccessfulShotCount(), 3 + 2);
		assertEquals(quarks.getPoints(), 3 + 1);
	}
	
	@Test
	public void infractionTest()
	{
		Game g1 = new Game(1449002076, 1449002076+3600, "Mars", league, quarks, leptons);
		assertTrue(g1.getCompetitors().contains(quarks));
		assertTrue(g1.getCompetitors().contains(leptons));
		
	  new Infraction(Color.RED, false, 1449002076+3599, electron, g1);
	  new Infraction(Color.RED, true, 1449002076+3219, muon, g1);
	  new Infraction(Color.YELLOW, false, 1449002076+3199, electron, g1);
		
	  // Test game data
	  assertEquals(g1.numberOfInfractions(), 3);
	  
	  //Test player data
	  assertEquals(electron.numberOfInfractions(), 2);
	  assertEquals(electron.getRedInfractionCount(), 1);
	  assertEquals(electron.getYellowInfractionCount(), 1);
	  assertEquals(electron.getPenaltyShotCount(), 0);
	  assertEquals(muon.numberOfInfractions(), 1);
	  assertEquals(muon.getRedInfractionCount(), 1);
	  assertEquals(muon.getYellowInfractionCount(), 0);
	  assertEquals(muon.getPenaltyShotCount(), 1);
	  
	  //Test team data
	  assertEquals(leptons.getTotalInfractionCount(), 3);
	  assertEquals(leptons.getRedInfractionCount(), 2);
	  assertEquals(leptons.getYellowInfractionCount(), 1);
	  assertEquals(leptons.getPenaltyShotCount(), 1);
	    
		Game g2 = new Game(1429002076, 1429002076 + 10000, "Pluto", league, leptons, bosons);
		assertTrue(g2.getCompetitors().contains(leptons));
		assertTrue(g2.getCompetitors().contains(bosons));
		
		new Infraction(Color.RED, false, 1449002076+3599, electron, g2);
	  new Infraction(Color.RED, true, 1449002076+3219, muon, g2);
	  new Infraction(Color.YELLOW, true, 1449002076+3199, muon, g2);
	  
	  // Test game data
	  assertEquals(g2.numberOfInfractions(), 3);
	  
	  //Test player data
	  assertEquals(electron.numberOfInfractions(), 2+1);
	  assertEquals(electron.getRedInfractionCount(), 1+1);
	  assertEquals(electron.getYellowInfractionCount(), 1);
	  assertEquals(electron.getPenaltyShotCount(), 0);
	  assertEquals(muon.numberOfInfractions(), 1+2);
	  assertEquals(muon.getRedInfractionCount(), 1+1);
	  assertEquals(muon.getYellowInfractionCount(), 0+1);
	  assertEquals(muon.getPenaltyShotCount(), 1+2);
	  
	  //Test team data
	  assertEquals(leptons.getTotalInfractionCount(), 6);
	  assertEquals(leptons.getRedInfractionCount(), 2+2);
	  assertEquals(leptons.getYellowInfractionCount(), 1+1);
	  assertEquals(leptons.getPenaltyShotCount(), 1+2);
	}
	
	@Test
	public void sortTest()
	{
		Game g1 = new Game(1449002076, 1449002076+3600, "Venus", league, quarks, leptons);

	  new Infraction(Color.RED, false, 1449002076+3599, electron, g1);
	  new Infraction(Color.RED, true, 1449002076+3219, top, g1);
	  new Infraction(Color.YELLOW, false, 1449002076+3199, electron, g1);
		new Infraction(Color.RED, false, 1449002076+3599, electron, g1);
	  new Infraction(Color.RED, true, 1449002076+3219, muon, g1);
	  new Infraction(Color.YELLOW, true, 1449002076+3199, muon, g1);
		new Shot(true, 1449002076 + 1000, charm, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1001, charm, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1140, top, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1500, up, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1040, top, tauNeutrino, g1);
		new Shot(true, 1429002076 + 1000, charm, higgsBoson, g1);
		new Shot(true, 1429002076 + 1001, charm, higgsBoson, g1);
		new Shot(false, 1429002076 + 1140, top, higgsBoson, g1);
		new Shot(true, 1429002076 + 1500, zBoson, strange, g1);
		new Shot(true, 1429002076 + 1040, zBoson, strange, g1);

    ArrayList<Player> infSort = new ArrayList<Player>(league.getPlayers());
    Collections.sort(infSort, Player.COMPARE_BY_TOTAL_INFRACTIONS);
    Collections.reverse(infSort);
    assertEquals(infSort.get(0), electron);
    assertEquals(infSort.get(1), muon);
    assertEquals(infSort.get(2), top);

    ArrayList<Player> shotSort = new ArrayList<Player>(league.getPlayers());
    Collections.sort(shotSort, Player.COMPARE_BY_SUCCESSFUL_SHOTS);
    Collections.reverse(shotSort);
    assertEquals(shotSort.get(0), charm);
    assertEquals(shotSort.get(1), zBoson);
    assertEquals(shotSort.get(2), up);

    ArrayList<Player> nameSort = new ArrayList<Player>(league.getPlayers());
    Collections.sort(nameSort, Player.COMPARE_BY_NAME);
    Collections.reverse(nameSort);
    assertEquals(nameSort.get(0), bottom);
    assertEquals(nameSort.get(1), charm);
    assertEquals(nameSort.get(2), down);
	}

  @Test
  public void persistenceTest()
  {
    Game g1 = new Game(1449002076, 1449002076+3600, "Venus", league, quarks, leptons);

	  new Infraction(Color.RED, false, 1449002076+3599, electron, g1);
	  new Infraction(Color.RED, true, 1449002076+3219, top, g1);
	  new Infraction(Color.YELLOW, false, 1449002076+3199, electron, g1);
		new Infraction(Color.RED, false, 1449002076+3599, electron, g1);
	  new Infraction(Color.RED, true, 1449002076+3219, muon, g1);
	  new Infraction(Color.YELLOW, true, 1449002076+3199, muon, g1);
		new Shot(true, 1449002076 + 1000, charm, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1001, charm, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1140, top, tauNeutrino, g1);
		new Shot(true, 1449002076 + 1500, up, tauNeutrino, g1);
		new Shot(false, 1449002076 + 1040, top, tauNeutrino, g1);
		new Shot(true, 1429002076 + 1000, charm, higgsBoson, g1);
		new Shot(true, 1429002076 + 1001, charm, higgsBoson, g1);
		new Shot(false, 1429002076 + 1140, top, higgsBoson, g1);
		new Shot(true, 1429002076 + 1500, zBoson, strange, g1);
		new Shot(true, 1429002076 + 1040, zBoson, strange, g1);

    League.save(league);
    league.delete();
    assertEquals(0, league.numberOfTeams());
    assertEquals(0, league.numberOfPlayers());
    league = League.load();
    assertEquals(3, league.numberOfTeams());
    assertEquals(17, league.numberOfPlayers());
  }
}

