package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    final private String[] process = {"-","\\","|","/"};

    public void run() {
        try {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading..." + process[count++ % 4]);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("\rLoading interrupted");
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            /* симулируем выполнение параллельной задачи в течение 10 секунд. */
            Thread.sleep(10000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
