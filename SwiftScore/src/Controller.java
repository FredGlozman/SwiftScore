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
		if(args[0].equals("PA")) //how to choose way of sorting??
		{
			PlayerAnalysisMode();
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
		
		//player submit button and Player is on team
		if (args[0].equals("Done") && args[1].equals(Find.getPlayer(args[1],team))) 
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
	public static void PlayerAnalysisMode()
	{
		//sorts via number of goals
		List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());
		Collections.sort(players, Player.COMPARE_BY_SUCCESSFUL_SHOTS);
		
	}
}