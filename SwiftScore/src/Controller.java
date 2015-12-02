//import ca.mcgill.ecse321.scorekeeper.model;

public class Controller {
	//args[0] is the action
	//args[1] is the actor (player, goalie etc)
	public static void main(String [] args)
	{
		

		//Live Score Keeping argument(action,player,number,team)
		//player submit button
		if (args[0].equals("Select")) //&& if player is there
		{
			Team team = new Team(args[3], League.getInstance());
			Player player = new Player (args[1], Integer.parseInt(args[2]), team, League.getInstance());
			Goalie goalie = new Goalie("bob", 2, team, League.getInstance());
			Game game = new Game(40, 160, "Olimpique stadium", League.getInstance(), team);
			
			
			if (args[0].equals("SHOT"))
			{
				Shot shot = new Shot(false, 30, player, goalie, game);
				game.addShot(shot);
				player.addShot(shot);
				
			}
			else if(args[0].equals("GOAL"))
			{
				Shot shot = new Shot(true, 30, player, goalie, game)
				
			}
			else if(args[0].equals("YELLOWCARD"))
			{
				
			}
			else if(args[0].equals("REDCARD"))
			{
				
			}
		}
		
		
		

	}
}
