# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2568번 전깃줄-2 문제 자바(java)  풀이</span>
- 랭크 : 골드2
- [백준 2568번 전깃줄-2](https://www.acmicpc.net/problem/2568)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 교차하는 전깃줄을 없애서 전깃줄이 교차하지 않도록 하려고 한다.
2. 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄 최소 개수 구하기
3. 전깃줄의 개수는 최대 100,000개 이다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
전깃줄의 상태를 왼쪽 기준 오름차순으로 나열하면 아래와 같습니다.
```
A 전봇대: 1 2 3 4 6 7 9 10 
B 전봇대: 8 2 9 1 4 6 7 10
```
어떻게 하면 제일 조금 자를 수 있을까 생각해보면 B 전봇대의 1,2,3번째 전깃줄을 자르면 3개로 제일 작게 자르는 경우가 됩니다.  
왜나하면 왼쪽수는 이미 증가 상태이기 때문에 오른쪽도 오름차순 이면 전깃줄이 엉키지 않습니다.  
즉 최장 증가 수열(Longest Increasing Sequence)를 구하면 됩니다.  
예에서는 (1 4 6 7 10)이 길이 5로 LIS가 됩니다. 이를 제외하고 3개를 자르면 됩니다.  

LIS를 구하는 방법에 O(n^2) 방법과 O(nlogn) 방법이 있습니다.  
- dp방법: O(n^2)이기 때문에 n이 최대 100,000이기 때문에 이 방법으로 풀 수 없습니다
- segment tree: O(nlogn) 방법으로 n이 10만이기 때문에 이 방법을 이용합니다.
- 이분 탐색: O(nlogn)으로 segment tree와 시간 복잡도가 같습니다.
<br><br>

# <span style="color: blue; font-size:15pt">LIS</span>
n이 10만으로 크기 때문에 이분 탐색과 segment tree를 이용해야 합니다.  
이분 탐색을 이용해 풀어 보겠습니다!!  
0. 입력 받은 전깃 줄 연결 정보를 arr에 저장하고 LIS가 아닌 수를 판별하기 위한 visited 배열을 true로 초기화 합니다.
1. 전봇대 A 인덱스를 기준으로 arr 배열을 정렬합니다.
2. LIS를 저장할 lis 배열에 arr의 첫번째 값의 전봇대 B값을 넣습니다.
    trace는 그 r 값이 들어가는 위치를 추적합니다.
    trace[0] = new Pair(0, arr[0].l)은 0번째 값에 대해서 조사해보니 그 값이 들어가는 위치는 0이고, 그때 전봇대 A의 값은 arr[0].l 임을 나타냅니다.
3. lis의 맨 뒤에 있는 값 보다 추가하려는 값이 더 크다면 LIS 배열의 맨 뒤에 추가합니다. 그리고 trace 배열에도 값을 써줍니다.
4. 그리고 trace 배열의 맨 뒤 부터 조사하며 index를 줄여가며 해당 index를 갖는 값을 차례대로 list에 넣습니다.
    TC를 예로 들겠습니다.
    trace.l: 0 0 1 0 1 2 3 4
    trace.r: 1 2 3 4 6 7 9 10
    뒤에서 부터 l값을 기준으로 list에 담아 r 값을 출력하면
    l=4일때 (10) => l=3일때 (9) => l=2일때 (7) => l=1일때 (6) => l=0일때 (4)
    4 6 7 9 10 이 LIS임을 알 수 있습니다.
    이렇게 하지 않고 lis에 lower_bound를 이용해 값만 써나가면 1 2 4 3가 있을때
    LIS로 124가 아닌 134가 나오게 됩니다.
5. list에 담긴 수들이 제거하지 않을 수 들입니다. visited 배열에서 그에 해당하는 index를 false로 바꿔줍니다.
6. visited 배열에서 true 값을 가지는 index를 출력합니다.