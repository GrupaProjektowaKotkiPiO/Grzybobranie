package app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFile {

    //main class method -> loads basketCords from file
    public int[][] load() throws FileNotFoundException {
        int[][] basketCords = new int[37][2];

        Scanner reader = new Scanner(new File("src/main/resources/app/controller/txt/basketCords.txt"));

        for(int i = 0; reader.hasNextLine(); i++) {
            basketCords[i][0] = reader.nextInt();
            basketCords[i][1] = reader.nextInt();
        }

        return basketCords;
    }
}
