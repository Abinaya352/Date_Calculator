import java.util.Scanner;

public class Input {

    public static void parse(String input) {
        String[] tokens = input.split("-");
        int date = Integer.parseInt(tokens[0]);
        String month = tokens[1];
        int year = Integer.parseInt(tokens[2]);
        MyDate d = new MyDate(date, month, year);
        d.add(60);
        System.out.println("After adding days: " + d.date + "-" +
                d.month_array[d.month - 1] + "-" + d.year);
        d.subtract(5);
        System.out.println("After subtracting days: " + d.date + "-" +
                d.month_array[d.month - 1] + "-" + d.year);
        d.age("03-May-2002");
        d.day_finder();
        d.difference("03-May-2002");
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String date = "11-Jan-2024";
            parse(date);
        }
    }
}
