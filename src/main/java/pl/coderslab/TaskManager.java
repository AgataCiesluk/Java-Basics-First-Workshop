package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        tasks();
        availableOptions();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "add":
                addTask();
                break;
            default:
                System.out.println("Please select a correct option.");
        }


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
            tasks[i] = lines[i].split(",");
        }
//        quick check if String [][] tasks has been populated correctly
//        for (String[] rows : tasks){
//            for (String tokens : rows) {
//                System.out.println(tokens);
//            }
//        }

        return tasks;
    }

    public static String[][] addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String taskDescript = scanner.nextLine();

        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();

        System.out.println("Is your task important? true/false");
        String importance = scanner.nextLine();

        int tasksArraylength = tasks().length;
        String [] newLine = {taskDescript, " " + dueDate, " " + importance};
        String [][] addedTask = Arrays.copyOf(tasks(), (tasksArraylength + 1));
        addedTask[tasks().length] = Arrays.copyOf(newLine, newLine.length);

//      quick check if String [][] addedTask has been populated correctly
        System.out.println(Arrays.deepToString(addedTask));
        System.out.println(Arrays.deepToString(tasks()));

        return addedTask;
        }

    }


