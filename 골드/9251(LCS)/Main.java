import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        // 비교할 두 문자열 입력받기
        char[] str = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int len1 = str.length;
        int len2 = str2.length;
        int[][] lcs = new int[len1+1][len2+1];

        for(int i=0; i<=len1; i++){
            for(int j=0; j<=len2; j++){
                if(i==0 || j==0)
                    continue;
                // 두 문자가 같은 경우
                if(str[i-1] == str2[j-1])
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                // 그렇지 않은 경우
                else
                    // 이전 문자 까지 읽었을때 LCS의 값과 비교해서 큰 값을 취한다.
                    lcs[i][j] = lcs[i-1][j] > lcs[i][j-1] ? lcs[i-1][j] : lcs[i][j-1];
            }
        }

        System.out.println(lcs[len1][len2]);
    }
}
