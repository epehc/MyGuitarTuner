package com.example.myguitartuner.logic;

import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Task class in charge of performing the recording every time the a button is clicked
 */
public class Task {
    /**
     * the button that will be passed in the constructor
     */
    private Button button;

    /**
     * Filename to be passed when creating a TaskThread, and for the file containing the audio files.
     */
    private String fileName;

    /**
     * Custom thread class in charge of managing the recording sessions and performing updates.
     */
    private TaskThread taskThread;

    /**
     * MainActivity Object used to pass on to created TaskThread objects
     */
    private MainActivity main;

    /**
     * static list to keep track of every active Task (used for the later method disableAllOtherButtons)
     */
    private static List<Task> list = new ArrayList<>();

    /**
     * Constructor for the Task class
     * @param button the button responsible for the actions to be taken.
     * @param fileName name of the file to be passed on to TaskThread object
     * @param main MainActivity Object that called to create this Object (used for updating UI in TaskThread)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task(Button button, String fileName, MainActivity main){
        this.button = button;
        this.fileName = fileName;
        this.main = main;
        if(getId() != R.id.buttonStop){
            taskThread = new TaskThread(button, fileName, main);
        }
        list.add(this);
        System.out.println(list.size());
    }

    /**
     * What to do in case of a button press.
     * In case the button is active (TaskThread is running), do nothing
     * In case the thread is not active, start a new TaskThread
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void performOnClick(){
        if(getId() != R.id.buttonStop && taskThread != null && taskThread.getIsActive()){
            return;
        }
        disableAllOtherButtons();
        if(getId() != R.id.buttonStop){
            taskThread = new TaskThread(button, fileName, main);
            taskThread.start();
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
     * Getter for the List
     * @return
     */
    public static List<Task> getList() {
        return list;
    }

    /**
     * Getter for the TaskThread
     * @return
     */
    public TaskThread getTaskThread() {
        return taskThread;
    }

    /**
     * Getter for fileName
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Getter for the MainActivity object
     * @return
     */
    public MainActivity getMain() {
        return main;
    }

    /**
     * Method that disables all other Buttons before starting a new Recording, by going through the list
     * of saved MyButtons, and disabling all Buttons except the current, and the Stop button
     *
     * in case this method is called via the stop button, it will stop all recordings if active,
     * and re-enable the rest of the buttons
     */
    public void disableAllOtherButtons(){
        for(Task other: list){
            if(getId() == R.id.buttonStop && other.getId() != R.id.buttonStop){
                other.setEnabled(true);
                if(other.taskThread != null && other.taskThread.getIsActive()){
                    other.taskThread.setActive(false);

                }
            }
            else if(getButton().getId() != other.getId() && other.getId() != R.id.buttonStop){
                other.setEnabled(false);
            }
        }
    }


}
