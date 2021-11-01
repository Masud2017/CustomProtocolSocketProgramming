package monster;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subscriber {

    
    /** 
     * @param arg
     * @throws IOException
     */
    public static void main(String[] arg) throws IOException {
        String port = arg[0];
        Client client = new Client(Integer.parseInt(port));
        List<StringBuilder> list;
        Display disp = new Display();
        

        while (true) {
            client.send("subscribe","victory","victory"); // subscribing to the victory topic with victory identifier
            client.send("subscribe","position","victory"); //subscribing to the publish topic wtih the victroy identifier
            list = client.recv();


            while (true) {
                int signal = disp.play(list);
                
        
                list.clear();
                String guess;
                if (signal == 2021) {
                    System.out.println("/!\\YOU GOT IT/!\\");
                    Runtime.getRuntime().exit(10);
                    
                } else {
                    Scanner sc = new Scanner(System.in);
                    
                    System.out.println("Take a guess : ");
                    guess = sc.nextLine();
                    client.send("publish","guess",guess); // sending the player guess to the server
                    // sc.close();
                    list = client.recv(); // the answer back from server
                }
                
                
            }
        }

    }
}
