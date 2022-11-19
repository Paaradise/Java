package Networking.Simplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static final int PORT=50007;
    public static final String HOST = "127.0.0.1";

    public static void receive_message(BufferedReader inp) throws IOException {
        String msg = inp.readLine();
        System.out.println("<Received:> "+msg);
    }

    public static void send_message(PrintWriter outp, String msg)
    {
        outp.println(msg);
        outp.flush();
    }

    public static String get_message(BufferedReader br) throws IOException {
        System.out.println("<Send:> ");
        return br.readLine();
    }

    public static void main(String[] args) throws IOException
    {
        communicate();
    }

    public static void communicate() throws IOException {
        //nawiazanie polaczenia z serwerem
        Socket sock;
        try {
            sock = new Socket(HOST, PORT);
            System.out.println("Connection with: " + sock);
        } catch (IOException e){
            System.out.println("Connection error");
            return;
        }

        //tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu
        BufferedReader klaw;
        klaw=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outp;
        outp=new PrintWriter(sock.getOutputStream());
        BufferedReader inp;
        inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));

        //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia
        String msg = "";

        while (!msg.equals("exit")) {
            try {
                msg = get_message(klaw);
                send_message(outp, msg);
                receive_message(inp);
            }
            catch (SocketException e) {
                System.out.println("Connection lost");
                break;
            }
        }

        System.out.println("Closing connection");

        //zamykanie polaczenia
        klaw.close();
        outp.close();
        sock.close();
    }
}
