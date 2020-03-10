# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 11650번 좌표 정렬하기 문제 자바(java)  풀이</span>
- 랭크 : 실버5
- 풀이시간: 5분
- [백준 1650번 좌표 정렬하기](https://www.acmicpc.net/problem/11650)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 2차원 평면 위의 점 N개가 주어진다.
2. 주어진 점을 x좌표가 증가하는 순으로 정렬하라.
3. x좌표가 같다면 y좌표가 증가하는 순서로 정렬하여라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 완전 간단하다. 자바로 객체배열또는 객체list의 comparator만 구현할 수 있다면 풀 수 있다.  
객체는 나이와 이름을 담도록 class를 생성합니다.  
배열을 이용한다면 Arrays.sort(arr, comparator구현) list라면 Collections.sort(list, comparator구현)을 이용하면 된다.  
comprater를 구현하여 sorting하고 출력하면 됩니다.  
comparator구현에 익숙하지 않다면 다음 [좌표 정렬하기 문제](https://www.acmicpc.net/problem/10814)와도 거의 같기 때문에 같이 풀어보는 것을 추천드립니다!!
<br><br>

# <span style="color: red; font-size:15pt">Comparator 구현</span>
오름차순 정렬시  
왼쪽 인자의 값이 더 작다면 -1, 더 크다면 1 같다면 0을 반환합니다.  
하지만 이 문제는 같을 경우 y도 오름차순으로 정렬해야하기 때문에 0인 경우도 마찬가지 방식으로 값을 반환하게 구현합니다.  
내림차순의 경우 반환값을 반대로 하면 됩니다.  
```java
    Arrays.sort(arr, new Comparator<Pos>() {
        @Override
        public int compare(Pos p1, Pos p2){
            // x기준 오름차순 정렬
            if(p1.x < p2.x)
                return -1;
            else if(p1.x > p2.x)
                return 1;
            else{
                // x좌표가 같은 경우 y기준 오름차순 정렬
                if(p1.y < p2.y)
                    return -1;
                else if(p1.y > p2.y)
                    return 1;
            }
            return 0;
        }
    });
```
