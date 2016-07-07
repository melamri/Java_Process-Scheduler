import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

public class sort{
	public sort(){
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