import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int i=0; i<tc; i++) {
            String s = br.readLine();
            char first = s.charAt(0);
            char last = s.charAt(s.length() - 1);
            System.out.println(first + "" + last);
        }
    }
}
