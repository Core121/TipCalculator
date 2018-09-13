package com.example.corey.tipcalculator;


import android.os.Bundle;
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


public class Profession extends Fragment implements OnItemSelectedListener {

    private double tip = 0;
    private double totalwtipamount = 0;
    private String outputter = "";
    private double custtipperc = 0;

    public String getOutputter() {
        return outputter;
    }

    public void setOutputter(String outputter) {
        this.outputter = outputter;
    }

    public void setTip(double temptip) {
        this.tip = temptip;
    }

    public void setTotalwtipamount(double temptotalwtip) {
        this.totalwtipamount = temptotalwtip;
    }

    public void setCusttipperc(double tempCusttipperc) {
        this.custtipperc = tempCusttipperc;
    }

    public class professional {

        private double bestservice;
        private double goodservice;
        private double betterservice;

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

        public professional(double good, double better, double best) {
            this.bestservice = best;
            this.goodservice = good;
            this.betterservice = better;
        }

        public professional() {
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


    }

    professional HairDresser = new professional(.15, .20, .30);
    professional PizzaDelivery = new professional(.10, .15, .20);
    professional Waiter = new professional(.15, .20, .23);
    professional Valet = new professional(.15, .20, .25);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        return inflater.inflate(R.layout.app_content_profession, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Tip Profession");



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
        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinnerbro);
        tipamount.setEnabled(false);
        tag.setEnabled(false);
        totalwtip.setEnabled(false);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.professions_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
                    if (tipten.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(HairDresser.getGoodservice(), total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(HairDresser.getbetterservice(), total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 1) {
                        tiptemp = calculatetip(HairDresser.getBestservice(), total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(PizzaDelivery.getGoodservice(), total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(PizzaDelivery.getbetterservice(), total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 2) {
                        tiptemp = calculatetip(PizzaDelivery.getBestservice(), total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(Waiter.getGoodservice(), total);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(Waiter.getbetterservice(), total);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 3) {
                        tiptemp = calculatetip(Waiter.getBestservice(), total);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(0, 0);
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(0, 0);
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 0) {
                        tiptemp = calculatetip(0, 0);
                    } else if (tipten.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.goodservice;
                    } else if (tipfifteen.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.betterservice;
                    } else if (tiptwenty.isChecked() && spinner.getSelectedItemPosition() == 4) {
                        tiptemp = total + Valet.bestservice;
                    } else {
                        Toast.makeText(getActivity(), "No profession selected", Toast.LENGTH_SHORT).show();
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
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
    RadioButton tipgood = (RadioButton)getView().findViewById(R.id.tip_ten);
    RadioButton tipbetter = (RadioButton)getView().findViewById(R.id.tip_fifteen);
    RadioButton tipbest = (RadioButton)getView().findViewById(R.id.tip_twenty);
        switch (position) {
            case 0:
                tipgood.setText("Good");
                tipbetter.setText("Better");
                tipbest.setText("Best");
                break;
            case 1:
                //Hair Dresser
                setallradiobuttontext(HairDresser);
                break;
            case 2:
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

    public void setallradiobuttontext(professional pro){
        RadioButton tipgood = (RadioButton)getView().findViewById(R.id.tip_ten);
        RadioButton tipbetter = (RadioButton)getView().findViewById(R.id.tip_fifteen);
        RadioButton tipbest = (RadioButton)getView().findViewById(R.id.tip_twenty);

        tipgood.setText(String.valueOf(pro.getGoodservice()));
        tipbetter.setText(String.valueOf(pro.getbetterservice()));
        tipbest.setText(String.valueOf(pro.getBestservice()));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

