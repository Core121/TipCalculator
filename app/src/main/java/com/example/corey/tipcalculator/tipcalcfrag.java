package com.example.corey.tipcalculator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
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

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Belal on 18/09/16.
 */


public class tipcalcfrag extends Fragment {
    //declaration of variables
    private double tip = 0;
    private double totalwtipamount = 0;
    private String outputter = "";
    private double custtipperc = 0;

    //Setters
    public void setTip(double temptip) {
        this.tip = temptip;
    }

    public void setTotalwtipamount(double temptotalwtip) {
        this.totalwtipamount = temptotalwtip;
    }

    public void setCusttipperc(double tempCusttipperc) {
        this.custtipperc = tempCusttipperc;
    }

    public void setOutputter(String tempoutput) {
        this.outputter = tempoutput;
    }

    //Getters
    public double getTip() {
        return tip;
    }

    public double getTotalwtipamount() {
        return tip;
    }

    public double getCusttipperc() {
        return custtipperc;
    }

    public String getOutputter() {
        return outputter;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        return inflater.inflate(R.layout.passer, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Tip Calculator");
        //Instantiations
        final Button calcbtn = (Button) getView().findViewById(R.id.calcbtn);
        final EditText totalamount = (EditText) getView().findViewById(R.id.totalamount);
        final RadioButton tipten = (RadioButton) getView().findViewById(R.id.tip_ten);
        final RadioButton tipfifteen = (RadioButton) getView().findViewById(R.id.tip_fifteen);
        final RadioButton tiptwenty = (RadioButton) getView().findViewById(R.id.tip_twenty);
        final RadioButton customtip = (RadioButton) getView().findViewById(R.id.customtip);
        final TextView tag = (TextView) getView().findViewById(R.id.Tag);
        final TextView totalwtip = (TextView) getView().findViewById(R.id.totalwtip);
        final TextView tipamount = (TextView) getView().findViewById(R.id.tipamount);
        final RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.tipRadioGroup);

        tipamount.setEnabled(false);
        tag.setEnabled(false);
        totalwtip.setEnabled(false);

        //anonymous inner class
        calcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTip(0);
                if (totalamount.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "No total amount entered", Toast.LENGTH_SHORT).show();
                } else {
                    double total = Double.parseDouble(totalamount.getText().toString());
                    double tiptemp = 0;
                    if (tipten.isChecked()) {
                        tiptemp = calculatetip(.10, total);
                    } else if (tipfifteen.isChecked()) {
                        tiptemp = calculatetip(.15, total);
                    } else if (tiptwenty.isChecked()) {
                        tiptemp = calculatetip(.20, total);
                    } else if (customtip.isChecked()) {
                        tiptemp = calculatetip(custtipperc, total);
                        tipten.toggle();
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.customtip) {
                    AlertDialog.Builder Customtipprompt = new AlertDialog.Builder(getActivity());
                    Customtipprompt.setTitle("Custom Tip (Only Accepts Integers)");
                    Customtipprompt.setCancelable(false);
                    final EditText custtipval = new EditText(getActivity());
                    custtipval.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Customtipprompt.setView(custtipval);
                    Customtipprompt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (custtipval.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Empty Custom Percent", Toast.LENGTH_SHORT).show();
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
}

