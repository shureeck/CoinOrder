import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Poliakov.A on 12/11/2017.
 */
public class Loger {
    public static void setLog(String msg){
        //Creating date-time formats
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("y-M-d");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        String logName = "bank.gov.ua_"+formatDate.format(date)+".log";
        File log = new File(logName);

        //Create new file if not exist
        try {
            if (!log.exists()) {
                log.createNewFile();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Write data in .log file
        BufferedWriter writer=null;
        try {
            writer =new BufferedWriter(new FileWriter(log, true));
            writer.write(formatTime.format(date)+": "+msg+'\n');
        }//try
        catch (IOException e){
            e.printStackTrace();
        }//catch
        finally {
            try {
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }//finally
    }

    public static void setReport (StringBuffer msg){

        String logName = "Order.csv";
        File log = new File(logName);
        BufferedWriter writer=null;


        //Create new file if not exist
        try {
            if (!log.exists()) {
                log.createNewFile();

                writer= new BufferedWriter(new OutputStreamWriter( new FileOutputStream (log),"UTF-8"));
                writer.write("Логин"+"\t"+"Пароль"+"\t"+"ФИО"+"\t"+"Дата выхода в оборот"+"\t"+"Название монеты"+"\n");
                writer.close();
                System.out.println("Успешно: Файл отчета "+logName+" создан");
                Loger.setLog("Успешно: Файл отчета "+logName+" создан");
            }
        }
        catch (IOException e){
            System.out.println("Ошибка: Не удалось создать файл "+logName);
            Loger.setLog("Ошибка: Не удалось создать файл "+logName+"\n"+e.toString());
            e.printStackTrace();
        }

        //Write data in .log file

        try {
            writer =new BufferedWriter(new OutputStreamWriter( new FileOutputStream (log, true),"UTF-8"));
            writer.write(msg+"\n");
        }//try
        catch (IOException e){
            System.out.println("Ошибка: Не удалось записать данные по монете файл "+logName);
            Loger.setLog("Ошибка: Не удалось записать данные по монете файл "+logName+"\n"+e.toString());
            e.printStackTrace();
        }//catch
        finally {
            try {
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }//finally
    }
}
