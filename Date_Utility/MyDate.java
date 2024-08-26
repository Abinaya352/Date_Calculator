enum Month {
    Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
};

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
};

public class MyDate {
    int date;
    int month;
    int year;
    static String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    static int[] oddDays_perMonth = { 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3 };
    int[] month_val = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    String[] month_array = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

    public static int leap(int year) {
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            return 1;
        }
        return 0;
    }

    MyDate(int date, String month, int year) {
        this.date = date;
        this.month = Month.valueOf(month).ordinal() + 1;
        this.year = year;
    }

    public void age(String date) {
        MyDate birthDate = new MyDate(Integer.parseInt(date.substring(0, 2)), date.substring(3, 6),
                Integer.parseInt(date.substring(7)));
        int age = this.year - birthDate.year;
        if (this.month < birthDate.month || (this.month == birthDate.month && this.date < birthDate.date)) {
            age--;
        }
        System.out.println("Age: " + age);
    }

    public void day_finder() {
        int[] array = { 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3 };
        if (leap(year) == 1) {
            array[1] = 1;
        }

        int yearMod = (year % 400) - 1;
        int totalOddDays = (yearMod / 4) * 2 + (yearMod - (yearMod / 4));

        for (int i = 0; i < month - 1; i++) {
            totalOddDays += array[i];
        }

        totalOddDays += date;
        totalOddDays = totalOddDays % 7;

        System.out.println("Day: " + Day.values()[totalOddDays]);
    }

    public void add(int n) {
        while (n != 0) {
            if (month == 12 && date + n > 31) {
                year++;
                month = 1;
                n -= (31 - date + 1);
                date = 1;
            } else if (date + n > month_val[month - 1]) {
                n -= (month_val[month - 1] - date + 1);
                month++;
                date = 1;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            } else {
                date += n;
                n = 0;
            }
            // int ch = leap(year);
            // date = date + ch;
        }
    }

    public void subtract(int n) {
        while (n != 0) {
            if (month == 1 && date - n < 1) {
                year--;
                month = 12;
                n -= date;
                date = 31;
            } else if (date - n < 1) {
                n -= date;
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
                date = month_val[month - 1];
            } else {
                date -= n;
                n = 0;
            }
        }
    }

    public void difference(String dat) {
        int diff_date = Integer.parseInt(dat.substring(0, 2));
        String m = dat.substring(3, 6);
        int diff_month = Month.valueOf(m).ordinal() + 1;
        int diff_year = Integer.parseInt(dat.substring(7, 11));

        int totalDays1 = calculateTotalDays(this.date, this.month, this.year);
        int totalDays2 = calculateTotalDays(diff_date, diff_month, diff_year);

        int days_difference = Math.abs(totalDays1 - totalDays2);
        System.out.println("Difference in days: " + days_difference);

        if (date < diff_date) {
            date += 30;
            month--;
        }

        if (month < diff_month) {
            year--;
            month += 12;

        }
        System.out.println("Difference between two dates:" + (date - diff_date) + "days" + ":"
                + (month - diff_month) + "Months" + ":"
                + (year - diff_year) + "Years");
        // int d_date = 0, d_month = 0, d_year = 0;
        // while (days_difference > 365) {
        // d_year++;
        // days_difference -= 365;
        // }
        // System.out.println("Differebce in year:" + d_year);

        // System.out.println("remain:" + days_difference);

    }

    private int calculateTotalDays(int date, int month, int year) {
        int days = date;
        for (int i = 0; i < month - 1; i++) {
            days += month_val[i];
        }
        days += (year * 365);
        days += (year / 4) - (year / 100) + (year / 400);
        return days;
    }
}
