import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import ca.mcgill.ecse321.scorekeeper.model;

public class Controller {
	//args[0] is the type of view
	//args[1]..args[n] is different for each view
	public static void main(String [] args)
	{
		//Live score keeping:
		if (args[0].equals("LSK"))
		{
			String[] array = {args[1], args[2],args[3],args[4]};
			liveScoreKeeping(array);
		}
		//TODO:missing batch
		if (args[0].equals("BATCH"))
		{
			String[] array = {args[1], args[2],args[3],args[4],args[5]};
			Batch(array);
		}
		
		//League Configuration:
		//Create team
		if (args[0].equals("CT"))
		{
			String[] array = {args[1]};
			CreateTeam(array);
		}
		//Create Player
		if (args[0].equals("CP"))
		{
			String[] array = {args[1], args[2], args[3], args[4]};
			CreatePlayer(array);
		}
		//Remove Team
		if (args[0].equals("RT"))
		{
			String[] array = {args[1]};
			RemoveTeam(array);
		}
		//Remove Player
		if (args[0].equals("RP"))
		{
			String[] array = {args[1], args[2], args[3]};
			RemovePlayer(array);
		}
		
		//Player Analysis Mode
		if(args[0].equals("PA")) 
		{
			String[] array = {args[1]};
			PlayerAnalysisMode(array);
		}
		//League Analysis Mode
		if(args[0].equals("LA")) 
		{
			String[] array = {args[1]};
			LeagueAnalysisMode(array);
		}

	}
	
	//(action,player,number,team)
	public static void liveScoreKeeping(String [] args)
	{
		//initialize method variables
		Team team = new Team(args[3], League.getInstance());
		Player player = new Player (args[1], Integer.parseInt(args[2]), team, League.getInstance());
		Goalie goalie = new Goalie("bob", 2, team, League.getInstance());
		Game game = new Game(0, 160, "Olimpique stadium", League.getInstance(), team);
		double time = System.currentTimeMillis()/1000-game.getStartTime();
		
		//check Player is on team
		if (args[1].equals(Find.getPlayer(args[1],team))) 
		{
			if (args[0].equals("SHOT"))
			{
				Shot shot = new Shot(false, (int)time, player, goalie, game);
				game.addShot(shot);
				player.addShot(shot);
				goalie.addSave(false, (int)time, player, game);
				
			}
			else if(args[0].equals("GOAL"))
			{
				Shot shot = new Shot(true, (int)time, player, goalie, game);
				game.addShot(shot);
				player.addShot(shot);
			}
			else if(args[0].equals("YELLOWCARD"))
			{
				Color color = Color.YELLOW;
				Infraction infraction = new Infraction(color, false, (int)time, player, game);
				player.addInfraction(infraction);
				player.addInfraction(color, false, (int) time, game);
			}
			else if(args[0].equals("REDCARD"))
			{
				Color color = Color.RED;
				Infraction infraction = new Infraction(color, true, (int)time, player, game);
				player.addInfraction(infraction);
				player.addInfraction(color, true, (int) time, game);
			}
		}
	}
	
	//(team1,team2,location,startTime,endTime)
	public static void Batch(String [] args)
	{
		Team team1 = new Team(args[0], League.getInstance());
		Team team2 = new Team(args[1], League.getInstance());
		League.getInstance().addGame(Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[2], team1,team2);
//		Game game = new Game(Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[2], League.getInstance(), team1, team2);
//		League.getInstance().addGame(game);
		
	}
	
