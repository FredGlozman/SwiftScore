package ca.mcgill.ecse321.scorekeeper.model_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	  
	  //Check a sample team-player association(both ways)
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
	}

}

