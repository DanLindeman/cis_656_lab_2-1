package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import compute.Compute;

public class Client {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        while (running) {
            System.out.println("What would you like to do?");
            System.out.println("1 - Calculate digits of pi up to a given decimal place");
            System.out.println("2 - See a list of primes between two numbers");
            System.out.println("3 - Exit");
            Integer choice = scanner.nextInt();
            try {
                String name = "Compute";
                Registry registry = LocateRegistry.getRegistry(args[0]);
                Compute comp = (Compute) registry.lookup(name);
                switch (choice) {
                    case 1:
                        System.out.println("How many digits of Pi shall I calculate?");
                        Integer digits = scanner.nextInt(); // How many digits?
                        Pi task = new Pi(digits);
                        BigDecimal pi = comp.executeTask(task);
                        System.out.println("Pi to " + digits + " digits is " + pi);
                        break;
                    case 2:
                        System.out.println("Minimum?");
                        Integer min = scanner.nextInt();
                        System.out.println("Maximum?");
                        Integer max = scanner.nextInt();
                        Prime primeTask = new Prime(min, max);
                        ArrayList<Integer> primes = primeTask.execute();
                        System.out.println("Primes between <" + min + " and " + max + ">:  " + primes);
                        break;
                    case 3:
                        System.out.println("See ya!");
                        running = false;
                        break;
                    default:
                        System.out.println("Sorry, didn't understand that");
                }
            } catch (Exception e) {
                System.err.println("Client exception:");
                e.printStackTrace();
            }
        }
    }
}
