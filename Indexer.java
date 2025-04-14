import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Indexer {
    //rizz that code up code boy
    public HashMap<String, ArrayList<Integer>> Indexify() { 
        String string = ReadIn();
        string = Simplify(string);
        ArrayList<Integer> intList = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> wordCounter = new HashMap<>();
        String word = "";
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ' || string.charAt(i) == '\n') {
                if (wordCounter.get(word) == null) {
                    intList = new ArrayList<>();
                }
                else {
                    intList = wordCounter.get(word);
                }
                intList.add(counter);
                wordCounter.put(word, intList);
                word = "";
                counter++;
            }
            else {
                word = word + string.charAt(i);
            }
        }
        if (wordCounter.get(word) == null) {
            intList = new ArrayList<>();
        }
        else {
            intList = wordCounter.get(word);
        }
        intList.add(counter);
        wordCounter.put(word, intList);
        return wordCounter;
    }

    public String Simplify(String string) {
        String newString = string.replaceAll("\\p{Punct}", "");
        newString = newString.replaceAll("[^\\x00-\\x7F]", "");
        return newString.toLowerCase();
    }

    public String ReadIn() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your filename: ");
        String fileName = in.nextLine();
        in.close();
        String data = "";
        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                data = data + reader.nextLine() + '\n';
            }
            reader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        return data;
    }

public static void main(String[] args) {
    Indexer indexer = new Indexer();
    System.out.println(indexer.Indexify());
}
}
