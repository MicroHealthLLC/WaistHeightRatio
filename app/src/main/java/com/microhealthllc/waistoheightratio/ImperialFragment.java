package com.microhealthllc.waistoheightratio;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImperialFragment extends Fragment {


    boolean isfemale = false;
    boolean units= false;
    EditText heightft_text;
    EditText waistft_text;
    EditText height_inch_text;
    EditText waist_inch_text;
    Button calc;
    TextView error;
    double waist_value;
    double height_value;
    double waist_inches_value;
    double height_inches_value;
    public ImperialFragment() {
        // Required empty public constructor
    }
    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            isfemale = bundle.getBoolean("isfemale");
            units = bundle.getBoolean("units");
        }
    }
    public static ImperialFragment newInstance(boolean isfemale, boolean units) {

        Bundle bundle = new Bundle();
        bundle.putBoolean("isfemale", isfemale);
        bundle.putBoolean("units", units);

        ImperialFragment fragment = new ImperialFragment();

        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        waistft_text = (EditText)getActivity().findViewById(R.id.waist_feets);
        error = (TextView) getActivity().findViewById(R.id.error_check);
        heightft_text = (EditText)getActivity().findViewById(R.id.height_feets);
        height_inch_text = (EditText)getActivity().findViewById(R.id.height_inches);
        waist_inch_text = (EditText)getActivity().findViewById(R.id.waist_inches);
        calc = (Button) getActivity().findViewById(R.id.calc);

        validateInput(isInputValid());
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (waist_inch_text.getText().toString().isEmpty()){
                    waist_inches_value =0;

                }
                else{
                    waist_inches_value = Double.parseDouble(waist_inch_text.getText().toString());

                }


                if(height_inch_text.getText().toString().isEmpty()){
                    height_inches_value=0;
                }
                else {
                    height_inches_value =  Double.parseDouble(height_inch_text.getText().toString());
                }
                waist_value = (12* Double.parseDouble(waistft_text.getText().toString() )) +waist_inches_value;
                height_value = (12 *Double.parseDouble(heightft_text.getText().toString())) +height_inches_value;

                if (height_value > 260 || height_value <= 0 ||waist_value >1000 || waist_value<=0) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please check proportions");
                } else {

                    error.setVisibility(View.GONE);
                    error.setText("");
                    double ratio = ratio(waist_value, height_value);
                    Intent wtr = new Intent(getActivity(), WtRActivity.class);
                    wtr.putExtra("ratio", ratio);
                    wtr.putExtra("waistvalue", waist_value);
                    wtr.putExtra("height_value", height_value);
                    wtr.putExtra("isfemale", isfemale);
                    startActivity(wtr);
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.imperial, container, false);
        readBundle(getArguments());
        return v;
    }

    public double ratio(double waist_value , double height_value){
        return (waist_value/height_value);
    }
    public void validateInput( boolean validate) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isInputValid()) {
                    calc.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    calc.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isInputValid()) {
                    calc.setVisibility(View.VISIBLE);
                }
            }
        };
        waistft_text.addTextChangedListener(watcher);
        heightft_text.addTextChangedListener(watcher);
    }
    public boolean isInputValid(){
        return (waistft_text.getText().length() >0 && heightft_text.getText().length()>0);
    }
}
