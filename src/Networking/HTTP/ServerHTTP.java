package Networking.HTTP;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.*;
import java.util.Optional;

class Serve extends Thread
{
    Socket clientSocket;

    Serve(Socket sock)
    {
        this.clientSocket = sock;
    }

    void task() throws IOException {
        //strumienie danych
        InputStream is;
        OutputStream os;
        try {
            is = clientSocket.getInputStream();
            os=clientSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader inp=new BufferedReader(new InputStreamReader(is));
        DataOutputStream outp=new DataOutputStream(os);

        //przyjecie zadania (request)
        String request= null;
        try {
            request = inp.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (request == null)
        {
            return;
        }

        String fileName = request.split(" ")[1];
        System.out.println("file: " + fileName);

        String code = "HTTP/1.0 200 OK\r\n";
        FileInputStream file;

        try {
            file = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\Networking\\HTTP\\" + fileName));
        }
        catch (FileNotFoundException e) {
            try {
                file = new FileInputStream(System.getProperty("user.dir") + "\\src\\Networking\\HTTP\\FileNotFound.html");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            code = "HTTP/1.0 404 FileNotFound\r\n";
        }

        byte[] buf;
        buf = new byte[1024];
        int n = 0;

        int contentLength = 0;
        try {
            contentLength = file.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String extension = Optional.ofNullable(fileName).filter(f -> f.contains(".")).map(f -> f.substring(fileName.lastIndexOf(".") + 1)).get();
        System.out.println("extension: " + extension);

        //wyslanie odpowiedzi (response)
        if(request.startsWith("GET"))
        {
            //response header
            outp.writeBytes(code);
            if (extension.equals("html"))
            {
                outp.writeBytes("Content-Type: text/html\r\n");
            }
            else
            {
                outp.writeBytes("Content-Type: \r\n");
            }
            outp.writeBytes("Content-Length: " + contentLength + "\r\n");
            outp.writeBytes("\r\n");

            while ((n = file.read(buf)) != -1)
            {
                outp.write(buf);
            }
        }
        else
        {
            outp.writeBytes("HTTP/1.1 501 Not supported.\r\n");
        }

            /*while (inp.ready())
            {
                System.out.println(request);
                request = inp.readLine();
            }*/

        System.out.println(request);

        //zamykanie strumieni
        inp.close();
        outp.close();
        this.clientSocket.close();
    }

    public void run()
    {
        try {
            task();
        }
        catch (IOException e) {return;}
    }
}

public class ServerHTTP
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serv=new ServerSocket(80);

        while(true)
        {
            //przyjecie polaczenia
            System.out.println("Oczekiwanie na polaczenie...");
            Socket sock=serv.accept();

            //strumienie danych
            InputStream is=sock.getInputStream();
            OutputStream os=sock.getOutputStream();
            BufferedReader inp=new BufferedReader(new InputStreamReader(is));
            DataOutputStream outp=new DataOutputStream(os);

            //przyjecie zadania (request)
            String request=inp.readLine();

            if (request == null)
            {
                continue;
            }

            String fileName = request.split(" ")[1];
            System.out.println("file: " + fileName);

            String code = "HTTP/1.0 200 OK\r\n";
            FileInputStream file;

            try {
                file = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\Networking\\HTTP\\" + fileName));
            }
            catch (FileNotFoundException e) {
                file = new FileInputStream(System.getProperty("user.dir") + "\\src\\Networking\\HTTP\\FileNotFound.html");
                code = "HTTP/1.0 404 FileNotFound\r\n";
            }

            byte[] buf;
            buf = new byte[1024];
            int n = 0;

            int contentLength = file.available();

            String extension = Optional.ofNullable(fileName).filter(f -> f.contains(".")).map(f -> f.substring(fileName.lastIndexOf(".") + 1)).get();
            System.out.println("extension: " + extension);

            //wyslanie odpowiedzi (response)
            if(request.startsWith("GET"))
            {
                //response header
                outp.writeBytes(code);
                if (extension.equals("html"))
                {
                    outp.writeBytes("Content-Type: text/html\r\n");
                }
                else
                {
                    outp.writeBytes("Content-Type: \r\n");
                }
                outp.writeBytes("Content-Length: " + contentLength + "\r\n");
                outp.writeBytes("\r\n");

                while ((n = file.read(buf)) != -1)
                {
                    outp.write(buf);
                }
            }
            else
            {
                outp.writeBytes("HTTP/1.1 501 Not supported.\r\n");
            }

            /*while (inp.ready())
            {
                System.out.println(request);
                request = inp.readLine();
            }*/

            System.out.println(request);

            //zamykanie strumieni
            inp.close();
            outp.close();
            sock.close();
        }
    }
}
