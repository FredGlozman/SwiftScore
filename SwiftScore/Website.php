<html>
<head>
<title>SwiftScore</title>
</head>
<body background="soccer.jpg">
	<form action="" method="get">
		<center>

			<input type="radio" name="UseCase" value="1"><font color="white" size = 10x >Live Score Keeping</font> <br> 
			<input type="radio" name="UseCase" value="2"><font color="white" size = 10x >Batch Input</font> <br> 
			<input type="radio" name="UseCase" value="3"><font color="white" size = 10x >League Configuration</font> <br> 
			<input type="radio" name="UseCase" value="4"><font color="white" size = 10x >Player Analysis</font> <br> 
			<input type="radio" name="UseCase" value="5"><font color="white" size = 10x >League Analysis</font> <br>
			<br> <input type="submit" value="Go">
		</center>
	</form>
<?php
	$UseCase = $_GET["UseCase"];
	if($UseCase == 1 || $UseCase == 2 ||$UseCase == 3)
	{
		header('Location: Authentication.html');
				$array = array(
			"user" => "password",
			);
		$username = $_GET["user"];
		$password = $_GET["password"];
		if($array[0] != $username && $array[1] != $password)
		{
			echo "Authentication failed";
		}
		else
		{
			if($UseCase == 1 )
			{
				exec('LiveScoreKeeping.php');
			}
			if($UseCase == 2 )
			{
				exec('Batch.html');
			}
			if($UseCase == 3 )
			{
				exec('LeagueConfiguration.php');
			}
			
		}
	}
	if($UseCase == 4)
	{
		exec('PlayerAnalysis.php');
	}
	if($UseCase == 5)
	{
		exec('LeagueAnalysis.php');
	}

	?>
</body>
</html>
