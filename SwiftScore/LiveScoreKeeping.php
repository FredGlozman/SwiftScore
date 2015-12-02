	<?php
	$player = $_POST["player"]
	$number = $_POST["number"]
	$team = $_POST["team"]
	$action = $_POST["action"]
		exec("java -jar JARNAME.jar Controller $action $player $number $team", $output);
	?>
