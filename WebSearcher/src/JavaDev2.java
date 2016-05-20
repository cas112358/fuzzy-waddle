import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JavaDev2 {

	static ConsoleHandler console = new ConsoleHandler();
	static Searcher stackoverflow = new Searcher();
	static ArrayList<String> method = new ArrayList<String>();
	static LocalSearcher local = new LocalSearcher();
	static FileHandler files = new FileHandler();

	public static void main(String[] args) {

		JavaTree tree = new JavaTree();
		String input;

		while(true){

			if (tree.GetCurrentNode().getUsed()){
				if (tree.GetCurrentNode().getLeft() != null){
					tree.moveLeft();
				}
			}
			String choice = "";
			try {
				choice = selectOptions(tree.GetCurrentNode().getValue());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (choice.compareTo("@)@P*&S9LaF") != 0){

				if (choice.contains("*ACES*")){
					input = "";
					try {
						input = selectTypeOptions(files.readAllLines("src/Known_Code/Access.txt"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					choice = choice.substring(0, choice.indexOf("*ACES*")) + input + choice.substring(choice.indexOf("*ACES*") + 6);
				}

				if (choice.contains("*TYPE*")){
					input = "";
					try {
						input = selectTypeOptions(files.readAllLines("src/Known_Code/Types.txt"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					choice = choice.substring(0, choice.indexOf("*TYPE*")) + input + choice.substring(choice.indexOf("*TYPE*") + 6);
				}

				if (choice.contains("*NAME*")){

					console.printText("Please Enter Name :");
					input = console.readString();

					choice = choice.substring(0, choice.indexOf("*NAME*")) + input + choice.substring(choice.indexOf("*NAME*") + 6);
				}

				console.printText("Method is currently as follows :");
				for (int m = 0; m < method.size(); m++){
					console.printText((m) + " | " + method.get(m));
				}

				if (method.size() == 0){
					console.printText("0 | ");
				}

				console.printText("Please choose line to insert new code after :");

				input = console.readString();

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

				console.printText("Method is now as follows :");
				for (int m = 0; m < method.size(); m++){
					console.printText((m) + " | " + method.get(m));
				}

				if (method.size() == 0){
					console.printText("0 | ");
				}
				tree.GetCurrentNode().setUsedTrue();
			}
		}
	}

	public static String selectOptions(ArrayList<String> snippets) throws IOException{
		int option = 0;
		if (snippets.size() != 1){
			console.printText("Choose Correct Code From List Below, Enter 'Save Method' To Save Code To File :");
			for (int i = 0; i < snippets.size(); i++){
				System.out.println((i+1) + ") " + snippets.get(i));
			}

			String inputStr = console.readString();

			if (inputStr.length() > 0){
				if (inputStr.compareToIgnoreCase("Save Method") != 0){
					int input = Integer.parseInt(inputStr);
					if (input > 0 && input <= snippets.size()){
						option = input;
					}
				}else{
					files.writeAllLines("Method.txt", method);
					return "@)@P*&S9LaF";
				}			
			}else{
				console.printText("No Search Term Entered");
			}
			return snippets.get(option-1);
		}else{
			console.printText("New Code is : " + snippets.get(0));
			return snippets.get(0);
		}
	}

	public static String selectTypeOptions(ArrayList<String> snippets) throws IOException{
		int option = 0;

		console.printText("Choose Correct Type From List Below :");
		for (int i = 0; i < snippets.size(); i++){
			System.out.println((i+1) + ") " + snippets.get(i));
		}

		String inputStr = console.readString();

		if (inputStr.length() > 0){
			int input = Integer.parseInt(inputStr);
			if (input > 0 && input <= snippets.size()){
				option = input;
			}					
		}else{
			console.printText("No Search Term Entered");
		}
		return snippets.get(option-1);
	}
}
