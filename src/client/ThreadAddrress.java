package client;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadAddrress implements Runnable {
    private Address address;


    public ThreadAddrress(Address address) {
        this.address = address;
    }


    @Override
    public void run() {
        saveAddressToFile(address);
        System.out.println("Write Address " + address.getAddress());


    }

    private static void saveAddressToFile(Address addresses) {
        try {
            List<Address> addressList = new ArrayList<>(readAddressFromFile());
            addressList.add(addresses);
            FileWriter fw = new FileWriter("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Address.txt");

            for (Address address : addressList) {
                String dataSave = address.getAddress() + ",";
                fw.write(dataSave);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed Add Order Data");
        }

    }

    private static List<Address> readAddressFromFile() {
        List<Address> addressList = new ArrayList<Address>();


        try {
            FileReader fr = new FileReader("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Address.txt");
            int byteStream;
            StringBuilder dataBuffer = new StringBuilder();
            while ((byteStream = fr.read()) != -1) {
                dataBuffer.append((char) byteStream);
            }

            List<String> arrayBuffer = new ArrayList<String>(Arrays.asList(dataBuffer.toString().split(",")));
            for (String str : arrayBuffer) {
                addressList.add(new Address(str));
            }


        } catch (
                IOException e) {
            System.out.println("Failed Read Order Data");
        }
        return addressList;
    }
}

