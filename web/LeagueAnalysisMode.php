<html>
<style>
p, font, th, td {
	color: white;
}
</style>
<head>
<title>Swift Score</title>
</head>
<body background="soccer.jpg">
<center>
<p>
<font color="white" size = 20x >Sort by</font>: 
</p>
<select>
	<option name="sort" value="N">Name</option>
	<option name="sort" value="S">Shots</option>
	<option name="sort" value="SS">Successful Shots</option>
	<option name="sort" value="P">Points</option>
	<option name="sort" value="I">Infractions</option>
	<option name="sort" value="PS">Penalty Shots</option>
	<option name="sort" value="RC">Red Cards</option>
	<option name="sort" value="YC">Yellow Cards</option>
</select>
<br>

		<p>
			<font color="white" size = 20x >Display League analysis</font>
		</p>
	</center>
<?php
	$type = "LA";
	$sortType = $_POST["sort"];

	exec("java -jar LeagueAnalysisMode.jar Controller $type $sortType", $output);
	?>
</body>
</html>