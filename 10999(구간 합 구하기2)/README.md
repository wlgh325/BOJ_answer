# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10999번 구간 합 구하기 문제2 자바(java)  풀이</span>
- 랭크 : 플레티넘4
- [백준 10999번 구간 합 구하기](https://www.acmicpc.net/problem/10999)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. N개의 수가 주어진다.
2. 중간에 수의 변경이 빈번히 일어난다. 그리고 어떤 부분의 부분합을 구하려 한다.
3. 쿼리가 주어진다.
4. 쿼리의 맨 앞이 1이면 값을 변경, 2이면 구간 합을 구한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
수의 변경이 빈번하게 일어나고 계속 해서 구간합을 구해야 하기 때문에 세그먼트 트리를 이용하면 빠르게 구할 수 있습니다.  
수를 변경하는데 O(logN), 구간합을 구하는데 O(logN) 이를 M번 하면 O(MlogN)정도면 구할 수 있을 것 같습니다.  
그리고 입력받는 값의 범위가 크기 때문에 합을 저장하는 변수와 tree를 long형으로 선언합니다.
1. tree를 만들고 base index를 구합니다.
2. tree의 base index 부터 N개의 값을 넣어줍니다.
3. 구간 합을 구하며 tree 초기화를 해줍니다.
4. 쿼리를 입력 받아서 a에 따라 값을 변경하거나(update) 구간 합(range_sum)을 구합니다.

1. 일반적인 방법으로 구간의 값에 더해진 값을 써주고 update하는 방법 
    구간의 합을 구햐는 것은 O(NlogN)의 시간이 걸립니다.
    값을 변경하는 것은 구간개수 만큼(end-start+1) 변경해야 하기 때문에 O(NlogN)이 걸립니다.
2. 수 1개가 아닌 여러개의 값을 변경한다. range_sum()
    이 방법도 결국은 update를 하기 위해 트리의 모든 노드를 방문해야 합니다.
    트리의 노드 개수는 NlogN이기 때문에 이도 결국 update하는 경우 O(NlogN)이 걸립니다.
    모두 update 쿼리로만 들어오게 된다면 O(N(M+K)logN)이 되므로 시간초과가 나옵니다.
3. segment tree lazy propogation (세그먼트 트리 나중에 업데이트 하기)
    더 이상 update를 수행하지 않고 나중에 다시 업데이트를 수행하러 그 노드를 방문했을 때 업데이트 한다.
	그러기 위해서 leaf노드가 아니라면 자식에게 lazy 값을 물려주면 됩니다.
    이렇게 해서 구간에 대한 갱신을 더 빠르게 할 수 있습니다. 밑에 까지 내려가서 갱신하지 않고
    필요할때 나중에 갱신하기 때문입니다.
    [segment tree lazy propogation 설명 참조](https://www.acmicpc.net/blog/view/26)
