package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

public class MainView extends AppCompatActivity {

    //to do: beginnen met test invoeren! -> trainingsgegevens herleiden en een advies geven voor trainingsprogramma.
    // workout: starten borgscore eerst, timer lopen, zien wie er aan het sporten zijn LIVE updates -> eindigen weer borgscore. en toevoegen aan lijst.
    // informatie icoontjes in menubalk met dialogs per scherm.
    // mainmenu aanpassen naar menu in menubalk, startpagina veranderen naar -> mijn workouts en voortgang!
    //strings nakijken
    // code netter maken

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        DatabaseHelper.createDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.title_main_view);

        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "mainView");
        }else if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {
        String[] menuItems = getResources().getStringArray(R.array.MenuItems);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menuItem = (String) parent.getItemAtPosition(position);
                Intent intent;
                switch (menuItem) {
                    case "Overzicht":
                        intent = new Intent(MainView.this, MainView.class);
                        startActivity(intent);
                        break;
                    case "Mijn profiel":
                        intent = new Intent(MainView.this, MyProfile.class);
                        startActivity(intent);
                        break;
                    case "Doelen":
                        intent = new Intent(MainView.this, Goals.class);
                        startActivity(intent);
                        break;
                    case "Mijn workouts":
                        intent = new Intent(MainView.this, MyWorkouts.class);
                        startActivity(intent);
                        break;
                    case "Agenda":
                        intent = new Intent(MainView.this, MyCalendar.class);
                        startActivity(intent);
                        break;
                    case "Trainingsprogramma":
                        intent = new Intent(MainView.this, TrainingProgram.class);
                        startActivity(intent);
                        break;
                    case "Instellingen":
                        intent = new Intent(MainView.this, Settings.class);
                        startActivity(intent);
                        break;
                    case "Extra informatie":
                        intent = new Intent(MainView.this, Info.class);
                        startActivity(intent);
                        break;
                    case "Help":
                        intent = new Intent(MainView.this, Help.class);
                        startActivity(intent);
                        break;
                    case "Maatjes":
                        intent = new Intent(MainView.this, Buddies.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }
}
