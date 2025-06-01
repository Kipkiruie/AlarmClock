import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filepath = "KAMA SI MZIKI - FLEVA ALKEE (official video).wav";

        while (alarmTime == null) {
            try {
                System.out.print("Enter an alarm time (HH:mm:ss): ");
                String inputTime = scanner.nextLine();
                alarmTime = LocalTime.parse(inputTime, formatter);

            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use HH:mm:ss");
            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime,filepath,scanner);
        Thread alarmThread = new Thread(alarmClock);
        alarmThread.start();


    }
}
