import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    Server server = new Server();
    private Socket clientConnection;
    private BufferedReader br;
    private InputStreamReader input;
    private PrintWriter pr;
    private ArrayList<ClientHandler> clients;
    private int layer = 0;
    String yourName = "";

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws IOException{
        this.clientConnection = client;
        this.clients = clients;
        input = new InputStreamReader(clientConnection.getInputStream());
        br = new BufferedReader(input);
        pr = new PrintWriter(clientConnection.getOutputStream());
    }




    @Override
    public void run(){
        ClientGUI cgui = new ClientGUI();
        layer = 0;
        while(layer == 0){
            try{
                pr.println("Enter UserName or type NEW followed by a Username to create one");
                pr.flush();
                String request = br.readLine();
                System.out.println("Client said: "+request);
                if(request.contains("NEW ")){
                    String temp = request.substring(4);
                    pr.println(server.createUsername(temp));
                    pr.flush();
                    if(server.createUsername(temp) == "Username "+temp+" already exists"){

                    }
                }
                else if(!request.contains("NEW ") && layer == 0){
                    pr.println(server.Login(request));
                    pr.flush();
                    if(server.Login(request) == "Incorrect...."){

                    }
                    else{
                        yourName = request;
                        layer = 1;
                    }
                }
                else if(!cgui.user.isEmpty()){
                    pr.println(server.Login(cgui.user));
                    pr.flush();
                    if(server.Login(cgui.user) == "Incorrect....");
                    else{
                        yourName = cgui.user;
                        layer = 1;
                    }
                }
            }catch(IOException e){

            }
        }

        pr.println("Logged In");
        pr.flush();
        while(layer == 1){//change to while login == true;
            try{
            String request = br.readLine();//Moves it to a String
            System.out.println("Client said: "+request);
            if(request.equals("logout")){
                outToAll("has logged out");
                run();
            }
            if(request.equals("help") || request.equals("Help")){
                pr.println("Faggot type something into the chat");//what to send back to client
                pr.flush();
            }
            else if(!request.equals("help")||!request.equals("Help") && layer == 1){
                outToAll(request);
            }
            
            else{
                pr.println("Type 'help', to recieve a message");
                pr.flush();
            }
            
        }catch(IOException e){
            System.err.println("IOException in Client Handler");
            System.err.println(e.getStackTrace());
        } 
        }
        pr.close();
        try{
        br.close();}catch(IOException e){
            e.printStackTrace();
        }
        
    }
    private void outToAll(String toAll){
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).layer == 1){
                clients.get(i).pr.println(yourName+": "+toAll);
                clients.get(i).pr.flush();
            }
        }
    }
}