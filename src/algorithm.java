import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class algorithm {
    static String ReadPath = "D://date/input.txt";
    static String ReadPath3 = "D://date/input_3.txt";
    static String ReadPath2 = "D://date/input_2.txt";
    static String WirtePath = "D://date/out.txt";
    static void create_String(int length) throws IOException {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        System.out.println(val);

        FileWriter fw = new FileWriter(ReadPath2,true);
        fw.write(val);
        fw.write("\r\n");
        fw.flush();
        fw.close();

    }

    public static void main(String[] args) throws IOException {
        for(int i = 0;i < 2;i++){
            create_String(22);
        }
    }
}
