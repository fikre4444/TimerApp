package Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerPanel extends JPanel {
    

    public TimerPanel(){
        this.setLayout(new WrapLayout(FlowLayout.LEFT));
        this.setBackground(new Color(20, 20, 40));
        TimerComp tt = new TimerComp(3, 2, 1);
        this.add(tt);
        
    }

    public void visibleCheckBox(boolean ok){
        int numOfTimers = this.getComponentCount();
        for(int i = 0; i<numOfTimers; i++){
            TimerComp t = (TimerComp)this.getComponent(i);
            t.setCheckBoxVisiblity(ok);
        }
    }

    public void deleteSelected(){
        Component[] comps = this.getComponents();
        this.removeAll();
        for(int i = 0; i<comps.length; i++){
            TimerComp tc = (TimerComp)comps[i];
            if(!tc.checkSelection()){
                this.add(tc);
                tc.setCheckBoxVisiblity(false);
            }else {
                tc.clickReset(); // when timers are removed from the panel we need to stop the counting and the thread
                
            }
        }
        this.revalidate();
        this.repaint();
        
    }

    public void addTimer(int h, int m, int s){
        this.add(new TimerComp(h, m, s));
        this.revalidate();
        this.repaint();
    }
   
}