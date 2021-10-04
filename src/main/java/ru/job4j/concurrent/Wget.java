package ru.job4j.concurrent;

import java.io.*;
import java.net.URL;
import java.time.LocalTime;

public class Wget implements Runnable {

    private final String url;
    private final long speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed * 1000L;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long timeMark = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                long delay = System.currentTimeMillis() - timeMark;
                if (delay < speed) {
                    System.out.println("sleep " + (speed - delay));
                    Thread.sleep(speed - delay);
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
