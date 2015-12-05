/**
 * Controller of the activity_remove_player view
 * Contains the logic which analyzes the name inputed by the user
 * determines what team that corresponds to and removes the team
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
import android.widget.EditText;
import android.widget.Spinner;
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

public class RemoveTeam extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_team);
        setTitle("Remove Team");

        populateSpinner();
    }

    private void populateSpinner()
    {
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

    /**Removes the team specified by the user in the drop down menu
     * @param view is the view that calls this method
     * @return void
     */
    public void removeTeam(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        //get text from fields
        Spinner team1_spinner = (Spinner) findViewById(R.id.team_name);
        String teamName = team1_spinner.getSelectedItem().toString().trim();

        //find team and remove it
        Team team = Find.getTeam(teamName);
        if(team!= null) {
            for(Game game : team.getGames())
            {
                team.removeGame(game);
            }
            for(Player player : team.getPlayers())
            {
                team.removePlayer(player);
            }
            League.getInstance().removeTeam(team);
            populateSpinner();

            Toast.makeText(this, "Team Removed", Toast.LENGTH_SHORT).show();

            return;
        }
        else
        {
            Toast.makeText(this, "Team Doesn't Exist", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
