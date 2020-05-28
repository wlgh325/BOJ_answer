import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.Collections;

class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int tc=1; tc<=T; tc++){
			int num = Integer.parseInt(br.readLine());
			
			// 숫자, 개수
			TreeMap<Integer, Integer> treemap = new TreeMap<>();

			for(int i=0; i<num; i++){
				String[] temp = br.readLine().split(" ");
				
				int input = Integer.parseInt(temp[1]);
				switch(temp[0]){
					case "I":
						if(treemap.containsKey(input))
							treemap.put(input, treemap.get(input)+1);
						else
							treemap.put(input, 1);
						break;
					case "D":
						if(treemap.size() != 0){
							if(input == -1){
								int min = treemap.firstKey();
								if(treemap.get(min) == 1)
									treemap.remove(min);
								else
									treemap.put(min, treemap.get(min)-1);
							}
							else{
								int max = treemap.lastKey();
								if(treemap.get(max) == 1)
									treemap.remove(max);
								else
									treemap.put(max, treemap.get(max)-1);
							}
						}
						break;
				}
			}

			if(treemap.isEmpty())
				System.out.println("EMPTY");
			else if(treemap.size() == 1)
				System.out.println(treemap.firstKey() + " " + treemap.firstKey());
			else
				System.out.println(treemap.lastKey() + " " + treemap.firstKey());
		}
	}
}