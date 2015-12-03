
	<?php
	$type = "CT";
	$teamName = $_POST["teamName"];

	exec("java -jar CreateTeam.jar Controller $type $teamName", $output);
	?>
