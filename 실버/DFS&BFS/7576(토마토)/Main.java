import java.io.*;
import java.util.*;

public class Main {

    static int M, N;
    static int[][] map;
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        bfs();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {   // 끝까지 못 익은 토마토
                    System.out.println(-1);
                    return;
                }
                answer = Math.max(answer, map[i][j]);
            }
        }

        // 처음부터 1로 시작했으므로 -1
        System.out.println(answer - 1);
    }

    static void bfs() {

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
