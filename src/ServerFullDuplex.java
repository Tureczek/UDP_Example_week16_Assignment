import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerFullDuplex {
    public static void main(String[] args) { // DNS Server

        Scanner input = new Scanner(System.in);

        try{
            DatagramSocket dgs = new DatagramSocket(6780);

            byte[] recieveArr = new byte[1000];
            DatagramPacket recievePackage = new DatagramPacket(recieveArr, recieveArr.length);
            // dgs.receive(recievePackage);
            InetAddress clientAddress = recievePackage.getAddress();
            long clientPort = recievePackage.getPort();


            Thread recieveMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try{
//                            byte[] recieveArr = new byte[1000];
                            //DatagramPacket recievePackage = new DatagramPacket(recieveArr, recieveArr.length);
                            dgs.receive(recievePackage);

                            String inMsg = new String(recieveArr, 0, recievePackage.getLength());
                            System.out.println(inMsg);


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });




            Thread sendMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try{
                            String msg = input.nextLine();
                            byte[] outArr = msg.getBytes();
                            DatagramPacket outPackage = new DatagramPacket(outArr, outArr.length, recievePackage.getAddress(), recievePackage.getPort());
                            dgs.send(outPackage);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });



//            byte[] recieveArray = new byte[1000]; //1500 bytes er maks, hvad der kan sendes pr pakke
//
//            DatagramPacket recievePacket = new DatagramPacket(recieveArray, recieveArray.length); //Hvad skal vi modtage, og hvor meget
//            dgs.receive(recievePacket); // modtager data fra client
//            // Recieve() "blokere"

//            System.out.println("Modtaget pakke");
//            String inMsg = new String(recieveArray, 0, recievePacket.getLength());
//            System.out.println(inMsg);

            // Vi sender et svar tilbage
//            String msg = "12.43.221.3"; // Fake Ip adresse
//            byte[] outArr = msg.getBytes();
//            DatagramPacket outPackage = new DatagramPacket(outArr, outArr.length
//                    , recievePacket.getAddress(), recievePacket.getPort());
//            dgs.send(outPackage);



            recieveMessage.start();
            sendMessage.start();


        }catch (Exception e){
            System.out.println( "error " + e.getMessage());
        }

    }
}
