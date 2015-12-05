/**
 * Controller of the activity_league_analysis view
 * Contains the logic which analyzes which mode the user has chosen
 * and transitions to the appropriate configuration view
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import java.io.File;

import ca.mcgill.ecse321.scorekeeper.model.League;

public class LeagueConfiguration extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_configuration);
        setTitle("League Configuration");
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

    /** method that gets called when the user presses the go button
     * this method records the state of the radio buttons
     * and transitions the app into the view that coresponds to the selected mode
     * @param view is the view that calls this method
     * @return void
     */
    public void go(View view)
    {
        RadioButton new_team = (RadioButton) findViewById(R.id.radio_new_team);
        RadioButton new_player = (RadioButton) findViewById(R.id.radio_new_player);
        RadioButton remove_team = (RadioButton) findViewById(R.id.radio_remove_team);
        //RadioButton remove_player = (RadioButton) findViewById(R.id.radio_remove_player);

        if(new_team.isChecked())
        {
            final Intent intent = new Intent(this, CreateTeam.class);
            startActivity(intent);
        }
        else if(new_player.isChecked())
        {
            final Intent intent = new Intent(this, AddPlayer.class);
            startActivity(intent);
        }
        else if(remove_team.isChecked())
        {
            final Intent intent = new Intent(this, RemoveTeam.class);
            startActivity(intent);
        }
        else
        {
            final Intent intent = new Intent(this, RemovePlayer.class);
            startActivity(intent);
        }

        return;
    }
}
