package com.microhealthllc.waistoheightratio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link MetricFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MetricFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    boolean isfemale = false;
    boolean units= false;
    EditText height_text;
    EditText waist_text;
    Button calc;
    TextView error;
    double waist_value;
    double height_value;
    public MetricFragment() {
        // Required empty public constructor
    }
    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            isfemale = bundle.getBoolean("isfemale");
            units = bundle.getBoolean("units");
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MetricFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MetricFragment newInstance(boolean isfemale, boolean units) {

        Bundle bundle = new Bundle();
        bundle.putBoolean("isfemale", isfemale);
        bundle.putBoolean("units", units);







        MetricFragment fragment = new MetricFragment();
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        waist_text = (EditText)getActivity().findViewById(R.id.waist_cm);
        error = (TextView) getActivity().findViewById(R.id.error_check);
        height_text = (EditText)getActivity().findViewById(R.id.height_cm);
        calc = (Button) getActivity().findViewById(R.id.calc);

         validateInput(isInputValid());
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                waist_value = Double.parseDouble(waist_text.getText().toString());
                height_value = Double.parseDouble(height_text.getText().toString());

                if (height_value > 260 || height_value <= 0 ||waist_value >1000 || waist_value<=0) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please check proportions");
                } else {
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
        View v = inflater.inflate(R.layout.metric_fragment, container, false);
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
        waist_text.addTextChangedListener(watcher);
        height_text.addTextChangedListener(watcher);
    }
    public boolean isInputValid(){
        return (waist_text.getText().length() >0 && height_text.getText().length()>0);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
