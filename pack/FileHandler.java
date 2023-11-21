package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler{
    public static ArrayList<String> readFromFile(String inputFile) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String date = reader.readLine(); 

        while(date != null){
            lines.add(date);
            date = reader.readLine();
        }
        reader.close(); 
        return lines;
    } 

    public static void WriteToFile(ArrayList<MyData> data, String outputFile)throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for(MyData item : data){
            writer.write(item.toString());
            writer.newLine();
        }
        writer.close();
        System.out.println("dates written to file" + outputFile); 
    }
}