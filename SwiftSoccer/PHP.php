<html>
	<head>
		<title> SwiftScore</title>
	</head>
	<body>
		<?php
		$useCase = $_POST["UseCase"];
		
		if($useCase==1||$useCase==2 ||$useCase==3)
		{
			header('Location: Authentication.html')//go to authentication page
		}
		else if($useCase==4)
		{
			header('LeagueAnalysisMode.html')//go league analysis
		}
		else if($useCase==5)
		{
			header('PlayerAnalysisMode.html')//go to Player Analysis
		}
		?>
	</body>
</html>