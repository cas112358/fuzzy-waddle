import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class JavaDev {

	static ConsoleHandler console = new ConsoleHandler();
	static Searcher stackoverflow = new Searcher();
	static ArrayList<String> method = new ArrayList<String>();
	static LocalSearcher local = new LocalSearcher();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true){
			console.printText("Please Enter Search Term, To Free Type Code In Use The $$ Prefix Or Type 'Save Method' To Save Method To txt File :");

			String input = "";
			try{
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				input = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}



			if (input.length() > 0){

				if (input.compareTo("Save Method") != 0){
					String choice = null;

					if (input.charAt(0) != '$' && input.charAt(1) != '$'){
						String[] URLs = stackoverflow.search(input);
						ArrayList<String> codeSnipets = new ArrayList<String>();
						
						String[] results = local.matchTerm(input);
						
						codeSnipets.add(results[0]);
						codeSnipets.add(results[1]);
						//codeSnipets.add(results[2]);

						for (int i = 0; i<URLs.length; i++){
							codeSnipets.addAll(stackoverflow.parseHTML(URLs[i]));
						}	

						try {
							choice = selectOptions(codeSnipets);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						choice = input.substring(2);
					}

					if (choice.contains("replaceThisTextHere")){
						
						console.printText("Please Enter Name :");
						try{
							BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
							input = bufferRead.readLine();
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
						
						choice = choice.substring(0, choice.indexOf("replaceThisTextHere")) + input + choice.substring(choice.indexOf("replaceThisTextHere") + 19);
						System.out.println(choice);
					}

					if (choice.compareTo("9*9/^7AT%@") != 0) {
						console.printText("Method is currently as follows :");
						for (int m = 0; m < method.size(); m++){
							console.printText((m) + " | " + method.get(m));
						}

						if (method.size() == 0){
							console.printText("0 | ");
						}

						console.printText("Please choose line to insert new code after :");

						try{
							BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
							input = bufferRead.readLine();
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}

						int position = Integer.parseInt(input);

						ArrayList<String> linesList = new ArrayList<String>();

						while (choice.contains("\n")){					
							String subChoice = choice.substring(0, choice.indexOf("\n"));
							choice = choice.substring(choice.indexOf("\n") + 1);
							linesList.add(subChoice);
						}

						linesList.add(choice);

						for (int i = linesList.size() - 1; i >= 0; i--){
							if (position + 1 < method.size()){
								method.add(position + 1, linesList.get(i));
							}else{
								method.add(linesList.get(i));
							}
							
							if (linesList.get(i).contains("{")){
								if (position + 2 < method.size()){
									method.add(position + 2, "}");
								}else{
									method.add("}");
								}
							}
						}
						console.clear();
						
						console.printText("Method is now as follows :");
						for (int m = 0; m < method.size(); m++){
							console.printText((m) + " | " + method.get(m));
						}

						if (method.size() == 0){
							console.printText("0 | ");
						}
					}
				}else{
					try(  PrintWriter out = new PrintWriter("Method.txt")  ){
						for (int i = 0; i < method.size(); i++){
							out.println(method.get(i));
						}
						console.printText("Method Saved To File");
					} catch (FileNotFoundException e) {
						//TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}else{
				console.printText("No Search Term Entered");
			}
		}
	}

	public static String selectOptions(ArrayList<String> snippets) throws IOException{
		int option = 0;

		console.printText("Choose Correct Code From List Below, Enter 8 to edit the text or Enter 9 if the correct code is not shown :");
		for (int i = 0; i < 5; i++){
			System.out.println((i+1) + ") " + snippets.get(i));
		}

		String inputStr = "";
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			inputStr = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		if (inputStr.compareTo("") != 0){
			int input = Integer.parseInt(inputStr);

			if (input > 0 && input < 6){
				option = input;
			}else if (input == 9){
				return "9*9/^7AT%@";
			}else{
				console.printText("Incorrect Number Chosen, please pick again.");

				try{
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					inputStr = bufferRead.readLine();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return snippets.get(option-1);
	}

}
