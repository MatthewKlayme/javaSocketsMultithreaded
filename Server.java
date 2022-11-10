import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Server{

    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();//Stores all the threads of different client sockets
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    String filer = "UserPassword.txt";
    Boolean exists = false;

    public void writer(String newUser)throws IOException{
        try(FileWriter fw = new FileWriter(filer, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pr = new PrintWriter(bw)){
                pr.println(newUser);
        }catch(IOException e){
            
        }
    }
    public Boolean reader(String User)throws FileNotFoundException{
        exists = false;
        FileReader fr = new FileReader(filer);
        Scanner scan = new Scanner(fr);
        while(scan.hasNextLine()){
            if(scan.nextLine().equals(User)){
                exists = true;
            }
        }
        return exists;
    }

    public String Login(String loginifo) throws FileNotFoundException{
        String note = "";
        try{
        if(reader(loginifo) == true){
            note = "Logging In....";
        }
        else if(reader(loginifo) != true){
            note = "Incorrect....";
        }
    }catch(NullPointerException e){
            note = "Incorrect....";
        }
        return note;
    }
    public String createUsername(String newUser)throws IOException{
        String note = "";
        if(reader(newUser) == false){
            // userNames.put(newUser, true);
            note = "Username "+newUser+" Created";
            writer(newUser);
        }
        else if(reader(newUser) == true){
            note = "Username "+newUser+" already exists";
        }
        return note;
    }
    public static void main(String[]args) throws IOException{
        ServerSocket ss = new ServerSocket(4999);
        
        while(true){
            System.out.println("[SERVER] WAITING FOR CLIENT CONNECTIONS...");
            Socket clientConnection = ss.accept();
            System.out.println("[SERVER] A CLIENT HAS CONNNECTED");
            ClientHandler clientThread = new ClientHandler(clientConnection, clients);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}