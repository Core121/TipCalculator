package com.example.corey.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Profession extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //variables
    private double bestservice;
    private double goodservice;
    private double badservice;

    public Profession(double best, double good, double bad){
        this.bestservice = best;
        this.goodservice = good;
        this.badservice = bad;
    }
    public Profession(){
        this.bestservice = 0;
        this.badservice = 0;
        this.goodservice = 0;
    }

    public void setBadservice(double badservice) {
        this.badservice = badservice;
    }

    public void setGoodservice(double goodservice) {
        this.goodservice = goodservice;
    }

    public void setBestservice(double bestservice) {
        this.bestservice = bestservice;
    }

    public double getBadservice() {
        return badservice;
    }

    public double getGoodservice() {
        return goodservice;
    }

    public double getBestservice() {
        return bestservice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tipcalc) {
            Intent intent = new Intent(this, Tip_Calculator.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_profession) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
