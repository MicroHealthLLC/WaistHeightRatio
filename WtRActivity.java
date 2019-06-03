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


public class  WtRActivity extends AppCompatActivity {
// AU: field variables that have never been used have been commented taken out
    public double waist ;
    public double height;
    public double ratio;
    //public boolean infotabcheck=false;
    TextView Height;
    TextView Waist;
    TextView Whtr;
    //TextView Ratio;
    //TextView MiniTip;
    //ImageView img;
    TextView bfnote;
    //extView ratio_display;
    ColorArcProgressBar bar;
    //BubbleSeekBar  waistseekbar;
    //BubbleSeekBar heightseekbar;
    DecimalFormat df = new DecimalFormat("#.##");
    // RadioGroup measuringRadioGroup;
    Boolean isFemale;

    Button b ;

    public WtRActivity() {
        isFemale = false;
    }

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
        bar.setcolor1(getResources().getColor(R.color.blue));
        bar.setcolor2(getResources().getColor(R.color.blue));
        bar.setcolor3(getResources().getColor(R.color.blue));
        TipChanger(ratio,isFemale);

    }


// AU: better user advice and texts given!
    public void TipChanger(double ratio,boolean isFemale){
        // AU: Redundant variable, ratio has already been declared!

              //double holder = ratio;
        if(isFemale){
            if(ratio<.42){ bfnote.setText("You`re Underweight! Consider healthy weight gaining options!");    }
            if(ratio>=0.42 && ratio<=0.48){ bfnote.setText("Congrats! Perfect and healthy weight!");    }
            if(ratio>=0.49 && ratio<=0.57){ bfnote.setText("You`re Overweight! Consider healthy weight loss options!");  }
            if(ratio>=0.58){bfnote.setText("Obese!"); }
        }
        else{
            if(ratio<.43){ bfnote.setText("You`re Underweight! Consider healthy weight gaining options!");    }
            if(ratio>=0.43 && ratio<0.53){ bfnote.setText("Congrats! Perfect and healthy weight!");    }
            if(ratio>=0.53 && ratio<0.63){ bfnote.setText("You`re Overweight! Consider healthy weight loss options!");  }
            if(ratio >=0.63){bfnote.setText("Obese!"); }
        }


    }

}
