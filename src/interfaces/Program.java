package interfaces;

import entities.Product;
import entities.Purchase;
import handles.FileHandler;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Program {


    public void executeProgram() {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message.PATH_SOLICITATION);
        String path = scanner.nextLine();
        File file = new File(path);

        List<Purchase> purchases = FileHandler.processFile(Objects.requireNonNull(FileHandler.readFile(path)));
        FileHandler.writeFile(FileHandler.createDirectory(file), FileHandler.processFile(purchases));
    }
}
