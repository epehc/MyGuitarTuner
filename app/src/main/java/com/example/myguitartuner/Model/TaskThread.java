package com.example.myguitartuner.Model;

import static com.example.myguitartuner.Model.FastFourierTransform.fft;

import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.myguitartuner.MainActivity;

/**
 * Class that is in charge of recording and restarting the recording every so often, to make sure there are audio files that can be used to analyze the audio
 */
public class TaskThread extends Thread{

    /**
     * Button object used for calling the update UI method of the MainActivity.
     * Used for identification purposes.
     */
    private final Button button;

    /**
     * RecordAudio Object, in charge of doing the actual recording of the audio
     */
    private RecordAudio audioRecorder;

    /**
     * Boolean in charge of controlling if the thread should be running or not
     */
    private boolean isActive;

    /**
     * MainActivity object used to call for UI updates (Passed on from Constructor, to ensure
     * the caller MainActivity is used)
     */
    private MainActivity main;

    /**
     * FastFourierTransform Object, in charge of calculating and transforming the audio from Time Domain, to Frequency Domain.
     */
    private FastFourierTransform fastFourierTransform;

    /**
     * The highest frequency calculated after performing an FFT
     */
    private double highestFrequency;

    /**
     * Contstructor for a TaskThread object
     * @param fileName the filename to be given to each recording.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TaskThread(Button button, String fileName, MainActivity main){
        this.audioRecorder = new RecordAudio(fileName);
        this.main = main;
        this.button = button;

    }

    public double getHighestFrequency() {
        return highestFrequency;
    }

    public void setHighestFrequency(double highestFrequency) {
        this.highestFrequency = highestFrequency;
    }

    /**
     * getter for the isActive boolean variable
     * @return
     */
    public boolean getIsActive(){
        return isActive;
    }

    /**
     * setter for the isActive boolean variable, used outside of this class to stop the thread from running
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Run method.
     * It will start a recording while isActive is true, and then sleep for 5 seconds (the amount of time the recording lasts)
     * before starting a new recording and overwriting the current audio file for the specified guitar string.
     * After isActive is set to False, it will check if the recording is still active, and stop it.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        isActive = true;
        while(isActive){
            callRunOnUiMethod(button);
            audioRecorder.startRecording();
            try{
                sleep(1024);
                audioRecorder.stopRecording();
                fastFourierTransform = new FastFourierTransform(audioRecorder.getPcmData());
                fft();
                setHighestFrequency(fastFourierTransform.getHighestFrequency());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        main.setDefaultUI();
    }

    /**
     * Method to call for an UI update on the main Activity
     * @param button to use for identification
     */
    public void callRunOnUiMethod(Button button){
        main.runOnUi(getHighestFrequency(), button);
    }
}
