package Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerComp extends JPanel implements ActionListener {
    private JLabel timerValues; //this displays the The timer values
    private int[] originalValues; //this is used to reset to the original values
    private int h, m, s; // used for temporary storage of time
    private JButton play; //used for starting or continuing the timer
    private JButton reset; //used for reseting the timer
    private Timer t; // this is the object that is the timer
    private JPanel upperPanel; // used to hold the upper parts like (timervalues, checkbox, closebutton)
    private JPanel lowerPanel; // used to hold the two buttons (play/pause and reset)
    private Runnable r; //this is used to start the timer countdown on a new thread
    private Thread t1; // the actual thread that runs the countdown
    private boolean counting; // to check whether the timer is counting
    private JCheckBox jcx; // used for deleting different timers
    private JButton x; // used to close the timer
    

    public TimerComp(int hours, int minutes, int seconds){
        Color special = new Color(180, 160, 120);
        //creating the basic structure of the interface
        upperPanel = new JPanel();
        upperPanel.setBackground(special);
        lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(special);
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, upperPanel);
        this.add(BorderLayout.SOUTH, lowerPanel);

        //instantiating the visible component fields
        jcx = new JCheckBox(); // the checkbox used to delete this timercomponent
        x = new JButton("X"); // the button used to close or delete this timerComponent
        timerValues = new JLabel(""+hours+":"+minutes+":"+seconds); // the label used to display the time
        play = new JButton("play"); // the button used to start/pause the countdown
        reset = new JButton("reset"); // the button used to reset the timer

        //instantiating the invisible but useful fields
        counting =  false; // used for the thread to continue its counting or not
        originalValues = new int[]{hours, minutes, seconds}; // used to reset the timer to the original values
        h = hours; m = minutes; s = seconds; // used to hold the temporary time to update the countdown
        t = new Timer(hours, minutes, seconds); //actual timer object


        //doing some editing to the visible components
        jcx.setVisible(false); // so that the checkbox is initially invisible
        play.addActionListener(this); // choose a listener for this button 
        x.addActionListener(this); // choose a listener for this button which is this object itself
        //creating an action for the reset button(resets the current and label and timer to original values and pauses the timer)
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                t.setValues(originalValues); // sets the timer object to original values
                h = originalValues[0]; 
                m = originalValues[1];
                s = originalValues[2];
                timerValues.setText(""+h+":"+m+":"+s); // sets the label
                if(t1 != null) // if the countdown is happening then interrupt the thread and stop it
                    t1.interrupt();
                play.setText("play"); //change the button name to play
                
            }
        });

        //adding the visible objects to the pannels
        upperPanel.add(jcx); //adds the checkbox
        upperPanel.add(timerValues); //adds the timerVales
        upperPanel.add(x); //adds the close button
        lowerPanel.add(play); //adds the play/pause button
        lowerPanel.add(reset); //add the reset button
        //this.repaint(); //repaints the the panel to make the added components visible
    }
   
    public void setLabel(int h, int m, int s){ //used to set the label timer for upadating
        timerValues.setText(""+h+":"+m+":"+s);
    }

    public void countD(){ //used for counting down
        r = () -> {
            try{ // this try catch is used because we use a thread delay for counting
            while(!(h==0 && m==0 && s==0) && counting){ //while we are counting and the timer isn't zero
                Thread.sleep(1000); //wait 1 second
                this.t.subtract(); //subract 1 second from the timer object
                int[] current = this.t.getValues(); //after subtracting get the values from that object
                h = current[0]; // set these variables the values
                m = current[1];
                s = current[2];
                this.setLabel(h, m, s); //set the labels
                this.repaint(); //update the panel
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        };
        t1 = new Thread(r); //using that above runnable object create a thread
        t1.start(); //start the counting down thread
    }


    public void actionPerformed(ActionEvent ev){ //used for the various buttons
        JButton j = (JButton)ev.getSource(); //get the button that was pressed
        if(j == x){ // if the button is the close one 
            TimerPanel tp = (TimerPanel)this.getParent(); //get the panel that is holding this component
            jcx.setSelected(true); // make it selected to be deleted
            tp.deleteSelected(); // call the parent's method to delete its selected TimerComps
            return; // go out of this method
        }
        if(j.getText().equals("play")){ // if the pressed button is the play button and its text is "Play"
            counting = true; //make the counting true because it will be counting
            this.countD(); //call the count method
            play.setText("pause");  //change the button's label
        }
        else { //if the button's name is the "pause" 
            play.setText("play"); //change the name to play
            counting = false; // stop the counting
            t1.interrupt(); // and interrupt the thread that is counting
            }       
    }

    public boolean checkSelection(){ //this is used by the parent container if a certain timer is selected to be deleted
        return jcx.isSelected();
    }

    public void setCheckBoxVisiblity(boolean vis){ // used to either make the checkbox visible or invisible
        jcx.setVisible(vis);
    }

    public void clickReset(){
        //this is used by the containing parent when the timer is being deleted to stop the thread associated with it
        reset.doClick();
    }
    
}
