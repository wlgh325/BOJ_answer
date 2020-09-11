# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1168번 요세푸스 문제2 자바(java)  풀이</span>
- 랭크 : 실버3
- [백준 1158번 요세푸스 문제2](https://www.acmicpc.net/problem/1168)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 1~N번까지 N명의 사람이 원 모양으로 앉아있다.
2. k가 주어질때 k번째 사람을 제거한다.
3. 한 사람이 제거되면 남은 사람들로 이루어진 원에서 2번을 반복한다.
4. N명의 사람이 모두 제거될 때까지 계속된다.
5. 사람들이 제거 되는 순서를 출력해라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
두 가지 방법으로 풀이 가능합니다. n과 m이 100,000으로 크기 때문에 naive한 풀이로는 시간안에 풀 수 없습니다.  
O(NM)이 되기 때문에 O(logN)에 풀 수 있도록 해야합니다.  
1. 원형 리스트 이용
    ArrayList를 이용하여 값을 지우기를 반복합니다.
    인덱스가 넘는 다면 list의 크기 만큼 나누어 주어 원형 list처럼 동작하도록 합니다.
2. 세그먼트 트리 이용
    배열의 시작 index를 1이라고 한다면 sum(k)를 L[1] + L[2] + L[3] + ... + L[k]라 하고
    직전에 제거된 위치가 i라면, 다음에 제거될 위치는 L[j] - L[i] = M을 만족하는 가장 작은 j 입니다.
    이떄 sum(k)를 segment tree를 이용해서 O(logN)만에 계산할 수 있다.
    또한 j는 binary search로 O(NlogN) 대신 O(logN ^ 2)만에 계산할 수 있습니다.
    이때 j의 범위는 선형이 아니라 원형이다.
<br><br>

# <span style="color: red; font-size:15pt">원형 리스트 이용 풀이</span>
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();

		sb.append("<");
		for(int i=1; i<=n; i++)
			list.add(i);
		
		int idx = 0;
		while(list.size() > 1){
			// list의 k번째 사람의 숫자가 k+1이므로 k-1번째 사람(숫자가 k)을 없앤다.
			idx += m-1;

			// 원형 list처럼 동작
			if(idx >= list.size())
				idx %= list.size();
			sb.append(list.get(idx) + ", ");
			list.remove(idx);
		}
		// 마지막 element
		sb.append(list.get(0) + ">");
		System.out.println(sb);
	}
}
```
<br><br>

# <span style="color: red; font-size:15pt">세그먼트 트리</span>
세그먼트 트리(segment tree)를 이용하면 구간에 대한 질문에 효율적으로 대답할 수 있습니다.  그래서 segment tree(부분 트리, 구간 트리)입니다.
그래서 구간 합 같은 문제에 많이 사용하게 됩니다.  
한 번만 계산해 두면 중복된 계산을 피해 답을 빨리 구할 수 있는 자료구조 입니다.
- 리프 노드: 배열의 그 수 자체
- 다른 노드: 왼쪽 자식과 오른쪽 자식의 합을 저장
- 왼쪽 자식 번호: 2i
- 오른쪽 자식의 번호: 2i+1
- 일차원 배열로 표현, 배열의 길이 : 2<sup>ceil( log<sub>2</sub>N) + 1</sup> ex) N=2, 2^4 =16
