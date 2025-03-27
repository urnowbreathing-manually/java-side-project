import javax.swing.*;
import java.awt.*;

public class instances {
 
    int count = 3;

    public int alterCount(){
        instances alterCountInst = new instances();
    	int countModified = 0;

    	countModified = alterCountInst.count;
    	countModified *= 2;


    	return countModified;
    }

    public static void main(String args[]) {
    	instances countMainInst = new instances();
        int countMain = countMainInst.alterCount();

        System.out.println(countMain);
    }



}