
	<?php
	$type = "CP";
	$teamName = $_POST["team"];
	$playerName = $_POST["PlayerName"];
	$playerNumber = $_POST["PlayerNumber"];
	$typePlayer = $_POST["Type"];


	exec("java -jar CreateTeam.jar Controller $type $teamName $playerName $playerNumber $typePlayer", $output);
	?>
