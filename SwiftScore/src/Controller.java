//import ca.mcgill.ecse321.scorekeeper.model;
import java.util.*;
public class Controller {
	//args[0] is the action
	//args[1] is the actor (player, goalie etc)
	public static void main(String [] args)
	{

		//Live Score Keeping
		if (args[0].equals("SHOT"))
		{
			//Player.getInstance().addShot( false, int aTime, Goalie aGoalie, Game aGame);
			//return.. which one???//Game.getInstance().addShot(boolean aGoal, int aTime, args[1], Goalie aGoalie);
		}
		else if(args[0].equals("GOAL"))
		{
			//Player.getInstance().addShot( true, int aTime, Goalie aGoalie, Game aGame);
			//Game.getInstance().addShot(true, int aTime, args[1], Goalie aGoalie);
		}
		else if(args[0].equals("YELLOWCARD"))
		{
			//Game.getInstance().addInfraction(Infraction aInfraction)
			//Infraction.getInstance().Infraction(Color.getInstance().Color[1], boolean aPenaltyShot, int aTime,args[1], Game aGame)
		}
		else if(args[0].equals("REDCARD"))
		{
			//Game,getInstance().addInfraction(Infraction aInfraction)
			//Infraction.getInstance().Infraction(Color.getInstance().Color[0], boolean aPenaltyShot, int aTime,args[1], Game aGame)
		}

	}
}
