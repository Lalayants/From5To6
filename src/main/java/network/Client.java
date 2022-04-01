package network;

import labStuff.LabWork;
import utilities.ConsoleIO;
import utilities.LabWorkCreator;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client app for Lab6 v751300 by Kirill Lalayants R3137 2022 \nApp Started");
            SocketChannel channel = SocketChannel.open();
            channel.connect(new InetSocketAddress(9999));
            System.out.println("Connected to server");
            System.out.println("Приложение готово к работе, введите команду, для справки введите help.");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            while (channel.isConnected()) {
                try {
                    System.out.print(">");
                    String[] raw = ConsoleIO.ConsoleIn().trim().split(" ");
                    if (raw.length > 1) {
                        if (raw[0].equals("execute_script")) {
                            out.writeObject(new Request(raw[0], raw[1]));
                            out.flush();
                            ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
                            while (buf.hasRemaining())
                                channel.write(buf);
                        } else if (raw[0].equals("remove_by_id") || raw[0].equals("count_less_than_minimal_point")) {
                            out.writeObject(new Request(raw[0], raw[1]));
                            out.flush();
                            ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
                            while (buf.hasRemaining())
                                channel.write(buf);
                        } else {
                            if (raw[0].equals("update")) {
                                int id = Integer.parseInt(raw[1]);
                                LabWork lab = new LabWorkCreator().create();
                                lab.setIdBlindly(id);


                                // ooss.writeObject(new Request(raw[0], lab));
                                out.writeObject(new Request(raw[0], lab));
                                out.flush();
                                ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
                                while (buf.hasRemaining())
                                    channel.write(buf);
                            } else {
                                System.out.println("В команде ошибка, проверьте написание, возможно лишние аргументы");
                                throw new Exception();
                            }
                        }
                    } else {
                        if (raw[0].equals("add") || raw[0].equals("add_if_min") || raw[0].equals("remove_lower")) {
                            LabWork a = new LabWorkCreator().create();
                            out.writeObject(new Request(raw[0], a));
                            out.flush();
                            ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
                            while (buf.hasRemaining())
                                channel.write(buf);
                        } else if (raw[0].equals("help") || raw[0].equals("info") || raw[0].equals("show") || raw[0].equals("clear") || raw[0].equals("remove_first") || raw[0].equals("print_descending") || raw[0].equals("print_unique_minimal_point")) {
                            out.writeObject(new Request(raw[0], null));
                            out.flush();
                            ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
                            while (buf.hasRemaining())
                                channel.write(buf);
                        } else if (raw[0].equals("exit")) {
                            System.out.println("Пока");
                            System.exit(0);
                        } else {
                            System.out.println("В команде ошибка, проверьте написание, возможно лишние аргументы");
                            throw new Exception();
                        }
                    }

                    ByteBuffer buf = ByteBuffer.allocate(1000);
                    channel.read(buf);
                    buf.flip();
                    ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    System.out.println((String) ois.readObject());

                } catch (NumberFormatException e) {
                    System.out.println("Аргументом должно быть целое число");
                } catch (Exception e) {
                }
            }
        }catch (ConnectException e){
            System.out.println("Запустите сначала серверное приложение, а потом клиент");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


//оно работает и возвращает команду в дебаге
//public class Client {
//    public static void main(String[] args){
//        try {
//            SocketChannel channel = SocketChannel.open();
//            channel.connect(new InetSocketAddress(9999));
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream out = new ObjectOutputStream(bos);
//            out.writeObject(new Request("add", "1"));
//            out.flush();
//            ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
//            while(buf.hasRemaining())
//                channel.write(buf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

//        out.writeObject(new Request("add", "1"));
//        out.flush();
//        ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
//        while (buf.hasRemaining())
//        channel.write(buf);

//  out.writeObject(new Request("add", "1"));
//          out.flush();
//          ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
//          while (buf.hasRemaining())
//          channel.write(buf);