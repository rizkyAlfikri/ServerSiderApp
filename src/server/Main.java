package server;

import client.Address;
import client.Food;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        List<Food> foodList = new ArrayList<>(readFoodFromFile());
        List<Address> addressList = new ArrayList<>(readAddressFromFile());

        ExecutorService executorFood = Executors.newFixedThreadPool(1);
        ExecutorService executorAddress = Executors.newFixedThreadPool(1);


        ThreadReadFood threadReadFood = new ThreadReadFood();
        ThreadReadAddress threadReadAddress = new ThreadReadAddress();
        executorFood.execute(threadReadFood);
        executorAddress.execute(threadReadAddress);

        if (threadReadAddress.isWriteStatusAddress() && threadReadFood.isWriteStatusFood()) {
            for (Food food : foodList) {
                System.out.println("Read " + food.getNameFood());
            }

            for (Address address : addressList) {
                System.out.println("Read " + address.getAddress());
            }
            executorFood.shutdown();
            executorAddress.shutdown();
        }

    }

    private static List<Food> readFoodFromFile() {
        List<Food> foodList = new ArrayList<Food>();

        try {
            FileReader fr = new FileReader("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Food.txt");
            int byteStream;
            StringBuilder dataBuffer = new StringBuilder();
            while ((byteStream = fr.read()) != -1) {
                dataBuffer.append((char) byteStream);
            }

            List<String> arrayBuffer = new ArrayList<String>(Arrays.asList(dataBuffer.toString().split(",")));
            for (String str : arrayBuffer) {
                foodList.add(new Food(str));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodList;
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
            e.printStackTrace();
        }
        return addressList;
    }
}
