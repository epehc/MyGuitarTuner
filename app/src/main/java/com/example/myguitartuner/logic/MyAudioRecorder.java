package com.example.myguitartuner.logic;

import static com.example.myguitartuner.MainActivity.APP_DIRECTORY;

import android.annotation.SuppressLint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class in charge of recording the audio
 */
public class MyAudioRecorder {
    /**
     * Source for the audio input
     */
    private static final int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;

    /**
     * Recorder sample rate
     */
    private static final int RECORDER_SAMPLERATE = 8000;

    /**
     * Audio Format (Mono)
     */
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;

    /**
     * Audio Encoding (PCM 16 bit)
     */
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    /**
     * Amount of Buffer Elements
     */
    private static final int BUFFER_ELEMENTS_TO_PLAY = 1024;

    /**
     * Bytes per element
     */
    private static final int BYTES_PER_ELEMENT = 2;

    /**
     * Buffer size
     */
    private static final int BUFFER_SIZE_IN_BYTES = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
            RECORDER_CHANNELS,
            RECORDER_AUDIO_ENCODING);

    /**
     * Thread object to have the recording be done on a parallel thread.
     */
    private Thread recordingThread;

    /**
     * Boolean for starting/stopping the recording
     */
    private boolean isRecording = false;

    /**
     * AudioRecord Object
     */
    private AudioRecord ar;

    /**
     * File where the recorded PCM data will be stored.
     */
    private File file;

    /**
     * Array of data read from the recorded PCM file
     */
    private byte[] data;

    /**
     * Data from the PCM file converted into double.
     */
    private double[] pcmData;

    /**
     * Constructor for a MyAudioRecorder object.
     * It creates/overrides the existing file for the guitar string, sets the byte array data to
     * the buffer size in bytes, and sets up the recorder for the first time.
     * @param fileName, the filename under which the file will be saved to.
     */
    public MyAudioRecorder(String fileName){
        this.file = new File(APP_DIRECTORY.getPath() +"/"+  fileName + ".pcm");

        data = new byte[BUFFER_SIZE_IN_BYTES];

        setUpRecorder();

    }

    /**
     * Getter for the boolean isRecording.
     * @return true if isRecording, false if not.
     */
    public boolean isRecording() {
        return isRecording;
    }

    /**
     * Getter for the double pcmData array. Used to perform an fft
     * @return the double array
     */
    public double[] getPcmData() {
        return pcmData;
    }

    /**
     * Method to start recording the new audio file using a thread.
     * The thread itself opens a new FileOutputStream, so that the AudioRecord object can write
     * data into it, as long as the isRecording attribute is true.
     */
    public void startRecording(){
        setUpRecorder();
        ar.startRecording();
        isRecording = true;
        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream os = null;
                try{
                    os = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(isRecording){
                    ar.read(data, 0, data.length);
                    try{
                        os.write(data, 0, BUFFER_SIZE_IN_BYTES);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        recordingThread.start();
    }

    /**
     * Method to stop a recording. It stops the AudioRecord Object from recording, sets it to null,
     * and then proceeds to read the PCM data into a double array, to be able to use it for an fft.
     */
    public void stopRecording(){
        if(ar != null){
            isRecording = false;
            ar.stop();
            ar.release();
            ar = null;
            recordingThread = null;
            pcmData = readPCM();
        }
    }

    /**
     * Method used to set up the AudioRecord object every time it is needed.
     */
    @SuppressLint("MissingPermission")
    public void setUpRecorder(){
        ar = new AudioRecord(AUDIO_SOURCE,
                RECORDER_SAMPLERATE,
                RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING, BUFFER_ELEMENTS_TO_PLAY * BYTES_PER_ELEMENT);
    }

    /**
     * Method to turn the data from the recorded PCM file into a double array.
     * Source: https://stackoverflow.com/questions/25097651/reading-pcm-file-to-double-array
     * @return a double array with the information of the recording.
     */
    public double[] readPCM() {

        double[] result = null;

        try {
            InputStream in = new FileInputStream(file);
            int bufferSize = (int) (file.length()/2);

            result = new double[bufferSize];

            DataInputStream is = new DataInputStream(in);

            for (int i = 0; i < bufferSize; i++) {
                result[i] = is.readShort() / 32768.0;
            }

        } catch (FileNotFoundException e) {
            Log.i("File not found", "" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
