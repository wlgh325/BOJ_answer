# BOJ 10826번 피보나치 수4 문제 자바 풀이
- 백준 온라인 저지(BOJ) 10826번 피보나치 수4 자바 풀이
- [피보나치 수4](https://www.acmicpc.net/problem/10826)

## 티스토리 
[티스토리 주소](https://hoho325.tistory.com/72)


## 문제 풀이
```
이 문제의 n이 최대 10000이라는 것에 주의해야 합니다.
그냥 보기에는 되게 작아보이지만 10000번째 피보나치 수는 엄청나게 큰 수가 나옵니다.
그래서 long형으로도 구할 수 없어서 overflow가 납니다.
(64bit long형의 최대 값: 9223372036854775807 (대충 920경))
```
1. n을 입력받는다.
2. BigInteger 배열을 초기화한다. (i번째 idx : i번째 피보나치 수)
3. for문을 순회하며 이전 두 수를 더해서 다음 피보나치 수를 구하며 arr 배열에 저장한다.
4. BufferedWrite를 이용해 출력한다.

# 자바(java) BigInteger
```
java에서 큰 수를 가지고 어떤 연산을 할때
편리하게 다룰 수 있는 패키지를 제공합니다
java.math.BigInteger를 import하면 사용할 수 있습니다.
```

# BigInteger 선언과 연산
```java
// 선언
BigInteger big = new BigInteger("123456789012345678901234567890");
BigInteger big2 = new BigInteger("12345");

// 덧셈
BigInteger big3 = big.add(big2);

// 뺄셈
BigInteger big4 = big.subtract(big2);

// 곱하기
BigInteger big5 = big.multiply(big2);
BigInteger big5 = big.multiply(BigInteger.valueOf(2));	// int형을 넣으면 안됨

// 나누기
BigInteger big6 = big.divide(big2);

// 상수
BigInteger zero = BigInteger.Zero; // BigInteger type 0
BigInteger one = BigInteger.One;	// BigInteger type 1

// 변환
BigInteger trans = BigInteger.valueOf(9000000000);
```