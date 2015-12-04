package ca.mcgill.ecse321.scorekeeper.model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class the runs the Desktop Application
 * @author JonAdalin
 *
 */
public class Main extends Application {

	Stage window;
	Scene mainScene, pwLiveScene, pwBatchScene, playerScene, leagueScene,
			liveScene, batchScene;
	
	boolean hasLoggedIn = false;
	
	String[] topTeams = { "New York Red Bulls", "FC Dallas",
			"Columbus Crew SC", "Portland Timbers", "Vancouver Whitecaps FC",
			"D.C. United", "Montreal Impact", "LA Galaxy",
			"Sporting Kansas City", "Seattle Sounders FC",
			"New England Revolution" };
	String[] topTeamsPoints = { "60", "60", "53", "53", "53", "51", "51", "51",
			"51", "50" };

	String[] topPlayers = { "Sebastian Giovinco", "Kei Kamara", "Robbie Keane",
			"David Villa", "Bradley Wright-Phillips" };

	String[] topPlayersPoints = { "22", "22", "20", "18", "17" };

	String[] infPlayers = { "Marco Donadel", "Damien Perrinelle",
			"Kendall Waston", "Luis Garrido", "Christian Higuita" };

	String[] infPlayersPoints = { "13", "11", "11", "10", "10" };

	/**
	 * Main, which launches the desktop app
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("swiftScore");

		GridPane mainGrid = new GridPane();
		createMain(mainGrid);

		GridPane pwAuthLiveGrid = new GridPane();
		createPwAuthLive(pwAuthLiveGrid);

		GridPane pwAuthBatchGrid = new GridPane();
		createPwAuthBatch(pwAuthBatchGrid);

		GridPane playerGrid = new GridPane();
		createPlayerAnalysis(playerGrid, topPlayers, topPlayersPoints,
				infPlayers, infPlayersPoints);

		GridPane leagueGrid = new GridPane();
		createLeagueAnalysis(leagueGrid, topTeams, topTeamsPoints);

		GridPane liveGrid = new GridPane();
		createLiveMode(liveGrid);

		GridPane batchGrid = new GridPane();
		createBatchMode(batchGrid);

		window.setScene(mainScene);
		window.show();

	}

	/**
	 * Creates the scene for the main menu page
	 * @param grid The layout to be used
	 */
	public void createMain(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Image image = new Image("ball.png");
		ImageView ballImage = new ImageView();
		ballImage.setImage(image);
		ballImage.setFitWidth(200);
		ballImage.setPreserveRatio(true);
		GridPane.setConstraints(ballImage, 0, 5);

		Label welcome = new Label("Welcome to swiftScore!");
		welcome.setTextFill(Color.web("#0076a3"));
		welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(welcome, 0, 0);

		Button playerButton = new Button("Player Analysis Mode");
		GridPane.setConstraints(playerButton, 0, 1);
		playerButton.setOnAction(e -> window.setScene(playerScene));

		Button leagueButton = new Button("League Analysis Mode");
		leagueButton.setOnAction(e -> window.setScene(leagueScene));
		GridPane.setConstraints(leagueButton, 0, 2);

		Button liveButton = new Button("Live Input Mode");
		liveButton.setOnAction(e -> window.setScene(pwLiveScene));
		GridPane.setConstraints(liveButton, 0, 3);

		Button batchButton = new Button("Batch Input Mode");
		batchButton.setOnAction(e -> window.setScene(pwBatchScene));
		GridPane.setConstraints(batchButton, 0, 4);

		grid.getChildren().addAll(ballImage, welcome, playerButton,
				leagueButton, liveButton, batchButton);

		mainScene = new Scene(grid, 220, 310);
	}

	/**
	 * Creates the scene for the password authentication page going to the live input mode
	 * @param grid The layout to be used
	 */
	public void createPwAuthLive(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label pleaseEnter = new Label("Please enter your username & password:");
		GridPane.setConstraints(pleaseEnter, 1, 0);

		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 1);

