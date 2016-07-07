import java.util.Random;

public class pcb{
	Random rand=new Random();
	int processId, processArrival, processCycles, processIO, currentCycle;
	pcb() {
		processId=rand.nextInt(25-5)+5; 
		processArrival=rand.nextInt(25-5)+5;
		processCycles=rand.nextInt(((processId*5)-0)+0);
		processIO=rand.nextInt(processArrival-(processArrival-1));
		currentCycle=0;
		while(processIO == 0){
			processIO=rand.nextInt(processArrival);
		}
	}
	public void addPcb(int arrive, int cycle, int IOc){
		//allows user to add a process with given arrival time, cycles, io cycle
		processArrival = arrive;
		processCycles = cycle;
		processIO = IOc;
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
  
	public int getCurrentCycle(){
		return currentCycle;
	}
  
	public int setCurrentCycle(){
		currentCycle = currentCycle+1;
		return currentCycle;
	}
  
	public int setCycles(int cyclesLeft){
		processCycles = cyclesLeft;
		return processCycles;
	}
  
	public int getIO(){
		return processIO;
	}
}