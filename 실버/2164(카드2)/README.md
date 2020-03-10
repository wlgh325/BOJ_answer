# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2164번 카드2 자바(java)  풀이</span>
- 랭크 : 실버4
- 풀이시간: 40분
- [백준 2164번 카드2](https://www.acmicpc.net/problem/2164)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. N장의 카드가 주어지며 1~N까지의 번호를 갖는다.
2. 1번 카드가 제일 위에, N번이 제일 뒤에 있다.
3. 다음 동작을 카드가 한 장남을때 까지 반복한다.
    제일 위에 있는 카드르 버린다.
    그 다음 제일 위에 있는 카드를 제일 아래로 옮긴다.
4. N이 주어졌을 때, 제일 마지막에 남게 되는 카드를 구하여라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 카드1의 연장선에 있는 문제입니다. [카드1](https://www.acmicpc.net/problem/2161)을 먼저 풀어보세요!!  
카드1 문제는 N이 최대 1000입니다. 하지만 카드2 문제는 N이 최대 500000입니다. 이만 주의하면 됩니다.  
저는 카드1 문제를 ArrayList로 구현하였다가 카드2에 냈더니 '사간초과'가 발생하였습니다.  
그래서 규칙을 찾기 시작했습니다. (ArrayList를 **queue**로 바꿔서 했더니 이는 **통과**했습니다.)
<br><br>

# <span style="color: red; font-size:15pt">규칙 찾기</span>
마지막에 남는 수를 나열해보면 다음과 같습니다.  
```
1 / 2 /2 4 /2 4 6 8 /2 4 6 8 10 12 14 16/2
```
1을 빼고 군수열임을 알 수 있습니다. 군의 개수가 1 2 4 8 16..으로 늘어납니다.  
이는 공비수열 이므로 n군까지의 개수의 합은 '2<sup>n</sup> - 1'입니다. (첫항이 1, 공비가 2)  
n이 속할 군수열을 구해서 그 군안에서 수를 구하면 됩니다.  
예를 들어 7이라면 6이 답입니다.  
1. 우선 n에서 1을 뻅니다(1 제외)
2. n-1을 넘는 군의 index를 구합니다.
    3번째 군까지 개수의 합이 7이므로 n-1번째 수는 3번째 군에 있음을 알 수 있습니다.
    n-1번쨰 수는 3번쨰 군에서 (n-1) - 2<sup>2</sup>-1 = 3입니다.
    군 안에서는 2씩 증가하는 등차수열입니다. 2*3 = 6이 됩니다.

# <span style="color: red; font-size:15pt">queue 이용 소스코드</span>
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; i++)
			q.offer(i);
		
		while(q.size() > 1) {
			q.poll();	
			q.add(q.poll());
		}
		System.out.print(q.poll());
	}
}
```