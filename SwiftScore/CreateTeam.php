<html>
<head>
	<title>SwiftScore</title>
</head>
<body background="soccer.jpg">
<form action="" method="post">
<center>
<font color="white" size = 30x >Create Team</font>
<br><br>
<font color="white" size = 30x >Team Name:</font> <input type="text" name="teamName"> 
<br><br>
<input type= "submit" name= "Done" value= "Done">
</center>
</form>
	<?php
	$type = "CT";
	$teamName = $_POST["teamName"];

	exec("java -jar CreateTeam.jar Controller $type $teamName", $output);
	?>
</body>
</html>