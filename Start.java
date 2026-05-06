import service.PswService;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int selection;
        do {
            System.out.println("\nPasswordmanagementsystem \n What do you want to do \n");
            System.out.println(
                "1) Write a new entry \n" +
                "2) View / Edit an existing entry \n" +
                "3) Close whole application"
            );
            System.out.println("Enter a valid number:");
            
            try {
                selection = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                selection = 0; 
            }

            switch (selection) {
                case 1:
                    System.out.println("NEW ENTRY");
                    PswService.addPswEntry(sc);
                    break;
                case 2:
                    System.out.println("VIEW ENTRIES");
                    PswService.viewPswEntry(sc);
                    break;
                case 3:
                    System.out.println("BYE");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, try again.");
            }
        } while (true);    
    }
}
