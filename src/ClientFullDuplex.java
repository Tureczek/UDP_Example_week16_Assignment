import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientFullDuplex {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try{

            DatagramSocket dgs = new DatagramSocket();
//            String msg = "Hvad er din \"Fake IP\"?"; //Dette skal laves om til et array, da Server kun modtager Array
//            byte[] outArray = msg.getBytes();
            //Her skal vi bruge en ip adresse
//            InetAddress address = InetAddress.getByName("localhost");
//            DatagramPacket outPacket = new DatagramPacket(outArray, outArray.length
//                    , address, 6780);
//            dgs.send(outPacket);


            Thread sendMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String msg = input.nextLine();
                        byte[] sendArray = msg.getBytes();


                        try {
                            InetAddress address = InetAddress.getByName("localhost");
                            DatagramPacket outPacket = new DatagramPacket(sendArray, sendArray.length
                                    , address, 6780);
                            dgs.send(outPacket);

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            });

            // Modtage fra serer:
            Thread recieveMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try{
                            byte[] inArr = new byte[1000];
                            DatagramPacket inPackage = new DatagramPacket(inArr, inArr.length);

                            dgs.receive(inPackage);
                            String inMsg = new String(inArr, 0, inPackage.getLength());
                            System.out.println(inMsg);


                        }catch (Exception e){
                            e.getMessage();
                        }



                    }
                }
            });

            sendMessage.start();
            recieveMessage.start();








            // Modtage fra serer:
//            byte[] inArr = new byte[1000];
//            DatagramPacket inPackage = new DatagramPacket(inArr, inArr.length);

//            dgs.receive(inPackage);
//            String inMsg = new String(inArr, 0, inPackage.getLength());
//            System.out.println(inMsg);

        }catch (Exception e){
            e.getMessage();
        }
    }
}
