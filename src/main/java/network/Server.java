//package network;
//
//import commands.Save;
//import labStuff.LabCollection;
//import utilities.Decode;
//import utilities.FileReader;
//
//import java.io.*;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.Iterator;
//import java.util.Set;
//
//public class Server {
//    public static void main(String[] args){
//        ServerSocketChannel serverSocketChannel = null;
//        SocketChannel sock = null;
//        String anwser = "";
//        boolean wait = false;
//        try {
//            serverSocketChannel = ServerSocketChannel.open();
//            serverSocketChannel.bind(new InetSocketAddress(9999));
//            System.out.println("Connection established");
//            Selector sel = Selector.open();
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream out = new ObjectOutputStream(bos);
//            serverSocketChannel.configureBlocking(false);
//            SelectionKey key = serverSocketChannel.register(sel, SelectionKey.OP_ACCEPT);
//            LabCollection collection = new LabCollection();
//            Decode.fill(FileReader.readFromFile());
//            System.out.println("Collection filled");
//            while (true){
//
//                try {
//                    sel.select();
//                    Set<SelectionKey> keys = sel.selectedKeys();
//                    for (Iterator<SelectionKey> iter = keys.iterator(); iter.hasNext(); ) {
//                        key = (SelectionKey) iter.next();
//                        iter.remove();
//                        if (key.isValid()) {
//                            if (key.isAcceptable()) {
//                                ServerSocketChannel serv = (ServerSocketChannel) key.channel();
//                                sock = serv.accept();
//                                wait = false;
//                                System.out.println(sock.socket() + " подключен");
//                                sock.configureBlocking(false);
//                                sock.register(key.selector(), SelectionKey.OP_READ);
//                            }
//                            if (key.isReadable()) {
//                                System.out.println(sock.socket() + " readable");
//                                sock = (SocketChannel) key.channel();
//                                ByteBuffer buf = ByteBuffer.allocate(1000);
//                                buf.clear();
//                                sock.read(buf);
//                                buf.flip();
//                                ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
//                                ObjectInputStream ois = new ObjectInputStream(bis);
//                                Request req = ((Request) ois.readObject());
//                                if (!req.getCommand().getName().equals("exit")) {
//                                    anwser = req.getCommand().execute(req.getArgs());
//                                    System.out.println("Executing " + req.getCommand().getName());
//                                    new Save().execute(null);
//                                    sock.configureBlocking(false);
//                                    sock.register(key.selector(), SelectionKey.OP_WRITE);
//                                } else {
//                                    System.out.println(sock.socket() + " отключен");
//                                    sock.close();
//                                }
//                            }
//                            if (key.isWritable()) {
//                                System.out.println(sock.socket() + " writable");
//                                sock = (SocketChannel) key.channel();
//                                out.writeObject(anwser);
//                                out.flush();
//                                ByteBuffer buf = ByteBuffer.wrap(bos.toByteArray());
//                                while (buf.hasRemaining()) {
//                                    assert sock != null;
//                                    sock.write(buf);
//                                }
//                                sock.configureBlocking(false);
//                                sock.register(key.selector(),SelectionKey.OP_READ);
//                            }
//                        }
//                    }
//                }catch(IOException e){
//                    System.out.println(sock.socket() + " dead");
//                    if (!wait)
//                        System.out.println("Клиенту плохо, несите нового");
//                    assert sock != null;
//                    sock.close();
//                    wait = true;
//                }
//            }
////            sel.close();
////            ByteBuffer buf = ByteBuffer.allocate(1000);
////            client.read(buf);
////            buf.flip();
////            System.out.println(Arrays.toString(buf.array()));
////            ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
////            ObjectInputStream ois = new ObjectInputStream(bis);
////            System.out.println((Request) ois.readObject());
//        } catch (IOException | ClassNotFoundException e) {
//
//            e.printStackTrace();
//        }
//
//    }
//}




//оно работает и возвращает команду в дебаге
//public class Server {
//    public static void main(String[] args){
//        ServerSocketChannel serverSocketChannel = null;
//        SocketChannel client = null;
//        try {
//            serverSocketChannel = ServerSocketChannel.open();
//            serverSocketChannel.bind(new InetSocketAddress(9999));
//            serverSocketChannel.configureBlocking(false);
//            client = serverSocketChannel.accept();
//            System.out.println("Connection established");
//            Selector selector = Selector.open();
//            ByteBuffer buf = ByteBuffer.allocate(1000);
//            client.read(buf);
//            buf.flip();
//            System.out.println(Arrays.toString(buf.array()));
//            ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            System.out.println((Request) ois.readObject());
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//}