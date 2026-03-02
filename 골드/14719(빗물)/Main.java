import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j=0; j<height; j++) {
                map[j][i] = 1;
            }
        }

        // block, block 사이의 빈 공간
        int count = 0;
        for (int i=0; i<H; i++) {
            boolean isLeftBlock = false;
            boolean isEmpty = false;

            int temp = 0;
            for (int j=0; j<W; j++) {
                if (isLeftBlock && map[i][j] == 0) {
                    temp++;
                    isEmpty = true;
                }

                if (isLeftBlock && map[i][j] == 1) {
                    count += temp;
                    isLeftBlock = false;
                    temp = 0;
                    isEmpty = false;
                }

                if (!isEmpty && map[i][j] == 1) {
                    isLeftBlock = true;
                }

            }
        }

        System.out.println(count);
    }
}
