<html>
<head>
	<title>SwiftScore</title>
</head>
<body background="soccer.jpg">
<center>
<form action="" method="post">
<font color="white" size = 30x >Remove Team</font>
<br><br>
<font color="white" size = 30x >Team:</font> <!-- implement get teams -->
<select>
	<option value="team1">Team1</option>
	<option value="team2">Team2</option>
</select>

<br><br>
<input type= "submit" name= "remove" value= "Remove">
</form>
</center>
</body>
<?php
	$type = "RT";
	$teamName = $_POST["teamName"];

	exec("java -jar RemoveTeam.jar Controller $type $teamName", $output);
	?>
</body>
</html>