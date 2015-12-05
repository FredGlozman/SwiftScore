/**
 * Controller of the activity_date_input view
 * Contains the logic which reads from the fields in the view,
 * analyzes user input and determines the end time of the soccer game
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.io.File;

import ca.mcgill.ecse321.scorekeeper.model.League;

public class EndTimeInput extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_input);
        TextView title = (TextView) findViewById(R.id.start);
        title.setText("End Time");
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
     * this method records the end time of the game
     * and passes the data along to the MatchFullData view
     * @param view is the view that calls this method
     * @return void
     */
    public void next(View view)
    {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerStart);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String team1 = extras.getString("team1");
            String team2 = extras.getString("team2");
            String location = extras.getString("location");
            String date = extras.getString("date");
            String dateStart = extras.getString("dateStart");

            int hours = timePicker.getCurrentHour();
            int minutes = timePicker.getCurrentMinute();

            String dateEnd = date + hours + ":" + minutes;

            final Intent intent = new Intent(this, MatchFullData.class);
            intent.putExtra("team1", team1);
            intent.putExtra("team2", team2);
            intent.putExtra("location", location);
            intent.putExtra("dateStart", dateStart);
            intent.putExtra("dateEnd", dateEnd);
            startActivity(intent);
        }
    }
}
