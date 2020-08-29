package ru.zubovis.cutFileToPart;

import org.apache.log4j.Logger;
import ru.zubovis.cutFileToPart.Utills.FileUtils;


/**
 * Программа предназначена для разделения файлов на несколько частей, количество частей определяется переменной partfile.
 * Данное приложение принимает как атрибуты два параметра. Первый параметр - имя файла для разделения, второй параметр -
 * префикс результирующего файла.
 */


public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Application was started");

        FileUtils fileUtils = new FileUtils()
                .setFileName("test.txt")
                .setNumberOfParts(3)
                .splitFile();
        log.info("Application was finished");


    }
}
