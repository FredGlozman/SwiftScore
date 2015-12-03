<html>

<head>
<title>Batch Input Mode</title>
</head>
<body background="soccer.jpg">
<p>
<center>
<font color="white" size = 30x >Batch Input</font>
</p>
<form action="" method="post">
<font color="white" size = 30x >Team1:</font> 
<select>
	<option name="team1" value="Canada">Canada</option>
	<option name="team1" value="Germany">Germany</option>
	<option name="team1" value="Italy">Italy</option>
</select>
<font color="white" size = 30x >vs. 
Team2: </font>
<select>
	<option name="team2" value="Canada">Canada</option>
	<option name="team2" value="Germany">Germany</option>
	<option name="team2" value="Italy">Italy</option>
</select>
<br>
<font color="white" size = 30x >Location: </font><input type="text" name="location"> 
<br>
<font color="white" size = 30x >StartTime: <input type="text" name="startTime"> EndTime: <input type="text" name="endTime"> 
<br><br>
<input type= "submit" value= "Done">
</form> 
</center>
<?php
	$type = "BATCH";
	$team1 = $_POST["team1"];
	$team2 = $_POST["team2"];
	$location = $_POST["location"];
	$startTime = $_POST["startTime"];
	$endTime = $_POST["endTime"];

	exec("java -jar Batch.jar Controller $type $team1 $team2 $location $startTime $endTime", $output);
?>
</body>
</html>
