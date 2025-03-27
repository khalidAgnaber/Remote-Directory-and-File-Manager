//Khalid Agnaber 101150
//Intissar Gourainy 94357
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private static final int PORT = 7005;

    public static void main(String[] args) throws Exception{
        try(ServerSocket ss  = new ServerSocket(PORT)){
            System.out.println("Server is bounded to "+PORT);
                while(true){
                System.out.println("Server is Listening ...");
                try(Socket connectionFromClient = ss.accept()){
                System.out.println("You are connected with the client");
                    new ClientHandler(connectionFromClient);   
            }
        }
        }
    }
}