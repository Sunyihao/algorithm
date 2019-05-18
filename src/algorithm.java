import java.io.*;
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
    static int[][] FindMaxStrngArray(String[] Str1,String[] Str2){
        int[][] b = new int[Str1.length][Str2.length];
        int[][] c = new int[Str1.length][Str2.length];
        for(int i=1; i<Str1.length; i++)
        {
            for(int j=1; j<Str2.length; j++)
            {
                //对应第一个性质
                if( Str1[i] == Str2[j])
                {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 1;
                }
                //对应第二或者第三个性质
                else if(c[i-1][j] >= c[i][j-1])
                {
                    c[i][j] = c[i-1][j];
                    b[i][j] = 0;
                }
                //对应第二或者第三个性质
                else
                {
                    c[i][j] = c[i][j-1];
                    b[i][j] = -1;
                }
            }
        }
        return b;
    }
    public static void Display(int[][] b, String[] x, int i, int j)
    {
        if(i == 0 || j == 0)
            return;
        if(b[i][j] == 1)
        {
            Display(b, x, i-1, j-1);
            System.out.print(x[i] + " ");
        }
        else if(b[i][j] == 0)
        {
            Display(b, x, i-1, j);
        }
        else if(b[i][j] == -1)
        {
            Display(b, x, i, j-1);
        }
    }
    public static void main(String[] args) throws IOException {
        for(int i = 0;i < 2;i++){
            create_String(22);
        }
    }
}
