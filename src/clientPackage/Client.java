package clientPackage;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.net.InetAddress;
public class Client {
    static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("192.168.0.48");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress,1234);
        System.out.println("Je suis un client pas encore connecté...");
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket();
        socket.connect(inetSocketAddress);
        System.out.println("je suis un client connecté");
        OutputStream os = socket.getOutputStream();
        System.out.println("1. Addition");
        System.out.println("2. Soustraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        System.out.print("Entrer x : ");
        double x = scanner.nextDouble();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(choix);
        dos.writeDouble(x);
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        double nb = dis.readDouble();
        System.out.println("le serveur a calculé nb = "+nb);
        socket.close();
        dis.close();
        is.close();
        dos.close();
        os.close();
    }
}