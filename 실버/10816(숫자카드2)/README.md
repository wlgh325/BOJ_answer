# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10816번 숫자 카드2 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- [백준 10816번 숫자 카드2](https://www.acmicpc.net/problem/10816)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 숫자 카드에는 정수 하나가 적혀있다.
2. 상근이는 숫자 카드 N개를 가지고 있다.
3. 정수 M개가 주어졌을 때, 이 수가 적혀있는 카드를 상근이가 몇 개 가지고 있는지 구하여라.
4. N은 최대 500,000이고 숫자 카드에 적혀 있는 숫자는 최소 -천만, 최대 +천만 이다.
5. M의 최대는 N과 같고 구해야할 숫자도 숫자 카드에 적혀있는 숫자 범위와 같다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
m개에 대해서 n번 탐색하게 되면 최대 2천5백억번을 탐색해야 합니다. 즉 단순하 탐색으로는 찾을 수 없습니다.  
딱 생각난 풀이는 이진탐색이었습니다. 이진탐색을 구현하여 찾아야 할 수를 logn 시간에 찾습니다.
<br><br>

# <span style="color: red; font-size:15pt">이진 탐색</span>
이 문제는 숫자카드 문제와 다르게 일반 적인 이분 탐색을 해서 값을 찾게 되면 시간초과가 납니다.
1. 초기의 left값은 0(왼쪽 끝 인덱스), right 값은 배열의 오른쪽 끝 인덱스입니다.
2. left가 right보다 커질때 까지 반복합니다.
    2.1 (left+right)/2를 계산하여 중간 index(mid)를 찾습니다.
    2.2 찾으려는 값과 배열의 mid의 값이 같으면 그 인덱스를 반환합니다.
    2.3 찾으려는 값이 배열의 mid 위치의 값보다 작으면 mid보다 왼쪽에 있음을 뜻합니다. 그렇기 때문에 왼쪽에서만 찾기 위해서 right 인덱스를 mid보다 1작게 합니다.
    2.4 찾으려는 값이 배열의 mid 위치의 값보다 크면 mid보다 오른쪽에 있음을 뜻합니다. 그렇기 떄문에 오른쪽에서만 찾기 위해서 left index를 mid보다 1크게 합니다.
3. 찾지 못하고 while문을 나오게 되면 -1을 반환합니다.
<br><br>

# <span style="color: blue; font-size:15pt">찾은 수 양쪽으로 같은 수가 더 있는지 검색</span>
이진 탐색으로 인덱스를 찾고 찾은 수 앞뒤로 같은 수가 더있는지 찾도록 하였습니다.  
하지만 이는 최악의 경우 n을 모두 탐색하기 때문에 시간초과가 났습니다.
<br><br>

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

# <span style="color: blue; font-size:15pt">개수 찾기</span>
upper bound의 위치에서 lower bound의 위치를 빼면 원하는 값 k의 개수를 찾을 수 있습니다.  
위의 예에서 lower bound=1, upper bound=3이므로 개수는 2개 입니다.  
단 찾으려는 값이 끝에 나열되어 있는 경우 k를 초과한 값이 처음 나오는 위치가 없습니다. 이 경우를 방지하기 위해 반환하려는 right index에 있는 값이 찾으려는 값 k와 같은 경우 index를 1을 더 더해서 반환하도록 합니다.  
```
예를 들어 1 2 3 3 3이 있을때 3에 대해서 찾습니다.
3에 관한 lower, upper bound를 구합니다.
lower bound = 2 upper bound=4가 나옵니다. 이대로 답을 구하면 2개가 됩니다.
3이 배열의 끝이기 때문입니다. 이 경우는 arr[right]의 값이 구하려는 값 3이기 때문에 right를 1 증가시켜 반환하여 5를 반환합니다.
그러면 5-2=3으로 3개임을 구할 수 있습니다.

lower bound의 경우에는 이상이기 때문에 맨 왼쪽에 있어도 찾을 수 있습니다.
```