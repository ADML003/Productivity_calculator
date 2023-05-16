import java.io.*;
import java.util.*;


public class Task {
    public static void main(String args[]) {
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<Integer> durations = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);


        boolean loggedIn = false;
        String username = "";
        String password = "";


        System.out.println("___________________     PRODUCTIVITY CALCULATOR APP     __________________\n\n");
        System.out.println("      ******************************************************************\n");
        System.out.println("  || THIS IS A PLACE WHERE YOU CAN KEEP TRACK ON YOUR DAILY ROUTINE TASK  ||\n ");
        System.out.println("  \n**  IF YOU ARE A NEW USER ENTER YOUR NEW USERNAME AND PASSWORD ");
        System.out.println("  \n**  IF YOU ARE AN EXISTING USER ENTER YOUR EXISTING USERNAME AND PASSWORD\n ");


        while (true) {
            if (!loggedIn) {
                System.out.print("   Enter username : ");
                String enteredUsername = scanner.nextLine();
                System.out.print("   Enter password : ");
                String enteredPassword = scanner.nextLine();


                // create file with username and password
                File inputFile = new File(enteredUsername + "_Productivity_Calculator.txt");


                if (!inputFile.exists()) {
                    try {
                        FileWriter fw = new FileWriter(inputFile);
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println("USERNAME = " + enteredUsername);
                        pw.println("PASSWORD = " + enteredPassword);
                        System.out.println(" \n* NEW ACCOUNT CREATED!! *");
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                // check if entered username and password match stored values
                try {
                    Scanner fileScanner = new Scanner(inputFile);


                    String storedUsername = fileScanner.nextLine().substring(11); // remove "USERNAME = " from the stored value
                    String storedPassword = fileScanner.nextLine().substring(11); // remove "PASSWORD = " from the stored value


                    if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                        System.out.println("\n\n _______ WELCOME!! LOGGED IN SUCCESSFULLY AS " + enteredUsername + " ______\n\n");
                        loggedIn = true;
                        username = enteredUsername;
                        password = enteredPassword;
                    } else {
                        System.out.println("Incorrect username or password, please try again.\n");
                    }
                } catch (IOException e) {
                    System.out.println("Incorrect username or password, please try again.\n");
                }
            }


           



            if (loggedIn) {
                System.out.println("  BEGIN BY ADDING YOUR FIRST TASK ");


                System.out.println("Enter task name:");
                String task = scanner.nextLine();
                System.out.println("Enter task duration in minutes:");
                int duration = scanner.nextInt();
                scanner.nextLine();
                tasks.add(task);
                durations.add(duration);


                // write task to file
                try {
                    FileWriter fw = new FileWriter(username + "_Productivity_Calculator.txt", true);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(task + " ( " + duration + " ) \n");
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println("\nNOW YOU HAVE ADDED YOUR TASK AND NOW YOU CAN PERFORM OTHER OPERATIONS");


                int choice = 0;
                while (choice != 5) {
                    System.out.println("\nTHESE ARE VARIOUS OPTIONS YOU CAN PERFORM ");
                    System.out.println("\nEnter\n 1 : To Add Task\n 2 : To Update Task\n 3 : To Delete Task\n 4 : To Display Tasks\n 5 : EXIT HERE\n\n");


                    choice = scanner.nextInt();
                    scanner.nextLine();


                                    if (choice == 1) {
                    System.out.println("Enter task name:");
                    task = scanner.nextLine();
                    System.out.println("Enter task duration in minutes:");
                    duration = scanner.nextInt();
                    scanner.nextLine();
                    tasks.add(task);
                    durations.add(duration);


                    // write task to file
                    try {
                        FileWriter fw = new FileWriter(username + "_Productivity_Calculator.txt", true);
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println(task + " ( " + duration + " ) \n");
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    System.out.println("TASK ADDED SUCCESSFULLY");
                } else if (choice == 2) {
                    System.out.println("Enter task name to update:");
                    String oldTask = scanner.nextLine();
                    int index = tasks.indexOf(oldTask);


                    if (index == -1) {
                        System.out.println("Task not found.");
                    } else {
                        System.out.println("Enter new task name:");
                        task = scanner.nextLine();
                        System.out.println("Enter new task duration in minutes:");
                        duration = scanner.nextInt();
                        scanner.nextLine();
                        tasks.set(index, task);
                        durations.set(index, duration);


                        // update task in file
                        try {
                            FileWriter fw = new FileWriter(username + "_Productivity_Calculator.txt");
                            PrintWriter pw = new PrintWriter(fw);


                            for (int i = 0; i < tasks.size(); i++) {
                                pw.println(tasks.get(i) + " ( " + durations.get(i) + " ) \n");
                            }


                            pw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        System.out.println("TASK UPDATED SUCCESSFULLY");
                    }
                } else if (choice == 3) {
                    System.out.println("Enter task name to delete:");
                    String taskToDelete = scanner.nextLine();
                    int index = tasks.indexOf(taskToDelete);


                    if (index == -1) {
                        System.out.println("Task not found.");
                    } else {
                        tasks.remove(index);
                        durations.remove(index);


                        // update file
                        try {
                            FileWriter fw = new FileWriter(username + "_Productivity_Calculator.txt");
                            PrintWriter pw = new PrintWriter(fw);


                            for (int i = 0; i < tasks.size(); i++) {
                                pw.println(tasks.get(i) + " ( " + durations.get(i) + " ) \n");
                            }


                            pw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        System.out.println("TASK DELETED SUCCESSFULLY");
                    }
                } else if (choice == 4) {
                    System.out.println("YOUR CURRENT TASKS ARE: ");


                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(tasks.get(i) + " ( " + durations.get(i) + " minutes )");
                    }
             } else if (choice == 5) {
    System.out.println("EXITING APPLICATION");
    int totalDuration = 0;
    for (int d : durations) {
        totalDuration += d;
    }
    int productiveHours = totalDuration / 60;
    int productiveMinutes = totalDuration % 60;
    System.out.println("Total productive time: " + productiveHours + " hours " + productiveMinutes + " minutes");

    // write productive hours to file
    try {
        FileWriter fw = new FileWriter(username + "_Productivity_Calculator.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("Total Productive Time in hours: " + productiveHours + " hours " + productiveMinutes + " minutes");
        pw.println("TOTAL PRODUCTIVE TIME IN MINUTES = " + totalDuration + " minutes");
        pw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("Thank you for using the productivity calculator!");
    System.exit(0);
} else {
    System.out.println("Invalid input, please try again.");
}




 
}
}
}
}}

      
