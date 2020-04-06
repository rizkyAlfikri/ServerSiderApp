package client;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadFood implements Runnable {
    private Food food;

    public ThreadFood(Food food) {
        this.food = food;
    }


    @Override
    public void run() {
        saveFoodToFile(food);
        System.out.println("Write Food " + food.getNameFood());

    }

    private static void saveFoodToFile(Food foods) {
        List<Food> foodList = new ArrayList<>(readFoodFromFile());
        foodList.add(foods);

        try {
            FileWriter fw = new FileWriter("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Food.txt");

            for (Food food : foodList) {
                String dataSave = food.getNameFood() + ",";
                fw.write(dataSave);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed Add Order Data");
        }

        System.out.println("Write Food " + foods.getNameFood());
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


        } catch (
                IOException e) {
            System.out.println("Failed Read Order Data");
        }
        return foodList;
    }
}

