
	<?php
	$type = "RP";
	$teamName = $_POST["team"];
	$playerName = $_POST["PlayerName"];
	$playerNumber = $_POST["PlayerNumber"];

	exec("java -jar RemovePlayer.jar Controller $type $teamName $playerName $playerNumber", $output);
	?>
