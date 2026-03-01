import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
   static int N;
   static char[][] map;
   static int[] xdir = {-1, 1, 0, 0};
   static int[] ydir = {0, 0, -1, 1};
   static boolean[][] visited;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      map = new char[N][N];
      visited = new boolean[N][N];

      // Init map
      for (int i=0; i<N; i++) {
         String temp = br.readLine();
         for (int j=0; j<N; j++) {
            map[i][j] = temp.charAt(j);
         }
      }

      // 적록색약 아님
      Set<Character> rSets = Set.of('R');
      Set<Character> gSets = Set.of('G');
      Set<Character> rgSets = Set.of('R', 'G');
      Set<Character> bSets = Set.of('B');

      int count = 0;
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
               switch (map[i][j]) {
                  case 'R':
                     dfs(i, j, rSets);
                     break;
                  case 'G':
                     dfs(i, j, gSets);
                     break;
                  case 'B':
                     dfs(i, j, bSets);
                     break;
               }
               count++;
            }
         }
      }

      // 적록색약
      int count2 = 0;
      visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
               switch (map[i][j]) {
                  case 'R':
                  case 'G':
                     dfs(i, j, rgSets);
                     break;
                  case 'B':
                     dfs(i, j, bSets);
               }
               count2++;
            }
         }
      }

      System.out.format("%d %d", count, count2);
   }

   static void dfs(int x, int y, Set<Character> sets) {
      visited[x][y] = true;

      for (int i=0; i<4; i++) {
         int dx = x + xdir[i];
         int dy = y + ydir[i];

         if (isValidation(dx, dy) && !visited[dx][dy] && sets.contains(map[dx][dy])) {
            dfs(dx, dy, sets);
         }
      }
   }

   static boolean isValidation(int x, int y) {
      if (x < 0 || x >= N || y < 0 || y >= N) return false;
      return true;
   }
}
