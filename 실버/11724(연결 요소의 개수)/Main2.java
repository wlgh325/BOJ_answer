import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main2 {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        // 초기화
        // 처음 각 점의 부모는 자기 자신
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            // 부모 구하기
            int aParent = findParent(a);
            int bParent = findParent(b);

            // 부모가 같지 않다면 union
            if(aParent != bParent)
                parent[bParent] = aParent;
        }
        
        // 서로 다른 부모를 가지는 점의 개수 구하기
        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<=N; i++)
            set.add(findParent(i));
        System.out.println(set.size());
    }

    public static int findParent(int x){
        if(parent[x] == x) return x;
        else
            return parent[x] = findParent(parent[x]);
    }
}