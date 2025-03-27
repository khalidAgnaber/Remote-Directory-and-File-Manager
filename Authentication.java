//Khalid Agnaber 101150
//Intissar Gourainy 94357
import java.io.*;
import java.util.Scanner;

public class Authentication {
    
    private String filename;

    public Authentication(String filename) {
        this.filename = filename;
    }
    public boolean authenticate(String email, String password) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split(" ", 2);
                if (str.length == 2 && str[0].equals(email) && str[1].equals(password)) {
                    return true; 
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: "+ e.getMessage());
        }
        return false; 
    }
}
