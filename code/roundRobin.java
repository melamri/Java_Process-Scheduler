import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

public class roundRobin{
	public roundRobin(){
		int timeQuantum, dispatch;
		int totalCycles = 0;
		ArrayList<pcb> myList = new ArrayList<pcb>();
		//replace this with read from file
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		int n = reader.nextInt();
		System.out.println("Enter time quantum value: ");
		timeQuantum = reader.nextInt();
		System.out.println("Enter dispatch penalty: ");
		dispatch = reader.nextInt();
		
		for(int i=0; i<n; i++)
			myList.add(new pcb());
		
		for(pcb pcb21: myList)
			totalCycles += pcb21.getCycles();
		
		int time2 = 0;
		int over2 = 0;
		int totalCycles2 = 0;
		for(pcb pcb21: myList)
			totalCycles2 += pcb21.getCycles();
		int time = myList.get(0).arrivalTime();
		int over = 0;
		int check = 0;
		while(totalCycles > 0)
		{
			for(int i = 0; i < myList.size(); i++){         
				if(myList.get(i).getCycles()>timeQuantum && myList.get(i).arrivalTime()-time < 1){ //if cycle time is greater than the time quantum, move the process to the wait queue, subtract the quantum from cycles remaining
					if(myList.get(i).arrivalTime()-time == 0 && over < myList.size() && over != 0){
						time = myList.get(i).arrivalTime()+1;
					}
		  
				int penalty = 0;
				while(penalty != dispatch){
					System.out.println("T" + time + " Dispatch Penalty");
					time++;
					totalCycles++;
					penalty++;
				}
		  
				  //Round Robin Quantum with I/O
				  int quantumMax = 0;
				  System.out.println(" P ID: " + myList.get(i).getId() + " is now active.");
				  while(quantumMax != timeQuantum)
				  {
				    myList.get(i).setCurrentCycle();
				    if(myList.get(i).getCurrentCycle() == myList.get(i).getIO())
				    {
				      System.out.println("T" + time + " I/O...");
				      totalCycles++;
				      myList.get(i).setCycles(myList.get(i).getCycles()-1);
				    }
				    else
				    {
				      System.out.println("T" + time + " P ID: " + myList.get(i).getId() + " is running.");
				    }
				    time++;
				    quantumMax++;
				  }
				  
				  System.out.println(" Max Quantum " + timeQuantum + " reached");
				  totalCycles -= timeQuantum;
				  myList.get(i).setCycles(myList.get(i).getCycles()-timeQuantum);
				  pcb tail = myList.get(i);
				  myList.remove(myList.get(i));
				  myList.add(myList.size(),tail);
				}
		
		else if(myList.get(i).getCycles()<=timeQuantum && myList.get(i).arrivalTime()-time < 1)
		{
		  //Dispatcher
		  int penalty = 0;
		  while(penalty != dispatch)
		  {
		    System.out.println("T" + time + " Dispatch Penalty");
		    time++;
		    totalCycles++;
		    penalty++;
		  }
		  
		  int cyclesLeft = 0;
		  while(cyclesLeft != myList.get(i).getCycles())
		  {
		    System.out.println("T" + time + " P ID: " + myList.get(i).getId() + " is running.");
		    time += 1;
		    cyclesLeft++;
		  }
		  System.out.println(" P ID: " + myList.get(i).getId() + " is complete");
		  myList.remove(myList.get(i));
		  over++;
		}  
	      } 
	    }
    }
}
    
    