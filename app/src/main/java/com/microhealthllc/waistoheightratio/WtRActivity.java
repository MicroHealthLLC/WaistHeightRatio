package com.microhealthllc.waistoheightratio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.microhealthllc.waistoheightratio.Chart.ColorArcProgressBar;
import com.microhealthllc.waistoheightratio.bubbleseekbar.BubbleSeekBar;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.DecimalFormat;


public class WtRActivity extends AppCompatActivity {

    public double waist ;
    public double height;
    public double ratio;
    public boolean infotabcheck=false;
    TextView Height;
    TextView Waist;
    TextView Whtr;
    TextView Ratio;
    TextView MiniTip;
    ImageView img;
    TextView bfnote;
    TextView ratio_display;
    ColorArcProgressBar bar;
    BubbleSeekBar  waistseekbar;
    BubbleSeekBar heightseekbar;
    DecimalFormat df = new DecimalFormat("#.##");
    RadioGroup measuringRadioGroup;
    Boolean isFemale = false;

    Button b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_waist_to_height);
        Bundle extras = getIntent().getExtras();

        Height = (TextView)findViewById(R.id.display_height);
        Waist = (TextView)findViewById(R.id.display_waist);
        Whtr = (TextView) findViewById(R.id.display_whtr);

        bfnote = (TextView) findViewById(R.id.bf_note);

             isFemale =    extras.getBoolean("isfemale");
             ratio = extras.getDouble("ratio");
             waist =extras.getDouble("waistvalue");
             height = extras.getDouble("height_value");
        bar = (ColorArcProgressBar) findViewById(R.id.bar1);
        Waist.setText(""+waist);
        Height.setText(""+height);
        Whtr.setText(df.format(ratio));
        double valued = ratio *100;
        Log.i("Value",""+valued);
        bar.setCurrentValues(valued);
        bar.setcolor1(getResources().getColor(R.color.green));
        bar.setcolor2(getResources().getColor(R.color.green));
        bar.setcolor3(getResources().getColor(R.color.green));
        TipChanger(ratio,isFemale);

    }



    public void TipChanger(double ratio,boolean isFemale)
    {

        double holder=ratio;

        if(isFemale){
            if(holder<.42){ bfnote.setText("You are Underweight");    }
            if(holder>=0.42 && holder <=0.48){ bfnote.setText("You have a healthy weight");    }
            if(holder>=0.49 && holder <=0.57){ bfnote.setText("You are OverWeight");  }
            if(holder>=0.58){bfnote.setText("Obese"); }
        }
        else{
            if(holder<.43){ bfnote.setText("You are Underweight");    }
            if(holder>=0.43 && holder <0.53){ bfnote.setText("You have a healthy weight");    }
            if(holder>=0.53 && holder <0.63){ bfnote.setText("You are OverWeight");  }
            if(holder>=0.63){bfnote.setText("Obese"); }
        }


    }

}
