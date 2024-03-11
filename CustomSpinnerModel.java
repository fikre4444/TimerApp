package Timer;

import javax.swing.*;
import javax.swing.event.*;

public class CustomSpinnerModel extends AbstractSpinnerModel {

    // The current value of the spinner
    private int value;

    // The minimum and maximum values of the spinner
    private int min;
    private int max;

    // The constructor that takes the initial, minimum, and maximum values
    public CustomSpinnerModel(int value, int min, int max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    // The method that returns the current value of the spinner
    @Override
    public Object getValue() {
        return value;
    }

    // The method that sets the current value of the spinner
    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            int newValue = (Integer) value;
            if (newValue >= min && newValue <= max) {
                this.value = newValue;
                fireStateChanged(); // notify the listeners that the value has changed
            }
        }
    }

    // The method that returns the next value of the spinner
    @Override
    public Object getNextValue() {
        if (value == max) {
            return min; // loop from max to min
        } else {
            return value + 1; // increment by 1
        }
    }

    // The method that returns the previous value of the spinner
    @Override
    public Object getPreviousValue() {
        if (value == min) {
            return max; // loop from min to max
        } else {
            return value - 1; // decrement by 1
        }
    }
}
