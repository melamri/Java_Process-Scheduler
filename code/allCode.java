import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.regex.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


    public static void main(String[] args) {
        ArrayList<pcb> myList = new ArrayList<pcb>(); //arraylist of type pcb definition
		String fileName = "data.txt";
		String input = "";
		int numSpace = 0;
		int numLine = 0;
		try{ 
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			StringBuffer inputBuffer = new StringBuffer();
			try{
				while(null!= (line=reader.readLine())){
					inputBuffer.append(line);
					inputBuffer.append("\n");
					numLine += 1;
				}
			}
			catch(IOException e) {System.out.println("Error. Cannot read.");}
			input = inputBuffer.toString();
			try{reader.close();}
			catch(IOException e) {System.out.println("Error. Cannot close.");}
		}
		catch(FileNotFoundException e) {System.out.println("Error. File not found.");}
		System.out.println(numLine);
		String lineDelim = "[\n]+";
		String[] processes = input.split(lineDelim);
		for (int procNum=0; procNum<numLine; procNum++){
			String process = processes[procNum];
			System.out.println(process);
			String procDelim = "[ ]+";
			String[] tokens = process.split(procDelim);
			int Arrival = 0;
			int Cycle = 0;
			int Io = 0;
			for (int token=0; token<=2; token++){
				//System.out.println(tokens[token]);
				Arrival = Integer.parseInt(tokens[0]);
				Cycle = Integer.parseInt(tokens[1]);
				Io = Integer.parseInt(tokens[2]);
			}
			myList.add(new pcb(Arrival, Cycle, Io));
		}
		int count = 1;
		for(pcb p : myList){
			p.setId(count);
			String IOmessage = "";
			if (p.getIO() == -1){IOmessage = " and has no IO event";}
			else {IOmessage = " and has an IO event at cycle " + p.getIO();}
			System.out.println("P ID: " + p.getId() + " arrives at T=" + p.getArrival() + " and requires " + p.getCycles() + " cycles" + IOmessage);
			count++;
		}
	}
}
class generateData{

	public static void main(String[] args) {
		Random rand=new Random();
		int processId, processArrival, processCycles, hasIO, IOcycle; 
		int numProc = rand.nextInt(11) + 5;
		try {
			File file = new File("data.txt");
			if (!file.exists()) {file.createNewFile();}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i=0; i<=numProc; i++){
				processArrival=rand.nextInt(20)+1;
				processCycles=rand.nextInt(10)+1;
				if (processCycles == 1) {hasIO = 0;}
				else {hasIO = rand.nextInt(2);}
				if (hasIO == 1){IOcycle = rand.nextInt(processCycles)+1;}
				else {IOcycle = -1;}
				String content = "";
				if (i==numProc){content = processArrival + " " + processCycles + " " + IOcycle;}
				else {content = processArrival + " " + processCycles + " " + IOcycle + "\n";}
				bw.write(content);
			}
			bw.close();
 
			System.out.println("Done");
		}
		catch(IOException e) {e.printStackTrace();}
		
		
	}
}



