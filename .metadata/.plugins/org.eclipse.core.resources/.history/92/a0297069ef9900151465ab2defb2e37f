<?php
	$type = "BATCH";
	$team1 = $_POST["team1"];
	$team2 = $_POST["team2"];
	$location = $_POST["location"];
	$startTime = $_POST["startTime"];
	$endTime = $_POST["endTime"];

	exec("java -jar Batch.jar Controller $type $team1 $team2 $location $startTime $endTime", $output);
?>
