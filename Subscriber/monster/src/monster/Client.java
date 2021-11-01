package monster;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Client {
    private Socket socket;
    private String address = "127.0.0.1";
    InputStream input;
    OutputStream output;



    public Client(int port) throws NumberFormatException, UnknownHostException, IOException {
        this.socket = new Socket(this.address, port);
        this.output = this.socket.getOutputStream();
        this.input = this.socket.getInputStream();
        this.socket.setSoTimeout(1000);
    }

        /**
     * @param port
     * @return
     * @throws NumberFormatException
     * @throws UnknownHostException
     * @throws IOException
     */
    public int connect(String port) throws NumberFormatException, UnknownHostException, IOException {
        this.socket = new Socket(this.address, Integer.parseInt(port));

        if (socket == null) {
            return -1;
        }

        return 0;
    }

    
    /** 
     * @param n
     * @param n2
     * @param n3
     * @return byte[]
     */
    private static byte[] build_header(final int n, final int n2, final int n3) {
        return new byte[] { (byte)n, (byte)n2, (byte)n3 };
    }
    
    /** 
     * @param array
     */
    public static void print_bits(final byte[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            System.out.println(Integer.toString(array[i], 2));
        }
    }
    
    
    /** 
     * @param s
     * @param s2
     * @return byte[]
     * @throws AssertionError
     */
    public static byte[] generate_subscribe(final String s, final String s2) throws AssertionError {
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        final byte[] bytes2 = s2.getBytes(StandardCharsets.UTF_8);
        final int n = 5 + bytes.length + bytes2.length;

        if (2 + bytes2.length + bytes.length > 255) {
            throw new AssertionError((Object)"Payload superior to 255 bytes");
        }

        final byte[] array = new byte[n];
        final byte[] build_header = build_header(1, 0, 2 + bytes.length + bytes2.length);//changed

        System.arraycopy(build_header, 0, array, 0, build_header.length);
        array[build_header.length] = (byte)bytes.length;

        System.arraycopy(bytes, 0, array, build_header.length + 1, bytes.length);
        array[build_header.length + bytes.length + 1] = (byte)bytes2.length;

        System.arraycopy(bytes2, 0, array, build_header.length + bytes.length + 2, bytes2.length);
        
        return array;
    }

    
    /** 
     * @param s
     * @return byte[]
     * @throws AssertionError
     */
    public static byte[] generate_ack(final String s) throws AssertionError {
        int n = 1;
        byte[] bytes = new byte[0];
        byte[] array;
        if (s == null) {
            array = "OK".getBytes(StandardCharsets.UTF_8);
        }
        else {
            array = "ERROR".getBytes(StandardCharsets.UTF_8);
            bytes = s.getBytes(StandardCharsets.UTF_8);
            n = 2;
        }
        final int n2 = 3 + n + array.length + bytes.length;
        if (n + array.length + bytes.length > 255) {
            throw new AssertionError((Object)"Payload superior to 255 bytes");
        }
        final byte[] array2 = new byte[n2];
        final byte[] build_header = build_header(1, 2, n + array.length + bytes.length);
        System.arraycopy(build_header, 0, array2, 0, build_header.length);
        array2[build_header.length] = (byte)array.length;
        System.arraycopy(array, 0, array2, build_header.length + 1, array.length);
        if (s != null) {
            array2[build_header.length + array.length + 1] = (byte)bytes.length;
            System.arraycopy(bytes, 0, array2, build_header.length + array.length + 2, bytes.length);
        }
        return array2;
    }

    
    /** 
     * @param s
     * @param s2
     * @return byte[]
     * @throws AssertionError
     */
    public static byte[] generate_publish(final String s, final String s2) throws AssertionError {
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        final byte[] bytes2 = s2.getBytes(StandardCharsets.UTF_8);
        final int n = 5 + bytes.length + bytes2.length;
        if (2 + bytes.length + bytes2.length > 255) {
            throw new AssertionError((Object)"Payload superior to 255 bytes");
        }
        final byte[] array = new byte[n];
        final byte[] build_header = build_header(1, 1, 2 + bytes2.length + bytes.length);
        System.arraycopy(build_header, 0, array, 0, build_header.length);
        array[build_header.length] = (byte)bytes.length;
        System.arraycopy(bytes, 0, array, build_header.length + 1, bytes.length);
        array[build_header.length + bytes.length + 1] = (byte)bytes2.length;
        System.arraycopy(bytes2, 0, array, build_header.length + bytes.length + 2, bytes2.length);
        return array;
    }

    
    /** 
     * @param array
     */
    public void test_binary(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Integer.toString(array[i],2));
        }
    }

    
    /** 
     * @param s
     * @param s2
     * @return byte[]
     * @throws AssertionError
     */
    public static byte[] generate_maloform(final String s, final String s2) throws AssertionError {
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        final byte[] bytes2 = s2.getBytes(StandardCharsets.UTF_8);
        final int n = 5 + bytes.length + bytes2.length;

        if (2 + bytes2.length + bytes.length > 255) {
            throw new AssertionError((Object)"Payload superior to 255 bytes");
        }

        final byte[] array = new byte[n];
        final byte[] build_header = build_header(0, 0, 2 + bytes.length + bytes2.length);//changed

        System.arraycopy(build_header, 0, array, 0, build_header.length);
        array[build_header.length] = (byte)bytes.length;

        System.arraycopy(bytes, 0, array, build_header.length + 1, bytes.length);
        array[build_header.length + bytes.length + 1] = (byte)bytes2.length;

        System.arraycopy(bytes2, 0, array, build_header.length + bytes.length + 2, bytes2.length);
        
        return array;
    }

    /**
     * 
     * @param typeOfMsg
     * @param topic
     * @param msg
     * @throws IOException
     * 
     * For send publish use the syntax : send("publish","topic_name","msg")
     * For subscribe : send("subscribe","topic_name","subscribe_identifier")
     * For ack : send("ack",null,null) for send OK ; But any error occured then use send("ack",null,"error_msg")
     */
    public void send(String typeOfMsg,String topic,String msg) throws IOException {
       switch(typeOfMsg.toUpperCase()) {
           case "PUBLISH":
           output.write(generate_publish(topic, msg));
           output.flush();
           break;
           case "SUBSCRIBE":
           output.write(generate_subscribe(msg, topic));
           output.flush();
           break;
           case "ACK":
           output.write(generate_ack(msg));
           output.flush();
           break;
       }

    }

    /**
     * 
     * @return
     * @throws IOException
     * 
     * method for recieve the data from the server
     */
    public List<StringBuilder> recv() throws IOException {
        byte[] byte_data = new byte[1024];
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        Reader reader = new Reader(this.socket);
        reader.setup(true);

        while(true) {
            try {
                byte_data = reader.read_msg();
                byte[] sendable;

                if (byte_data == null) {
                    return list;
                } else  {
                    sendable = byte_data;
                }

                list.add(new StringBuilder(new String(sendable,StandardCharsets.UTF_8)));
                this.output.write(generate_ack(null));
            } catch(SocketTimeoutException e) {
                return list;
            }
        }

    }

}
