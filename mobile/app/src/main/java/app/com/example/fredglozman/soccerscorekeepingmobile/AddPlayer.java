/**
 * Controller of the activity_add_player view
 * Contains the logic which reads from the fields in the view,
 * analyzes user input and adds a player to the database
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.scorekeeper.search_utils.Find;
import ca.mcgill.ecse321.scorekeeper.model.Team;
import ca.mcgill.ecse321.scorekeeper.model.Goalie;
import ca.mcgill.ecse321.scorekeeper.model.League;


public class AddPlayer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        setTitle("Add Player");

        //populate the spinner with the names of all the teams in the league
        Spinner team1_spinner = (Spinner) findViewById(R.id.team_name);

        List<String> list_of_teams = new ArrayList<String>();

        for(Team team : League.getInstance().getTeams())
        {
            if(!team.getName().matches("HBU"))
                list_of_teams.add(team.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list_of_teams);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        team1_spinner.setAdapter(dataAdapter);
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

    /**Method that gets called when the user presses the add button
     * this method add the player to the team
     * @param view is the view that calls this method
     * @return void
     */
    public void addPlayer(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        //get team name
        Spinner team1_spinner = (Spinner) findViewById(R.id.team_name);
        String teamName = team1_spinner.getSelectedItem().toString().trim();

        //check if team name is not empty
        if(teamName.matches(""))
        {
            Toast.makeText(this, "Team Name empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //find team
        Team team = Find.getTeam(teamName);
        if(team==null)
        {
            Toast.makeText(this, "Team Doesn't Exist", Toast.LENGTH_SHORT).show();
            return;
        }

        //get text from player name and number field
        EditText player_field = (EditText) findViewById(R.id.player_name);
        EditText player_id_field = (EditText) findViewById(R.id.player_id);

        //get the player's jersey number and make sure it's a integer number
        int playerNumber = -1;
        String player_number_string = player_id_field.getText().toString().trim();
        if(android.text.TextUtils.isDigitsOnly(player_number_string))
        {
            playerNumber = Integer.parseInt(player_number_string);
        }
        else
        {
            Toast.makeText(this, "Player Number must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        //get the player's name
        String playerName = player_field.getText().toString().trim();


        //check if player doesn't already exist
        if(Find.playerExists(playerName))
        {
            Toast.makeText(this, "Player Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        //check if player name not empty
        else if(playerName.matches(""))
        {
            Toast.makeText(this, "Player Name Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        //add player
        else
        {
            //player or goalie
            RadioButton is_player_radio = (RadioButton) findViewById(R.id.radio_player);
            if(is_player_radio.isChecked())
            {
                team.addPlayer(playerName, playerNumber, League.getInstance());
                Toast.makeText(this, "Player Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //                  Goalie(String aName, int aJerseyNumber, Team aTeam, League aLeague)
                Goalie goalie = new Goalie(playerName, playerNumber, team, League.getInstance());
                team.addPlayer(goalie);
                Toast.makeText(this, "Goalie Added", Toast.LENGTH_SHORT).show();
            }

            //reset fields
            player_field.setText("");
            player_id_field.setText("");
        }
    }
}
