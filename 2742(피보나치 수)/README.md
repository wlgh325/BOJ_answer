# BOJ 2742번 피보나치 수 문제. java 풀이
- 백준 온라인 저지(BOJ) 2742번 피보나치 수 자바 풀이
- https://www.acmicpc.net/problem/2747

## 티스토리 주소
- https://hoho325.tistory.com/



## 문제 풀이
```
피보나치 수 : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597
단순한 재귀로 풀면 시간초과가 나온다!!
DP(Dynamic Programming)를 이용해서 풀어야한다
재귀를 그대로 쓰면 반복된 계산들이 많다
그래서 DP를 이용해 다시 계산하지 않도록 이전에 계산한 값을 저장하도록 하면 풀 수 있다
```
1. n을 입력받는다.
2. 점화식을 이용해 재귀로 표현한다.
3. fn = fn-1 + fn-2이므로 return fibo(n-1) + fibo(n-2);로 하면 된다.
4. 재귀에서 이미 계산된 값은 arr 배열에 저장하여 다시 계산하지 않고 참조하도록 한다

## 다른 풀이
```
	재귀를 이용하지 않고 단순히 dp를 이용하여 풀 수도 있다
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		n = n-1;

		// fn = fn-1 + fn-2
		int last1, last2, result = 0;

		if(n <= 1){
			bw.write("1");
			bw.flush();

			bw.close();
			br.close();
			return;
		}

		last1 = 1;
		last2 = 1;
		// last1, last2, result 순서이다
        // result 값이 계산 되면 last2 값이 맨 왼쪽의 last1 값이 되고
        // result 값이 두번째 값인 last2가 된다
		for(int i=1; i<n; i++){
			result = last1 + last2;
			last1 = last2;
			last2 = result;
		}
		
		bw.write(String.valueOf(result));
		bw.flush();

		br.close();
		bw.close();
	}
	
```