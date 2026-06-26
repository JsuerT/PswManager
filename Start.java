import service.PswService;
//import org.json.JSONObject; 
import java.io.File; 
import java.io.FileWriter;
import java.io.BufferedWriter; 
import java.nio.file.Paths;
import java.util.stream.Collectors; 
import java.io.IOException;
import java.util.Scanner;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        login(sc); 

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

    protected static void login(Scanner sc){
        File usrFile = new File("usrFile.json"); 
        try{
            if (usrFile.createNewFile()){
//                System.out.println("test test file ist jetzt daaa");
                
            }
        }
        catch (IOException e){
            //System.out.println("iuiuiuiu");
            System.out.println("Error while creating usrFile.json");
            return; 
        }
        
        if(usrFile.length() < 5){
            System.out.println("There is no previous usr login.");
            System.out.println("Please register\n");
            String password = "";
            String confirmpassword = "";

            while(true){
                System.out.println("Enter a new password");
                password = sc.nextLine();

                if(password.trim().isEmpty()){
                    System.out.println("Please enter a valid password");
                    continue; 
                }

                System.out.println("Confirm the password");
                confirmpassword = sc.nextLine();
                if(password.equals(confirmpassword)){
                    break; 
                }else{
                    System.out.println("Passwords must match");

                }
            }
            try (FileWriter writer = new FileWriter(usrFile)){
                writer.write("{\"password\":\"" + password+ "\"}");
                writer.flush(); 
                System.out.println("Success");
            }
            catch(IOException e){
                System.out.println("Error while saving the password");
            }
        }
        else{
            System.out.println("Login\n");
            String storedPassword = "";
            try (Scanner fileScanner = new Scanner(usrFile)){
                StringBuilder jsonContent = new StringBuilder();
                while(fileScanner.hasNextLine()){
                    jsonContent.append(fileScanner.nextLine());
                }
                storedPassword = jsonContent.toString()
                    .replaceAll(".*\"password\"\\s*:\\s*\"","")
                    .replaceAll("\".*","");
            }catch(IOException e){
                System.out.println("Error while reading .json");
                System.exit(1);
            }
            while(true){
                System.out.print("Enter your password \n");
                String input = sc.nextLine(); 
                if(input.equals(storedPassword)){
                    System.out.println("Welcome");
                    break;
                }
                else{
                    System.out.println("Wrong password, try again");
                }
            }
        }

    }
}
