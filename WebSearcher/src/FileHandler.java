import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandler {

	static ConsoleHandler console = new ConsoleHandler();
	
	public ArrayList<String> readAllLines(String File){
		ArrayList<String> allLines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(File))) {
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				allLines.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allLines;
	}

	public void writeAllLines(String File, ArrayList<String> method){
		try(  PrintWriter out = new PrintWriter(File)  ){
			for (int i = 0; i < method.size(); i++){
				out.println(method.get(i));
			}
			console.printText("Method Saved To File");
		} catch (FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
