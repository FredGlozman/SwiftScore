/**
 * Controller of the activity_date_input view
 * Contains the logic which reads from the fields in the view,
 * analyzes user input and determines the start time of the soccer game
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import java.io.File;

import ca.mcgill.ecse321.scorekeeper.model.Color;
import ca.mcgill.ecse321.scorekeeper.search_utils.Find;
import ca.mcgill.ecse321.scorekeeper.model.Game;
import ca.mcgill.ecse321.scorekeeper.model.Goalie;
import ca.mcgill.ecse321.scorekeeper.model.Infraction;
import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Player;
import ca.mcgill.ecse321.scorekeeper.model.Shot;
import ca.mcgill.ecse321.scorekeeper.model.Team;

public class StartTimeInput extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_input);
        setTitle("Batch Input");
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

    /** method that gets called when the user presses the next button
     * this method records the start time of the game
     * and passes the data along to the timePickerEnd view
     * @param view is the view that calls this method
     * @return void
     */
    public void next(View view)
    {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerStart);

        //get data that was passed to this view
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String team1 = extras.getString("team1");
            String team2 = extras.getString("team2");
            String location = extras.getString("location");
            String date = extras.getString("date");

            //get time
            int hours = timePicker.getCurrentHour();
            int minutes = timePicker.getCurrentMinute();

            String dateStart = date + hours + ":" + minutes;

            //pass data to the next view
            final Intent intent = new Intent(this, EndTimeInput.class);
            intent.putExtra("team1", team1);
            intent.putExtra("team2", team2);
            intent.putExtra("location", location);
            intent.putExtra("date", date);
            intent.putExtra("dateStart", dateStart);

            startActivity(intent);
        }
    }
}
