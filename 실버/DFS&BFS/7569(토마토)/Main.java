import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H;
    static int[][][] map;
    static boolean[][] visited;
    static int max;

    // 상, 하, 좌, 우, 앞, 뒤
    static int[] xdir = {-1, 1, 0, 0, 0, 0};
    static int[] ydir = {0, 0, -1, 1, 0, 0};
    static int[] zdir = {0, 0, 0, 0, -1, 1};
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][M][H];

        for (int k=0; k<H; k++) {
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<M; j++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k});
                    }
                }
            }
        }

        bfs();

        int answer = 0;

        for (int k=0; k<H; k++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }

                    answer = Math.max(answer, map[i][j][k]);
                }
            }
        }

        System.out.println(answer - 1);
        br.close();
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];

            for (int d=0; d<6; d++) {
                int dx = x + xdir[d];
                int dy = y + ydir[d];
                int dz = z + zdir[d];

                // 유효하면서 방문하지 않은 경우
                if (isValidPosition(dx, dy, dz) && map[dx][dy][dz] == 0) {
                    map[dx][dy][dz] = map[x][y][z] + 1;
                    queue.offer(new int[]{dx, dy, dz});
                }
            }
        }
    }

    static boolean isValidPosition(int x, int y, int z) {
        if (x < 0 || x >= N || y < 0 || y >= M || z < 0 || z >= H) return false;
        return true;
    }
}
