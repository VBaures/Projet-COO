package ChatApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCP extends Thread {
    ServerHandler serverHandler;
    Socket link;


    public TCP(ServerHandler serverHandler, Socket link){
        this.serverHandler=serverHandler;
        this.link=link;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outTCP = new ObjectOutputStream(link.getOutputStream());
            ObjectInputStream inTCP = new ObjectInputStream(link.getInputStream());
            User sender = serverHandler.getNetworkHandler().getAgent().getPseudoHandler().FindUserByIP(link.getInetAddress().getHostAddress());
            ChatHandler chat = new ChatHandler(sender, outTCP, link, serverHandler.getNetworkHandler().getAgent());
            System.out.println("Chat initié par :"+chat.getRecipient().getPseudo());
            serverHandler.getNetworkHandler().getAgent().getCurrentChat().add(chat);
            while(true){
                Object ObjectReceive=inTCP.readObject();
                System.out.println("Objet Reçu = "+ ObjectReceive);
                if (ObjectReceive instanceof StringMessage) {
                    StringMessage receive = (StringMessage) ObjectReceive;
                    System.out.println("Message reçu :"+ receive.getContentString());
                    serverHandler.getNetworkHandler().getAgent().ReceiveMessage(receive);
                } else if (ObjectReceive instanceof FileMessage) {
                    FileMessage receive = (FileMessage) ObjectReceive;
                    System.out.println("Message avec file reçu");
                    serverHandler.getNetworkHandler().getAgent().ReceiveMessage(receive);

                } else if (ObjectReceive instanceof String) {
                    String receive = (String) ObjectReceive;
                    if ((receive.equals("StopChat"))&(serverHandler.getNetworkHandler().getAgent().findChatHandler(sender.getID())!=null)){
                        serverHandler.getNetworkHandler().getAgent().StopChat(sender.getID());
                        break;
                    }
                }
            }
            link.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Problème reception ! ");
        }
    }
}
