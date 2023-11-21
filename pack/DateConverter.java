package pack;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//doesnt work for " February 15, 2009 Monday "

public class DateConverter {
   static ArrayList<MyData> parseData(ArrayList<String> lines){
      ArrayList<String> patterns = new ArrayList<>();
      patterns.add("(?<day>\\d{2})/(?<month>\\d{1,2}+)/(?<year>\\d{4}) (?<weekday>\\w+)");
      patterns.add("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2}) (?<weekday>\\w+)");
      patterns.add("(?<weekday>\\w+) (?<day>\\d{2}).(?<month>\\d{2}).(?<year>\\d{4})");
      patterns.add("(?<month>\\w+) (?<day>\\d{2}), (?<year>\\d{4}) (?<weekday>\\w+)");

      ArrayList<Pattern> readPatterns = new ArrayList<>(); //compiled patterns
      for (String pattern : patterns){
         readPatterns.add(Pattern.compile(pattern));
      }

      ArrayList<MyData> dates = new ArrayList<>();
      HashSet<String> doneDates = new HashSet<>();

      for(String line : lines ){
         System.out.println("Processing line: " + line);
         boolean matchFound = false;
         for(Pattern pp : readPatterns){
            System.out.println("trying pattern: " + pp.pattern());
            Matcher matcher = pp.matcher(line);

            if(matcher.matches()){

               int tempDay = Integer.parseInt(matcher.group("day"));
               int tempMonth;
               try { 
                  tempMonth = Integer.parseInt(matcher.group("month"));

               }catch (NumberFormatException e){
                  tempMonth = convertMonthStringToNumeric(matcher.group("month"));
               }
               //int tempMonth = Integer.parseInt(matcher.group("month"));
               int tempYear = Integer.parseInt(matcher.group("year"));
               String tempWeekDay = matcher.group("weekday");

               MyData myData = new MyData(tempDay, tempMonth, tempYear, tempWeekDay);
               dates.add(myData);

               matchFound = true;
               break;
               }
            }
           if(!matchFound){
               System.out.println("Error processing dates in line: " + line);
            }     
      }
      return dates;
   }  

   private static int convertMonthStringToNumeric(String monthString){
      String[] monthNames = {"January", "February" , "March" , "April" , "May" , "June" , " July" , "August" , "September" , "October" , "November" , "December" };
      for(int i = 0; i < monthNames.length; i++){
         if(monthNames[i].equalsIgnoreCase(monthString)){
            return i + 1;
         }
      }
      try {
        return Integer.parseInt(monthString);
    } catch (NumberFormatException e) {
        return -1; // Return -1 if the month string is not recognized
    }
   }
   
   public static int dateConvert(String inputFile, String outputFile) throws IOException{
         ArrayList<String> dates = FileHandler.readFromFile(inputFile);
         ArrayList<MyData> convertedDates = parseData(dates);
         FileHandler.WriteToFile(convertedDates, outputFile);
         return convertedDates.size();
   }


}

