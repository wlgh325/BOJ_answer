import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Arrays;

class Pos {
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 점들을 담을 배열
		Pos[] arr = new Pos[n];
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			arr[i] = new Pos(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}

		Arrays.sort(arr, new Comparator<Pos>() {
			@Override
			public int compare(Pos p1, Pos p2){
				if(p1.x < p2.x)
					return -1;
				else if(p1.x > p2.x)
					return 1;
				else{
					if(p1.y < p2.y)
						return -1;
					else if(p1.y > p2.y)
						return 1;
				}
				return 0;
			}
		});

		for(Pos p : arr)
			System.out.println(p.x + " " + p.y);		
	}
}