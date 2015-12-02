	<?php
	header('Location: Website.html')
	$UseCase = $_POST["UseCase"]
	if($UseCase == 1 || $UseCase == 2 ||$UseCase == 3)
	{
		header('Location: Authentication.html')
				$array = array(
			"user" => "password",
			);
		$username = $_POST["user"]
		$password = $_POST["password"]
		if($array[0] != $username)
		{
			echo Authentication failed
		}
		else if($array[1] != $password)
		{
			echo Authentication failed
		}
		else
		{
			if($UseCase == 1 )
			{
				header('Location: LiveScoreKeeping.html') 
			}
			if($UseCase == 2 )
			{
				header('Location: Batch.html') 
			}
			if($UseCase == 3 )
			{
				header('Location: LeagueConfiguration.html') 
			}
			
		}
	}
	if($UseCase == 4)
	{
		header('Location: PlayerAnalysis.html')
	}
	if($UseCase == 5)
	{
		header('Location: LeagueAnalysis.html')
	}

	?>
