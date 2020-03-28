import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos{
    int x;
    int y;

    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int[][] sdoku;
    static ArrayList<Pos> list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        list = new ArrayList<>();

        sdoku = new int[9][9];
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<9; j++){
                int t = Integer.parseInt(st.nextToken());
                sdoku[i][j] = t;
                if(t == 0)
                    list.add(new Pos(i,j));
            }
        }

        Pos first = list.get(0);
        // 숫자 채우기
        backtracking(first.x, first.y, 0);
    }

    private static void backtracking(int x, int y, int cnt){
        if(cnt == list.size() - 1){
            Pos p = list.get(cnt);
            for(int i=1; i<=9; i++){
                if(isValid(p.x, p.y, i)){
                    sdoku[p.x][p.y] = i;
                    break;
                }
            }

            if(sdoku[p.x][p.y] == 0)
                return;

            // 출력하기
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(sdoku[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
            return;
        }

        for(int i=1; i<=9; i++){
            if(isValid(x, y, i)){
                sdoku[x][y] = i;
                Pos p = list.get(cnt+1);
                backtracking(p.x, p.y, cnt+1);
                sdoku[x][y] = 0;
            }
        }
    }

    private static boolean isValid(int x, int y, int num){
        for(int i=0; i<9; i++){
            // 행 검사
            if(sdoku[x][i] == num){
                return false;
            }
            // 열 검사
            if(sdoku[i][y] == num)
                return false;
        }

        // 박스 검사
        // row (012) (345) (678)
        if(x < 3){
            if(y < 3){
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else if(y < 6){
                for(int i=0; i<3; i++){
                    for(int j=3; j<6; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else{
                for(int i=0; i<3; i++){
                    for(int j=6; j<9; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
        }
        else if(x < 6){
            if(y < 3){
                for(int i=3; i<6; i++){
                    for(int j=0; j<3; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else if(y < 6){
                for(int i=3; i<6; i++){
                    for(int j=3; j<6; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else{
                for(int i=3; i<6; i++){
                    for(int j=6; j<9; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
        }
        else{
            if(y < 3){
                for(int i=6; i<9; i++){
                    for(int j=0; j<3; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else if(y < 6){
                for(int i=6; i<9; i++){
                    for(int j=3; j<6; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
            else{
                for(int i=6; i<9; i++){
                    for(int j=6; j<9; j++){
                        if(sdoku[i][j] == num)
                            return false;
                    }
                }
            }
        }
        return true;
    }
}