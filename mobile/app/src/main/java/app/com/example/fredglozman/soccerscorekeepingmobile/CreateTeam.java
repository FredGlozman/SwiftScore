/**
 * Controller of the activity_create_team view
 * Contains the logic which reads from the fields in the view,
 * analyzes user input and adds a team to the database
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ca.mcgill.ecse321.scorekeeper.model.Team;
import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Player;
import ca.mcgill.ecse321.scorekeeper.model.Goalie;
import ca.mcgill.ecse321.scorekeeper.search_utils.Find;


public class CreateTeam extends ActionBarActivity {

    private Team newTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create Team");
        setContentView(R.layout.activity_create_team);
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

    /** method that gets called when the user presses the create button
     * this method adds the team to the league
     * @param view is the view that calls this method
     * @return void
     */
    public void create_team(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        Button create_button = (Button) findViewById(R.id.create_button);

        //get text from team name field
        EditText team_field = (EditText) findViewById(R.id.team_name);
        String teamName = team_field.getText().toString().trim();

        if(teamName.matches(""))
        {
            Toast.makeText(this, "Team Name empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Find.teamExists(teamName))
        {
            Toast.makeText(this, "Team Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        //create and add new team
        Team team = new Team(teamName, League.getInstance());
        newTeam = team;
        League.getInstance().addTeam(team);

        //disable team name field and create button
        team_field.setEnabled(false);
        create_button.setEnabled(false);

        //enable player name field and enable add button
        EditText player_field = (EditText) findViewById(R.id.player_name);
        EditText player_id_field = (EditText) findViewById(R.id.player_id);
        Button add_button = (Button) findViewById(R.id.add_button);
        player_field.setEnabled(true);
        player_id_field.setEnabled(true);
        add_button.setEnabled(true);
    }

    /** method that gets called when the user presses the add button
     * this method adds the player to the team
     * @param view is the view that calls this method
     * @return void
     */
    public void addPlayer(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        //get text from player name field
        EditText player_field = (EditText) findViewById(R.id.player_name);
        EditText player_id_field = (EditText) findViewById(R.id.player_id);

        //get player number. make sure it's an integer number
        int playerNumber = -1;
        String player_number_string = player_id_field.getText().toString().trim();
        if(!player_number_string.matches("") && android.text.TextUtils.isDigitsOnly(player_number_string))
        {
            playerNumber = Integer.parseInt(player_number_string);
        }
        else
        {
            Toast.makeText(this, "Player Number must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        String playerName = player_field.getText().toString().trim();

        if(Find.playerExists(playerName))
        {
            Toast.makeText(this, "Player Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //adding player
            RadioButton is_player_radio = (RadioButton) findViewById(R.id.radio_player);
            if(is_player_radio.isChecked())
            {
                newTeam.addPlayer(playerName, playerNumber, League.getInstance());
                Toast.makeText(this, "Player Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //adding goalie
                //                  Goalie(String aName, int aJerseyNumber, Team aTeam, League aLeague)
                Goalie goalie = new Goalie(playerName, playerNumber, newTeam, League.getInstance());
                newTeam.addPlayer(goalie);
                Toast.makeText(this, "Goalie Added", Toast.LENGTH_SHORT).show();
            }


            //reset fields
            player_field.setText("");
            player_id_field.setText("");
        }

        //get reference to text label and update its contents
        TextView players_label = (TextView) findViewById(R.id.names);
        players_label.setText(playersToString());
    }

    //turns players list to string
    private String playersToString()
    {
        String text = "";
        for(Player p : newTeam.getPlayers())
        {
            text += p.getName() + " " + p.getJerseyNumber() + "\n";
        }
        return text;
    }
}