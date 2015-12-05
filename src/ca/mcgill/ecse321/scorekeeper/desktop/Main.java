package ca.mcgill.ecse321.scorekeeper.desktop;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.*;

import ca.mcgill.ecse321.scorekeeper.model.*;
import ca.mcgill.ecse321.scorekeeper.search_utils.Find;

/**
 * Class the runs the Desktop Application
 * 
 * @author JonAdalin
 *
 */
public class Main extends Application {

	Stage window;
	Scene mainScene, pwLiveScene, pwBatchScene, playerScene, leagueScene,
			liveScene, batchScene;

	boolean hasLoggedIn = false;

	/**
	 * Main, which launches the desktop app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Set up

		// For testing, has some test variables
		setupData();

		League.LOAD();

		List<Player> playerList = new ArrayList<Player>();
		List<Team> teamList = new ArrayList<Team>();

		// Remove dummy instances for the purpose of this desktop application

		for (Player player : League.getInstance().getPlayers()) {
			if (player.getName().equals("Bob")) {
				// playerList.remove(player);
			} else
				playerList.add(player);
		}

		for (Team team : League.getInstance().getTeams()) {
			if (team.getName().equals("HBU")) {
				// playerList.remove(player);
			} else
				teamList.add(team);
		}

		// Sorting the top players and teams

		Collections.sort(playerList, Player.COMPARE_BY_SUCCESSFUL_SHOTS);
		Collections.reverse(playerList);

		List<Player> topPlayersGoals = new ArrayList<Player>(playerList);

		Collections.sort(playerList, Player.COMPARE_BY_TOTAL_INFRACTIONS);
		Collections.reverse(playerList);

		List<Player> topPlayersInf = new ArrayList<Player>(playerList);

		Collections.sort(teamList, Team.COMPARE_BY_POINTS);
		Collections.reverse(teamList);

		List<Team> topTeams = new ArrayList<Team>(teamList);

		// Generate the application

		window = primaryStage;
		window.setTitle("swiftScore");

		GridPane mainGrid = new GridPane();
		createMain(mainGrid);

		GridPane pwAuthLiveGrid = new GridPane();
		createPwAuthLive(pwAuthLiveGrid);

		GridPane pwAuthBatchGrid = new GridPane();
		createPwAuthBatch(pwAuthBatchGrid);

		GridPane playerGrid = new GridPane();
		createPlayerAnalysis(playerGrid, topPlayersGoals, topPlayersInf);

		GridPane leagueGrid = new GridPane();
		createLeagueAnalysis(leagueGrid, topTeams);

		GridPane liveGrid = new GridPane();
		createLiveMode(liveGrid);

		GridPane batchGrid = new GridPane();
		createBatchMode(batchGrid);

		window.setScene(mainScene);
		window.show();

		// System.out.println(League.getInstance().getPlayer(0).getName());
		// System.out.println(League.getInstance().getPlayer(1).getName());

	}

	/**
	 * Creates the scene for the main menu page
	 * 
	 * @param grid
	 *            The layout to be used
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
	 * Creates the scene for the password authentication page going to the live
	 * input mode
	 * 
	 * @param grid
	 *            The layout to be used
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
			}
		});
		GridPane.setConstraints(loginButton, 1, 3);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 5);
		backButton.setOnAction(e -> {
			pleaseEnter.setText("Please enter your username & password:");
			window.setScene(mainScene);
		});

		grid.getChildren().addAll(pleaseEnter, nameLabel, nameInput, pwLabel,
				pwInput, loginButton, backButton);

		pwLiveScene = new Scene(grid, 350, 250);
	}

	/**
	 * Creates the scene for the password authentication page going to the batch
	 * live input mode
	 * 
	 * @param grid
	 *            The layout to be used
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
			}
		});
		GridPane.setConstraints(loginButton, 1, 3);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 5);
		backButton.setOnAction(e -> {
			pleaseEnter.setText("Please enter your username & password:");
			window.setScene(mainScene);
		});

		grid.getChildren().addAll(pleaseEnter, nameLabel, nameInput, pwLabel,
				pwInput, loginButton, backButton);

		pwBatchScene = new Scene(grid, 350, 250);
	}

	/**
	 * Creates the scene for the player analysis page
	 * 
	 * @param grid
	 *            The layout to be used
	 * @param playerStrings
	 * @param pointStrings
	 * @param playerStrings2
	 * @param infStrings
	 */
	public void createPlayerAnalysis(GridPane grid, List<Player> topScorers,
			List<Player> topInf) {

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

		for (int i = 0; i < 5; i++) {
			playerLabels[i] = new Label(topScorers.get(i).getName());
			GridPane.setConstraints(playerLabels[i], 1, 2 + i);
		}

		for (int i = 0; i < 5; i++) {
			pointsLabels[i] = new Label(""
					+ topScorers.get(i).getSuccessfulShotCount());
			GridPane.setConstraints(pointsLabels[i], 2, 2 + i);
		}

		for (int i = 0; i < 5; i++) {
			player2Labels[i] = new Label(topInf.get(i).getName());
			GridPane.setConstraints(player2Labels[i], 6, 2 + i);
		}

		for (int i = 0; i < 5; i++) {
			infLabels[i] = new Label(""
					+ (topInf.get(i).getRedInfractionCount() + topInf.get(i)
							.getYellowInfractionCount()));
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
	 * 
	 * @param grid
	 *            The layout to be used
	 * @param teamNames
	 * @param topTeamPoints
	 */
	public void createLeagueAnalysis(GridPane grid, List<Team> topTeams) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("League Analysis");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 2, 12);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Label topTeamsLabel = new Label("Top Teams");
		topTeamsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(topTeamsLabel, 1, 1);

		Label points = new Label("Points");
		points.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		GridPane.setConstraints(points, 2, 1);

		Label[] teamLabels = new Label[10];
		Label[] pointsLabels = new Label[10];

		for (int i = 0; i < 10; i++) {
			teamLabels[i] = new Label(topTeams.get(i).getName());
			GridPane.setConstraints(teamLabels[i], 1, 2 + i);
		}

		for (int i = 0; i < 10; i++) {
			pointsLabels[i] = new Label("" + topTeams.get(i).getPoints());
			GridPane.setConstraints(pointsLabels[i], 2, 2 + i);
		}

		grid.getChildren().addAll(title, backButton, topTeamsLabel, points,
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
	 * 
	 * @param grid
	 *            The layout to be used
	 */
	public void createLiveMode(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("Live Input");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Label instructions = new Label(
				"Please enter a team name or player name then");
		GridPane.setConstraints(instructions, 1, 1);

		Label instructions2 = new Label(
				"increment the stats & save using the buttons.");
		GridPane.setConstraints(instructions2, 1, 2);

		Label playerLabel = new Label("Player:");
		GridPane.setConstraints(playerLabel, 0, 3);

		TextField playerInput = new TextField();
		GridPane.setConstraints(playerInput, 1, 3);

		Label teamLabel = new Label("Team: ");
		GridPane.setConstraints(teamLabel, 0, 4);

		TextField teamInput = new TextField();
		GridPane.setConstraints(teamInput, 1, 4);

		Label warning = new Label("");
		GridPane.setConstraints(warning, 1, 5);

		Button saveButton = new Button("Save");
		GridPane.setConstraints(saveButton, 1, 6);
		saveButton.setOnAction(e -> {
			playerInput.clear();
			teamInput.clear();
			League.SAVE();
			warning.setText("Save successful!");
		});

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 7);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Button goalButton = new Button("Goal");
		GridPane.setConstraints(goalButton, 2, 3);
		goalButton.setOnAction(e -> {
			if (playerInput.getText().equals("")) {
				warning.setText("Please enter a player name first!");
			} else {
				warning.setText("Goal incremented!");
				// Must implement a way to properly increment a player's goals
				// addGoals(
				// 1,
				// Find.getPlayer(playerInput.getText(),
				// Find.getTeam(teamInput.getText())),
				// Find.getTeam(teamInput.getText()));
			}
		});

		Button yellowButton = new Button("Yellow");
		GridPane.setConstraints(yellowButton, 3, 3);
		yellowButton.setOnAction(e -> {
			if (playerInput.getText().equals("")) {
				warning.setText("Please enter a player name first!");
			} else {
				warning.setText("Yellow Card incremented!");
				// Must increment yellow cards with a similar method described
				// for the
				// goal button
			}
		});

		Button redButton = new Button("Red");
		GridPane.setConstraints(redButton, 4, 3);
		redButton.setOnAction(e -> {
			if (playerInput.getText().equals("")) {
				warning.setText("Please enter a player name first!");
			} else {
				warning.setText("Red Card incremented!");
				// Must increment red cards with a similar method described for
				// the goal button
			}
		});

		Button winButton = new Button("Win");
		GridPane.setConstraints(winButton, 2, 4);
		winButton.setOnAction(e -> {
			if (teamInput.getText().equals("")) {
				warning.setText("Please enter a team name first!");
			} else {
				warning.setText("Wins incremented!");
				// Must increment wins for a team with a similar method
				// described for the goal button
			}
		});

		Button lossButton = new Button("Loss");
		GridPane.setConstraints(lossButton, 3, 4);
		lossButton.setOnAction(e -> {
			if (teamInput.getText().equals("")) {
				warning.setText("Please enter a team name first!");
			} else {
				warning.setText("Losses incremented!");
				// Must increment wins for a team with a similar method
				// described for the goal button
			}
		});

		Button tieButton = new Button("Tie");
		GridPane.setConstraints(tieButton, 4, 4);
		tieButton.setOnAction(e -> {
			if (teamInput.getText().equals("")) {
				warning.setText("Please enter a team name first!");
			} else {
				warning.setText("Ties incremented!");
				// Must increment wins for a team with a similar method
				// described for the goal button
			}
		});

		grid.getChildren().addAll(title, instructions, instructions2,
				backButton, saveButton, warning, playerLabel, playerInput,
				teamLabel, teamInput, goalButton, yellowButton, redButton,
				winButton, lossButton, tieButton);

		liveScene = new Scene(grid, 550, 270);
	}

	/**
	 * Creates the scene for the batch input mode page
	 * 
	 * @param grid
	 *            The layout to be used
	 */
	public void createBatchMode(GridPane grid) {

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label title = new Label("Batch Input");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 1, 0);

		Label instructions = new Label(
				"Enter the increment values for each stat, then Save.");
		GridPane.setConstraints(instructions, 1, 1);

		Label warning = new Label("");
		GridPane.setConstraints(warning, 1, 5);

		Label playerLabel = new Label("Player:");
		GridPane.setConstraints(playerLabel, 0, 2);

		TextField playerInput = new TextField();
		GridPane.setConstraints(playerInput, 1, 2);

		Label teamLabel = new Label("Team: ");
		GridPane.setConstraints(teamLabel, 0, 3);

		TextField teamInput = new TextField();
		GridPane.setConstraints(teamInput, 1, 3);

		Button backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 7);
		backButton.setOnAction(e -> window.setScene(mainScene));

