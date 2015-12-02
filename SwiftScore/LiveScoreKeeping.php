	<?php
	$type = "LSK"
	$player = $_POST["player"]
	$number = $_POST["number"]
	$team = $_POST["team"]
	$action = $_POST["action"]
		exec("java -jar LiveScoreKeeping.jar Controller $type $action $player $number $team", $output);
	?>
