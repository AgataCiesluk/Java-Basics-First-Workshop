package pl.coderslab;


public class TaskManager {

    public static void main(String[] args) {
        availableOptions();


    }

    public static void availableOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(ConsoleColors.WHITE + option);
        }
    }
}
