import java.io.PrintStream;
import java.util.Scanner;

class Solution {
    public final String AV = "av";

    public static String translate(String text) {
        String result = "";
        for (int i = text.length() - 1; i > 0; i--) {
            char ch = text.charAt(i);
            switch (ch) {
                case 'a':
                case 'e':
                case 'o':
                case 'i':
                case 'u':
                    result += "av" + ch;
                    break;
                default:
                    result += ch;
            }

        }

        return result;
    }

    /* Ignore and do not change the code below */
    // #region main
    public static void main(String[] args) {


    }
}