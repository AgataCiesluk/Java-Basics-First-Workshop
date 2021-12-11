package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        tasks();


    }

    public static void availableOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(ConsoleColors.WHITE + option);
        }
    }

    public static String[][] tasks() {
        File file = new File("tasks.csv");
        StringBuilder builder = new StringBuilder();
        int rowCounter = 0;
        String line;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                line = scan.nextLine();
                builder.append(line).append(";");
                rowCounter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        String allLines = builder.toString();
        String [] lines = allLines.split(";");
        String [][] tasks = new String[rowCounter][];
        for (int i = 0; i < tasks.length ; i++) {
            tasks[i] = lines[i].split(", ");
        }
//        quick check if String [][] tasks has been populated correctly
//        for (String[] rows : tasks){
//            for (String tokens : rows) {
//                System.out.println(tokens);
//            }
//        }

        return tasks;
    }
}
