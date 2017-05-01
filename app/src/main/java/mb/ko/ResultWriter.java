package mb.ko;

import android.os.Environment;
import android.os.LocaleList;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mbolg on 01.05.2017.
 */
public class ResultWriter {
    private static final String RESULT_FILENAME = "ko_results_test.csv";
    private static final String TITLES = "Номер участника,Время старта,Конец,Время,Результат,Баллы,Прошел/Не прошел";
    private static final String NEW_LINE = "\n\r";

    public static void saveResult(Competitor competitor){
        File testExternalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), RESULT_FILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(testExternalFile, true);
            fileOutputStream.write((competitor.getAllResult() + NEW_LINE).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createResultsFile(){

        File testExternalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), RESULT_FILENAME);

        if(testExternalFile.exists())
            return;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(testExternalFile, true);
            fileOutputStream.write(TITLES.getBytes());
            fileOutputStream.write(NEW_LINE.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