		Label goalLabel = new Label("Goals: ");
		GridPane.setConstraints(goalLabel, 2, 2);

		TextField goalInput = new TextField();
		GridPane.setConstraints(goalInput, 3, 2);

		Label yellowLabel = new Label("Yellow Cards: ");
		GridPane.setConstraints(yellowLabel, 4, 2);

		TextField yellowInput = new TextField();
		GridPane.setConstraints(yellowInput, 5, 2);

		Label redLabel = new Label("Red Cards: ");
		GridPane.setConstraints(redLabel, 6, 2);

		TextField redInput = new TextField();
		GridPane.setConstraints(redInput, 7, 2);

		Label winLabel = new Label("Wins: ");
		GridPane.setConstraints(winLabel, 2, 3);

		TextField winInput = new TextField();
		GridPane.setConstraints(winInput, 3, 3);

		Label lossLabel = new Label("Losses: ");
		GridPane.setConstraints(lossLabel, 4, 3);

		TextField lossInput = new TextField();
		GridPane.setConstraints(lossInput, 5, 3);

		Label tieLabel = new Label("Ties: ");
		GridPane.setConstraints(tieLabel, 6, 3);

		TextField tieInput = new TextField();
		GridPane.setConstraints(tieInput, 7, 3);

		Button saveButton = new Button("Save");
		GridPane.setConstraints(saveButton, 1, 6);
		saveButton
				.setOnAction(e -> {
					if (playerInput.getText().equals("")
							&& (!goalInput.getText().equals("0")
									|| !yellowInput.getText().equals("0") || !redInput
									.getText().equals("0"))) {
						warning.setText("Please enter a player name.");
					} else if (teamInput.getText().equals("0")
							&& (!winInput.getText().equals("0")
									|| !lossInput.getText().equals("0") || !tieInput
									.getText().equals("0"))) {
						warning.setText("Please enter a team name.");
					} else if (!isInt(goalInput.getText())
							|| !isInt(yellowInput.getText())
							|| !isInt(redInput.getText())
							|| !isInt(winInput.getText())
							|| !isInt(lossInput.getText())
							|| !isInt(tieInput.getText())) {
						warning.setText("Please enter the increment value as an integer.");
					} else if (playerInput.getText().equals("")
							&& teamInput.getText().equals("")) {
							warning.setText("Please enter a player or team name.");
					} else {
						/*
						 * Must implement a way to increment the stats by the
						 * input int values
						 */
						playerInput.clear();
						teamInput.clear();
						goalInput.setText("0");
						yellowInput.setText("0");
						redInput.setText("0");
						winInput.setText("0");
						lossInput.setText("0");
						tieInput.setText("0");
						League.SAVE();
						warning.setText("Update successful!");
					}
				});

