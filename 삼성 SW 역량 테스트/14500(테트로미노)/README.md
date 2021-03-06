# BOJ 14500번 테트로미노 문제 자바(java)  풀이
- 랭크 : 골드5
- 백준 온라인 저지(BOJ) 14500번 테트로미노 문제 자바 풀이
- [백준 14500번 테트로미노](https://www.acmicpc.net/problem/14500)

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# 문제정리
1. 폴리오미노: 크기가 1x1인 정사각형을 여러개 이어서 붙인 도형
2. 폴리오미노는 다음과 같은 조건을 만족하여야 한다.
    2.1 정사각형은 서로 겹치면 안 된다.
    2.2 도형은 모두 연결되어 있어야 한다.
    2.3 정사각형의 변끼리 연결되어 있어야 한다.즉, 꼭짓점과 꼭짓점만 맛닿아 있어야 한다.
3. 정사각형 4개를 이어 붙인 폴리오미노는 특별히 테트로미노라고 부른다.
4. NxM의 종이가 있으면 1x1의 크기로 나누어져 있다. 또한 각 칸마다 정수가 쓰여져 있다.
5. 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여져 있는 수들의 합을 최대로 하는 프로그램을 작성하여라.
6. 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
<br><br>

# 문제풀이
브루트 포스의 방법으로 모든 경우를 일일히 따져주었습니다.  
테트로미노를 회전, 대칭 시킬 수 있음을 주의합니다.  
주어진 5개의 테트로미노를 회전 대칭 시키면 총 19가지의 서로다른 테트로미노가 있습니다.  
모양 대로 인덱스를 구해서 더해보며 더한 뒤 max 값과 비교하여 갱신한다.  
인덱스만 잘 맞춰준다면 그리 어렵지는 않다.