package pack;
import java.io.IOException;

public class DateApp {
    
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Marcus\\Documents\\java_all\\task5\\input.txt";
        String outputFile = "C:\\Users\\Marcus\\Documents\\java_all\\task5\\output.txt";

        try{
            int convertedDates = DateConverter.dateConvert(inputFile, outputFile);
            System.out.println("Successfull \nNumber of written dates: " + convertedDates);
        }catch(IOException e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }

}
