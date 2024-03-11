package Timer;

import java.util.*;
public class Timer {
    private int hours;
    private int minutes;
    private int seconds;

    public Timer(int h, int m, int s){
        hours = h;
        minutes = m;
        if(s >= 59)
            seconds = s;
        else seconds = s;
    }

    public void subtract(){
        if(!(hours == -1 && minutes == -1 && seconds == -1)){
            seconds--;
            if(seconds == -1){
                minutes--;
                seconds = 59;
            }
            if(minutes == -1){
                hours--;
                minutes = 59;
                seconds = 59;
            }
            if(hours == -1){
                minutes = -1;
                seconds = -1;
            }
        }
    }

    public int[] getValues(){
        return new int[]{hours, minutes, seconds};
    }

    public void setValues(int[] values){
        hours = values[0];
        minutes = values[1];
        seconds = values[2];
    }


    public void countdown(){
        try{
            while(!(hours == 0 && minutes == 0 && seconds == 0)){
                System.out.println("H:"+hours+" M:"+minutes+" S:"+seconds);
                Thread.sleep(1000);
                subtract();
            }
            System.out.println("H:0 M:0 S:0");
        }catch(Exception ex){
            System.out.println("An error has occured.");
        } 
    }

    public static void main(String[] args){
        Timer newt = new Timer(0, 0, 10);
        newt.countdown();
    }
    
}







// public static void main(String[] args){
//         Scanner scan = new Scanner(System.in);
//         System.out.println("Please input the number of minutes");
//         int minutes = scan.nextInt();
//         scan.close();
//         System.out.println("The seconds is "+minutes);

//         try {
//             for(int i = 1; i<=minutes; i++){
//                 for(int j = 1; j<=60; j++){
//                     Thread.sleep(1000);
//                     System.out.print(j+" ");
//                 }
//                 System.out.println("\n"+i);
//             }
//         }catch(Exception ex){
//             ex.printStackTrace();
//         }
//     }