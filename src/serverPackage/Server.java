package serverPackage;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {
    static void main(String[] args) throws IOException {
        //La première étape : créer la socket d'écoute réservée au port 1234
        InetAddress inetAddress= InetAddress.getByName("192.168.0.48");
        InetSocketAddress inetSocketAddress=new InetSocketAddress(inetAddress,1234);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(inetSocketAddress);
        Socket socket = serverSocket.accept();
        System.out.println("Le client est Connecté");
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        int choix = dis.readInt();
        double x = dis.readDouble();

        double resultat = switch (choix) {
            case 1 -> x + 5;
            case 2 -> x - 5;
            case 3 -> x * 5;
            case 4 -> x / 5;
            default -> Double.NaN; // choix invalide
        };

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeDouble(resultat);
        socket.close();
        dos.close();
        os.close();
        dis.close();
        is.close();
        serverSocket.close();
    }
}