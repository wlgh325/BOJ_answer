import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] temp = br.readLine().split(" ");
    	
    	// width > height
    	int diagonal = Integer.parseInt(temp[0]);
    	int height = Integer.parseInt(temp[1]);
    	int width = Integer.parseInt(temp[2]);
    	
    	// 높이와 너비를 구하기
    	// 소수점이 나올 경우, 그 수보다 작으면서 가장 큰 정수 출력 (floor)
    	double res = Math.sqrt(Math.pow(diagonal, 2) / ( Math.pow(height, 2) + Math.pow(width, 2) ));
    	System.out.print((int)Math.floor(res*height) + " ");
    	System.out.println((int)Math.floor(res*width));
    }
}