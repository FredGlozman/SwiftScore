/**
 * Controller of the activity_player_analysis view
 * Contains the logic which analyzes which sorting mode the user has chosen
 * and displays the top 10 players sorted by the mode chosen by the user
 * @author Fred Glozman
 */

package app.com.example.fredglozman.soccerscorekeepingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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

public class PlayerAnalysis extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_analysis);
        setTitle("Player Analysis");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> modes = new ArrayList<String>();
        modes.add("Goals");
        modes.add("Shots");
        modes.add("Total Infractions");
        modes.add("Yellow Cards");
        modes.add("Red Cards");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, modes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        TextView text = (TextView) findViewById(R.id.player_analysis);
    }

    private void printList(List<Player> players, int mode)
    {
        TextView text = (TextView) findViewById(R.id.player_analysis);
        text.setText("");

        if(players.size()>10)
            switch (mode)
            {
                case 0:
                    for(int i=players.size()-1; i>=players.size()-10; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getSuccessfulShotCount());
                    }
                    break;
                case 1:
                    for(int i=players.size()-1; i>=players.size()-10; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getShots().size());
                    }
                    break;
                case 2:
                    for(int i=players.size()-1; i>=players.size()-10; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getInfractions().size());
                    }
                    break;
                case 3:
                    for(int i=players.size()-1; i>=players.size()-10; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getYellowInfractionCount());
                    }
                    break;
                case 4:
                    for(int i=players.size()-1; i>=players.size()-10; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getRedInfractionCount());
                    }
                    break;
                default:
                    break;
            }
        else if (players.size()!=0)
            switch (mode)
            {
                case 0:
                    for(int i=players.size()-1; i>=0; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getSuccessfulShotCount());
                    }
                    break;
                case 1:
                    for(int i=players.size()-1; i>=0; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getShots().size());
                    }
                    break;
                case 2:
                    for(int i=players.size()-1; i>=0; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getInfractions().size());
                    }
                    break;
                case 3:
                    for(int i=players.size()-1; i>=0; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getYellowInfractionCount());
                    }
                    break;
                case 4:
                    for(int i=players.size()-1; i>=0; i--)
                    {
                        Player player = players.get(i);
                        text.setText(text.getText() + "\n" + player.getName() + " -> " + player.getRedInfractionCount());
                    }
                    break;
                default:
                    break;
            }

    }

    @Override
    /**Sort the players based on number of shots, goals, total infractions, yellow card or red cards
     * and display the 10 players with the highest count
     * @param view is the view that calls this method
     * @return void
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        String selection = parent.getItemAtPosition(pos).toString();
        if(selection.matches("Goals"))
        {
            List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());

            Collections.sort(players, Player.COMPARE_BY_SUCCESSFUL_SHOTS);

            printList(players, 0);
        }
        else if(selection.matches("Shots"))
        {
            List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());

            Collections.sort(players, Player.COMPARE_BY_SHOTS);

            printList(players, 1);
        }
        else if(selection.matches("Total Infractions"))
        {
            List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());

            Collections.sort(players, Player.COMPARE_BY_TOTAL_INFRACTIONS);

            printList(players, 2);
        }
        else if(selection.matches("Yellow Cards"))
        {
            List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());

            Collections.sort(players, Player.COMPARE_BY_YELLOW_CARDS);

            printList(players, 3);
        }
        else
        {
            List<Player> players = new ArrayList<Player>(League.getInstance().getPlayers());

            Collections.sort(players, Player.COMPARE_BY_RED_CARDS);

            printList(players, 4);
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
}
