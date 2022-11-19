package Networking.Duplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Receive extends Thread
{
    Socket sock;
    BufferedReader input;
    ServerSend ss = null;

    Receive(Socket sock) throws IOException {
        this.sock = sock;
        this.input = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
    }

    Receive(Socket sock, ServerSend ss) throws IOException {
        this.sock = sock;
        this.input = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        this.ss = ss;
    }

    public void run()
    {
        String msg = "";
        System.out.println("Starts Receiving");
        while (!msg.equals("exit"))
        {
            try {
                if (this.input.ready()){
                    msg = this.input.readLine();
                    System.out.println("<Received:> " + msg);
                }
            }
            catch (IOException e){ break; }
            if (this.isInterrupted())
            {
                break;
            }
        }

        System.out.println("Finished receiving.");
        try {
            this.input.close();
            this.sock.close();
            if (this.ss != null)
            {
                this.ss.interrupt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Send extends Thread
{
    Socket sock;
    PrintWriter output;
    BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in));
    Receive r = null;

    Send(Socket sock) throws IOException {
        this.sock = sock;
        this.output = new PrintWriter(this.sock.getOutputStream());
    }

    Send(Socket sock, Receive r) throws IOException {
        this.sock = sock;
        this.output = new PrintWriter(this.sock.getOutputStream());
        this.r = r;
    }

    public void run()
    {
        String msg = "";
        while (!msg.equals("exit"))
        {
            try {
                msg = klaw.readLine();
                output.println(msg);
                output.flush();
                System.out.println("<Sent:> " + msg);
            }
            catch (IOException e) {}
        }

        try {
            output.close();
            sock.close();
            klaw.close();
            if (this.r != null)
            {
                this.r.interrupt();
            }
        }
        catch (IOException e){ throw new RuntimeException("Error occurred while closing connections."); }
    }
}

public class Client {
    public static final int PORT=50007;
    public static final String HOST = "127.0.0.1";

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

        //komunikacja
        Receive rec = new Receive(sock);
        rec.start();
        new Send(sock, rec).start();
    }
}