import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import static java.lang.reflect.Array.getLength;


public class algorithm {
    static String ReadPath = "D://date/input.txt";
    static String ReadPath3 = "D://date/input_3.txt";
    static String ReadPath2 = "D://date/input_2.txt";
    static String WirtePath = "D://date/out.txt";
    static int num = 0;
    private static final int MAX = Integer.MAX_VALUE;
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
        for(int i = 0;i < Str1.length;i++){
            c[i][0] = 0;
        }
        for(int i = 0;i<Str2.length;i++){
            c[0][i] = 0;
        }
        for(int i=1; i<Str1.length; i++)
        {
            for(int j=1; j<Str2.length; j++)
            {
                if( Str1[i] == Str2[j])
                {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 1;
                }
                else if(c[i-1][j] >= c[i][j-1])
                {
                    c[i][j] = c[i-1][j];
                    b[i][j] = 0;
                }
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
            num++;
            System.out.println(x[i]+" ");
        }
        else if(b[i][j] == 0)
        {
            Display(b, x, i-1, j);
        }
        else
        {
            Display(b, x, i, j-1);
        }
    }
    static public int[][] FindMaxArray(int[] p){
        int n = p.length-1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for(int i = 1;i<n;i++){
            m[i][i] = 0;
        }
        for(int l=2;l<=n;l++){
            for(int i =1;i < n-l+1;i++){
                int j = i+l-1;
                m[i][j] = m[i+1][j]+p[i-1]*p[j]*p[i];
                s[i][j] = i;
                for(int k = i;k<j-1;k++){
                    //p[i-1]*p[k]*p[j];此处有错误
                    int q = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
                    if(q<m[i][j]){
                        m[i][j]=q;
                        s[i][j]=k;
                    }
                }
            }
        }
        return s;
    }
    static class th{
        int weight;
        int value;
        int no;
    }
    static void Pack(){
        System.out.println("输入物品种数");
        Scanner in = new Scanner(System.in);
        int type_no = in.nextInt();
        th[] t = new th[type_no];
        for(int i = 0;i < type_no;i++){
            t[i].no = t[i].value = t[i].weight = 9999;
        }
        System.out.println("请输入物品重量和价值");
        for(int i = 0;i < type_no;i++){
            t[i].weight = in.nextInt();
            t[i].value = in.nextInt();
            t[i].no = i + 1;
        }
        System.out.println("输入背包容量");
        int Pack_Capacity = in.nextInt();
        Arrays.sort(t,0,type_no);
        int ch;
        int[] br = new int[100];
        int[] wr = new int[100];
        int brr = 0;
        int wrr = 0;
        int Pack_Max_value = 0;
        for(ch = 0;ch < type_no;ch++){
            if(t[ch].weight > Pack_Capacity){
                break;
            }
            br[brr++] = t[ch].no;
            wr[wrr++] = t[ch].value;
            Pack_Max_value = Pack_Max_value + t[ch].value;
            Pack_Capacity = Pack_Capacity - t[ch].weight;

            if(ch < type_no){
                br[brr++] = t[ch].no;
                wr[wrr++] = Pack_Capacity;
                Pack_Max_value = t[ch].value/t[ch].weight*Pack_Capacity;
                Pack_Capacity = 0;
            }
        }
    }
    public static class act{
        int no = 0;
        int start = 0;
        int end = 0;
    }
    static boolean cmp(act a,act b){
        return a.end<= b.end;
    }
    static void Activity_A(){
        int[] r = new int[100];
        int rr = 0;
        Scanner in=new Scanner(System.in);
        System.out.println("请输入活动数");
        int a;
        a = in.nextInt();
        act[] ac = new act[a];
        for(int i = 0;i<a;i++){
            ac[i] = new act();
            ac[i].start = in.nextInt();
            ac[i].end = in.nextInt();
            ac[i].no = i + 1;

        }
//        Arrays.sort(ac,1,a-1);
        r[rr++] = ac[0].no;
        int k = 0;
        for(int i = 0;i<a;i++){
            if(ac[i].start>=ac[k].end){
                r[rr++] = ac[i].no;
                k = i;
            }
        }

        for(int i = 0;i<a;i++){
            System.out.println(r[i]);
        }
    }
    static void show(int[][] s,int start,int end){
        if(start == end){
            System.out.print("A"+(start+1));
        }
        else {
            System.out.print("(");
            show(s,start,s[start][end]);
            show(s,s[start][end]+1,end);
            System.out.print(")");
        }
    }
    public static void main(String[] args) throws IOException {
//        for(int i = 0;i < 2;i++){
//            create_String(22);
//        }
        ArrayList<String> arrayList = new ArrayList<>();
        FileReader fr = new FileReader(ReadPath2);
        BufferedReader bf = new BufferedReader(fr);
        String str;
        while ((str = bf.readLine()) != null){
            arrayList.add(str);
        }
        bf.close();
        fr.close();
        int length = arrayList.size();
        String[] arraystring = new String[length];

//        for(int i = 0;i<length;i++){
//            String s = arrayList.get(i);
//            arraystring[i] = s;
//            arraystring[i] = arrayList.get(i);
//        }
//        String[] s1 = new String[arraystring[0].length()];
//        String[] s2 = new String[arraystring[1].length()];
//        String[] s3 = {" ","A","B","C","B","D","A"};
//        String[] s4 = {" ","B","D","C","A","B","A"};
//        int[] s5 = {5,10,3,12,5,50,6};
//        for(int i = 0;i < arraystring[0].length();i++){
//            s1[i] = arraystring[0].substring(i,i+1);
//        }
//        for(int i = 0;i < arraystring[1].length();i++){
//            s2[i] = arraystring[1].substring(i,i+1);
//        }
//        int[][] b = FindMaxStrngArray(s3,s4);
//        int[][] s= FindMaxArray(s5);
//        show(s,0,s5.length-2);
//        Display(b,s3,s3.length - 1,s4.length - 1);
//        System.out.println("长度为"+num);
//        char[] s1 = arraystring[0].toCharArray();
//        char[] s2 = arraystring[1].toCharArray();
//        System.out.println(s1);
//        System.out.println(s2);
//        for(int i = 0;i<s.length;i++){
//            System.out.println(s[i]);
        Activity_A();
//        }
    }
}
