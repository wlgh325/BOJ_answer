# BOJ 14501 퇴사 문제 자바(java)  풀이
- 랭크 : 실버4
- 백준 온라인 저지(BOJ) 14501 퇴사 문제 자바 풀이
- [백준 14501 퇴사](https://www.acmicpc.net/problem/14501)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 각각의 상담은 상담을 완료하는데 걸리는 기간(t)과 상담을 했을 때 받을 수 있는 금액(p)로 이루어져 있다.
2. 1일에 3일이 걸리는 상담을 하게되면 4일 부터 상담을 할 수 있다.
3. n+1일 째는 회사에 있지 않다.

* 백준이가 얻을 수 있는 최대 수익은??

# 문제풀이
이 문제는 조합을 알면 풀 수 있다.
1. 모든 가능한 경우를 구해 가며 수익을 계산해 나간다.
2. 상담할 날을 1개만 선택하는 경우, 2개, n개 선택하는 경우 모두 계산해본다.(모든 상담이 하루만에 끝날 수 있음)
3. 상담을 하게되면 참조할 날짜를 증가시킨다. 그리고 증가된 날짜 이후의 상담만 check한다.
4. 상담을 마치는 날짜가(증가된 날짜 i) n을 넘으면 for문을 탈출하고 pay에 더하지 않는다.
5. 계산을 마치고 Math.max 함수를 이용해 최대값을 계속 갱신하여 출력한다.