	//League Configuration
	//(team name)
	public static void CreateTeam(String [] args)
	{
		String teamName = args[0];
		if(Find.getTeam(teamName) == null)//team not on list already
		{
			League.getInstance().addTeam(teamName);
		}
	}
	//(teamName,playerName,playerNumber,typePlayer)
	public static void CreatePlayer(String [] args)
	{
		if(Find.getTeam(args[0]) != null)//team exists
		{
			Team team = new Team(args[0],League.getInstance());
			if (args[3].equals("player"))
			{
				League.getInstance().addPlayer(args[1], Integer.parseInt(args[2]), team);
				team.addPlayer(args[1], Integer.parseInt(args[2]), League.getInstance());
				
			}
			else if (args[3].equals("goalie"))
			{
				League.getInstance().addPlayer(args[1], Integer.parseInt(args[2]), team);
				team.addPlayer(new Goalie(args[1], Integer.parseInt(args[2]), team, League.getInstance()));
			}
		}
	}
	//(teamName)
	public static void RemoveTeam(String [] args)
	{
		if(Find.getTeam(args[0]) != null)//team exists
		{
			Team team = new Team(args[0],League.getInstance());
			League.getInstance().removeTeam(team);
		}
	}
	//(teamName,playerName, playerNumber)
	public static void RemovePlayer(String [] args)
	{
		Team team = new Team(args[0],League.getInstance());
		if(Find.getPlayer(args[1], team) !=null)//player exists 
		{
			Player player = new Player (args[1], Integer.parseInt(args[2]), team, League.getInstance());
			team.removePlayer(player);
			League.getInstance().removePlayer(player);
		}
	}
	
	//(type of sorting)
	public static void PlayerAnalysisMode(String [] args)
	{
		List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());
		//sorts via:
		//Name
		if (args[0].equals("N"))
		{
			Collections.sort(players, Player.COMPARE_BY_NAME);
		}
		//Jersey
		else if (args[0].equals("J"))
		{
			Collections.sort(players, Player.COMPARE_BY_JERSEY);
		}
		//Shots
		else if (args[0].equals("S"))
		{
			Collections.sort(players, Player.COMPARE_BY_SHOTS);
		}
		//successful shots
		else if (args[0].equals("SS"))
		{
			Collections.sort(players, Player.COMPARE_BY_SUCCESSFUL_SHOTS);
		}
		//Infractions
		else if (args[0].equals("I"))
		{
			Collections.sort(players, Player.COMPARE_BY_TOTAL_INFRACTIONS);
		}
		//Penalty shots
		else if (args[0].equals("PS"))
		{
			Collections.sort(players, Player.COMPARE_BY_PENALTY_SHOTS);
		}
		//Red Cards
		else if (args[0].equals("RC"))
		{
			Collections.sort(players, Player.COMPARE_BY_RED_CARDS);
		}
		//Yellow Cards
		else if (args[0].equals("YC"))
		{
			Collections.sort(players, Player.COMPARE_BY_YELLOW_CARDS);
		}		
	}
	
	//(type of sorting)
		public static void LeagueAnalysisMode(String [] args)
		{
			List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());
			//sorts via:
			//Name
			if (args[0].equals("N"))
			{
				Collections.sort(teams, Team.COMPARE_BY_NAME);
			}
			//Shots
			else if (args[0].equals("S"))
			{
				Collections.sort(teams, Team.COMPARE_BY_SHOTS);
			}
			//successful shots
			else if (args[0].equals("SS"))
			{
				Collections.sort(teams, Team.COMPARE_BY_SUCCESSFUL_SHOTS);
			}
			//Points
			else if (args[0].equals("P"))
			{
				Collections.sort(teams, Team.COMPARE_BY_POINTS);
			}
			//Infractions
			else if (args[0].equals("I"))
			{
				Collections.sort(teams, Team.COMPARE_BY_TOTAL_INFRACTIONS);
			}
			//Penalty shots
			else if (args[0].equals("PS"))
			{
				Collections.sort(teams, Team.COMPARE_BY_PENALTY_SHOTS);
			}
			//Red Cards
			else if (args[0].equals("RC"))
			{
				Collections.sort(teams, Team.COMPARE_BY_RED_CARDS);
			}
			//Yellow Cards
			else if (args[0].equals("YC"))
			{
				Collections.sort(teams, Team.COMPARE_BY_YELLOW_CARDS);
			}			
		}
		

}
