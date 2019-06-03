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
  // AU: field variable can be converted to local RadioGroup measuringRadioGroup;
  private boolean isfemale = false;
  private boolean metric_units= false;
  //private RadioGroup genderadio;
   Fragment fragment;
    @Override
    //AU: redundant field variables have been converted to locals as they are not accessed by
    // any subclasses or multiple methods
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_calc);
       RadioGroup measuringRadioGroup = (RadioGroup)findViewById(R.id.units_radio) ;
       RadioGroup genderadio =(RadioGroup)findViewById(R.id.gender);
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.replace, new ImperialFragment());
        // AU: This back stacks and commits at once
        transaction.addToBackStack(null).commit();

//       This can be commited after it has been backstacked.
        //transaction.commit();
        //AU: This statement simplifies the gender test
genderadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

         if(checkedId!=R.id.female){
            isfemale=false;

        }

            isfemale = true;

    }
});
       measuringRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.metrics_radio){
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
                transaction.addToBackStack(null).commit();
                        // AU: transaction already commited above
                //transaction.commit();
            }
        });

    }

}
