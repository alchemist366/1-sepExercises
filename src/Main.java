
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;


public class Main {


    public static void main(String[] args) {
        String from = "Твоя жизнь в твоих руках!";
        StringReader str = new StringReader(from);
        String newString = "";
        try {
            for (int i = 0; i < from.length(); i++) {
                newString +=(char) str.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newString);
    }


}
