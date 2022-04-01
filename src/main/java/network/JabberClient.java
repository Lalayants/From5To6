package network;

import exceptions.IdBusyException;
import labStuff.LabCollection;
import labStuff.LabWork;
import utilities.ConsoleIO;
import utilities.LabWorkCreator;

import javax.security.auth.callback.LanguageCallback;
import java.net.*;

import java.io.*;
import java.nio.channels.Channels;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.Vector;

public class JabberClient {
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        Socket socket = new Socket(addr, MultiJabberServer.PORT);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Client app for Lab6 v751300 by Kirill Lalayants R3137 2022 \nApp Started");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); //kill it
            InputStream inFromServer = socket.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            socket.getChannel();
            System.out.println("Connected to server");
            System.out.println("Приложение готово к работе, введите команду, для справки введите help.");

          //  ObjectOutputStream ooss = new ObjectOutputStream(Channels.newOutputStream(socket.getChannel()));

            while (socket.isConnected()) {
                try {
                    System.out.print(">");
                    String[] raw = ConsoleIO.ConsoleIn().trim().split(" ");
                    if (raw.length > 1) {
                        if (raw[0].equals("execute_script")) {
                            oos.writeObject(new Request(raw[0], raw[1]));
                            oos.flush();
                        } else if (raw[0].equals("remove_by_id") || raw[0].equals("count_less_than_minimal_point")) {
                            oos.writeObject(new Request(raw[0], raw[1]));
                            oos.flush();
                        } else {
                            if (raw[0].equals("update")) {
                                int id = Integer.parseInt(raw[1]);
                                LabWork lab = new LabWorkCreator().create();
                                lab.setIdBlindly(id);


                                // ooss.writeObject(new Request(raw[0], lab));
                                oos.writeObject(new Request(raw[0], lab));
                                oos.flush();
                            } else {
                                System.out.println("В команде ошибка, проверьте написание, возможно лишние аргументы");
                                throw new Exception();
                            }
                        }
                    } else {
                        if (raw[0].equals("add") || raw[0].equals("add_if_min") || raw[0].equals("remove_lower")) {
                            LabWork a = new LabWorkCreator().create();
                            oos.writeObject(new Request(raw[0], a));
                            oos.flush();
                        } else if (raw[0].equals("help") || raw[0].equals("info") || raw[0].equals("show") || raw[0].equals("clear") || raw[0].equals("remove_first") || raw[0].equals("print_descending") || raw[0].equals("print_unique_minimal_point")) {
                            oos.writeObject(new Request(raw[0], null));
                            oos.flush();
                        } else if (raw[0].equals("exit")) {
                            System.out.println("Пока");
                            System.exit(0);
                        } else {
                            System.out.println("В команде ошибка, проверьте написание, возможно лишние аргументы");
                            throw new Exception();
                        }
                    }
                    System.out.println(in.readUTF());
                } catch (NumberFormatException e) {
                    System.out.println("Аргументом должно быть целое число");
                }
                catch (Exception e)
                {
                }
            }


        } catch (EOFException e){
            System.out.println("Вы напугали сервер, он сломался");
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
// Вывод автоматически Output быталкивается PrintWriter'ом.
//PrintWriter out = new PrintWriter(new BufferedWriter(
//new OutputStreamWriter(socket.getOutputStream())), true);
//PrintWriter out = new PrintWriter(new ObjectOutputStream(socket.getOutputStream()), true);
//ByteArrayOutputStream baos = new ByteArrayOutputStream(socket.getOutputStream());
// LabWork a = new LabWorkCreator().create();
//String raw = in.readLine();
//LabCollection collection = (LabCollection) in.readObject();
//                collection.showt();