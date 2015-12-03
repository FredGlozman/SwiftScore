
	<?php
	$type = "RT";
	$teamName = $_POST["teamName"];

	exec("java -jar CreateTeam.jar Controller $type $teamName", $output);
	?>
