# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 3020번 개똥벌레 자바(java)  풀이</span>
- 랭크 : 골드5
- [백준 3020번 개똥벌레](https://www.acmicpc.net/problem/3020)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 동굴의 길이는 N미터, 높이는 H미터이다.
2. 첫 번째 장애물은 항상 석순이다. 그 다음에는 종유순과 석순이 번갈아서 나온다.
3. 개똥벌레는 장애물을 피하지 않는다. 갈 구간을 정하고 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다.
4. 개똥벌레가 파괴해야 하는 장애물의 최소값과 그러한 구간이 총 몇 개 있는지 구하여라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. 아래에 있는 장애물을 기준으로 한다.
2. 1번 구간에서의 파괴해야 하는 장애물의 개수는 높이-1 보다 큰 위에서 달린 장애물의 개수 + 아래에서 1보다 큰 장애물의 개수이다.
3. 즉 N번 구간에서 파괴해야 하는 장애물의 개수는 높이-N 보다 큰 위에서 달린 장애물의 개수 + 아래에서 N보다 큰 장애물의 개수이다.
이것만 이해한다면 문제를 풀 수 있습니다.
<br>

1. naive하게 높이 1부터 H까지 차근 차근 합을 구해갈 수 있습니다. 합은 위에서 설명한대로 구합니다. 그리고 min 값을 갱신해 나갑니다.
    만약 min값과 같은 sum이 나왔다면 cnt를 증가시켜줍니다.
2. binarySearch를 이용하여 개수를 구할 수 있습니다.
    합들을 모두 list에 넣고 binary search를 하기 위해 정렬합니다.
    그러면 min 값은 list의 맨 앞에 값이 됩니다.
    개수는 min 값 이상의 수가 처음 나오는 위치(lower bound)와
    min 값 보다 큰 수가 처음나오는 위치(upper bound)를 찾아서
    두 수를 빼주면 min 값과 같은 값을 갖는 sum의 개수를 구할 수 있습니다.
<br>

# <span style="color: blue; font-size:15pt">Lower Bound Binary Search</span>
binary serach를 통해 lower bound를 찾았습니다.  
lower bound는 원하는 값 k **이상**의 수가 처음 나오는 위치를 찾는 것입니다.  
이는 binary search의 조건을 약간만 변형하면 구할 수 있습니다.  
예를들어 1 2 2 5 6 에서 2의 lower bound를 찾는다면 index가 1이 됩니다.  
1. left = 0, right = 4, mid = (4+0)/2 = 2
2. 2에 있는 값은 2 입니다. 처음 나오는 위치를 찾아야 하기 때문에 왼쪽 부분을 탐색하야 합니다.
	이떄 이진탐색과 다른 점은 'right=mid-1'이 아닌 'right=mid'로 합니다.
	왼쪽에 같은 수가 더 없어서 처음 나오는 위치가 될 수 있기 때문입니다.
3. left=0, right=2, mid=1 입니다. 1 위치에 있는 값은 2 입니다.
4. left=0, right=1, mid=0 입니다. 0 위치에 있는 값은 1 입니다.
5. left=1, right=1 으로 같아졌으므로 반복을 끝냅니다. right 값을 반환합니다.
<br><br>

# <span style="color: blue; font-size:15pt">Upper Bound Binary Search</span>
이는 원하는 값 k를 **초과**한 값이 처음 나오는 위치를 찾습니다.  
이도 lower bound와 거의 같습니다. lower bound는 찾는 값이 중간에 있는 값보다 같거나 작은 경우 right=mid로 해주었습니다.  
하지만 upper bound는 작은 경우에만 'right=mid'로 바꿔줍니다. 이상이 아닌 초과한 값이 처음나오는 위치를 찾기 때문입니다.  
위와 같은 예로 살펴보겠습니다.  
1. left=0, right=4, mid = 2
2. 2에 있는 값은 2로 같기 때문에 left=mid+1로 해줍니다.
3. left=3, right=4, mid=3
	index 3의 값은 5로 찾으려는 값보다 큽니다. right=mid 해줍니다.
4. left=3, right=3으로 left와 right값이 같아졌으므로 while문을 탈출하고 right를 반환합니다.
즉 upper bound의 위치는 3이 됩니다.
<br><br>