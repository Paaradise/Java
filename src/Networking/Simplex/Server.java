package Networking.Simplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT=50007;

    public static void send_message(PrintWriter outp, String msg)
    {
        System.out.println("<Send:> " + msg);
        outp.println(msg);
        outp.flush();
    }

    public static String receive_message(BufferedReader in) throws IOException {
        String msg = in.readLine();
        System.out.println("<Received:> " + msg);
        return msg;
    }

    public static void main(String[] args) throws IOException
    {
        communicate();
    }

    public static void communicate() throws IOException {
        //tworzenie gniazda serwerowego
        ServerSocket serv;
        serv=new ServerSocket(PORT);

        //oczekiwanie na polaczenie i tworzenie gniazda sieciowego
        System.out.println("Nasluchuje: "+serv);
        Socket sock;
        sock=serv.accept();
        System.out.println("Jest polaczenie: "+sock);

        //tworzenie strumienia danych pobieranych z gniazda sieciowego
        BufferedReader inp;
        inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter outp;
        outp=new PrintWriter(sock.getOutputStream());

        //komunikacja - czytanie danych ze strumienia
        String msg = "";
        while (!msg.equals("exit")) {
            msg = receive_message(inp);
            if(msg.equals("ping"))
            {
                send_message(outp, "pong");
            }
            else if (msg.equals("exit"))
            {
                send_message(outp, "closing connection");
            }
            else
            {
                send_message(outp, "unknown command");
            }
        }

        System.out.println("closing connection");

        //zamykanie polaczenia
        inp.close();
        sock.close();
        serv.close();
    }
}
