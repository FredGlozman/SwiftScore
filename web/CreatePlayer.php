<html>
<head>
<title>SwiftScore</title>
</head>
<body background="soccer.jpg">
<form action="" method="post">
<center>
	<font color="white" size = 30x >Create Player</font> 
	<br><br> 
	<font color="white" size = 30x >Team:</font>
	
	<select>
		<option name=team value="team1">Team1</option>
		<option name=team value="team2">Team2</option>
	</select>
	<font color="white" size = 30x > Player Name:</font> <input type="text" name="PlayerName"> 
	<br>
	<font color="white" size = 30x >Player Number:</font> <input type="text" name="PlayerNumber">
	<br>
	<input type="radio" name="Type" value="player"><font color="white" size = 30x >Player</font>
	<input type="radio" name="Type" value="goalie"><font color="white" size = 30x >Goalie</font>
	<br><br> <input type="submit" name="Done" value="Done">
</center>
</form>
	<?php
	$type = "CP";
	$teamName = $_POST["team"];
	$playerName = $_POST["PlayerName"];
	$playerNumber = $_POST["PlayerNumber"];
	$typePlayer = $_POST["Type"];


	exec("java -jar CreatePlayer.jar Controller $type $teamName $playerName $playerNumber $typePlayer", $output);
	?>
</body>
</html>