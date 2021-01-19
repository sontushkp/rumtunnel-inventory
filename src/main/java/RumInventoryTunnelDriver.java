import java.util.Scanner;

/**
 * This is a driver class for RumInventoryTunnel for user input
 * user have to specify how many operations(an int) need to be performed in total before any command
 * This class print the amount of the rum of the barrel as soon as it encounter a remove command
 * program will wait until the number of command reach it maximum number as provided by user
 */
public class RumInventoryTunnelDriver {

    public static void main(String[] args) {


        RumInventoryTunnel rumInventoryList = new RumInventoryTunnel();


        Scanner scanner = new Scanner(System.in);

        int numberOfCommand = scanner.nextInt();
        scanner.nextLine();

        int count =1;
        while (count<=numberOfCommand) {

            String commandLine = scanner.nextLine();
            Scanner lineScan = new Scanner(commandLine);
            lineScan.useDelimiter(" ");

            String commandSuffix = lineScan.next();


            if (commandSuffix.equalsIgnoreCase("add")) {
                String commandMiddle = lineScan.next();
                if (commandMiddle.equalsIgnoreCase("A")) {
                    String addCommandPrefix = lineScan.next();
                    int rumAmount = Integer.parseInt(addCommandPrefix);
                    rumInventoryList.addA(rumAmount);

                } else if(commandMiddle.equalsIgnoreCase("B")) {

                    String addCommandPrefix = lineScan.next();
                    int rumAmount = Integer.parseInt(addCommandPrefix);
                    rumInventoryList.addB(rumAmount);

                } else if(commandMiddle.equalsIgnoreCase("C")) {

                    String addCommandPrefix = lineScan.next();
                    int rumAmount = Integer.parseInt(addCommandPrefix);
                    rumInventoryList.addC(rumAmount);

                } else {
                    System.out.println("Invalid Input");
                }
            } else if(commandSuffix.equalsIgnoreCase("remove")) {

                String removeCommandPrefix = lineScan.next();
                if (removeCommandPrefix.equalsIgnoreCase("A")) {
                    System.out.println(rumInventoryList.removeA());
                } else if (removeCommandPrefix.equalsIgnoreCase("B")) {
                    System.out.println(rumInventoryList.removeB());
                } else if (removeCommandPrefix.equalsIgnoreCase("C")) {
                    System.out.println(rumInventoryList.removeC());
                } else {
                    System.out.println("Invalid Input");
                }

            } else {
                System.out.println("Invalid Input");
            }
            count++;
        }


    }
}
