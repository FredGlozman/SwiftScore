/**
 * Controller of the activity_match_full_data view
 * Contains the logic which analyzes the name/jersey_number inputed by the user
 * determines what player that corresponds to and adds a shot, goal, red card or yellow card to the player
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.mcgill.ecse321.scorekeeper.model.Team;
import ca.mcgill.ecse321.scorekeeper.model.Game;
import ca.mcgill.ecse321.scorekeeper.search_utils.Find;
import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Player;
import ca.mcgill.ecse321.scorekeeper.model.Goalie;
import ca.mcgill.ecse321.scorekeeper.model.Color;


public class MatchFullData extends ActionBarActivity {

    private Team team1;
    private Team team2;

    private Game match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_full_data);
        setTitle("Batch Input");

        //get team information and create match
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            String team1_name = extras.getString("team1");
            String team2_name = extras.getString("team2");
            String location = extras.getString("location");
            String startTime = extras.getString("dateStart");
            String endTime = extras.getString("dateEnd");

            team1 = Find.getTeam(team1_name);
            team2 = Find.getTeam(team2_name);

            try
            {
                SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                Date startDate = df.parse(startTime);
                long epochStart = startDate.getTime();

                Date endDate = df.parse(endTime);
                long epochEnd = endDate.getTime();

                //  public Game(int aStartTime, int aEndTime, String aLocation, int aVictor, League aLeague, Team... allCompetitors)
                match = new Game((int)epochStart, (int)epochEnd, location, League.getInstance(), team1, team2);
            }
            catch(Exception e)
            {
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    /** method that gets called when the user presses the done button.
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

    /**Adds a shot, goal, red card or yellow card to the player
     * depending on what radio button is checked off
     * @param view is the view that calls this method
     * @return void
     */

    public void add(View view)
    {
        EditText player_id_field = (EditText) findViewById(R.id.player_id_field);
        String player_id = player_id_field.getText().toString().trim();

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

        //get radio buttons
        RadioButton shot_radio = (RadioButton) findViewById(R.id.radio_shot);
        RadioButton goal_radio = (RadioButton) findViewById(R.id.radio_goal);
        RadioButton yellow_radio = (RadioButton) findViewById(R.id.radio_yellow);
        //RadioButton red_radio = (RadioButton) findViewById(R.id.radio_red);

        //!!!NEED TO ASK USER FOR TIME!!!
        int time_since_start = -1; //(Time provided by user) - match.getStartTime()

        //                Goalie(String aName, int aJerseyNumber, Team aTeam, League aLeague)
        Goalie goalie = new Goalie("bob", 1, team1, League.getInstance());

        if(shot_radio.isChecked())
        {
            //  public Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie)
            player.addShot(false, time_since_start, goalie, match);
            team1.removePlayer(goalie);

            Toast.makeText(this, "Shot: " + player_id, Toast.LENGTH_SHORT).show();
        }
        else if(goal_radio.isChecked())
        {
            //  public Shot(boolean aGoal, int aTime, Player aPlayer, Goalie aGoalie)
            player.addShot(true, time_since_start, goalie, match);
            team1.removePlayer(goalie);

            Toast.makeText(this, "Goal: " + player_id, Toast.LENGTH_SHORT).show();
        }
        else if(yellow_radio.isChecked())
        {
            //public Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer)
            player.addInfraction(Color.YELLOW, false, time_since_start, match);
        }
        else
        {
            //public Infraction(Color aColor, boolean aPenaltyShot, int aTime, Player aPlayer)
            player.addInfraction(Color.RED, false, time_since_start, match);
        }
    }
}
