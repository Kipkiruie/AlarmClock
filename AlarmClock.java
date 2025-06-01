import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable {
    private final LocalTime alarmTime;
    private final String filePath;
    private Scanner scanner = null;

    AlarmClock(LocalTime alarmTime, String filepath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath = filepath;
        this.scanner = this.scanner;
    }

    @Override
    public void run() {
        System.out.println("Alarm set for: " + alarmTime);

        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000); // wait 1 second
                LocalTime now = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n"
                                                    ,now.getHour(),
                                                    now.getMinute(),
                                                    now.getSecond());
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
                return;
            }
        }

        System.out.println("⏰ Alarm time reached: " + alarmTime);
       playSound(filePath);
    }
    private  void playSound(String filePath){
        File audioFile = new File(filePath);

        try (AudioInputStream audioStream= AudioSystem.getAudioInputStream(audioFile)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("press Enter to stop the alarm: ");
            scanner.nextLine();
            clip.stop();
            scanner.close();
            Thread.sleep(2000);

        }catch (UnsupportedAudioFileException e){
            System.out.println("adudio file is not supported");
        }
        catch (LineUnavailableException e){
            System.out.println("adudio is unavailable");
        }catch (IOException e){
            System.out.println("Error reading the audio file");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
