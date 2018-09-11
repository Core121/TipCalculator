package com.example.corey.tipcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.example.corey.tipcalculator.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tip_Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //declaration of variables
    double tip = 0;
    double totalwtipamount = 0;
    String s = "";
    double custtipperc = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
                tip = 0;
                if (totalamount.getText().toString().equals("")) {
                    Toast.makeText(Tip_Calculator.this, "No total amount entered", Toast.LENGTH_SHORT).show();
                } else {
                    double total = Double.parseDouble(totalamount.getText().toString());
                    if (tipten.isChecked()) {
                        tip = calculatetip(.10, total);
                    } else if (tipfifteen.isChecked()) {
                        tip = calculatetip(.15, total);
                    } else if (tiptwenty.isChecked()) {
                        tip = calculatetip(.20, total);
                    } else if (customtip.isChecked()) {
                        tip = calculatetip(custtipperc, total);
                        tipten.toggle();
                    }

                    Double tipprecision = BigDecimal.valueOf(tip).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    totalwtipamount = tip + total;
                    Double totalprecision = BigDecimal.valueOf(totalwtipamount).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    s = String.valueOf(tipprecision);
                    tipamount.setText(s);
                    s = String.valueOf(totalprecision);
                    totalwtip.setText(s);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.customtip) {
                    AlertDialog.Builder Customtipprompt = new AlertDialog.Builder(Tip_Calculator.this);
                    Customtipprompt.setTitle("Custom Tip (Only Accepts Integers)");
                    Customtipprompt.setCancelable(false);
                    final EditText custtipval = new EditText(Tip_Calculator.this);
                    custtipval.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Customtipprompt.setView(custtipval);
                    Customtipprompt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (custtipval.getText().toString().equals("")) {
                                Toast.makeText(Tip_Calculator.this, "Empty Custom Percent", Toast.LENGTH_SHORT).show();
                                tipten.toggle();
                            } else {
                                custtipperc = Double.parseDouble(custtipval.getText().toString());
                                custtipperc /= 100;
                            }
                        }
                    });
                    Customtipprompt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            tipten.toggle();
                        }
                    });
                    Customtipprompt.show();
                }
            }
        });
    }


    //Function that calculates tip
    public double calculatetip(double tip, double total) {
        double tipreturn = 0;
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
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tipcalc) {

        }
        else if (id == R.id.nav_professioncalc) {

        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

