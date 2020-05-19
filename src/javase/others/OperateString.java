package javase.others;

/**
 * Author: CodeJerry
 * Date: 2020/03/17 16:14
 * @author 76582
 */
public class OperateString {
    public static String operateString(String s){
        if (s == null){return null;}
        char []chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c: chars) {
            c += s.length();
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "DEF";
        System.out.println(operateString(s));
    }
}
