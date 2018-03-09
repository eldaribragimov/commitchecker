import javax.print.attribute.standard.MediaSize;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

public class FileReader {

    public ArrayList<Files> readlink(String text) throws FileNotFoundException {
        try {
            RandomAccessFile r = new RandomAccessFile(text,"r");
            ArrayList<Files> b = new ArrayList<>();
            Long i=0L;
            int c=0;
            while (c!=-1) {
                String s = r.readLine();
                if (s!=null) {
                    Files x = new Files();
                    String[] a;
                    a = s.split(" ");
                    x.setName(a[0]);
                    x.setLink(a[1]);
                    b.add(x);
                }
                else {
                    c=-1;
                }
            }
            return b;
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Files> readdead(String text) throws FileNotFoundException {
        try {
            RandomAccessFile r = new RandomAccessFile(text,"r");
            ArrayList<Files> b = new ArrayList();
            Long i=0L;
            int c=0;
            while (c!=-1) {
                String s = r.readLine();
                if (s!=null) {
                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    Files x = new Files();
                    String[] a;
                    a = s.split(" ",2);
                    Date d = df.parse(a[1]);
                    long milliseconds = d.getTime();
                    x.setName(a[0]);
                    x.setDate(milliseconds);
                    b.add(x);
                }
                else {
                    c=-1;
                }
            }
            return b;
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileReader a = new FileReader();
        a.readlink("text");
    }
}
