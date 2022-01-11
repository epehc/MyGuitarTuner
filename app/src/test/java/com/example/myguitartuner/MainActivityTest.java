package com.example.myguitartuner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.os.Environment;

import androidx.test.core.app.ActivityScenario;

import com.example.myguitartuner.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

public class MainActivityTest {

    private File existentDirectory;
    private File nonExistentDirectory;

    @Before
    public void setup(){
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        nonExistentDirectory = Mockito.mock(File.class);
        Mockito.when(nonExistentDirectory.exists()).thenReturn(false);

        Mockito.mock(Environment.class);
    }


    @Test
    public void testSetIvProgress() {
        int previousProgressImage = MainActivity.ivProgress.getId();

        MainActivity.setIvProgress(2);

        assertNotEquals(previousProgressImage, MainActivity.ivProgress.getId());

    }
}
