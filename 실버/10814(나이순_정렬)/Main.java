import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Arrays;

class People{
	int age;
	String name;

	People(int age, String name){
		this.age = age;
		this.name = name;
	}
}

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		People[] peoples = new People[n];

		// 회원정보 입력받기
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			peoples[i] = new People(Integer.parseInt(temp[0]), temp[1]);
		}

		// 나이가 우선, 같으면 가입한 순(index 순)
		Arrays.sort(peoples, new Comparator<People>() {
			@Override
			public int compare(People p1, People p2){
				if(p1.age < p2.age)
					return -1;
				else if(p1.age > p2.age)
					return 1;
				return 0;
			}
		});

		for(People p : peoples)
			System.out.println(p.age + " " + p.name);
			
		br.close();
	}
}