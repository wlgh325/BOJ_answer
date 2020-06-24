# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 18870번 좌표 압축 c++  풀이</span>
- 난이도: 실버2
- [백준 18870번 좌표 압축](https://www.acmicpc.net/problem/18870)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 수직선 위의 값이 주어질때 이를 좌표압축해여 표현하여라
2. 주어지는 수의 개수는 1 <= N <= 1,000,000
3. 주어지는 수는 -10^9 <= X <= 10^9
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
vector를 이용하여 중복된 값을 제거하고 lower_bound를 이용하여 좌표 압축을 진행합니다.
<br><br>

# <span style="color: red; font-size:15pt">좌표 압축</span>
좌표 압축은 주어지는 수의 범위가 매우 크지만 그 범위의 수 중에서  
나오는 수는 그렇게 많지 않을때 사용합니다.
만약 범위가 -10억 ~ 10억 인데 숫자는 10만개 밖에 없다면 좌표 압축을 통해 범위를 줄여서 무언가를 수행한다면 더욱 효율적으로 할 수 있습니다.  
이는 세그먼트 트리나 구간 쿼리 처리에 쓰이는 경우가 많다고 합니다.
<br><br>

# <span style="color: red; font-size:15pt">c++ 문제 풀이</span>
1. vector를 이용하여 좌표 값을 모두 입력 받습니다. 이때 원래 상태를 저장하기 위해 두 개의 벡터에 입력받습니다.
2. 중복된 수를 제거하기 위해 오름차순으로 정렬합니다.
3. 그리고 unique를 이용하여 중복된 수를 뒤로 밀고 resize나 erase를 이용해서 뒤로 밀려난 중복된 수를 제거합니다.
4. 그리고 lower_bound를 이용하여 원래의 수를 찾습니다. 즉 index의 위치가 좌표 압축을 한 값이 됩니다.
<br><br>

# <span style="color: red; font-size:15pt">자바 문제 풀이</span>
기본적으로 c++의 풀이한 개념을 그대로 사용하였습니다.  
처음에는 list.remove를 이용하여 지워나갔지만 내부적으로 시간이 많이 걸려서 그렇지 시간초과가 발생하여 새로운 list를 만들어 해결하였습니다.  
1. 원래의 수 순서대로 담은 리스트와 정렬할 리스트 두개를 생성한다.
2. 중복된 수를 제거하기 위해 정렬을 합니다.
3. 정렬된 수를 탐색하며 이전 수와 같지 않은 경우에만 새로운 list에 넣습니다. -> 중복 제거
4. 이렇게 하면 마지막 원소가 들어가지 않기 때문에 마지막 원소를 따로 넣어줍니다.
5. lower_bound를 이용하여 원소의 인덱스를 찾아서 좌표 압축을 진행합니다.
6. 위치를 모두 StringBuilder에 담았다가 한번에 출력합니다.(최대 10만개로 출력 개수가 많기 때문)
<br><br>

# <span style="color: red; font-size:15pt">c++ erase 인자 1개</span>
v.erase(iter)
iter가 가리키는 원소 제거
size만 줄어들고 capacity(할당된 메모리)는 그대로 남는다
erase는 파라미터 하나를 받을때와 두개를 받을 때 다르다
<br><br>

# <span style="color: red; font-size:15pt">c++ erase 인자 두개</span>
v.erase(start, end)
start, end 범위의 인자를 삭제 (start 이상 end 미만의 인자)
<br><br>

# <span style="color: red; font-size:15pt">c++ unique</span>
unique
연속된 중복 원소를 vector의 제일 뒷부분으로 쓰레기값으로 보내버린다.
-> 반환되는 값이 쓰레기값의 첫번째 위치가 된다.
이에 end까지 erase하면 중복된 원소 제거가능
<br><br>
