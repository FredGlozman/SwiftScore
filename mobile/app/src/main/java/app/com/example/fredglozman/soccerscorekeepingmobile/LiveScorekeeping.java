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

import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Team;

public class LiveScorekeeping extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_scorekeeping);
        setTitle("Live Scorekeeping");

        //populate the spinner with the names of all the teams in the league
        Spinner team1_spinner = (Spinner) findViewById(R.id.spinner_team1);
        Spinner team2_spinner = (Spinner) findViewById(R.id.spinner_team2);

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
        team2_spinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
            case R.id.cancel:
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
    public void play(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Spinner team1_spinner = (Spinner) findViewById(R.id.spinner_team1);
        Spinner team2_spinner = (Spinner) findViewById(R.id.spinner_team2);
        String team1 = team1_spinner.getSelectedItem().toString().trim();
        String team2 = team2_spinner.getSelectedItem().toString().trim();

        //check if the teams exist
        if(!team1.matches(team2))
        {
            final Intent intent = new Intent(this, LiveGame.class);
            intent.putExtra("team1", team1);
            intent.putExtra("team2", team2);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Team can't play against itself", Toast.LENGTH_SHORT).show();
            return;
        }

        return;
    }

    //returns true if team1 is a team in the league, returns false otherwise
    public boolean isRealTeam(String team1)
    {
        //check if teams exist
        List<Team> teams = League.getInstance().getTeams();
        for(Team team : teams)
        {
            if(team.getName().matches(team1))
            {
                return true;
            }
        }
        return false;
    }
}
