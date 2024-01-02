package components.logger;

import remoteControl.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class Logger {
    private static Logger instance;
    private Stack<Command> audioCommandHistory;
    private Stack<Command> lightCommandHistory;
    private String errorLogFilePath = "resources/logs/errorLogs.log";
    private String audioLogFilePath = "resources/logs/audioLogs.log";
    private String lightLogFilePath = "resources/logs/lightLogs.log";

    private Logger() {
        audioCommandHistory = new Stack<>();
        lightCommandHistory = new Stack<>();

        clearLogFile(lightLogFilePath, "Light");
        clearLogFile(audioLogFilePath, "Audio");
        clearLogFile(errorLogFilePath, "Error");
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void execute(Command command) {
        command.execute();
        if (command.toString().contains("Audio")) {
            audioCommandHistory.push(command);
            logToAudioFile("Executed: " + command.toString());
        } else if (command.toString().contains("Light")) {
            lightCommandHistory.push(command);
            logToLightFile("Executed: " + command.toString());
        } else {

        }
    }

    private void logToAudioFile(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(audioLogFilePath, true))) {
            writer.write(timestamp + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logToLightFile(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lightLogFilePath, true))) {
            writer.write(timestamp + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearLogFile(String logFilePath, String logType) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath))) {
            writer.write(logType + " Command History Logs - " + timestamp + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String message, Exception e) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        try (FileWriter fw = new FileWriter(errorLogFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(timestamp + " - ERROR: " + message);
            e.printStackTrace(out);
        } catch (IOException ioException) {
            System.err.println("Logging error: " + ioException);
        }
    }

    public boolean undoLight() {
        if (!lightCommandHistory.isEmpty()) {
            Command command = lightCommandHistory.pop();
            command.undo();
            logToLightFile("Undo: " + command.toString());
            return !lightCommandHistory.isEmpty();
        }
        return false;
    }
    public boolean undoAudio() {
        if (!audioCommandHistory.isEmpty()) {
            Command command = audioCommandHistory.pop();
            command.undo();
            logToAudioFile("Undo: " + command.toString());
            return !audioCommandHistory.isEmpty();
        }
        return false;
    }
}
