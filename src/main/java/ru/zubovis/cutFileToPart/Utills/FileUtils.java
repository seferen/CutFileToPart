package ru.zubovis.cutFileToPart.Utills;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    private static Logger log = Logger.getLogger(FileUtils.class);

    /**
     * Функция делает расчет количества строк в файле через Streams
     *
     * @param fileName наименование файла который необходимо будет разделить на несколько
     *                 частей
     * @return возвращает количество строк в файле
     * @throws IOException
     */
    public static long getCountString(String fileName) throws IOException {

        long result = Files.lines(Paths.get(fileName)).count();
        log.debug("File " + fileName + " was read " + result + " lines");
        return result;
    }
}
