import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main1{

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 'e' to use an existing file or 'n' to create a new file: ");
		String input = in.nextLine();
		
		
		
		while(!(input.equals("e") || input.equals("n"))){
			System.out.println("Invalid input. Please try again.");
			System.out.println("Enter 'e' to use an existing file or 'n' to create a new file: ");
			input = in.nextLine();
		}
		
		String useFile = "";
		if(input.equals("e")){
			System.out.println("File name: ");
			useFile = in.nextLine();
		}
		else if(input.equals("n")){
			try {
				File file = new File("newData.txt");
				if (!file.exists()) {file.createNewFile();}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				System.out.println("How many processes would you like to add? ");
				int numProcess = Integer.parseInt(in.nextLine());
				String info="";
				for(int i=1;i<=numProcess;i++){
					System.out.println("For process "+i+" enter arrival time: ");
					info = info+in.nextLine();
					System.out.println("For process "+i+" enter number of cycles for completion: ");
					info = info+" "+in.nextLine();
					System.out.println("Does process "+i+" have an I/O event? (y/n): ");
					String answer = in.nextLine();
					if(answer.equals("n")){info = info + " -1";}
					else if(answer.equals("y")){
						System.out.println("At what cycle does the IO event happen? ");
						info = info+" "+in.nextLine();
					}
					if(i != numProcess){info += "\n";}
				}
				bw.write(info);
				bw.close();
				
				useFile = "newData.txt";
				System.out.println("New file called newData.txt created.");
			}
			catch(IOException e) {e.printStackTrace();}
			catch(NumberFormatException e){System.out.println("Invalid input.");}
		
		}
		
		ArrayList<pcb> myList = new ArrayList<pcb>(); 
		String input1 = "";
		int numLine = 0;
		
		try{ 
			BufferedReader reader = new BufferedReader(new FileReader(useFile));
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
			input1 = inputBuffer.toString();
			try{reader.close();}
			catch(IOException e) {System.out.println("Error. Cannot close.");}
		}
		
		catch(FileNotFoundException e) {System.out.println("Error. File not found.");}
		//System.out.println(numLine);
		String lineDelim = "[\n]+";
		String[] processes = input1.split(lineDelim);
		for (int procNum=0; procNum<numLine; procNum++){
			String process = processes[procNum];
			//System.out.println(process);
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
		}
		
		/*int count = 1;
		for(pcb p : myList){
			p.setId(count);
			count++;
		}*/
		
		
		System.out.println();
		String menu = "";
		while(!(menu.equals("exit"))){
			System.out.println("Menu:\n(1)List all processes");
			System.out.println("(2)Add processes\n(3)Update an existing process\n(4)Run FCFS Algorithm\n(5)Run RR Alorgithm");
			System.out.println("Type in 'exit' to end the program.");
			menu = in.nextLine();
			if(menu.equals("1")){
				for(pcb p : myList){
					String IOmessage = "";
					if (p.getIO() == -1){IOmessage = " and has no IO event";}
					else {IOmessage = " and has an IO event at cycle " + p.getIO();}
					System.out.println("P ID: " + p.getId() + " arrives at T=" + p.arrivalTime() + " and requires " + p.getCycles() + " cycles" + IOmessage);
				}
			}
			else if(menu.equals("2")){
				System.out.println("How many processes would you like to add? ");
				int numProcess = Integer.parseInt(in.nextLine());
				for(int i=1;i<=numProcess;i++){
					System.out.println("For process "+i+" enter arrival time: ");
					int arrive = Integer.parseInt(in.nextLine());
					System.out.println("For process "+i+" enter number of cycles for completion: ");
					int cycles = Integer.parseInt(in.nextLine());
					System.out.println("Does process "+i+" have an I/O event? (y/n): ");
					String answer = in.nextLine();
					int IOevent = 0;
					if(answer.equals("n")){IOevent = -1;}
					else if(answer.equals("y")){
						System.out.println("At what cycle does the IO event happen? ");
						IOevent= Integer.parseInt(in.nextLine());
					}
					pcb p = new pcb(arrive,cycles,IOevent);
					myList.add(p);
					p.setId(myList.size());
				}
			}
			else if(menu.equals("3")){
				//update a process
			}
			else if(menu.equals("4")){
				fcfs();
			}
			else if(menu.equals("5")){
				roundRobin();
			}
			
		}

	}

}
