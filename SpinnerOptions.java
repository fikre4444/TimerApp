package Timer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.*;
import java.awt.event.*;


public class SpinnerOptions {
    int timeValues[];
    String namek = "fikre";
    JDialog f;
    public SpinnerOptions(){
       
    }

    public int[] getTimeValues(JFrame framu){
        f = new JDialog(framu, "Input Time", true);
        f.setSize(200, 100);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        JButton jb = new JButton("Create");
        JButton jn = new JButton("Cancel");
        jn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent eve){
                f.dispose();
            }
        });
        f.add(jn, BorderLayout.SOUTH);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                JButton j = (JButton)ev.getSource();
                JDialog fr = (JDialog)j.getParent().getParent().getParent().getParent();
                JPanel jpan = (JPanel)fr.getContentPane().getComponent(2);
                JSpinner js = ((JSpinner)jpan.getComponent(0));
                JSpinner js1 = ((JSpinner)jpan.getComponent(1));
                JSpinner js2 = ((JSpinner)jpan.getComponent(2));
                timeValues = new int[3];
                timeValues[0] = (Integer)js.getValue();
                timeValues[1] = (Integer)js1.getValue();
                timeValues[2] = (Integer)js2.getValue();
                System.out.println("the values are "+timeValues[0]+" "+timeValues[1]+" "+timeValues[2]);
                fr.dispose();
            }
        }); 
        f.add(jb, BorderLayout.NORTH);
        JPanel pp = new JPanel();

        CustomSpinnerModel csm = new CustomSpinnerModel(0, 0, 24);
        JSpinner js = new JSpinner(csm);
        ((DefaultEditor) js.getEditor()).getTextField().setEditable(false);

        CustomSpinnerModel csm1 = new CustomSpinnerModel(0, 0, 59);
        JSpinner js1 = new JSpinner(csm1);
        ((DefaultEditor) js1.getEditor()).getTextField().setEditable(false);

        CustomSpinnerModel csm2 = new CustomSpinnerModel(0, 0, 59);
        JSpinner js2 = new JSpinner(csm2);
        ((DefaultEditor) js2.getEditor()).getTextField().setEditable(false);

        pp.setPreferredSize(new Dimension(100, 45));
        pp.setLayout(new GridLayout(0, 3));
        pp.add(js);
        pp.add(js1);
        pp.add(js2);
        
        f.add(pp, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true); 
        return timeValues;
    }


}
