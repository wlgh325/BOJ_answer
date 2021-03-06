# BOJ 1713번 후보 추천하기 문제 자바(java)  풀이
- 랭크 : 골드5
- 백준 온라인 저지(BOJ) 1713번 후보 추천하기 문제 자바 풀이
- [백준 1713번 후보 추천하기](https://www.acmicpc.net/problem/1713)

## [티스토리 주소](https://hoho325.tistory.com/81?category=780777)

# 문제정리
1. 특정 학생을 추천 하면 반드시 사진 틀에 걸린다.
2. 비어있는 사진 틀이 없는 경우, 현재 까지 추천 횟수가 가장 적은 학생을 제거한다.
    * 2.1 가장 적은 학생이 여러명일 경우 가장 오래전에 게시한 학생을 제거한다.
3. 현재 사진이 게시된 학생이 추천을 받은 경우 추천수만 증가시킨다. (시간 갱신 안됨)
4. 사진틀에 게시된 학생이 삭제되는 경우 다시 추천을 받아도 이전 추천 수가 남아있지 않는다( 추천 받은 수 0으로 초기화 )

# ArrayList 정렬하는 방법
## Comparable의 compareTo 오버라이드
```
compareTo는 현재 멤버 변수의 값이 파라미터로 넘어온 값보다
작으면 음수를 리턴
같으면 0을 리턴
크면 양수를 리턴 하도록 작성한다.
```

## Comparator 구현 (compare 오버라이드)
```
Collections.sort 메소드는 두 번째 인자로 Comparator 인터페이스를 받을 수 있다
Comparator 인터페이스의 compare 메소드를 오버라이드 한다.
규칙은 위의 Comparator의 compareTo와 동일하다.
```

## 문제풀이
이 문제는 정렬이 핵심이라고 생각한다. 그리 어렵지 않다 생각했는데 꽤 많이 틀렸다...  
자바 comparator 구현을 더 공부해야겠다.
1. 걸려있는 사진에 관한 정보를 관리하는 frame이라는 이름의 arraylist를 생성한다.
2. 이미 사진틀에 걸려있는지 확인하고 걸려있다면 추천 수를 증가시킨다.
3. 그렇지 않다면 걸 수 있는 자리가 있는지 확인한다.
    * 3.1 걸 수 있는 자리가 있다면 list에 새로 추가한다.
    * 3.2.1 그렇지 않다면 점수 순 우선 정렬하고 점수가 같다면 idx를 기준 오름차순 정렬한다. -> 점수가 가장 낮고 idx가 작은 후보가 list의 0번 인덱스에 있게된다.
    * 3.2.2 가장 왼쪽 항목을 지우고 새로운 후보를 등록한다.
4. 모든 후보를 등록하고 sorting을 통해 번호 순으로 정렬한다.
5. 출력한다. 
