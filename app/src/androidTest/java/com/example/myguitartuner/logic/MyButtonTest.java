package com.example.myguitartuner.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.core.app.ActivityScenario;

import com.example.myguitartuner.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyButtonTest {
    ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setUp() throws Exception {
        activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testAssertListSizeIsCorrect() {
        assertEquals(7, MyButton.getList().size());
    }

    @Test
    public void testTaskElementsAreNotNull() {
        for(int i = 0; i < MyButton.getList().size()-2; i++){
            assertNotNull(MyButton.getList().get(i).getTaskThread());
            assertNotNull(MyButton.getList().get(i).getFileName());
            assertNotNull(MyButton.getList().get(i).getMain());
        }

    }

    @After
    public void cleanup(){
        activityScenario.close();
    }

}
