//Khalid Agnaber 101150
//Intissar Gourainy 94357
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
    private static final int PORT = 7005;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {

        try(Socket connectionToServer = new Socket(HOST, PORT)){
            InputStream in = connectionToServer.getInputStream();
            OutputStream out = connectionToServer.getOutputStream();
            BufferedReader headerReader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter headerWriter = new BufferedWriter(new OutputStreamWriter(out));
        
            Scanner sc = new Scanner(System.in);
            System.out.print("Please input your email: ");
            String email = sc.nextLine();
            System.out.print("Please input your password: ");
            String password = sc.nextLine();

            String header ="LOGIN "+email+" "+password+ "\n";
            headerWriter.write(header);
            headerWriter.flush();

            header = headerReader.readLine();
            StringTokenizer strk = new StringTokenizer(header, " ");
            String command  =strk.nextToken();
            if (command.equals("WELCOME"))
            {
                while (true)
                {
                    System.out.print("Please input the command (l: list | cf: create file | cd: create directory |exit: exit): ");
                    String com = sc.nextLine();
                
                    if (com.equals("l"))
                    {
                        header ="LIST"+"\n";
                        headerWriter.write(header);
                        headerWriter.flush();

                        header = headerReader.readLine();
                        strk = new StringTokenizer(header, " ");
                        String c =strk.nextToken();

                        if (c.equals("LISTED")) 
                        {
                            System.out.println("Files and directories: ");
                            while ((c = headerReader.readLine()) != null && !c.isEmpty())
                            {
                                System.out.println(c);
                            }
                        }
                        else if (c.equals("EMPTY")) 
                        {
                            System.out.println("The directory is empty");
                        } 
                    }
                    else if (com.equals("cf"))
                    {
                        System.out.print("Please input the file name: ");
                        String filename = sc.nextLine();
                        header ="CREATE_F "+filename + "\n";
                        headerWriter.write(header);
                        headerWriter.flush();
                
                        header = headerReader.readLine();
                        strk = new StringTokenizer(header, " ");
                        String c =strk.nextToken();

                        if (c.equals("CREATED")) 
                        {
                            System.out.println("The file has been created successfully!");  
                        }
                        else if (c.equals("EXISTS")) 
                        {
                            System.out.println("The file already exists");
                        } 
                    }
                    else if (com.equals("cd"))
                    {
                        System.out.print("Please input the directory name: ");
                        String directoryname = sc.nextLine();
                        header ="CREATE_D "+directoryname + "\n";
                        headerWriter.write(header);
                        headerWriter.flush();
                
                        header = headerReader.readLine();
                        strk = new StringTokenizer(header, " ");
                        String c =strk.nextToken();

                            if (c.equals("CREATED")) 
                            {
                                System.out.println("The directory has been created successfully!");  
                            }
                            else if (c.equals("EXISTS"))
                            {
                                System.out.println("The directory already exists");
                            } 
                    }

                    else if (com.equals("exit"))
                    {
                        break;
                    }
                }
            }
            else{
                System.out.println("User not found, the connection is terminated");
            }
            sc.close();
        }   
    }  
}
