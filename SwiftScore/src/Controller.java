//import ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;
public class Controller {
	//args[0] is the type of view
	//args[1]..args[n] is different for each view
	public static void main(String [] args)
	{
		//Live score keeping
		if (args[0].equals("LSK"))
		{
			String[] array = {args[1], args[2],args[3],args[4]};
			liveScoreKeeping(array);
		}
		//TODO:missing batch
		//Create team
		if (args[0].equals("CT"))
		{
			String[] array = {args[1], args[2],args[3],args[4]};
			CreateTeam(array);
		}
		//Create Player
		
		

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
	//(team name, player name, player number, type player)
	public static void CreateTeam(String [] args)
	{
		
	}
	
}
