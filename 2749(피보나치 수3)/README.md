# BOJ 2749번 피보나치 수3 문제. java 풀이
- 백준 온라인 저지(BOJ) 2749번 피보나치 수 자바 풀이
- [백준2749번 피보나치 수](https://www.acmicpc.net/problem/2749)

## [티스토리 주소](https://hoho325.tistory.com/)

# 피사노 주기
> 피사노 주기를 이용한다.
주기의 길이가 P이면 N번째 피보나치 수를 M으로 나눈 나머지는 N%P번째 피보나치 수를 M으로 나눈 나머지와 같다.
M=10^k일 때, k >2 라면, 주기는 항상 15*10^k-1이 된다.
이 문제에서는 M=10^6 이기 때문에, 주기는 15*10^5가 된다

하지만 이 사실을 몰라도 주기를 직접 구해서 풀 수 있다.
주기를 직접 구하는 것은 아래를 참조해주세요!!
> [피사노주기](https://github.com/wlgh325/BOJ_answer/tree/master/9471(%ED%94%BC%EC%82%AC%EB%85%B8%20%EC%A3%BC%EA%B8%B0))

# mod 성질 이용하기
> (a + b)mod m = (a mod m + b mod m ) mod m

## 문제 풀이
```
DP(Dynamic Programming)를 이용해서 풀어야한다
재귀를 그대로 쓰면 반복된 계산들이 많다
그래서 DP를 이용해 다시 계산하지 않도록 이전에 계산한 값을 저장하도록 하면 풀 수 있다
```
1. n을 입력받는다.
2. last1과 last2 수를 1로 초기화 한다.
3. result 변수에 last1과 last2를 더해 값을 구하고 값을 업데이트 해준다.
3. 3-1. last1, last2, result
		last2, result, new_result 와 같이 값이 update 된다
4. 이렇게 주기 만큼 피보나치 수를 구한다.
5. n을 주기로 나눈 나머지의 index를 참조하여 답을 구한다.
6. 이때 형변환을 잘해야한다. int idx = (int)(n%p); 이렇게 해야한다. int idx = (int)n%p로 했다가 이상한 값 나와서 틀렸었다.
```

```
