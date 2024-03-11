package Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class TimerGui extends JFrame implements ActionListener, ComponentListener {
    int x = 0;
    JButton adder, delete;
    JPanel buttonContainer;
    TimerPanel t;
    public TimerGui(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        t = new TimerPanel();
        JScrollPane ss = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        buttonContainer = new JPanel();
        adder = new JButton("Add Timer");
        delete = new JButton("Delete Timers");
        adder.addActionListener(this);
        delete.addActionListener(this);
        buttonContainer.add(adder, BorderLayout.NORTH);
        buttonContainer.add(delete, BorderLayout.SOUTH);
        buttonContainer.setBackground(new Color(10, 10, 20));
        this.add(ss);        
        this.add(buttonContainer, BorderLayout.NORTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args){
        TimerGui tt = new TimerGui();
        //tt.setSize(500, 500);
    }

    public void actionPerformed(ActionEvent ev){
        JButton j = (JButton)ev.getSource();
        if(j == delete){
            if(j.getText().equals("Delete Timers")){
                selectTimers();
                return;
            }
            if(j.getText().equals("Delete Selected")){
                deleteTimers();
                j.setText("Delete Timers");
                return;
            }
        }
        if(j == adder){
            addTimer();
        }
    }

    public void selectTimers(){
        this.t.visibleCheckBox(true);
        delete.setText("Delete Selected");
    }

    public void deleteTimers(){
        int result = JOptionPane.showConfirmDialog(this, "Do You really want to delete the selected Timers");
        if(result == JOptionPane.OK_OPTION)
            this.t.deleteSelected();
    }

    public void addTimer(){
        SpinnerOptions so = new SpinnerOptions();
        int[] values = so.getTimeValues(this);
        if(values != null)
            t.addTimer(values[0], values[1], values[2]);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'componentResized'");
        t.revalidate();
        t.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'componentMoved'");
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'componentShown'");
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'componentHidden'");
    }
    
}



//this.addComponentListener(new ComponentAdapter() {
        //     public void componentResized(ComponentEvent e) {
        //         // This is only called when the user releases the mouse button.
        //         t.repaint();
        //     }
        // });