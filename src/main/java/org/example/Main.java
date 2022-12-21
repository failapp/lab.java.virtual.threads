package org.example;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        //main.platformThreads();
        main.virtualThreads();
    }

    public void platformThreads() {

        System.out.println("platform threads");
        final Instant start = Instant.now();

        for (int i =0; i < 50_000; i++) {
            if (i % 10_000 == 0) {
                System.out.println(i);
            }
            new Thread( () -> {
                try {
                    Thread.sleep(5_000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }

        printElapsedTime( Duration.between( start, Instant.now()) );
    }

    public void virtualThreads() {

        System.out.println("virtual threads");
        final Instant start = Instant.now();

        for (int i =0; i < 50_000; i++) {
            if (i % 10_000 == 0) {
                System.out.println(i);
            }
            Thread.startVirtualThread (() -> {
                try {
                    Thread.sleep(5_000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        printElapsedTime( Duration.between( start, Instant.now()) );

    }


    private void printElapsedTime(Duration duration) {
        long seconds = duration.getSeconds() % 60;
        long millis = duration.toMillis() % 60;
        System.out.println( String.format("Time: %d seconds, %d milliseconds", seconds, millis) );
    }


}