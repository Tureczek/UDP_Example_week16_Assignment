import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try{

            DatagramSocket dgs = new DatagramSocket();
            String msg = input.nextLine(); //Dette skal laves om til et array, da Server kun modtager Array
            byte[] outArray = msg.getBytes();
            //Her skal vi bruge en ip adresse
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket outPacket = new DatagramPacket(outArray, outArray.length
                    , address, 6780);
            dgs.send(outPacket);




            // Modtage fra serer:
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
