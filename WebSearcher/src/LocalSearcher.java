import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LocalSearcher {

	static ArrayList<String> knownCodeTitles = new ArrayList<String>();
	static ArrayList<String> knownCode = new ArrayList<String>();

	LocalSearcher(){
		try {
			Files.walk(Paths.get("src/Known_Code")).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					knownCodeTitles.add(filePath.getFileName().toString().substring(0,filePath.getFileName().toString().indexOf('.')));
					try {
						knownCode.add(deserializeString(filePath.toString()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String deserializeString(String string) throws IOException {
		int len;
		char[] chr = new char[4096];
		final StringBuffer buffer = new StringBuffer();
		final FileReader reader = new FileReader(string);
		try {
			while ((len = reader.read(chr)) > 0) {
				buffer.append(chr, 0, len);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	public String[] matchTerm(String searchTerm){

		int[] match = new int[knownCodeTitles.size()];
		
		for (int z = 0; z < match.length; z++){
			match[z] = 0;
		}

		for (int i = 0; i < knownCodeTitles.size(); i++){
			String titleSearch = knownCodeTitles.get(i);
			String options = "";

			while (titleSearch.contains("_")){
				options = options + titleSearch.substring(0, titleSearch.indexOf('_')) + "|";
				titleSearch = titleSearch.substring(titleSearch.indexOf('_') + 1);
			}
			options = options + titleSearch.substring(0);

			System.out.println(options);
			
			Matcher m = Pattern.compile(options.toLowerCase()).matcher(searchTerm.toLowerCase());
			while (m.find()){      
				match[i] = match[i] + 1;
			}
		}

		int maxMatch = 0;
		int bestMatch = 0;
		int ndBestMatch = 0;
		int rdBestMatch = 0;

		for(int a = 0; a < match.length; a++){
			if(match[a] > maxMatch){
				maxMatch = match[a];
				rdBestMatch = ndBestMatch;
				ndBestMatch = bestMatch;
				bestMatch= a;
				System.out.println(knownCode.get(a));
			}
		}

		String[] matched = {knownCode.get(bestMatch), knownCode.get(ndBestMatch), knownCode.get(rdBestMatch)};
		return matched;
	}
}
