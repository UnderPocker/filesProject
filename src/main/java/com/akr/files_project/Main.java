package com.akr.files_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу на ввод:");
        Path inputFilePath = Paths.get(scanner.nextLine());

        System.out.println("Введите путь к файлу на вывод:");
        Path outputFilePath = Paths.get(scanner.nextLine());

        Map<Character, Integer> frequency = new HashMap<>();

        try (InputStream is = Files.newInputStream(inputFilePath); InputStreamReader isr = new InputStreamReader(is)){

            while (isr.ready()){
                char ch = (char) isr.read();

                if (Character.isLetter(ch) && frequency.containsKey(ch))
                    frequency.put(ch, frequency.get(ch) + 1);
                else if (Character.isLetter(ch))
                    frequency.put(ch, 1);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом для ввода");
            return;
        }

        try {
            Files.writeString(outputFilePath, frequency.toString());
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом для вывода");
            return;
        }
    }
}