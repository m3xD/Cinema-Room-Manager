
import java.util.*;

public class Main {
    static int c = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        // -------------------------------------
        char[][] seatStatus = new char[numberOfRows][numberOfSeats];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                seatStatus[i][j] = 'S';
            }
        }
        System.out.print("\n");
        //---------------------------------------
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            int rows = 0;
            int seatNumber = 0;
            if (option == 0) break;
            switch (option) {
                case 1:
                    printCurrentStateSeats(seatStatus, numberOfSeats, numberOfRows);
                    break;
                case 2:
                    buyTicket(numberOfSeats, numberOfRows, rows, seatNumber, seatStatus);
                    break;
                case 3:
                    statistics(c, numberOfSeats, numberOfRows);
                    break;
            }
        }
    }

    public static void printCurrentStateSeats(char[][] currentState, int numberOfSeats, int numberOfRows) {
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }

        System.out.print("\n");
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numberOfSeats; j++) {
                System.out.print(currentState[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    static int totalIncome = 0;

    public static void buyTicket(int numberOfSeats, int numberOfRows, int rows, int seatNumber, char[][] seatStatus) {
        System.out.println("Enter a row number:");
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatNumber = scanner.nextInt();
        // ---------------------------------------------------
        while ((rows - 1 < 0 || seatNumber - 1 >= numberOfSeats || rows - 1 >= numberOfRows || seatNumber - 1 < 0) || (seatStatus[rows - 1][seatNumber - 1] == 'B')) {
            if (rows - 1 < 0 || seatNumber - 1 >= numberOfSeats || rows - 1 >= numberOfRows || seatNumber - 1 < 0) {
                System.out.println("Wrong input!\n");
            } else {
                System.out.println("That ticket has already been purchased!\n");
            }
            System.out.println("Enter a row number:");
            rows = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
        }
        c++;
        seatStatus[rows - 1][seatNumber - 1] = 'B';
        if (numberOfRows * numberOfSeats <= 60) {
            System.out.println("Ticket price: $" + 10);
            totalIncome += 10;
        } else {
            if (rows <= (numberOfRows / 2)) {
                System.out.println("Ticket price: $" + 10);
                totalIncome += 10;
            } else {
                System.out.println("Ticket price: $" + 8);
                totalIncome += 8;
            }
        }
        //----------------------------------------------------------
        System.out.println();
    }

    public static void statistics(int c, int numberOfSeats, int numberOfRows) {
        System.out.printf("Number of purchased tickets: %d%n", c);
        System.out.printf("Percentage: %.2f", (float) ((c * 100) / (float) (numberOfRows * numberOfSeats)));
        System.out.println("%");
        System.out.printf("Current income: $%d%n", totalIncome);
        if (numberOfRows * numberOfSeats <= 60) {
            System.out.println("Total income: " + "$" + (numberOfRows * numberOfSeats * 10));
        } else {
            int result = numberOfSeats * ((numberOfRows) / 2 * 10 + (numberOfRows - (numberOfRows) / 2) * 8);
            System.out.println("Total income: " + "$" + result);
        }
        System.out.println();
    }
}