import service.PswService;
import java.util.Scanner;

import java.io.InputStreamReader;
import java.io.IOException;

public class Start{
  public static void main (String[]args){
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);

    System.out.println("Passwordmanagementsystem \n What do you want to do \n");
    System.out.println(
        "1) Write a new entry \n" +
        "2) View an existing entry \n"+ 
        "3) Close whole application"
        );

    int selection;
    do{
      System.out.println("Enter a valid number:");
      selection = sc.nextInt();
      sc.nextLine();
      switch(selection){
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
      }
    }while(selection > 3);
  }
}
