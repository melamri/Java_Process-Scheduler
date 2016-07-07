import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;

public class fcfs{
	fcfs(){
		//read from file instead
		//replace all below with a sort();
		ArrayList<pcb> myList = new ArrayList<pcb>();
		class arrivalComparator implements Comparator<pcb>{ 
			public int compare(pcb p1, pcb p2){
				int result=p1.arrivalTime()-p2.arrivalTime();
				if (result==0){
					return p1.getId()-p2.getId();
				}
				else{
					return result;
				}
			}
		}
		Collections.sort(myList, new arrivalComparator());
	}
}
