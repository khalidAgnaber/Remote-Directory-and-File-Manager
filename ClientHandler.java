//Khalid Agnaber 101150
//Intissar Gourainy 94357
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler {

    InputStream in;
    OutputStream out;
    BufferedReader headerReader;
    BufferedWriter headerWriter;

    public ClientHandler(Socket connectionFromClient) throws Exception {
        in = connectionFromClient.getInputStream();
        out = connectionFromClient.getOutputStream();
        headerReader = new BufferedReader(new InputStreamReader(in));
        headerWriter = new BufferedWriter(new OutputStreamWriter(out));
        interact();
    }

    private void interact() throws Exception{
        String header = headerReader.readLine();
        StringTokenizer strk = new StringTokenizer(header, " ");
        String command  =strk.nextToken();
        String email = strk.nextToken();
        String password = strk.nextToken();
        Authentication auth = new Authentication("users.txt");

        if (auth.authenticate(email, password)) {
            header = "WELCOME"+"\n";
            headerWriter.write(header);
            headerWriter.flush();
        } else {
            header = "NOT "+ "FOUND" +"\n";
            headerWriter.write(header);
            headerWriter.flush();
        }

        header = headerReader.readLine();
        StringTokenizer strk1 = new StringTokenizer(header, " ");
        command = strk1.nextToken();

        if (command.equals("LIST")){
            File directory = new File("Direct/");
            
            if (directory.exists() && directory.isDirectory()) {
                String[] files = directory.list();
                if (files != null) {
                    headerWriter.write("LISTED\n");  
                    for (String file : files) {
                        headerWriter.write(file + "\n");  
                    }
                    headerWriter.flush();
                } else {
                    headerWriter.write("EMPTY"+"\n");
                    headerWriter.flush();
                }
            } 
        }

        else if (command.equals("CREATE_F")){
            File directory = new File("Direct/");
            String filename = strk1.nextToken();
            File newFile = new File(directory, filename);

            if (newFile.createNewFile()) {
            headerWriter.write("CREATED"+"\n"); 
           } else {
            headerWriter.write("EXISTS"+"\n");
        }
            headerWriter.flush();
        }

        else if (command.equals("CREATE_D")){
            File directory = new File("Direct/");
            String directoryname = strk1.nextToken(); 
            File newDirectory = new File(directory, directoryname);

        if (newDirectory.exists()) {
            headerWriter.write("EXISTS"+"\n");  
           } else {
            newDirectory.mkdir();
            headerWriter.write("CREATED"+"\n");
        }
            headerWriter.flush();
        }
    }
}
