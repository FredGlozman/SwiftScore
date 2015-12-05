/**
 * Controller of the activity_live_game view
 * Contains the logic which analyzes the name/jersey_number inputed by the user
 * determines what player that coresponds to and adds a shot, goal, red card or yellow card to the player
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.scorekeeper.model.Color;
import ca.mcgill.ecse321.scorekeeper.search_utils.Find;
import ca.mcgill.ecse321.scorekeeper.model.Game;
import ca.mcgill.ecse321.scorekeeper.model.Goalie;
import ca.mcgill.ecse321.scorekeeper.model.Infraction;
import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Player;
import ca.mcgill.ecse321.scorekeeper.model.Shot;
import ca.mcgill.ecse321.scorekeeper.model.Team;

public class LiveGame extends ActionBarActivity {

    private Team team1;
    private Team team2;

    private Player player;

    int score1 = 0;
    int score2 = 0;

    private Game match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_game);
        setTitle("Live Scorekeeping");

        //get name of teams (data passed from live_scorekeeping view)
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            team1 = Find.getTeam(extras.getString("team1"));
            team2 = Find.getTeam(extras.getString("team2"));

            int currentTime = (int)System.currentTimeMillis();

            //          Game(int aStartTime, int aEndTime, String aLocation, League aLeague, Team... allCompetitors)
            match = new Game(currentTime, currentTime+(2*60*60), "CTU", League.getInstance(), team1, team2);
        }
        updateScoreLabel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    /** method that gets called when the user presses the cancel button.
     * Returns to main menu
     * @param view is the view that calls this method
     * @return void
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                final Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return true;

        }
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

    private void updateScoreLabel()
    {
        //update team1 and team 2 score text views. display current score of each team
        TextView team1_score_text = (TextView) findViewById(R.id.team1_score_text);
        TextView team2_score_text = (TextView) findViewById(R.id.team2_score_text);
        team1_score_text.setText(team1.getName() + "\n" + score1);
        team2_score_text.setText(team2.getName() + "\n" + score2);
    }


    /**Verifies if player exists on either team1 or team2
     * @param view is the view that calls this method
     * @return void
     */
    public void select(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString().trim();

        player = Find.inferPlayer(player_id, team1, team2);

        if(player!=null)
        {
            //enable buttons
            Button goal_button = (Button) findViewById(R.id.goal);
            Button yellow_button = (Button) findViewById(R.id.yellow);
            Button red_button = (Button) findViewById(R.id.red);
            goal_button.setClickable(true);
            yellow_button.setClickable(true);
            red_button.setClickable(true);

            //disable text field
            player_id_field.setEnabled(false);
        }
        else
        {
            Toast.makeText(this, "Player Doesn't Exist", Toast.LENGTH_SHORT).show();
            return;
        }
        return;
    }

    /**Adds a shot to the player
     * @param view is the view that calls this method
     * @return void
     */
    public void shot(View view)
    {
        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString();

        if(player_id.matches(""))
        {
            Toast.makeText(this, "Empty Player ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = Find.inferPlayer(player_id, team1, team2);

        if(player == null)
        {
            Toast.makeText(this, "Player Doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }

        int time_since_start = (int) (System.currentTimeMillis()-match.getStartTime());

        Goalie goalie = new Goalie("Bob", 24, team1, League.getInstance());

        //              Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game aGame)
        player.addShot(false, time_since_start, goalie, match);
        team1.removePlayer(goalie);
        League.getInstance().removePlayer(goalie);

        //disable buttons
        Button goal_button = (Button) findViewById(R.id.goal);
        Button yellow_button = (Button) findViewById(R.id.yellow);
        Button red_button = (Button) findViewById(R.id.red);
        goal_button.setClickable(false);
        yellow_button.setClickable(false);
        red_button.setClickable(false);

        Toast.makeText(this, "Shot: " + player_id, Toast.LENGTH_SHORT).show();

        //reset field
        player_id_field.setText("");
        //enable text field
        player_id_field.setEnabled(true);

        player = null;

        return;
    }
    /**Adds a goal to the player
     * @param view is the view that calls this method
     * @return void
     */
    public void goal(View view)
    {
        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString();

        if(player_id.matches(""))
        {
            Toast.makeText(this, "Empty Player ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = Find.inferPlayer(player_id, team1, team2);

        if(player == null)
        {
            Toast.makeText(this, "Player Doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }

        int time_since_start = (int) (System.currentTimeMillis()*1000-match.getStartTime());

        Goalie goalie = new Goalie("Bob", 24, team1, League.getInstance());

        //  public Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie, Game game)
        player.addShot(true, time_since_start, goalie, match);
        team1.removePlayer(goalie);

        if (player.getTeam() == team1)
            score1++;
        else
            score2++;

        //update score label
        updateScoreLabel();

        //disable buttons
        Button goal_button = (Button) findViewById(R.id.goal);
        Button yellow_button = (Button) findViewById(R.id.yellow);
        Button red_button = (Button) findViewById(R.id.red);
        goal_button.setClickable(false);
        yellow_button.setClickable(false);
        red_button.setClickable(false);

        Toast.makeText(this, "Goal: " + player_id, Toast.LENGTH_SHORT).show();

        //reset field
        player_id_field.setText("");
        //enable text field
        player_id_field.setEnabled(true);

        player = null;

        return;
    }
    /**Adds a yellow card to the player
     * @param view is the view that calls this method
     * @return void
     */
    public void yellow(View view)
    {
        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString();

        if(player_id.matches(""))
        {
            Toast.makeText(this, "Empty Player ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = Find.inferPlayer(player_id, team1, team2);

        if(player == null)
        {
            Toast.makeText(this, "Player Doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }

        int time_since_start = (int) (System.currentTimeMillis()*1000-match.getStartTime());

        //                           Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer, Game aGame)
        player.addInfraction(Color.YELLOW, false, time_since_start, match);

        //disable buttons
        Button goal_button = (Button) findViewById(R.id.goal);
        Button yellow_button = (Button) findViewById(R.id.yellow);
        Button red_button = (Button) findViewById(R.id.red);
        goal_button.setClickable(false);
        yellow_button.setClickable(false);
        red_button.setClickable(false);

        Toast.makeText(this, "Yellow card: " + player_id, Toast.LENGTH_SHORT).show();

        //reset field
        player_id_field.setText("");
        //enable text field
        player_id_field.setEnabled(true);

        player = null;

        return;
    }
    /**Adds a red card to the player
     * @param view is the view that calls this method
     * @return void
     */
    public void red(View view)
    {
        //reset text field
        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString();

        if(player_id.matches(""))
        {
            Toast.makeText(this, "Empty Player ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = Find.inferPlayer(player_id, team1, team2);

        if(player == null)
        {
            Toast.makeText(this, "Player Doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }

        int time_since_start = (int) (System.currentTimeMillis()*1000-match.getStartTime());

        //                           Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer, Game aGame)
        player.addInfraction(Color.RED, false, time_since_start, match);

        //disable buttons
        Button goal_button = (Button) findViewById(R.id.goal);
        Button yellow_button = (Button) findViewById(R.id.yellow);
        Button red_button = (Button) findViewById(R.id.red);
        goal_button.setClickable(false);
        yellow_button.setClickable(false);
        red_button.setClickable(false);

        Toast.makeText(this, "Red card: " + player_id, Toast.LENGTH_SHORT).show();

        //reset field
        player_id_field.setText("");
        //enable text field
        player_id_field.setEnabled(true);

        player = null;

        return;
    }
}
