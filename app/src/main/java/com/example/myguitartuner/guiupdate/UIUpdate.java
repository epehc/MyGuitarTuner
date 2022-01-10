package com.example.myguitartuner.guiupdate;

import android.annotation.SuppressLint;
import android.widget.Button;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.R;
import com.example.myguitartuner.data.GuitarString;

/**
 * Class responsible for performing GUI update operations
 */
public class UIUpdate implements Runnable {

    /**
     * Button used to identify which button was pressed
     */
    private final Button button;

    /**
     * Frequency used to calculate in which range it currently is
     */
    private double frequency;

    /**
     * MainActivity Object, where the GUI updates should take place.
     */
    private MainActivity main;

    /**
     * Constructor
     * @param frequency current frequency
     * @param button Button pressed
     * @param main Activity where GUI is to be updated
     */
    public UIUpdate(double frequency, Button button, MainActivity main){
        this.frequency = frequency;
        this.button = button;
        this.main = main;
    }


    @Override
    public void run() {
        changeUIElements(frequency, button);
    }

    /**
     * Method to change the UI elements. It takes as parameters the frequency and the button that is
     * currently in use. using the frequency, it will compare it to the different ranges it can be
     * (using the GuitarString Enum), to determine to which state the GUI should change.
     * @param frequency last calculated frequency
     * @param button currently pressed button
     */
    @SuppressLint("NonConstantResourceId")
    public void changeUIElements(double frequency, Button button){
        switch (button.getId()){
            case R.id.buttonE4:
                if(GuitarString.e_4.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.e4_string_1);
                    break;
                }
                else if(GuitarString.e_4.getBelowFrequency(frequency)){
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.e4_string_2);
                    break;
                }
                else if(GuitarString.e_4.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.e4_string_3);
                    break;
                }
                else if(GuitarString.e_4.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.e4_string_2);
                    break;
                }else if(GuitarString.e_4.getWayAboveFrequency(frequency)){
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.e4_string_1);
                    break;
                }

            case R.id.buttonB:
                if(GuitarString.b.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.b_string_1);
                    break;
                }
                else if(GuitarString.b.getBelowFrequency(frequency)){
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.b_string_2);
                    break;
                }
                else if(GuitarString.b.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.b_string_3);
                    break;
                }
                else if(GuitarString.b.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.b_string_2);
                    break;
                }else if(GuitarString.b.getWayAboveFrequency(frequency)){
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.b_string_1);
                    break;
                }

            case R.id.buttonG:
                if(GuitarString.g.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.g_string_1);
                    break;
                }
                else if(GuitarString.g.getBelowFrequency(frequency)){
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.g_string_2);
                    break;
                }
                else if(GuitarString.b.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.g_string_3);
                    break;
                }
                else if(GuitarString.g.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.g_string_2);
                    break;
                }else{
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.g_string_1);
                    break;
                }

            case R.id.buttonD:
                if(GuitarString.d.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.d_string_1);
                    break;
                }
                else if(GuitarString.d.getBelowFrequency(frequency)){
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.d_string_2);
                    break;
                }
                else if(GuitarString.d.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.d_string_3);
                    break;
                }
                else if(GuitarString.d.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.d_string_2);
                    break;
                }else{
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.d_string_1);
                    break;
                }

            case R.id.buttonA:
                if(GuitarString.a.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.a_string_1);
                    break;
                }
                else if(GuitarString.a.getBelowFrequency(frequency)){
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.a_string_2);
                    break;
                }
                else if(GuitarString.a.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.a_string_3);
                    break;
                }
                else if(GuitarString.a.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.a_string_2);
                    break;
                }else{
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.a_string_1);
                    break;
                }

            case R.id.buttonE2:
                if(GuitarString.e_2.getWayBelowFrequency(frequency)){
                    MainActivity.setIvProgress(0);
                    MainActivity.ivbg.setImageResource(R.drawable.e2_string_1);
                    break;
                }
                else if(GuitarString.e_2.getBelowFrequency(frequency)){
                    System.out.println();
                    System.out.println(frequency);
                    MainActivity.setIvProgress(1);
                    MainActivity.ivbg.setImageResource(R.drawable.e2_string_2);
                    break;
                }else if(GuitarString.e_2.getTargetRange(frequency)){
                    MainActivity.setIvProgress(2);
                    MainActivity.ivbg.setImageResource(R.drawable.e2_string_3);
                    break;
                }
                else if(GuitarString.e_2.getAboveFrequency(frequency)){
                    MainActivity.setIvProgress(3);
                    MainActivity.ivbg.setImageResource(R.drawable.e2_string_2);
                    break;
                }else if(GuitarString.e_2.getWayAboveFrequency(frequency)){
                    MainActivity.setIvProgress(4);
                    MainActivity.ivbg.setImageResource(R.drawable.e2_string_1);
                    break;
                }
            default:
                main.setDefaultUI();
                break;
        }
    }
}
