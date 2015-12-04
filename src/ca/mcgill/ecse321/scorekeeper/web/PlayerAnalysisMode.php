<html>
<style>
p, font, th, td {
	color: white;
}
</style>
<head>
<title>SwiftScore</title>
</head>
<body background="soccer.jpg">
	<center>
	<p>
		<font size = 20x >Choose Sort: </font>
	</p>
<select>
	<option name="sort" value="N">Name</option>
	<option name="sort" value="J">Jersey</option>
	<option name="sort" value="S">Shots</option>
	<option name="sort" value="SS">Successful Shots</option>
	<option name="sort" value="I">Infractions</option>
	<option name="sort" value="PS">Penalty Shots</option>
	<option name="sort" value="RC">Red Cards</option>
	<option name="sort" value="YC">Yellow Cards</option>
</select>
<br>
<form action="" method="post">
		<p>
			<font size = 20x >Display Player Analysis</font> 
		</p>
	</form>	
	</center>
<?php
	$type = "PA";
	$sortType = $_POST["sort"];

	exec("java -jar PlayerAnalysisMode.jar Controller $type $sortType", $output);
	?>
</body>
</html>