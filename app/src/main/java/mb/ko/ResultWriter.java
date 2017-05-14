package mb.ko;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.LocaleList;
import android.support.v4.content.ContextCompat;
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
    private static final String TITLES = "ID устройства,Имя,Фамилия,День рождения,Телефон,Тип этапа,Номер этапа,Номер участника,Время,Результат,Баллы,Прошел/Не прошел,Количество попыток";
    private static final String NEW_LINE = "\r\n";

    public static void saveResult(Context context, Competitor competitor, Stage stage) {


        File testExternalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), RESULT_FILENAME);


        if (!testExternalFile.exists())
            createResultsFile();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(testExternalFile, true);

            fileOutputStream.write(getJudgeInfo(context).getBytes());
            fileOutputStream.write(",".getBytes());
            fileOutputStream.write(stage.getInfo(context).getBytes());
            fileOutputStream.write(",".getBytes());
            fileOutputStream.write((competitor.getAllResult() + NEW_LINE).getBytes());

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getJudgeInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String res = "";
        res += sharedPreferences.getString("id", "-");
        res += ",";
        res += sharedPreferences.getString("name", "-");
        res += ",";
        res += sharedPreferences.getString("surname", "-");
        res += ",";
        res += sharedPreferences.getString("birthday", "-");
        res += ",";
        res += sharedPreferences.getString("phone", "-");
        return res;
    }


    private static void createResultsFile() {

        File testExternalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), RESULT_FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(testExternalFile, true);
            fileOutputStream.write((TITLES + NEW_LINE).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