		goalInput.setText("0");
		yellowInput.setText("0");
		redInput.setText("0");
		winInput.setText("0");
		lossInput.setText("0");
		tieInput.setText("0");

		grid.getChildren().addAll(title, warning, instructions, backButton,
				saveButton, playerLabel, playerInput, teamLabel, teamInput,
				goalLabel, goalInput, yellowLabel, yellowInput, redLabel,
				redInput, winLabel, winInput, lossLabel, lossInput, tieLabel,
				tieInput);

		batchScene = new Scene(grid, 1200, 280);
	}

	private boolean isInt(String input) {
		try {
			int inc = Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * The following methods were added for testing and demonstrating purposes
	 * only:
	 */

	public static void setupData() {
		Team team0 = new Team("Montreal Impact", League.getInstance());
		createAndAddPlayer("Jon", 10, team0);
		createAndAddPlayer("Fred", 22, team0);
		createAndAddPlayer("Cesc Fabregas", 81, team0);
		createAndAddPlayer("Sergio Aguero", 23, team0);
		createAndAddPlayer("Sergio Busquets", 21, team0);
		createAndAddPlayer("David Silva", 25, team0);
		createAndAddPlayer("Gianluigi Buffon", 34, team0);
		createAndAddPlayer("Jack Bauer", 24, team0);
		createAndAddPlayer("Luis Suarez", 35, team0);
		createAndAddPlayer("Sergio Ramos", 34, team0);
		createAndAddPlayer("Vincent Kompany", 55, team0);
		createAndAddPlayer("Gerard Pique", 48, team0);
		createAndAddPlayer("Javier Mascherano", 67, team0);
		createAndAddPlayer("Lionel Messi", 25, team0);
		createAndAddPlayer("Cristiano Ronaldo", 27, team0);
		createAndAddPlayer("Andres Iniesta", 28, team0);
		createAndAddPlayer("Zlatan Ibrahimovic", 31, team0);
		createAndAddPlayer("Radamel Falcao", 26, team0);
		createAndAddPlayer("Robin van Persie", 29, team0);
		createAndAddPlayer("Andrea Pirlo", 33, team0);
		createAndAddPlayer("Yaya Toure", 37, team0);

		Team team1 = new Team("Toronto FC", League.getInstance());
		createAndAddPlayer("The Other Guy", 0, team1);

		Team team2 = new Team("New Yourk Red Bulls", League.getInstance());
		Team team3 = new Team("FC Dallas", League.getInstance());
		Team team4 = new Team("Columbus Crew SC", League.getInstance());
		Team team5 = new Team("Portland Timbers", League.getInstance());
		Team team6 = new Team("Vancouver Whitecaps FC", League.getInstance());
		Team team7 = new Team("D.C. United", League.getInstance());
		Team team8 = new Team("LA Galaxy", League.getInstance());
		Team team9 = new Team("Sporting Kansas City", League.getInstance());

		for (Team team : League.getInstance().getTeams()) {
			if (team.getName().matches("HBU")) {
				League.getInstance().removeTeam(team);
			}
		}
	}

	private static void createAndAddPlayer(String name, int number, Team team) {
		Player player = new Player(name, number, team, League.getInstance());
		Team dummy = new Team("HBU", League.getInstance());

		addShots(new Random().nextInt(50), player, dummy);
		addGoals(new Random().nextInt(50), player, dummy);
		addRed(new Random().nextInt(50), player, dummy);
		addYellow(new Random().nextInt(50), player, dummy);

		team.addPlayer(player);
		League.getInstance().removeTeam(dummy);
	}

	private static void addShots(int numberOfShots, Player player, Team team) {
		// Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game
		// aGame)
		for (int i = 0; i < numberOfShots; i++) {
			Goalie bob = new Goalie("Bob", -1, player.getTeam(),
					League.getInstance());
			// public Game(int aStartTime, int aEndTime, String aLocation,
			// League aLeague, Team... allCompetitors)
			Game game = new Game(1000, 1000, "ctu", League.getInstance(),
					player.getTeam(), team);

			player.addShot(false, 1000, bob, game);

			player.getTeam().removePlayer(bob);
			player.getTeam().removeGame(game);
			team.removePlayer(bob);
			League.getInstance().removePlayer(bob);
			League.getInstance().removeGame(game);
			League.getInstance().removeTeam(team);
		}
	}

	private static void addGoals(int numberOfShots, Player player, Team team) {
		// Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game
		// aGame)
		for (int i = 0; i < numberOfShots; i++) {
			Goalie bob = new Goalie("Bob", -1, player.getTeam(),
					League.getInstance());
			// public Game(int aStartTime, int aEndTime, String aLocation,
			// League aLeague, Team... allCompetitors)
			Game game = new Game(1000, 1000, "ctu", League.getInstance(),
					player.getTeam(), team);

			player.addShot(true, 1000, bob, game);

			player.getTeam().removePlayer(bob);
			player.getTeam().removeGame(game);
			team.removePlayer(bob);
			League.getInstance().removePlayer(bob);
			League.getInstance().removeGame(game);
			League.getInstance().removeTeam(team);
		}
	}

	private static void addRed(int numberOfShots, Player player, Team team) {
		// public Infraction(Color aColor, boolean aPenaltyShot, int aTime,
		// Player aPlayer, Game aGame)
		for (int i = 0; i < numberOfShots; i++) {
			Goalie bob = new Goalie("Bob", -1, player.getTeam(),
					League.getInstance());
			// public Game(int aStartTime, int aEndTime, String aLocation,
			// League aLeague, Team... allCompetitors)
			Game game = new Game(1000, 1000, "ctu", League.getInstance(),
					player.getTeam(), team);

			player.addInfraction(Color.YELLOW, false, 1000, game);

			player.getTeam().removePlayer(bob);
			player.getTeam().removeGame(game);
			team.removePlayer(bob);
			League.getInstance().removePlayer(bob);
			League.getInstance().removeGame(game);
			League.getInstance().removeTeam(team);
		}
	}

	private static void addYellow(int numberOfShots, Player player, Team team) {
		// public Infraction(Color aColor, boolean aPenaltyShot, int aTime,
		// Player aPlayer, Game aGame)
		for (int i = 0; i < numberOfShots; i++) {
			Goalie bob = new Goalie("Bob", -1, player.getTeam(),
					League.getInstance());
			// public Game(int aStartTime, int aEndTime, String aLocation,
			// League aLeague, Team... allCompetitors)
			Game game = new Game(1000, 1000, "ctu", League.getInstance(),
					player.getTeam(), team);

			player.addInfraction(Color.RED, false, 1000, game);

			player.getTeam().removePlayer(bob);
			player.getTeam().removeGame(game);
			team.removePlayer(bob);
			League.getInstance().removePlayer(bob);
			League.getInstance().removeGame(game);
			League.getInstance().removeTeam(team);
		}
	}
}
