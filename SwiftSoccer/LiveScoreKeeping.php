<html>
	<head>
		<title> SwiftScore</title>
	</head>
	<body>
	<?php
	$team1 = $_POST["team1"]
	$team2 = $_POST["team2"]
		exec("java -jar Controller.jar $team1 $team2", $output);
	?>
	</body>
</html>