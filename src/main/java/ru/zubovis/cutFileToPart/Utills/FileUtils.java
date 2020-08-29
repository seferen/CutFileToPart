package ru.zubovis.cutFileToPart.Utills;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileUtils {
    private static Logger log = Logger.getLogger(FileUtils.class);

    private String fileName;
    private long numberOfString;
    private long numberOfParts;
    private long numberOfStringInPart;

    public FileUtils() {
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * Функция устанавливает значение имени файла, а так же полностью обнуляет значения numberOfString и
     * numberOfStringInPart для возможности повтороного переиспользования функции при последовательной работе с
     * несколькими файлами
     *
     * @param fileName принимает наименование файла с которым необходимо работать.
     * @return
     */
    public FileUtils setFileName(String fileName) {

        this.fileName = fileName;
        log.debug(String.format("Set name of File: %s", this.fileName));
        this.numberOfString = 0;
        log.debug((String.format("Set number of string: %d", this.numberOfString)));
        this.numberOfStringInPart = 0;
        log.debug(String.format("Set number of string in part of file %s: %d", this.fileName, this.numberOfStringInPart));
        return this;
    }

    /**
     * Устанавливает количество необходимых для чтения строк из файла,
     * если количество строк в файле меньше чем то бросает исключение
     * @param numberOfString
     * @return
     */
    public FileUtils setNumberOfString(long numberOfString) {

        if (this.numberOfString == 0 && numberOfString > readNumberOfString().getNumberOfString())
            throw new IllegalArgumentException(String.format("In File: %s less lines then %d", fileName, numberOfString));


        this.numberOfString = numberOfString;
        return this;
    }

    public long getNumberOfString() {
        return numberOfString;
    }

    public long getNumberOfParts() {
        return numberOfParts;
    }

    public FileUtils setNumberOfParts(long numberOfParts) {
        this.numberOfParts = numberOfParts;
        log.info("number of parts: " + this.numberOfParts);
        if (numberOfString == 0) readNumberOfString().getNumberOfString();
        this.numberOfStringInPart = (numberOfString + numberOfParts) / numberOfParts;
        log.info("number of string in part: " + numberOfStringInPart);
        return this;
    }

    public long getNumberOfStringInPart() {
        return numberOfStringInPart;
    }


    /**
     * Функция делает расчет количества строк в файле через Streams и устанавливает данное значение в переменной для
     * дальнейшей работы.
     *
     * @return возвращает количество строк в файле
     */
    private FileUtils readNumberOfString() {
        log.info("File: " + fileName + ", a counting was started.");
        try {
            numberOfString = Files.lines(Paths.get(fileName)).count();
            log.debug("File: " + fileName + " was read: " + numberOfString + " lines");
        } catch (FileNotFoundException e) {
            log.error("File: " + fileName + "was not found. Counting was finished with error");
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("File: " + fileName + ", a counting was finished success. Read: " + numberOfString + " lines");

        return this;
    }

    /**
     * Функция делит файл на количество частей установленных в классе в переменной numberOfParts
     *
     * @return возсращает класс для последующей с ним работы
     */

    public FileUtils splitFile() {
        try {
            log.info("File: " + fileName + " start spliting");

            for (int i = 0; i < numberOfParts; i++) {

                log.info("File: ");
                Files.write(Paths.get(fileName + i),
                        Files.lines(Paths.get(fileName))
                                .skip(i * numberOfStringInPart)
                                .limit(numberOfStringInPart)
                                .collect(
                                        Collectors.toCollection(ArrayList::new))
                );

            }
        } catch (IOException e) {
            log.error("File: " + fileName + ". Spliting was finished with error");
            return this;
        }
        log.info("File: " + fileName + ". Spliting was finished succcess");
        return this;
    }
}
