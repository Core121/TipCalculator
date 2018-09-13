package com.example.corey.tipcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.corey.tipcalculator.Tip_Calculator.calculatetip;

public class Profession extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnItemSelectedListener {
    //variables
    private double tip = 0;
    private double totalwtipamount = 0;
    private String outputter = "";
    private double custtipperc = 0;
    private double bestservice;
    private double goodservice;
    private double betterservice;


    public void setTip (double temptip) {
        this.tip = temptip;
    }
    public void setTotalwtipamount (double temptotalwtip) {
        this.totalwtipamount = temptotalwtip;
    }
    public void setCusttipperc (double tempCusttipperc) {
        this.custtipperc = tempCusttipperc;
    }

    //Getters
    public double getTip (){
        return tip;
    }
    public double getTotalwtipamount (){
        return tip;
    }
    public double getCusttipperc (){
        return custtipperc;
    }

    public Profession(double good, double better, double best){
        this.bestservice = best;
        this.goodservice = good;
        this.betterservice = better;
    }
    public Profession(){
        this.bestservice = 0;
        this.betterservice = 0;
        this.goodservice = 0;
    }

    public void setBetterservice(double betterservice) {
        this.betterservice = betterservice;
    }

    public void setGoodservice(double goodservice) {
        this.goodservice = goodservice;
    }

    public void setBestservice(double bestservice) {
        this.bestservice = bestservice;
    }

    public double getbetterservice() {
        return betterservice;
    }

    public double getGoodservice() {
        return goodservice;
    }

    public double getBestservice() {
        return bestservice;
    }
    public String getOutputter (){
        return outputter;
    }

    public void setOutputter(String outputter) {
        this.outputter = outputter;
    }

    Profession HairDresser = new Profession(.15,.20,.30);
    Profession PizzaDelivery= new Profession(.10,.15,.20);
    Profession Waiter= new Profession(.15,.20,.23);
    Profession Valet= new Profession(4,7,12);

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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.professions_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Instantiations
        final Button calcbtn = (Button) findViewById(R.id.calcbtn);
        final EditText totalamount = (EditText) findViewById(R.id.totalamount);
        final RadioButton tipten = (RadioButton) findViewById(R.id.tip_ten);
        final RadioButton tipfifteen = (RadioButton) findViewById(R.id.tip_fifteen);
        final RadioButton tiptwenty = (RadioButton) findViewById(R.id.tip_twenty);
        final RadioButton customtip = (RadioButton) findViewById(R.id.customtip);
        final TextView tag = (TextView) findViewById(R.id.Tag);
        final TextView totalwtip = (TextView) findViewById(R.id.totalwtip);
        final TextView tipamount = (TextView) findViewById(R.id.tipamount);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.tipRadioGroup);

        tipamount.setEnabled(false);
        tag.setEnabled(false);
        totalwtip.setEnabled(false);

        //anonymous inner class
        calcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTip(0);
                if (totalamount.getText().toString().equals("")) {
                    Toast.makeText(Profession.this, "No total amount entered", Toast.LENGTH_SHORT).show();
                } else {
                    double total = Double.parseDouble(totalamount.getText().toString());
                    double tiptemp = 0;
                    if (tipten.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(HairDresser.goodservice, total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(HairDresser.betterservice, total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(HairDresser.bestservice, total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(PizzaDelivery.goodservice, total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(PizzaDelivery.betterservice, total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(PizzaDelivery.bestservice, total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(Waiter.goodservice, total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(Waiter.betterservice, total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(Waiter.bestservice, total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(.20, total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(.15, total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(.20, total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.goodservice;
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.betterservice;
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.bestservice;
                    } else {
                        Toast.makeText(Profession.this, "No profession selected", Toast.LENGTH_SHORT).show();
                    }
                    setTip(tiptemp);
                    Double tipprecision = BigDecimal.valueOf(tip).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    setTotalwtipamount(tip + total);
                    Double totalprecision = BigDecimal.valueOf(totalwtipamount).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    setOutputter(String.valueOf((tipprecision)));
                    tipamount.setText(getOutputter());
                    setOutputter(String.valueOf(totalprecision));
                    totalwtip.setText(getOutputter());
                }
            }
        });

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

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){

        switch (position) {
            case 0:
                //Hair Dresser
                setallradiobuttontext(HairDresser);
                break;
            case 1:
                //Pizza Delivery
                setallradiobuttontext(PizzaDelivery);
                break;
            case 3:
                //Waiter
                setallradiobuttontext(Waiter);
                break;
            case 4:
                //Valet
                setallradiobuttontext(Valet);
                break;

        }

    }

    public void setallradiobuttontext(Profession pro){
        RadioButton tipgood = (RadioButton)findViewById(R.id.tip_ten);
        RadioButton tipbetter = (RadioButton)findViewById(R.id.tip_fifteen);
        RadioButton tipbest = (RadioButton)findViewById(R.id.tip_twenty);

        tipgood.setText(String.valueOf(pro.getGoodservice());
        tipbetter.setText(String.valueOf(pro.getbetterservice());
        tipbest.setText(String.valueOf(pro.getBestservice());
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