class pcb2{
  public static void main(String[] args) {
    
    ArrayList<pcb2> myList = new ArrayList<pcb2>();
    
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter number of processes: "); //numProcess defined by user for now
    int n = reader.nextInt();
    
    for(int i=0; i<n; i++){
      myList.add(new pcb2());
    }
    
    System.out.println("Processes List:");
    for(pcb2 pcb21: myList)
    {
      //if (pcb21.getIO() == -1){IOmessage = " and has no IO event";}
      //else {IOmessage = " and has an IO event at cycle " + pcb21.getIO();}
      System.out.println("P ID: " + pcb21.getId() + " arrives at T=" + pcb21.arrivalTime() + " and requires " + pcb21.getCycles() + " cycles");
    }
    System.out.println();
    
    int totalCycles = 0;
    for(pcb2 pcb21: myList)
    {
      totalCycles += pcb21.getCycles();
    }
    
    int time2 = 0;
    int over2 = 0;
    int totalCycles2 = 0;
    for(pcb2 pcb21: myList)
    {
      totalCycles2 += pcb21.getCycles();
    }
   

    
    System.out.println("FCFS");
    while(totalCycles2 > 0)
    {
      for(int i = 0; i < myList.size(); i++)
      {
        if(i == 0)
        {
          time2 = myList.get(i).arrivalTime();
        }
        if(myList.get(i).getCycles()>timeQuantum){ //if cycle time is greater than the time quantum, move the process to the wait queue, subtract the quantum from cycles remaining
           
          System.out.println(" P ID: " + myList.get(i).getId() + " is now active.");
          int cyc = 0;
          while(cyc != myList.get(i).getCycles())
          {
            System.out.println("T" + time2 + " P ID: " + myList.get(i).getId() + " is running.");
            totalCycles2 -= 1;
            cyc++;
            time2+=1;
          }
          System.out.println(" P ID: " + myList.get(i).getId() + " is complete");
        } 
      }
    }
    
    System.out.println();
    System.out.println("Round Robin (Quantum 2)");
    while(totalCycles > 0)
    {
      for(int i = 0; i < myList.size(); i++)
      {
        if(myList.get(i).getCycles()>timeQuantum){ //if cycle time is greater than the time quantum, move the process to the wait queue, subtract the quantum from cycles remaining
          
          if(myList.get(i).arrivalTime() != time && over < myList.size())
          {
            time = myList.get(i).arrivalTime();
          }

          System.out.println(" P ID: " + myList.get(i).getId() + " is now active.");
          System.out.println("T" + time + " P ID: " + myList.get(i).getId() + " is running.");
          System.out.println("T" + (time+1) + " P ID: " + myList.get(i).getId() + " is running.");
          System.out.println(" Max Quantum (2) reached");
          
          totalCycles -= timeQuantum;
          myList.get(i).setCycles(myList.get(i).getCycles()-timeQuantum);
          pcb2 tail = myList.get(i);
          myList.remove(myList.get(i));
          myList.add(myList.size(),tail);
          i--;
          time += 2;
        }
        else
        {
          if(myList.get(i).getCycles() == 2)
          {
            System.out.println("T" + time + " P ID: " + myList.get(i).getId() + " is running.");
            System.out.println("T" + (time+1) + " P ID: " + myList.get(i).getId() + " is running.");
            time += 2;
            System.out.println(" P ID: " + myList.get(i).getId() + " is complete");
          }
          else if(myList.get(i).getCycles() == 1)
          {
            System.out.println("T" + time + " P ID: " + myList.get(i).getId() + " is running.");
            time += 1;
            System.out.println(" P ID: " + myList.get(i).getId() + " is complete");
          }
          else
          { 
            System.out.println(" P ID: " + myList.get(i).getId() + " is complete");
          }
          myList.remove(myList.get(i));
        }
        over++;
      }
    }
  }

  Random rand=new Random();
  public int processId, processArrival, processCycles; //add desired variables here
  pcb2() {
    processId=rand.nextInt(25-5)+5; 
    processArrival=rand.nextInt(25-5)+5;
    processCycles=rand.nextInt(((processId*5)-0)+0);
  }
  static class arrivalComparator implements Comparator<pcb2>{ 
    public int compare(pcb2 p1, pcb2 p2){
      int result=p1.arrivalTime()-p2.arrivalTime();
      if (result==0){
        return p1.getId()-p2.getId();
      }
      else{
        return result;
      }
    }
  }
  public int arrivalTime(){
    return processArrival;
  }
  
  public int getId(){
    return processId;
  }
  
  public int getCycles(){
    return processCycles;
  }
  
  public int setCycles(int cyclesLeft)
  {
    processCycles = cyclesLeft;
    return processCycles;
  }
}

import java.util.Scanner;

public class verify {

	public static void main(String[] args) {
		boolean keepGoing = true;
		while(keepGoing){
			Scanner in = new Scanner(System.in);
			
			System.out.println("Type in anything to check for integers --> ");
			String input = in.nextLine();
			try{
				Integer.parseInt(input);
				System.out.println("This is an integer.");
			}
			catch(Exception e){
				System.out.println("Not an integer");
			}
			keepGoing = false;
		}
	}

}
