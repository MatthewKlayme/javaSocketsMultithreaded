import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.awt.*;


public class Client{
    public static void main(String[]args) throws IOException{
        // Socket s = new Socket("192.168.1.8", 4999);
        Socket s = new Socket("192.168.68.1", 4999);
        // Socket s = new Socket("10.1.10.206", 4999);
        // Socket s = new Socket("192.168.194.253", 4999);

        ServerConnection listener = new ServerConnection(s);

        //Makes a message to send to Server
        Scanner scan = new Scanner(System.in);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
    
        new Thread(listener).start();

        while(true){
        System.out.println(">");
        String message = scan.nextLine();
        pr.println(message);//Sends message to server
        pr.flush();

        if(message.equals("quitchamp")){break;}
    }
        s.close();
        System.exit(0);
    }
}