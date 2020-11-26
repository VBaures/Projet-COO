package ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkHandler extends Thread {
    public static void Connect(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);

            while (true) {
                System.out.println("Awaiting connection");
                Socket link = ss.accept();
                new Thread(new NetworkListener(link)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void StartChat(int port) {
        new Thread(() -> {
            try {
                Socket link = new Socket("localhost", port);
                BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
                PrintWriter out = new PrintWriter(link.getOutputStream(), true);
                System.out.println(in.readLine());
                Scanner clavier = new Scanner(System.in);
                String envoie = clavier.nextLine();
                out.println(envoie);
                link.close();
            } catch (IOException e) {
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            }
        }).start();
    }
}