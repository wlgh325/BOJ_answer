import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        // 놀이기구별 운행 시간
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<m; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        if(n <= m){
            System.out.println(n);
            return;
        }
        // 마지막 사람이 타기전 시간 까지 몇 명이 탔는지 검색
        long time = binarySearch() - 1;
        long child = m;
        for(int i=0; i<m; i++)
            child += time / arr[i];

        int leftChild = n - (int)child; // 1분 사이에 더 태워야 하는 남은 사람
        int cnt = 0;
        int i = 0;
        
        // 1분 사이에 사람이 더 탈 수 있는 경우 앞에서 부터 count하며 index 찾기
        while(true){            
            if(((time+1) / arr[i]) != (time / arr[i]))
                cnt++;
            i++;
            if(cnt == leftChild)
                break;
        }
        System.out.println(i);
    }

    private static long binarySearch(){
        // 시간
        long left = 0;
        long right = 2000000000L * 30L;
        long result = -1;

        while(left <= right){
            long mid = (left + right) / 2;
            long child = m; // 처음에 m명 모두 탐

            // 시간 / 운행 시간 만큼 사람들이 타게 됨
            for(int i=0; i<m; i++)
                child += mid / arr[i];
            
            if(child >= n){
                result = mid;
                right = mid - 1;
            }
            else if(child < n)
                left = mid + 1;
        }
        return result;
    }
}
