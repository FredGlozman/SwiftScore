<html>
<style>
p {
	color: white;
}
</style>
<head>
<title>Live Score Keeping</title>
</head>
<body background="soccer.jpg">
<center>

		<p><font size = 30x >Live Game</font></p>
		<br>
		<br> <font color="white" size = 30x>Player: </font> <input type="text" name="player">
		<font color="white" size = 30x> Number:</font> <input type="text" name="number"> 
		<font color="white" size = 30x>Team: </font> <input type="text" name="number">
		 <br>
		<input type="submit" name="action" value="SHOT"> 
		<input type="submit" name="action" value="GOAL"> <br> 
		<input type="submit" name="action" value="YELLOWCARD"> 
		<input type="submit" name="action" value="REDCARD"> 
		<br> <br>
	<form action="" method="post">
		<input type="submit" name="action" value="Done">
	</form>
</center>
<?php
	$type = "LSK";
	$player = $_POST["player"];
	$number = $_POST["number"];
	$team = $_POST["team"];
	$action = $_POST["action"];
		exec("java -jar LiveScoreKeeping.jar Controller $type $action $player $number $team", $output);
	?>
</body>
</html>