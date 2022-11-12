import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.awt.*;


public class Client{
    public static void main(String[]args) throws IOException{
        Socket s = new Socket("enter your IPv4 address here", 4999);

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
