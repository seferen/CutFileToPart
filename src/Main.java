import java.io.*;
import java.util.ArrayList;

/**
 * Программа предназначена для разделения файлов на несколько частей, количество частей определяется переменной partfile.
 * Данное приложение принимает как атрибуты два параметра. Первый параметр - имя файла для разделения, второй параметр -
 * префикс результирующего файла.
 */







public class Main {
    public static void main(String[] args) {

        int partfile = 5; //количество частей на которые делится файл
        ArrayList<String> list = new ArrayList<String>();

        String fileName = args[0];//имя разбиваемого файла
        String rashir = fileName.contains(".") ? fileName.substring(fileName.indexOf("."), fileName.length())
                : ""; //раширение файла
        String fileprefix = args[1]; //префикс файла

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {

            while (br.ready()){

                list.add(br.readLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Данного файла не существует");
        } catch (IOException e) {
            e.printStackTrace();
        }

       // System.out.println(list.size()); //проверка количества строк




        int step = 0;

        if(list.size() / partfile == 0) {
            partfile = 1;
        }
        else if (list.size() % partfile == 0){
            step = list.size() / partfile;

        }
        else {
            step = list.size()/ partfile;
            partfile++;
        }


        for (int i = 0; i < partfile; i++) {

            int count = i  * step;

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileprefix + i + rashir)))) {
                for (int i1 = count;i1< list.size() && i1 < step + count; i1++) {
                    bw.write(list.get(i1));
                    bw.newLine();
                }
                bw.flush();
            } catch (IOException e) {
                System.out.println("ПРоизошла ошибка во время записи файла" + i);
            }
           // System.out.println(count);


        }
    }
}
