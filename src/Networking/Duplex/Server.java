package Networking.Duplex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


class ServerSend extends Thread
{
    Socket sock;
    PrintWriter output;
    ServerSend(Socket sock) throws IOException {
        this.sock = sock;
        this.output = new PrintWriter(this.sock.getOutputStream());
    }

    public void run()
    {
        int i = 0;
        System.out.println("Starts sending");
        while (true)
        {
            try {
                //sending messages separated by 3 second gaps
                this.output.println("Message number #" + i);
                this.output.flush();
                i++;
                sleep(3000);
            }
            catch (Exception e){ break; }
        }
        System.out.println("Finished sending");

        try {
            this.sock.close();
            this.output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Server {
    public static final int PORT=50007;

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

        //komunikacja
        ServerSend ss = new ServerSend(sock);
        ss.start();
        new Receive(sock, ss).start();
    }
}