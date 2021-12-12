package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class TaskManager {


    private static String[][] tasks;

    public static void main(String[] args) {
        tasks = tasks();
        availableOptions();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
        input = scanner.nextLine();
        switch (input) {
            case "add":
                addTask();
                availableOptions();
                break;
            case "list":
                listTasks();
                availableOptions();
                break;
            case "remove":
                removeTask();
                availableOptions();
            case "exit":
                break;
            default:
                System.out.println("Please rerun an app and select a correct option.");
        }
        } while (input.equals("add") || input.equals("remove") || input.equals("list"));




    }

    public static void availableOptions() {
        System.out.println(BLUE + "Please select an option: " + RESET);
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(option);
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

    public static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String taskDescript = scanner.nextLine();

        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();

        String importance;
        do {
        System.out.println("Is your task important? true/false");
        importance = scanner.nextLine();
        } while (!importance.equals("true") && !importance.equals("false"));

        int tasksArraylength = tasks.length;
        String [] newLine = {taskDescript, " " + dueDate, " " + importance};
        String [][] addedTask = Arrays.copyOf(tasks, (tasksArraylength + 1));
        addedTask[tasks.length] = Arrays.copyOf(newLine, newLine.length);
        tasks = addedTask;

//      quick check if String [][] addedTask has been populated correctly as well as tasks
//        System.out.println(Arrays.deepToString(tasks));
        }

    public static void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j]);
                if (j == tasks[i].length - 1) {
                    System.out.print(System.lineSeparator());
                }
            }
        }
    }

    public static void removeTask() {
        if (tasks.length == 0) {
            System.out.println("List of tasks is empty. Nothing to remove.");
        } else {

            Scanner scanner = new Scanner(System.in);
            int taskNumber;
            do {
                System.out.println("Please select number to remove. If you don't want to remove anything and go back to options menu, type: -1.");
                String wrongInput;

                while (!scanner.hasNextInt()) {
                    wrongInput = scanner.nextLine();
                    System.out.println("Incorrect value '" + wrongInput + "'. Please select number to remove from 0 to " + (tasks.length - 1));
                }
                taskNumber = scanner.nextInt();

            } while (taskNumber < -1 || taskNumber >= tasks.length);

            if (taskNumber != -1) {
                tasks = ArrayUtils.remove(tasks, taskNumber);
            }
        }
    }

    }


