	<?php
	$UseCase = $_GET["UseCase"];
	if($UseCase == 1 || $UseCase == 2 ||$UseCase == 3)
	{
		header('Location: Authentication.html');
				$array = array(
			"user" => "password",
			);
		$username = $_GET["user"];
		$password = $_GET["password"];
		if($array[0] != $username && $array[1] != $password)
		{
			echo "Authentication failed";
		}
		else
		{
			if($UseCase == 1 )
			{
				exec('LiveScoreKeeping.php');
			}
			if($UseCase == 2 )
			{
				exec('Batch.html');
			}
			if($UseCase == 3 )
			{
				exec('LeagueConfiguration.php');
			}
			
		}
	}
	if($UseCase == 4)
	{
		exec('PlayerAnalysis.php');
	}
	if($UseCase == 5)
	{
		exec('LeagueAnalysis.php');
	}

	?>
