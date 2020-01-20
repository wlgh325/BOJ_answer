import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Pair{
	int left;
	int right;

	Pair(int left, int right){
		this.left = left;
		this.right = right;
	}
}

class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int mem_size;
	static int code_len;
	static int input_size;
	
	static int inputIdx;
	static int memIdx;
	static int[] mem;

	static String text;
	static String program;

	static int loopCount;

	static Stack<Integer> stack;
	static Pair [] pairs;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		init();

		int testNum = Integer.parseInt(br.readLine());

		// test 개수
		for(int i=0; i<testNum; i++){
			String[] temp = br.readLine().split(" ");
			mem_size = Integer.parseInt(temp[0]);
			code_len = Integer.parseInt(temp[1]);
			input_size = Integer.parseInt(temp[2]);
			
			program = br.readLine();
			text = br.readLine();
			
			// '[', ']' 검사
			readBracket();

			solve();
			
			bw.newLine();
			
			init();
		}
		bw.flush();

		br.close();
		bw.close();
	}

	public static void readBracket(){
		for(int i=0; i<code_len; i++){
			if(program.charAt(i) == '['){
				stack.push(i);
				pairs[i] = new Pair(i, 0);
			}
			else if(program.charAt(i) == ']'){
				int temp = stack.pop();
				pairs[i] = new Pair(temp, i);
				pairs[temp].right = i;
			}
		}
	}

	public static void solve() throws IOException{
		// Read program
		int idx = 0;
		int max_index = 0;

		while(true){
			char command = program.charAt(idx);

			switch(command){
				case '-':
					mem[memIdx] = ( mem[memIdx] - 1 ) % 256;
					break;
				case '+':
					mem[memIdx] = ( mem[memIdx] + 1 ) % 256;
					break;
				case '<':
					if(memIdx == 0)
						memIdx = mem_size - 1;
					else
						memIdx--;
					break;
				case '>':
					if(mem_size-1 == memIdx)
						memIdx = 0;
					else
						memIdx++;
					break;
				case '[':
					
					if(mem[memIdx] == 0){
						idx = pairs[idx--].right;
						loopCount++;
					}
					break;
				case ']':
					if(mem[memIdx] != 0){
						idx = pairs[idx--].left;
						loopCount++;
					}
					break;
				case '.':
					break;
				case ',':
					if(inputIdx == input_size)
						mem[memIdx] = 255;
					else
						mem[memIdx] = (int)text.charAt(inputIdx++);
			}
			
			idx++;
			loopCount++;
			// loop문 도는 경우 갇힌 loop문 판단을 위한 index
			if(idx > max_index)
				max_index = idx;

			// 모든 명령 다 읽음
			if(idx == code_len){
				bw.write("Terminates");
				break;
			}
			if(loopCount > 50000000){
				bw.write("Loops" + " " + pairs[max_index].left + " " + pairs[max_index].right);
				break;
			}
		}
	}

	public static void init(){
		inputIdx = 0;
		memIdx = 0;
		loopCount = 0;

		stack = new Stack<>();
		pairs = new Pair[4096];

		mem = new int[100001];
		Arrays.fill(mem, 0);
	}
}

