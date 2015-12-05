/**
 * Controller of the activity_league_analysis view
 * Contains the logic which analyzes which sorting mode the user has chosen
 * and displays the top 10 teams sorted by the mode chosen by the user
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

import ca.mcgill.ecse321.scorekeeper.model.League;
import ca.mcgill.ecse321.scorekeeper.model.Player;
import ca.mcgill.ecse321.scorekeeper.model.Team;

public class LeagueAnalysis extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_analysis);
        setTitle("League Analysis");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> modes = new ArrayList<String>();
        modes.add("Points");
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
        TextView text = (TextView) findViewById(R.id.league_analysis);
    }

    private void printList(List<Team> teams, int mode)
    {
        TextView text = (TextView) findViewById(R.id.league_analysis);
        text.setText("");

        if(teams.size()>10)
            switch (mode)
            {
                case 0:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getSuccessfulShotCount());
                    }
                    break;
                case 1:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getTotalShotCount());
                    }
                    break;
                case 2:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getTotalInfractionCount());
                    }
                    break;
                case 3:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getYellowInfractionCount());
                    }
                    break;
                case 4:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getRedInfractionCount());
                    }
                    break;
                case 5:
                    for(int i=teams.size()-1; i>=teams.size()-10; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getPoints());
                    }
                    break;
                default:
                    break;
            }
        else if(teams.size()!=0)
            switch (mode)
            {
                case 0:
                    for(int i=teams.size()-1; i>=0; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getSuccessfulShotCount());
                    }
                    break;
                case 1:
                    for(int i=teams.size()-1; i>=0; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getTotalShotCount());
                    }
                    break;
                case 2:
                    for(int i=teams.size()-1; i>=0; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getTotalInfractionCount());
                    }
                    break;
                case 3:
                    for(int i=teams.size()-1; i>=0; i--)
                    {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getYellowInfractionCount());
                    }
                    break;
                case 4:
                    for(int i=teams.size()-1; i>=0; i--) {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getRedInfractionCount());
                    }
                    break;
                case 5:
                    for(int i=teams.size()-1; i>=0; i--) {
                        Team team = teams.get(i);
                        text.setText(text.getText() + "\n" + team.getName() + " -> " + team.getPoints());
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
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_SUCCESSFUL_SHOTS);

            printList(teams, 0);
        }
        else if(selection.matches("Points"))
        {
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_POINTS);

            printList(teams, 5);
        }
        else if(selection.matches("Shots"))
        {
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_SHOTS);

            printList(teams, 1);
        }
        else if(selection.matches("Total Infractions"))
        {
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_TOTAL_INFRACTIONS);

            printList(teams, 2);
        }
        else if(selection.matches("Yellow Cards"))
        {
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_YELLOW_CARDS);

            printList(teams, 3);
        }
        else
        {
            List<Team> teams = new ArrayList<Team>(League.getInstance().getTeams());

            Collections.sort(teams, Team.COMPARE_BY_RED_CARDS);

            printList(teams, 4);
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
