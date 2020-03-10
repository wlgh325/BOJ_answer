# <span style="color:orange; font-size:17pt; font-weight:bold"></span>BOJ 1517번 버블소트 문제 자바(java)  풀이</span>
- 랭크 : 골드3
- [백준 1517번 버블소트](https://www.acmicpc.net/problem/1517)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. N개의 수로 이루어진 수열이 있다.
2. 이 수열에 대해 버블 소트를 수행할때 swap이 몇 번 일어나는지 계산하여라.
3. N은 최대 500,000
4. 각각의 수는 최대 10억
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
N이 최대 500,000이므로 단순한 버블 정렬대로 하면서 계산하면 N<sup>2</sup>으로 시간초과가 날 것입니다.  
최소 nlogn 정도의 알고리즘을 구현해야 합니다.  

1. O(n)이라 생각한 알고리즘
```
    1.1 list에 모든 값을 다 넣고 최대값을 구해 최대값의 위치를 구합니다.
    1.2 그 값의 위치를 찾고 list의 맨 뒤 인덱스에서 값으 위치를 빼줍니다.(예를 들어 최대값의 위치 1, 맨 뒤 인덱스 5 -> 5-1=4)
    이 값이 뒤로 보내기 위해 필요한 swap 횟수입니다.
    1.3 list의 크기가 1이 될떄까지 반복합니다.
즉 N번 반복하기 때문에 O(N)이라 생각했는데 통과하지 못했습니다.
아마 최대값의 인덱스를 찾고 지우는 과정이 오래걸리는 것 같습니다.
```
<시간초과>
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 입력받기
		for(int i=0; i<n; i++){
			int t = Integer.parseInt(st.nextToken());
			list.add(t);
		}

		int sum = 0;
		int t, max;
		int idx;

		while(true){
			idx = list.size() - 1;
			max = Collections.max(list);
			t = list.indexOf(max);
			sum += idx - t;

			list.remove(t);
			if(list.size() == 1)
				break;
		}
		System.out.println(sum);
		br.close();
	}
}
```
<br><br>

2. 각 수 앞에 자기보다 큰 수들이 있는 만큼 교환이 일어난다.(병합정렬 이용)
우선 병합정렬(merge sort)란 1개가 될때 까지 나누어 간 다음 정렬하며 나눈 것들을 하나씩 합쳐가는 sorting 방법입니다. 이를 이용해 구해봅시다!!  
7 4 5 1 3 수열의 정렬을 예로 들어보겠습니다. 각 앞에 큰수의 개수는 다음과 같습니다.  
0 1 1 3 3 모두 더하면 8이 됩니다. 이는 버블 정렬시 교환되는 횟수 입니다.  
이 횟수를 merge sort를 이용해 구할 수 있습니다.  
left와 right 두 개의 list로 나누었을때 왼쪽 리스트의 값이 오른쪽 리스트의 값보다 큰 경우 왼쪽 list의 크기에 그 값의 index를 빼면 큰 수의 개수를 구할 수 있습니다.  위의 예를 다시 들어보겠습니다.
```
7 | 4 | 5 | 1 | 3
list의 개수가 한 개일때까지 모두 나누면 위와 같이 나뉩니다.
```
1. 1과 3을 보면 1보다 3이 크므로 앞쪽에 3보다 큰 수는 0 입니다.
2. 다음 left가 5 right가 1,3이 됩니다. 여기서 left를 고정하고 right를 비교해 나갑니다.
3. 5와 1을 비교하면 5가 더 크므로 1보다 큰 수는 1개입니다. 5와 3을 비교할 경우도 큰 수는 1개입니다. (누적 2)
4. 그러면 이제 1 3 5로 정렬되었습니다.
5. 7과 4를 보면 7이 4보다 크므로 4보다 큰 수는 앞에 1개가 있습니다(누적 3) => 4 7로 정렬
6. 이제 마지막 merge입니다. left는 4 7, right는 1 3 5입니다.
7. 4와 1을 보면 4가 더 크므로 1보다 큰 수는 4, 7 2개(size-0)입니다. (누적 5)
8. 4와 3을 보면 4가 더 크므로 3보다 큰 수는 4, 7 2개(size-0)입니다. (누적 7)
9. 4와 5를 보면 5가 더 큽니다. leftIdx를 이동시켜 7과 5를 보면 7이 더 크므로 5보다 큰 수는 앞에 1개 있습니다(size - 1) (누적 8)
이렇게 swap 횟수를 알아갈 수 있습니다.