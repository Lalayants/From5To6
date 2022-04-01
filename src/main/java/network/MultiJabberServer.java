package network;

import exceptions.IdBusyException;
import labStuff.LabCollection;
import labStuff.LabWork;
import utilities.Decode;
import utilities.FileReader;

import java.io.*;

import java.net.*;
import java.nio.channels.Channels;

class ServeOneJabber extends Thread {
    private Socket socket;
    private BufferedReader in;
   // private PrintWriter out;
    private int counter;
    private ObjectInputStream inn; // боюсь переделать in
    private ObjectOutputStream outt;
    private LabCollection collection = new LabCollection();
    private DataOutputStream dout;
   // private ObjectInputStream iinn;


    public ServeOneJabber(Socket s, int c) throws IOException {
        socket = s;
        counter = c;
        dout = new DataOutputStream(socket.getOutputStream());
        inn = new ObjectInputStream(socket.getInputStream());
        outt = new ObjectOutputStream(socket.getOutputStream());


        //iinn = new ObjectInputStream(Channels.newInputStream(socket.getChannel()));

        //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start(); // вызываем run()
    }

    public void run() {
        try {
            while (true) {
                Request request = (Request) inn.readObject();
               // Request request = (Request) iinn.readObject();
                if (!request.getCommand().getName().equals("exit")) {
                    System.out.println("Executing " + request.getCommand().getName());
                    dout.writeUTF(request.getCommand().execute(request.getArgs()));
                    dout.flush();
                } else
                    break;
            }
        } catch (EOFException e){

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("close#" + counter);
            try {
                socket.close();
            } catch (SocketException e){
                System.out.println("close#" + counter);
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}

public class MultiJabberServer {
    static final int PORT = 8080;
    static int counter = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        LabCollection collection = new LabCollection();
        Decode.fill(FileReader.readFromFile());
        collection.show();

        try {
            while (true) {
                Socket socket = s.accept();
                counter+=1;
                try {
                    System.out.println("Connection#" + counter);
                    new ServeOneJabber(socket, counter);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}


//            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
//            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
//
//            String line;
//
//            while(true){
//                line = in.readUTF();
//                System.out.println(line);
//                out.writeUTF(line + " прочитано");
//                out.flush();
//                System.out.println("Обработано");
//
//            }
//
//
//        }catch (Exception e){
//
//        }
