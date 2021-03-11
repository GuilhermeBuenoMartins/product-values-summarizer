package handles;

import entities.Product;
import entities.Purchase;
import interfaces.Message;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String OUTPUT_DIRECTORY = "\\out\\";

    private static final String OUTPUT_FILE = "summarized.csv";

    public static String[] readFile(String path) {
        String lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            lines = reader.readLine().concat("\n");
            String line = reader.readLine();
            while (line != null) {
                lines = lines.concat(line).concat("\n");
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println(Message.ERROR_TO_READ_FILE.concat(exception.getMessage()));
            return null;
        }
        System.out.println(Message.SUCCESS_IN_READ_FILE);
        return lines.split("\n");
    }

    public static boolean writeFile(String path, String[] lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.concat(OUTPUT_FILE)))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println(Message.ERROR_TO_WRITE_FILE.concat(exception.getMessage()));
            return false;
        }
        System.out.println(Message.SUCESS_IN_WRITE_FILE);
        return true;
    }

    public static String createDirectory(File file) {
        String path = file.getParent().concat(OUTPUT_DIRECTORY);
        file = new File(path);
        if (file.mkdir()) {
            System.out.println(Message.SUCESS_IN_CREATION_DIRECTORY);
        } else {
            System.out.println(Message.ERROR_TO_CREATE_DIRECTORY);
            return null;
        }
        return path;
    }

    public static List<Purchase> processFile(String[] lines) {
        List<Purchase> purchases = new ArrayList<>();
        for (String line : lines) {
            String[] contentLine = line.split(",");
            Product product = new Product(contentLine[0], Double.parseDouble(contentLine[1]));
            Purchase purchase = new Purchase(product, Integer.parseInt(contentLine[2].trim()));
            purchases.add(purchase);
        }
        return purchases;
    }

    public static String[] processFile(List<Purchase> purchases) {
        StringBuilder lines = new StringBuilder();
        for (Purchase purchase : purchases) {
            lines.append(String.format("%s, %.2f \n", purchase.getProduct().getName(), purchase.getSum()));
        }
        return lines.toString().split("\n");
    }
}
