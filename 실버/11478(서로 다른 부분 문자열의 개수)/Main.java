import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int len = s.length();
        Set<String> set = new HashSet<>();

        for (int i=0; i<len; i++) {
            for (int j=i; j<=len; j++) {
                String sub = s.substring(i, j);
                set.add(sub);
            }
        }

        // 공백제외
        System.out.println(set.size() - 1);
    }
}
