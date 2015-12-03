
	<?php
	$type = "CT";
	$teamName = $_POST["teamName"];
	$playerName = $_POST["playerName"];
	$playerNumber = $_POST["playerNumber"];
	$typePlayer = $_POST["type"];

	exec("java -jar CreateTeam.jar Controller $type $teamName $playerName $playerNumber $typePlayer", $output);
	?>
