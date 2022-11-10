import java.io.*;
import java.net.*;


public class ServerConnection implements Runnable{

    private Socket server;
    private InputStreamReader in;
    private BufferedReader br;

    public ServerConnection(Socket s) throws IOException{
        server = s;
        in = new InputStreamReader(server.getInputStream());
        br = new BufferedReader(in);
    }

    @Override
    public void run() {
        while(true){
            try{
            String str = br.readLine();
            System.out.println(str);
            } catch(IOException e){
                System.out.println(e);
            }
        }        
    }
    
}
