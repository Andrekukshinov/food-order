package main.by.food.orders.client.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static final String EXIT = "6";
    private static final String ERROR = "Try again, please";
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 8082;

    public static void main(String[] args) {
        Socket clientSocket;
        try {
            clientSocket = new Socket(LOCALHOST, PORT);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            String msg;
            do {
                msg = (String) in.readObject();
                System.out.println(msg);
                msg = scanner.nextLine();
                out.writeObject(msg);
            } while (!EXIT.equals(msg));

        } catch (Exception e) {
            System.out.println(ERROR);

        }

    }
}


