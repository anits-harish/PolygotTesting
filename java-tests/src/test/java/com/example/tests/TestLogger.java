package com.example.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLogger {
    private static final String LOG_FILE = "automation-execution.log";
    private static PrintWriter writer;

    static {
        try {
            // true for append mode
            writer = new PrintWriter(new FileWriter(LOG_FILE, true), true);
            writer.println("\n===========================================");
            writer.println(" Test Execution Started: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("===========================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        String formattedMessage = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + message;
        
        // Print to console
        System.out.println(formattedMessage);
        
        // Print to log file
        if (writer != null) {
            writer.println(formattedMessage);
        }
    }

    public static void close() {
        if (writer != null) {
            writer.println("===========================================");
            writer.println(" Test Execution Completed");
            writer.println("===========================================\n");
            writer.close();
        }
    }
}
