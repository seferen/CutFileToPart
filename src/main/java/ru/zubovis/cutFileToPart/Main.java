package ru.zubovis.cutFileToPart;

import org.apache.log4j.Logger;
import ru.zubovis.cutFileToPart.Utills.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Программа предназначена для разделения файлов на несколько частей, количество частей определяется переменной partfile.
 * Данное приложение принимает как атрибуты два параметра. Первый параметр - имя файла для разделения, второй параметр -
 * префикс результирующего файла.
 */


public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    static long part = Long.parseLong(System.getProperty("countOfPart", "5"));
    static String fileName = System.getProperty("fileName", "test.txt");


    public static void main(String[] args) {


        try {
            final long countString = FileUtils.getCountString(fileName);

            log.info("count of sting: " + countString);

            long countOfStrings = (countString + part) / part;
            log.info(countOfStrings);
            for (int i = 0; i < part; i++) {
                Files.write(Paths.get(fileName + i),
                        Files.lines(Paths.get(fileName)).skip(i * countOfStrings).limit(countOfStrings).collect(Collectors.toCollection(ArrayList::new)));

            }
        } catch (IOException e) {
            log.error(e.getMessage());

        }


//
    }
}
