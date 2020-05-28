# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 7622번 이중 우선순위 큐 문제 자바(java)  풀이</span>
- 랭크 : 골드5
- [백준 7622번 이중 우선순위 큐](https://www.acmicpc.net/problem/7622)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 큐에 저장된 값 자체가 큐의 우선순위를 나타낸다.
2. 큐에 연산을 처리하고 최종적으로 큐에 저장된 데이터 중 최대, 최소값을 출력하는 프로그램을 작성하세요
3. I n : n을 Q에 삽입
4. D 1 : Q에서 최댓값 삭제
5. D -1 : Q에서 최솟값 삭제
6. 최대, 최소 둘 이상인 경우, 하나만 삭제됨을 유념
7. Q가 비어있는데 적용할 연산이 'D'라면 이 연산은 무시한다.
8. 동일한 정수가 삽입될 수 있다.
9. 큐가 비어있다면 EMPTY를 출력하라. 그렇지 않으면 {최댓값, 최솟값} 출력
<br><br>

# <span style="color: red; font-size:15pt">문제접근, TreeMap</span>
처음엔 문제이름 그대로 우선순위 큐 두개를 이용해서 풀었지만 시간초과가 나서 다른 방안으로 TreeMap을 이용하였습니다.  
프로그래머스에서 우선순위 큐 두 개를 이용해 풀었는데 입력이 작은 것만 있었는지 통과하였습니다. <br>
두 개의 큐를 이용하는 경우 동기화 하는 과정에서 삭제해야하는데 여기서 시간초과가 나는 것 같습니다.  
TreeMap은 Map의 기본성질에 더해서 key를 기준으로 정렬된 상태로 가지고 있습니다.  
firstKey와 lastKey를 가지고 minHeap과 maxHeap의 기능을 합니다.
c++의 경우 multiset을 이용하면 쉽게 풀 수 있는 것 같습니다.
1. TreeMap<key, value> map = new TreeMap<>()하고 생성하면 기본 오름차순 정렬입니다.
2. 내림차순 정렬: TreeMap<key, value> map = new TreeMap<>(Collections.reverseOrder())
3. treemap.firstKey() -> 제일 작은 수
4. treemap.lastKey() -> 제일 큰 수
5. treemap의 value 값은 그 수(key)의 개수를 나타냅니다. (같은 수가 중복해서 들어갈 수 있기 때문) -> 5,5,3 인 상태에서 최대값을 삭제하는 경우 5,3이 되는데 이는 value를 하나 감소시켜주는 것과 같음
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
여우와 펭귄님의 풀이를 참조했습니다!
입력 값이 Long형이라 하였지만 32비트이므로 int형으로 충분합니다.
treemap 자료구조를 이용하지 않고 우선순위 큐 두개에 visited배열을 이용해 위와 같은 방법처럼 풀이도 가능합니다!
0. 주어진 입력을 파싱합니다.
1. I명령의 경우 두가지로 나뉩니다.
	1.1 넣으려는 숫자가 이미 있는 경우: value값을 1 증가시킵니다.
	1.2 넣으려는 숫자가 없는 경우: (key,1)을 추가합니다.
2. D 명령의 경우 두가지로 나뉩니다. 명령 실행전에 map이 비었는지 확인합니다.
	2.1 input이 -1인 경우: 최솟값을 삭제하기위해 firstKey를 참조. 그 key에 해당하는 value가 1인 경우 삭제, 그렇지 않은 경우 value를 1 감소시킨다.
	2.2 input이 1인 경우: 최댓값을 삭제하기 위해 lastKey를 참조. 그 key에 해당하는 value가 1인 경우 삭제, 그렇지 않은 경우 value를 1 감소시킨다.
3. treemap이 비어있는 경우 EMPTY, 1개인 경우 firstKey(lastKey) 출력, 그 외는 lastkey와 firstkey를 차례대로 출력합니다.