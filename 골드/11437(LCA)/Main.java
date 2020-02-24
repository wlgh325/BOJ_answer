import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {	
    static int vertexNum; 
    static ArrayList<ArrayList<Integer>> tree;
    static int m;
    static int[] depth;
    static int[] parent;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력받기
        vertexNum = Integer.parseInt(br.readLine());
        tree = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<vertexNum+1; i++)
            tree.add(new ArrayList<Integer>());
        
        // 주어진 점들로 트리 만들기
        for(int i=0; i<vertexNum-1; i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        
        depth = new int[vertexNum+1];
        parent = new int[vertexNum+1];
        m = Integer.parseInt(br.readLine());

        // 정점들의 depth 구하기
        dfs(1,1);
        for(int i=0; i<m; i++){
            // 공통 조상을 구할 두 노드
            String[] temp2 = br.readLine().split(" ");
            int a = Integer.parseInt(temp2[0]);
            int b = Integer.parseInt(temp2[1]);

            int same = solve(a, depth[a], b, depth[b]);
            System.out.println(same);
        }
			  
		br.close();
    }

    static int solve(int a, int a_depth, int b, int b_depth){
        // 둘의 depth가 같아질 때까지 위로 올린다.
        if(a_depth > b_depth){
            while(a_depth != b_depth){
                a_depth--;
                a = parent[a];
            }
        }
        else if(a_depth < b_depth){
            while(a_depth != b_depth){
                b_depth--;
                b = parent[b];
            }
        }

        
        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    static void dfs(int from, int cnt) {
    	depth[from] = cnt++;
    	for(Integer next: tree.get(from)) {
    		if(depth[next] == 0) {
    			dfs(next, cnt);
    			parent[next] = from;
    		}
    	}
    }
}