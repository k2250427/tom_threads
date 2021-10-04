package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.printf("%s %s"+System.lineSeparator(), first.getName(), first.getState());
        System.out.printf("%s %s"+System.lineSeparator(), second.getName(), second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.printf("%s %s"+System.lineSeparator(), first.getName(), first.getState());
            System.out.printf("%s %s"+System.lineSeparator(), second.getName(), second.getState());
        }
        System.out.println("Work of the threads is finished");

    }

}
