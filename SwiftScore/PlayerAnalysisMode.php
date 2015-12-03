	<?php
	$type = "PA";
	$sortType = $_POST["sort"];

	exec("java -jar PlayerAnalysisMode.jar Controller $type $sortType", $output);
	?>