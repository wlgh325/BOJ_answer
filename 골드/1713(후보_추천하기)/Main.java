import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class People implements Comparable<People>{
	int idx;
	int num;
	int score;

	People(int idx, int num, int score){
		this.idx = idx;
		this.num = num;
		this.score = score;
	}

	public int getNum(){
		return this.num;
	}

	@Override
	public int compareTo(People p){
		if(this.num < p.getNum())
			return -1;
		else if(this.num > p.getNum()){
			return 1;
		}
		return 0;
	}
}

class Main{	
	static int n;
	static int recommendedNum;
	static ArrayList<People> frame;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean flag = true;

		// 사진 틀의 개수
		n = Integer.parseInt(br.readLine());
		recommendedNum = Integer.parseInt(br.readLine());
		frame = new ArrayList<>();

		int idx = 0;

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<recommendedNum; i++){
			int num = Integer.parseInt(st.nextToken());

			// 사진틀에 이미 걸려 있는지 검사
			for(People p: frame){
				if(p.num == num){
					p.score++;
					flag = false;
					break;
				}
			}

			// 걸려있지 않은 번호인 경우
			if(flag){
				// 걸 수 있는 자리가 없는 경우
				if(frame.size() == n){
					// 점수가 다르면 
					Collections.sort(frame, new Comparator<People>() {
						@Override
						public int compare(People p1, People p2){
							if(p1.score == p2.score)
								return p1.idx-p2.idx;
							else
								return p1.score - p2.score;
						}
					});
					frame.remove(0);
					frame.add(new People(idx, num, 1));
					idx++;
				}
				else{
					// 걸 수 있는 곳이 있는 경우
					frame.add(new People(idx, num, 1));
					idx++;
				}
			}
			flag = true;
		}
		
		Collections.sort(frame);

		for(int i=0; i<frame.size(); i++){
			if(i == frame.size()-1)
				bw.write(frame.get(i).num + "");
			else
				bw.write(frame.get(i).num + " ");
		}

		bw.flush();
		br.close();
		bw.close();
	}
}