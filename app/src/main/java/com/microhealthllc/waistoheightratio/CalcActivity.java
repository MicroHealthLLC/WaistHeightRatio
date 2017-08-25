package com.microhealthllc.waistoheightratio;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CalcActivity extends FragmentActivity {
    RadioGroup measuringRadioGroup;
    boolean isfemale = false;
    boolean metric_units= false;
    RadioGroup genderadio;
Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        measuringRadioGroup = (RadioGroup) findViewById(R.id.units_radio) ;
        genderadio =(RadioGroup) findViewById(R.id.gender);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.replace, new ImperialFragment());
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
genderadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(checkedId== R.id.female){
            isfemale=true;

        }
        else {
            isfemale = false;
        }
    }
});
       measuringRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId ==R.id.metrics_radio){
                    metric_units = true;
                    fragment = new MetricFragment().newInstance(isfemale,metric_units);

                }
                else{
                    metric_units =false;
                    fragment = new ImperialFragment().newInstance(isfemale,metric_units);
                }

                if(getSupportFragmentManager().findFragmentById(R.id.replace) != null) {
                    getSupportFragmentManager()
                            .beginTransaction().
                            remove(getSupportFragmentManager().findFragmentById(R.id.replace)).commit();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.replace, fragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();
            }
        });

    }

}
