<html>
<head>
	<title>SwiftScore</title>
</head>

<body background="soccer.jpg">
<form action="" method="post">
<center>
	<font color="white" size = 30x >Remove Player</font>
	<br><br>
	<font color="white" size = 30x >Team:</font> <!-- implement get teams -->
	<select>
		<option name = team value="team1">Team1</option>
		<option name = team value="team2">Team2</option>
	</select>
	<br>
	<font color="white" size = 30x >Player Name:</font> <input type="text" name="PlayerName"> 
	<br>
	<font color="white" size = 30x >Player Number:</font> <input type="text" name="PlayerNumber"> 
	<br><br><br>
	<input type= "submit" name= "remove" value= "Remove">
</form>
</center>
	<?php
	$type = "RP";
	$teamName = $_POST["team"];
	$playerName = $_POST["PlayerName"];
	$playerNumber = $_POST["PlayerNumber"];

	exec("java -jar RemovePlayer.jar Controller $type $teamName $playerName $playerNumber", $output);
	?>
</body>
</html>
