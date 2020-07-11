package com.italo.waiter.utils;

import com.italo.waiter.model.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CSVParser {
    public static List<Product> parseProducts(){
        List<Product> products = new ArrayList<>();
        try {
            File f = new File("seed-products.csv");
            Scanner sc = new Scanner(new FileInputStream(f));
            sc.nextLine();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] split = line.split(",");
                Product p = new Product();
                p.setCode(split[0]);
                p.setDescription(split[1]);
                p.setProductionCost(Double.valueOf(split[2]));
                p.setSaleCost(Double.valueOf(split[3]));
                products.add(p);
            }
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
        return products;
    }
}
