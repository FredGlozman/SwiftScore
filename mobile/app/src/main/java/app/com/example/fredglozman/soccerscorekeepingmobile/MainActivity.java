/**
 * Controller of the activity_main view
 * Presents the 5 different modes and wait for the user to pick a mode.
 * This class also loads data into the app. for demonstration purposes.
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;


import ca.mcgill.ecse321.scorekeeper.model.League;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("swiftScore");
        setContentView(R.layout.activity_main);

        if(!getAuthenticationState())
            League.load();
    }

    /*
     *THE FOLLOWING COMMENTED CODE IS USED TO
     *LOAD IN DATA INTO THE APP
     *
     *THIS IS USED FOR DEMONSTRATION PURPOSES
     *
    private void setupDataForDemo()
    {
        Team team0 = new Team("CTU", League.getInstance());

        team0 = new Team("Docks United", League.getInstance());
        createAndAddPlayer("Bob Smith", 0, team0);

        team0 = new Team("Old Madrid", League.getInstance());
        createAndAddPlayer("Tony Almeida", 13, team0);

        team0 = new Team("Docks United", League.getInstance());
        createAndAddPlayer("Alexander Vladimirovich", 7, team0);

        team0 = new Team("Montreal Impact", League.getInstance());
        createAndAddPlayer("Taylor Swift", 22, team0);

        team0 = new Team("FC Edmonton", League.getInstance());
        createAndAddPlayer("Chloe O'brian", 33, team0);

        team0 = new Team("Future FC", League.getInstance());
        createAndAddPlayer("Michael White", 27, team0);

        team0 = new Team("Cavalier", League.getInstance());
        createAndAddPlayer("Walter White", 11, team0);

        team0 = new Team("Arsenal F.C.", League.getInstance());
        createAndAddPlayer("Ashley Cole", 77, team0);

        team0 = new Team("FC Barcelona", League.getInstance());
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

        team0 = new Team("FC Bayern Munich", League.getInstance());
        createAndAddPlayer("Mario Götze", 79, team0);

        team0 = new Team("Real Madrid C.F.", League.getInstance());
        createAndAddPlayer("Javier Mascherano", 67, team0);
        createAndAddPlayer("Lionel Messi", 25, team0);
        createAndAddPlayer("Cristiano Ronaldo", 27, team0);
        createAndAddPlayer("Andres Iniesta", 28, team0);
        createAndAddPlayer("Zlatan Ibrahimovic", 31, team0);
        createAndAddPlayer("Radamel Falcao", 26, team0);
        createAndAddPlayer("Robin van Persie", 29, team0);
        createAndAddPlayer("Andrea Pirlo", 33, team0);
        createAndAddPlayer("Yaya Toure", 37, team0);

        for(Team team : League.getInstance().getTeams())
        {
            if(team.getName().matches("HBU"))
            {
                League.getInstance().removeTeam(team);
            }
        }

    }
    private void createAndAddPlayer(String name, int number, Team team)
    {
        Player player = new Player(name, number, team, League.getInstance());
        Team dummy = new Team("HBU", League.getInstance());

        addShots(new Random().nextInt(50), player, dummy);
        addGoals(new Random().nextInt(50), player, dummy);
        addRed(new Random().nextInt(50), player, dummy);
        addYellow(new Random().nextInt(50), player, dummy);

        team.addPlayer(player);
        League.getInstance().removeTeam(dummy);
    }
    private void addShots(int numberOfShots, Player player, Team team)
    {
        // Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game aGame)
        for(int i=0; i<numberOfShots; i++)
        {
            Goalie bob = new Goalie("Bob", -1, player.getTeam(), League.getInstance());
            //public Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
            Game game = new Game(1000, 1000, "ctu", League.getInstance(), player.getTeam(), team);

            player.addShot(false, 1000, bob, game);

            player.getTeam().removePlayer(bob);
            player.getTeam().removeGame(game);
            team.removePlayer(bob);
            League.getInstance().removePlayer(bob);
            League.getInstance().removeGame(game);
            League.getInstance().removeTeam(team);
        }
    }
    private void addGoals(int numberOfShots, Player player, Team team)
    {
        // Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game aGame)
        for(int i=0; i<numberOfShots; i++)
        {
            Goalie bob = new Goalie("Bob", -1, player.getTeam(), League.getInstance());
            //public Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
            Game game = new Game(1000, 1000, "ctu", League.getInstance(), player.getTeam(), team);

            player.addShot(true, 1000, bob, game);

            player.getTeam().removePlayer(bob);
            player.getTeam().removeGame(game);
            team.removePlayer(bob);
            League.getInstance().removePlayer(bob);
            League.getInstance().removeGame(game);
            League.getInstance().removeTeam(team);
        }
    }
    private void addRed(int numberOfShots, Player player, Team team)
    {
        //public Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer, Game aGame)
        for(int i=0; i<numberOfShots; i++)
        {
            Goalie bob = new Goalie("Bob", -1, player.getTeam(), League.getInstance());
            //public Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
            Game game = new Game(1000, 1000, "ctu", League.getInstance(), player.getTeam(), team);

            player.addInfraction(Color.YELLOW, false, 1000, game);

            player.getTeam().removePlayer(bob);
            player.getTeam().removeGame(game);
            team.removePlayer(bob);
            League.getInstance().removePlayer(bob);
            League.getInstance().removeGame(game);
            League.getInstance().removeTeam(team);
        }
    }
    private void addYellow(int numberOfShots, Player player, Team team)
    {
        //public Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer, Game aGame)
        for(int i=0; i<numberOfShots; i++)
        {
            Goalie bob = new Goalie("Bob", -1, player.getTeam(), League.getInstance());
            //public Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
            Game game = new Game(1000, 1000, "ctu", League.getInstance(), player.getTeam(), team);

            player.addInfraction(Color.RED, false, 1000, game);

            player.getTeam().removePlayer(bob);
            player.getTeam().removeGame(game);
            team.removePlayer(bob);
            League.getInstance().removePlayer(bob);
            League.getInstance().removeGame(game);
            League.getInstance().removeTeam(team);
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        League.save(League.getInstance());
        deleteAuthenticationFile();
    }

    //deletes the file which contains the authentication status of the user
    private void deleteAuthenticationFile()
    {
        try {
            File dir = getFilesDir();
            File file = new File(dir, "authentication.txt");
            file.delete();
        } catch (Exception e) {}
    }

    public void read_input_from_main_menu(View view)
    {
        //get radio buttons
        RadioButton config_radio = (RadioButton) findViewById(R.id.radio_config);
        RadioButton live_radio = (RadioButton) findViewById(R.id.radio_live);
        RadioButton batch_radio = (RadioButton) findViewById(R.id.radio_batch);
        RadioButton player_radio = (RadioButton) findViewById(R.id.radio_player);
        //RadioButton league_radio = (RadioButton) findViewById(R.id.radio_league);

        boolean isAuthenticated = getAuthenticationState();

        //league configuration
        //ask for authentication
        if(config_radio.isChecked())
        {
            if (!isAuthenticated)
            {
                final Intent intent = new Intent(this, Authentication.class);
                intent.putExtra("mode", "LeagueConfiguration");
                startActivity(intent);
            }
            else
            {
                final Intent intent = new Intent(this, LeagueConfiguration.class);
                startActivity(intent);
            }
        }
        //live input mode
        //ask for authentication
        else if (live_radio.isChecked())
        {
            if(!isAuthenticated)
            {
                final Intent intent = new Intent(this, Authentication.class);
                intent.putExtra("mode", "LiveScorekeeping");
                startActivity(intent);
            }
            else
            {
                final Intent intent = new Intent(this, LiveScorekeeping.class);
                startActivity(intent);
            }
        }
        //batch input mode
        //ask for authentication
        else if (batch_radio.isChecked())
        {
            if(!isAuthenticated)
            {
                final Intent intent = new Intent(this, Authentication.class);
                intent.putExtra("mode", "BatchInput");
                startActivity(intent);
            }
            else
            {
                final Intent intent = new Intent(this, BatchInput.class);
                startActivity(intent);
            }
        }
        //player analysis mode
        //switch to the player analysis view
        else if (player_radio.isChecked())
        {
            final Intent intent = new Intent(this, PlayerAnalysis.class);
            startActivity(intent);
        }
        //league analysis mode
        //switch to the league analysis view
        else
        {
            final Intent intent = new Intent(this, LeagueAnalysis.class);
            startActivity(intent);
        }
    }

    private boolean getAuthenticationState()
    {
        String ret = "";

        try {
            InputStream inputStream = openFileInput("authentication.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (Exception e) {}

        return ret.matches("true");
    }
}