package com.example.myguitartuner.guiupdate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import android.widget.Button;

import androidx.constraintlayout.utils.widget.MockView;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GUIElementsChangeTest {

    @Before
    public void setUp() throws Exception {
        Button button = Mockito.mock(Button.class);
        MainActivity main = Mockito.mock(MainActivity.class);
        when(button.getId()).thenReturn(R.id.buttonE4);
    }

    @Test
    public void testChangeUIElements() {

    }
}