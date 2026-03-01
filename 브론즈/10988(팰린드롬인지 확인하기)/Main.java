import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int len = s.length();
        for (int i=0; i<len/2; i++) {
            if (s.charAt(i) != s.charAt(len-1-i)) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
