package pack;

public class MyData {
    
    int day;
    int month;
    int year;
    String weekday;

    MyData(int day, int month, int year, String weekday) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekday = weekday;
    }
    public String toString(){
        return "day= " + this.day + ", month= " + this.month + ", year= " + this.year + ", weekday= " + this.weekday;

    }    
}
