<html>
	<head>
		<title> SwiftScore</title>
	</head>
	<body>
	<?php
	$player = $_POST["player"]
	$action = $_POST["action"]
		exec("java -jar JARNAME.jar Controller $action $player", $output);
	?>
	</body>
</html>