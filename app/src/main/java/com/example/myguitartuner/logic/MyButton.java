package com.example.myguitartuner.logic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.Image;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom MyButton class in charge of performing the recording every time the a button is clicked
 */
public class MyButton{
    /**
     * the button that will be passed in the constructor
     */
    private Button button;

    /**
     * Filename to be passed when creating a MyThread, and for the file containing the audio files.
     */
    private String fileName;

    /**
     * Custom thread class in charge of managing the recording sessions.
     */
    private MyThread thread;

    /**
     * MainActivity Object used to pass on to created MyThread objects
     */
    private MainActivity main;

    /**
     * static list to keep track of every active MyButton (used for the later method disableAllOtherButtons)
     */
    private static List<MyButton> list = new ArrayList<>();

    /**
     * Constructor for the MyButton class
     * @param button the button responsible for the actions to be taken.
     * @param fileName name of the file to be passed on to MyThread object
     * @param main MainActivity Object that called to create this Object (used for updating UI in MyThread)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyButton(Button button, String fileName, MainActivity main){
        this.button = button;
        this.fileName = fileName;
        this.main = main;
        if(getId() != R.id.buttonStop){
            thread = new MyThread(fileName, main, button);
        }
        list.add(this);
    }

    /**
     * What to do in case of a button press.
     * In case the button is active (MyThread is running), do nothing
     * In case the thread is not active, start a new MyThread
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void performOnClick(){
        if(getId() != R.id.buttonStop && thread != null && thread.getIsActive()){
            return;
        }
        disableAllOtherButtons();
        if(getId() != R.id.buttonStop){
            thread = new MyThread(fileName, main, button);
            thread.start();
        }
    }

    /**
     * Method to set whether a button should be enabled or not
     * @param enabled boolean variable that states what the button should do
     */
    public void setEnabled(boolean enabled) {
        getButton().setEnabled(enabled);
    }

    /**
     * Getter for the button
     * @return the current button
     */
    public Button getButton() {
        return button;
    }

    /**
     * Custom getter for the ID of the current button
     * @return the ID of the button
     */
    public int getId(){
        return getButton().getId();
    }

    /**
     * Method that disables all other Buttons before starting a new Recording, by going through the list
     * of saved MyButtons, and disabling all Buttons except the current, and the Stop button
     *
     * in case this method is called via the stop button, it will stop all recordings if active,
     * and re-enable the rest of the buttons
     */
    public void disableAllOtherButtons(){
        for(MyButton other: list){
            if(getId() == R.id.buttonStop && other.getId() != R.id.buttonStop){
                other.setEnabled(true);
                if(other.thread != null && other.thread.getIsActive()){
                    other.thread.setActive(false);

                }
            }
            else if(getButton().getId() != other.getId() && other.getId() != R.id.buttonStop){
                other.setEnabled(false);
            }
        }
    }


}
