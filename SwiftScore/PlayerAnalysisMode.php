	<?php
	$type = "PA";
	$teamName = $_POST["teamName"];

	exec("java -jar PlayerAnalysisMode.jar Controller $type $teamName", $output);
	?>
