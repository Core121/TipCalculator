package com.coreapplications.corey.tipcalculater;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class Tip_Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new com.coreapplications.corey.tipcalculater.tipcalcfrag();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.passer, fragment);
        ft.commit();
        findViewById(R.id.passer).setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    //Function that calculates tip
    static public double calculatetip(double tip, double total) {
        double tipreturn;
        tipreturn = total * tip;
        return tipreturn;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
    displayView(item.getItemId());
        return true;
    }

    private void displayView(int itemId) {

        //creating fragment object
        Fragment fragment1 = new tipcalcfrag();
        Fragment fragment2 = new Profession();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_tipcalc:
                ft.replace(R.id.passer, fragment1);
                findViewById(R.id.passer).setVisibility(View.VISIBLE);
                findViewById(R.id.app_content_profession).setVisibility(View.INVISIBLE);
                break;
            case R.id.nav_profession:
                ft.replace(R.id.app_content_profession, fragment2);
                findViewById(R.id.app_content_profession).setVisibility(View.VISIBLE);
                findViewById(R.id.passer).setVisibility(View.INVISIBLE);
                break;
        }

        //replacing the fragment
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}

