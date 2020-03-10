# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2609번 최소공배수와 최대공약수 자바(java)  풀이</span>
- 랭크 : 실버5
- [백준 2609 최소공배수와 최대공약수](https://www.acmicpc.net/problem/2609)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
gcd(최소공배수)를 구해서 gcd를 가지고 lcm(최대공약수)을 구하면 됩니다.  
gcd는 말 그대로 공통되는 배수 중에 제일 작은 배수를 나타냅니다.  
즉 1부터 나눠서 처음으로 동시에 떨어지는 수가 있다면 그것이 GCD가 됩니다.  
lcm은 gcd를 활용해서 구할 수 있습니다. 공식을 이용하여 구하거나 반복문을 돌며 구하면 됩니다.  
1. 공식으로 구하기
lcm을 구하는 공식은 다음과 같습니다.
```
lcm = (a*b) / gcd
```
<br><br>

2. 또는 gcd의 배수로 나눠가며 처음으로 둘다 나누어 떨어지는 수를 구하면 lcm이 됩니다.
```java
    int lcm = 0;	// least common multiple
    if(gcd == 1)
        lcm = a*b;
    else {
        int i = 1;
        while(true) {
            lcm = gcd*i;
            i++;
            if(lcm % a == 0 && lcm % b == 0) 					
                break;				
        }	
    }
```
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. 열의 index를 먼저 정한다. (j)
2. 행의 iddex를 정한다.(t)
3. 열과 행의 index가 정해졌으므로 t부터 8개의 행을 비교한다. (i (t부터 8칸 확인))
4. 행이 체스판과 똑같은 경우는 열의 문자를 각각 탐색하지 않는다.
    만약 같지 않다면 체스판의 행들의 문자들을 각각 비교해서 다른 것의 개수를 더해나간다. (k) 이때 j부터 8칸만 확인한다.
5. 최소값을 갱신하고 sum 변수들을 초기화 시켜준다.
6. 두 체스판의 최소값인 min1과 min2 중 더 작은 값을 출력한다.
