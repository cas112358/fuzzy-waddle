import java.io.IOException;

import jline.ConsoleReader;

public class ConsoleHandler {

	static ConsoleReader r;
	
	ConsoleHandler(){
		try {
			r = new ConsoleReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printText(String str){

		try {
			r.printString(str);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			System.out.println("Empty String Error");
		}

		try {
			r.printNewline();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			r.flushConsole();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clear(){
		try {
			r.getHistory();
			r.flushConsole();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
