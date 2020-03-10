# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10814번 나이순 정렬 문제 자바(java)  풀이</span>
- 랭크 : 실버5
- 풀이시간: 5분
- [백준 210814 나이순 정렬](https://www.acmicpc.net/problem/10814)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 회원들의 나이와 이름이 주어질때 나이 순으로 정렬하여라. (가입한 순서대로 주어진다.)
2. 나이가 같다면 회원 먼저 가입한 사람이 앞에 오도록 하여라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 완전 간단하다. 자바로 객체배열또는 객체list의 comparator만 구현할 수 있다면 풀 수 있다.  
객체는 나이와 이름을 담도록 class를 생성합니다.  
배열을 이용한다면 Arrays.sort(arr, comparator구현) list라면 Collections.sort(list, comparator구현)을 이용하면 된다.  
comprater를 구현하여 sorting하고 출력하면 됩니다.  
comparator구현에 익숙하지 않다면 다음 [좌표 정렬하기 문제](https://www.acmicpc.net/problem/11650)와도 거의 같기 때문에 같이 풀어보는 것을 추천드립니다!!
<br><br>

# <span style="color: red; font-size:15pt">Comparator 구현</span>
다음과 같이 comparator에서 compare함수를 override하여 구현한다.
1. 왼쪽의 인자가 오른쪽의 인자보다 작다면 -1을 반환하게 한다.
2. 반대라면 1을 반환한다.
3. 같다면 0을 반환한다.
오름차순 정렬과 내림차순 정렬의 값은 반대로 한다.
```java
    // 나이가 우선, 같으면 가입한 순(index 순)
    Arrays.sort(peoples, new Comparator<People>() {
        @Override
        public int compare(People p1, People p2){
            // 왼쪽의 인자 값이 더 작은 경우 -1 return
            if(p1.age < p2.age)
                return -1;
            // 오른쪽의 인자 값이 더 큰 경우 1 return
            else if(p1.age > p2.age)
                return 1;
            return 0;   // 같은 경우 0 return
        }
    });
```