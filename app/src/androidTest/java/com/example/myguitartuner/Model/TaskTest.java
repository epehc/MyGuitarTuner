package com.example.myguitartuner.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.core.app.ActivityScenario;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.Model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskTest {
    ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setUp() throws Exception {
        activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testAssertListSizeIsCorrect() {
        assertEquals(7, Task.getList().size());
    }

    @Test
    public void testTaskElementsAreNotNull() {
        for(int i = 0; i < Task.getList().size()-2; i++){
            assertNotNull(Task.getList().get(i).getTaskThread());
            assertNotNull(Task.getList().get(i).getFileName());
            assertNotNull(Task.getList().get(i).getMain());
        }

    }

    @After
    public void cleanup(){
        activityScenario.close();
    }

}