		TextField nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 1);

		Label pwLabel = new Label("Password: ");
		GridPane.setConstraints(pwLabel, 0, 2);

		TextField pwInput = new TextField();
		GridPane.setConstraints(pwInput, 1, 2);

		Button loginButton = new Button("Log In");
		loginButton.setOnAction(e -> {
			if (nameInput.getText().equals("username")
					&& pwInput.getText().equals("password")) {
				hasLoggedIn = true;
				window.setScene(liveScene);
			} else {
				pwInput.clear();
				nameInput.clear();
				pleaseEnter.setText("Incorrect username or password.");
				pleaseEnter.setTextFill(Color.RED);
			}
		});
		GridPane.setConstraints(loginButton, 1, 3);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 5);
		backButton.setOnAction(e -> {
			pleaseEnter.setText("Please enter your username & password:");
			pleaseEnter.setTextFill(Color.BLACK);
			window.setScene(mainScene);
		});

		grid.getChildren().addAll(pleaseEnter, nameLabel, nameInput, pwLabel,
				pwInput, loginButton, backButton);

		pwLiveScene = new Scene(grid, 350, 250);
	}

	/**
	 * Creates the scene for the password authentication page going to the batch live input mode
	 * @param grid The layout to be used
	 */
	public void createPwAuthBatch(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label pleaseEnter = new Label("Please enter your username & password:");
		GridPane.setConstraints(pleaseEnter, 1, 0);

		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 1);

		TextField nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 1);

		Label pwLabel = new Label("Password: ");
		GridPane.setConstraints(pwLabel, 0, 2);

		TextField pwInput = new TextField();
		GridPane.setConstraints(pwInput, 1, 2);

		Button loginButton = new Button("Log In");
		loginButton.setOnAction(e -> {
			if (nameInput.getText().equals("username")
					&& pwInput.getText().equals("password")) {
				hasLoggedIn = true;
				window.setScene(batchScene);
			} else {
				pwInput.clear();
				nameInput.clear();
				pleaseEnter.setText("Incorrect username or password.");
				pleaseEnter.setTextFill(Color.RED);
			}
		});
		GridPane.setConstraints(loginButton, 1, 3);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 5);
		backButton.setOnAction(e -> {
			pleaseEnter.setText("Please enter your username & password:");
			pleaseEnter.setTextFill(Color.BLACK);
			window.setScene(mainScene);
		});

		grid.getChildren().addAll(pleaseEnter, nameLabel, nameInput, pwLabel,
				pwInput, loginButton, backButton);

		pwBatchScene = new Scene(grid, 350, 250);
	}

	/**
	 * Creates the scene for the player analysis page
	 * @param grid The layout to be used
	 * @param playerStrings
	 * @param pointStrings
	 * @param playerStrings2
	 * @param infStrings
	 */
	public void createPlayerAnalysis(GridPane grid, String[] playerStrings,
			String[] pointStrings, String[] playerStrings2, String[] infStrings) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("Player Analysis");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 10, 7);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Label topPlayersLabel = new Label("Top Players");
		topPlayersLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(topPlayersLabel, 1, 1);

		Label topPlayersPtsLabel = new Label("Goals");
		topPlayersPtsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(topPlayersPtsLabel, 2, 1);

		Label infPlayersLabel = new Label("Most Infracted Players");
		infPlayersLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(infPlayersLabel, 6, 1);

		Label infPlayersPointsLabel = new Label("Infractions");
		infPlayersPointsLabel
				.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(infPlayersPointsLabel, 10, 1);

		Label[] playerLabels = new Label[5];
		Label[] pointsLabels = new Label[5];
		Label[] player2Labels = new Label[5];
		Label[] infLabels = new Label[5];

		for (int i = 0; i < playerLabels.length; i++) {
			playerLabels[i] = new Label(playerStrings[i]);
			GridPane.setConstraints(playerLabels[i], 1, 2 + i);
		}

		for (int i = 0; i < pointsLabels.length; i++) {
			pointsLabels[i] = new Label(pointStrings[i]);
			GridPane.setConstraints(pointsLabels[i], 2, 2 + i);
		}

		for (int i = 0; i < player2Labels.length; i++) {
			player2Labels[i] = new Label(playerStrings2[i]);
			GridPane.setConstraints(player2Labels[i], 6, 2 + i);
		}

		for (int i = 0; i < infLabels.length; i++) {
			infLabels[i] = new Label(infStrings[i]);
			GridPane.setConstraints(infLabels[i], 10, 2 + i);
		}

		grid.getChildren().addAll(title, backButton, topPlayersLabel,
				topPlayersPtsLabel, infPlayersLabel, infPlayersPointsLabel,
				playerLabels[0], playerLabels[1], playerLabels[2],
				playerLabels[3], playerLabels[4], pointsLabels[0],
				pointsLabels[1], pointsLabels[2], pointsLabels[3],
				pointsLabels[4], player2Labels[0], player2Labels[1],
				player2Labels[2], player2Labels[3], player2Labels[4],
				infLabels[0], infLabels[1], infLabels[2], infLabels[3],
				infLabels[4]);

		playerScene = new Scene(grid, 550, 250);
	}

	/**
	 * Creates the scene for the league analysis page
	 * @param grid The layout to be used
	 * @param teamNames
	 * @param topTeamPoints
	 */
	public void createLeagueAnalysis(GridPane grid, String[] teamNames,
			String[] topTeamPoints) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("League Analysis");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 2, 12);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Label topTeams = new Label("Top Teams");
		topTeams.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(topTeams, 1, 1);

		Label points = new Label("Points");
		points.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(points, 2, 1);

		Label[] teamLabels = new Label[10];
		Label[] pointsLabels = new Label[10];

		for (int i = 0; i < teamLabels.length; i++) {
			teamLabels[i] = new Label(teamNames[i]);
			GridPane.setConstraints(teamLabels[i], 1, 2 + i);
		}

		for (int i = 0; i < pointsLabels.length; i++) {
			pointsLabels[i] = new Label(topTeamPoints[i]);
			GridPane.setConstraints(pointsLabels[i], 2, 2 + i);
		}

		grid.getChildren().addAll(title, backButton, topTeams, points,
				teamLabels[0], teamLabels[1], teamLabels[2], teamLabels[3],
				teamLabels[4], teamLabels[5], teamLabels[6], teamLabels[7],
				teamLabels[8], teamLabels[9], pointsLabels[0], pointsLabels[1],
				pointsLabels[2], pointsLabels[3], pointsLabels[4],
				pointsLabels[5], pointsLabels[6], pointsLabels[7],
				pointsLabels[8], pointsLabels[9]);

		leagueScene = new Scene(grid, 300, 350);
	}

	/**
	 * Creates the scene for the live input mode page
	 * @param grid The layout to be used
	 */
	public void createLiveMode(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("Live Input");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0);

		Label playerLabel = new Label("Player:");
		GridPane.setConstraints(playerLabel, 0, 1);

		TextField playerInput = new TextField();
		GridPane.setConstraints(playerInput, 1, 1);

		Label teamLabel = new Label("Team: ");
		GridPane.setConstraints(teamLabel, 0, 2);

		TextField teamInput = new TextField();
		GridPane.setConstraints(teamInput, 1, 2);

		Button saveButton = new Button("Save");
		GridPane.setConstraints(saveButton, 1, 5);
		saveButton.setOnAction(e -> {
			playerInput.clear();
			teamInput.clear();
		});

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 6);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Button goalButton = new Button("Goal");
		GridPane.setConstraints(goalButton, 2, 1);

		Button yellowButton = new Button("Yellow");
		GridPane.setConstraints(yellowButton, 3, 1);

		Button redButton = new Button("Red");
		GridPane.setConstraints(redButton, 4, 1);

		Button winButton = new Button("Win");
		GridPane.setConstraints(winButton, 2, 2);

		Button lossButton = new Button("Loss");
		GridPane.setConstraints(lossButton, 3, 2);

		Button tieButton = new Button("Tie");
		GridPane.setConstraints(tieButton, 4, 2);

		grid.getChildren().addAll(title, backButton, saveButton, playerLabel,
				playerInput, teamLabel, teamInput, goalButton, yellowButton,
				redButton, winButton, lossButton, tieButton);

		liveScene = new Scene(grid, 500, 220);
	}

	/**
	 * Creates the scene for the batch input mode page
	 * @param grid The layout to be used
	 */
	public void createBatchMode(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("Batch Input");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 5);
		backButton.setOnAction(e -> window.setScene(mainScene));

		grid.getChildren().addAll(title, backButton);

		batchScene = new Scene(grid, 350, 250);
	}

}
