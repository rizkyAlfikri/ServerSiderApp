package client;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Food food;
    private static Address address;

    public static void main(String[] args) {



        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int i = 0;
        ThreadFood threadFood;
        ThreadAddrress threadAddrress;

        while (i < 100) {
            addFoodData();
            addAddressData();
            threadFood = new ThreadFood(food);
            threadAddrress = new ThreadAddrress(address);

            executorService.execute(threadFood);

            executorService.execute(threadAddrress);

            i++;

        }

        executorService.shutdown();


    }


    private static void addFoodData() {

        List<Food> foods = new ArrayList<Food>();
        foods.add(new Food("Nasgor"));
        foods.add(new Food("Batagor"));
        foods.add(new Food("Cireng"));
        foods.add(new Food("Cendol"));
        foods.add(new Food("Cingcau"));
        foods.add(new Food("Goyobot"));
        foods.add(new Food("Awug"));
        foods.add(new Food("Lemper"));
        foods.add(new Food("Pecel"));
        foods.add(new Food("Nasgor"));

        SecureRandom random = new SecureRandom();
        int randomIndex = random.nextInt(foods.size());
        food = new Food(foods.get(randomIndex).getNameFood());

    }

    private static void addAddressData() {
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address("Bandung"));
        addresses.add(new Address("Jakarta"));
        addresses.add(new Address("Cirebon"));
        addresses.add(new Address("Purwakarta"));
        addresses.add(new Address("Bogor"));
        addresses.add(new Address("Depok"));
        addresses.add(new Address("Solo"));
        addresses.add(new Address("Garut"));
        addresses.add(new Address("Sumedang"));
        addresses.add(new Address("Tangerang"));

        SecureRandom random = new SecureRandom();
        int randomIndex = random.nextInt(addresses.size());
        address = new Address(addresses.get(randomIndex).getAddress());

    }
}
