# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 11286번 절댓값 힙 자바(java)  풀이</span>
- 랭크 : 실버1
- [백준 11286번 절댓값 힙](https://www.acmicpc.net/problem/11286)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 배열에 정수 x(x!=0)를 넣는다.
2. 배열에서 절댓값이 가장 작은 값을 출력하고 배열에서 제거한다.
3. 절댓값이 가장 작은 값이 여러개일 경우, 가장 작은 수를 출력하고 그 값을 배열에서 제거한다.
3. 프로그램은 처음에 비어있는 배열에서 시작한다.
4. 입력으로 주어진 수 x가 정수라면 그 수를 넣고, 0이면 배열에서 절댓값이 가장 작은 값을 출력하고 제거한다.
5. 배열이 비어있는데 가장 작은 값을 출력하라고 하는 경우 0을 출력한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
우선순위 큐를 이용해 값을 오름차순으로 유지합니다.  
이는 다음과 같이 comparator를 직접 구현하여야 합니다.
```java
    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        public int compare(Integer a, Integer b){
            if(Math.abs(a) > Math.abs(b))
                return 1;
            else if(Math.abs(a) < Math.abs(b))
                return -1;
            else{
                if(a > b)
                    return 1;
                else if(a < b)
                    return -1;
                else
                    return 0;
            }
        }
    });
```
절댓값을 비교해서 왼쪽의 인자가 더 큰 경우 1, 작은 경우 -1로 해주면 절댓값 기준 오름차순으로 졍렬됩니다.  
절댓값이 같은 경우 내림차순으로 해야하기 때문에 else문에서는 절댓값이 아닌 값을 비교해서 오름차순 정렬합니다.  
예를 들어 -13과 13이 있다면 절댓값은 같지만 -13이 더 작은 수이므로 -13, 13 순으로 정렬됩니다.   
Priority 큐는 내부적으로 heap을 이용해 만들어져 있습니다.
1. 입력 받은 값이 0인 경우: 비어있지 않으면 poll해서 출력
    비어있는 경우: 0 출력
2. 입력받은 값이 0이 아닌 경우: 큐에 넣기