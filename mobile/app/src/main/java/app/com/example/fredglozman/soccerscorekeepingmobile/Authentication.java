/**
 * Controller of the activity_authentication view
 * Contains the logic which reads from the fields in the view,
 * analyzes user input and determines if the user has entered
 * a correct username and password
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
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import ca.mcgill.ecse321.scorekeeper.model.League;

public class Authentication extends ActionBarActivity {

    private Map<String, String> authenticators = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Authentication");
        setContentView(R.layout.activity_authentication);

        //username and password pairs...
        authenticators.put("fred", "123");
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

    /** method that gets called when the user presses the authenticate button
     * this method reads user inputed username and password and verifies if the data is valid
     * before switching to the appropriate view
     * @param view is the view that calls this method
     * @return void
     */
    public void authenticate(View view)
    {
        //hide soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        //get text from username field
        EditText username_field = (EditText) findViewById(R.id.username);
        String username = username_field.getText().toString().trim();

        //get text from password field
        EditText password_field = (EditText) findViewById(R.id.password);
        String password = password_field.getText().toString().trim();

        //get password corresponding to username
        String value = authenticators.get(username);

        //if key-value pair does not exist or password entered does not match password on record, fail to authenticate
        if(value == null || !value.equals(password))
        {
            saveAuthenticationState(false);
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        //saves authentication
        saveAuthenticationState(true);

        //got passed information from the main view
        //decide what view to switch to
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mode = extras.getString("mode");

            if (mode.equals("LeagueConfiguration"))
            {
                final Intent intent = new Intent(this, LeagueConfiguration.class);
                startActivity(intent);
            }
            else if(mode.equals("LiveScorekeeping"))
            {
                final Intent intent = new Intent(this, LiveScorekeeping.class);
                startActivity(intent);
            }
            else
            {
                final Intent intent = new Intent(this, BatchInput.class);
                startActivity(intent);
            }
        }

        return;
    }

    //saves the user authentication to a file.
    private void saveAuthenticationState(boolean isAuthenticated)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("authentication.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(""+isAuthenticated);
            outputStreamWriter.close();
        }
        catch (IOException e) {}
    }
}
