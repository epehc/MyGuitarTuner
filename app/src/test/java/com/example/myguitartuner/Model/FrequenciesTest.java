package com.example.myguitartuner.Model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class FrequenciesTest {


    @Test
    public void testStringFrequencyMethodsBehaveCorrectly() {

        TaskThread thread = Mockito.mock(TaskThread.class);

        //The exact target frequency for the string e4
        Mockito.when(thread.getHighestFrequency()).thenReturn(329.63);

        /**
         * Make sure that all ranges behave as expected
         */
        assertTrue(StringFrequency.e_4.getTargetRange(thread.getHighestFrequency()));

        assertFalse(StringFrequency.e_4.getWayBelowFrequency(thread.getHighestFrequency()));
        assertFalse(StringFrequency.e_4.getBelowFrequency(thread.getHighestFrequency()));
        assertFalse(StringFrequency.e_4.getAboveFrequency(thread.getHighestFrequency()));
        assertFalse(StringFrequency.e_4.getWayAboveFrequency(thread.getHighestFrequency()));

        /**
         * Test with other strings
         */
        assertFalse(StringFrequency.e_2.getTargetRange(thread.getHighestFrequency()));
        assertTrue(StringFrequency.e_2.getWayAboveFrequency(thread.getHighestFrequency()));
        assertTrue(StringFrequency.b.getAboveFrequency(thread.getHighestFrequency()));
        assertTrue(StringFrequency.g.getWayAboveFrequency(thread.getHighestFrequency()));
        assertFalse(StringFrequency.d.getTargetRange(thread.getHighestFrequency()));
    }

}