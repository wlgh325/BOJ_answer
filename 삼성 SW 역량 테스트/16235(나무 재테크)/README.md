# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 16235번 나무 재테크 문제 자바(java)  풀이</span>
- 랭크 : 골드5
- [백준 16235번 나무 재테크](https://www.acmicpc.net/problem/16235)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. NxN 크기의 땅이 있으며 1x1 크기로 나누어져 있다. 각 칸은 (r,c)로 나타내며 r과 c는 1부터 시작한다
2. 가장 처음에 양분은 모두 5만큼 들어있다.
3. 같은 1x1 크기의 칸에 여러 개의 내무가 심어져 있을 수 있다.
4. 봄
- 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
- 각각의 나무는 나무가 있는 1x1 크기의 칸에 있는 양분만 먹을 수 있다.
- 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
- 만약 땅에 양분이 부족해서 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽는다
5. 여름
- 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가 된다. (소수점 아래는 버린다)
6. 가을
- 나무가 번식한다.
- 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 인접한 칸은 상하좌우, 4개의 대각선 방향해서 모두 8칸이다.
7. 겨울
- S2D2가 땅에 양분을 추가한다.
- 각 칸에 추가되는 양분의 양은 A[r][c]이고 입력으로 주어진다.
8. k년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 이 전에 풀었던 큐빙 보다는 훨씬 쉬웠습니다. 하지만 생각보다는 헤맸어요   
문제 주어진 그대로 구현하고 제출했더니 **시간초과**가 나왔습니다.  
안 날 줄 알았는데 보니까 시간 제한이 0.5초로 짧았어요  
그래서 매번 정렬을 하지 않고 deque를 이용하여 풀었습니다
다른 사람의 풀이도 찾아보니 LinkedList를 이용한 사람도 있었습니다.
LinkedList와 Deque 모두 앞과 뒤에 모두 붙일 수 있습니다.

1. 봄
봄에 자신의 나이만큼 영양분을 먹으며 나이가 어린순으로 먹게 됩니다.  
이를 구현하기 위해 매번 정렬하면 시간초과가 발생합니다.  
그래서 deque에서 하나씩 빼서 죽지 않은 경우 다시 넣고  
죽은 경우에는 Queue인 deadList에 넣었습니다.  
죽은 경우 처음 주어진 M에서 나무의 개수를 하나씩 뺍니다.
<br>

2. 여름
주어진 나무가 있는지 check 합니다. deadlist가 비어있지 않다면 하나씩 꺼내가며 비웁니다.  
그리고 2로 나눈 몫을 구해 영양분을 더해줍니다.
<br>

3. 가을
이도 봄과 마찬가지로 탐색합니다. 나무가 심어져 있다면 하나씩 꺼내 나이가 5의 배수인지 확인합니다.  
5의 배수라면 8가지 방향을 탐색하며 심을 수 있다면 심습니다.  
이때 오름차순 상태를 유지하기 위해 deque의 앞에 넣습니다.
<br>

4. 가을
주어진 정보에 맞게 영양분을 공급해줍니다.