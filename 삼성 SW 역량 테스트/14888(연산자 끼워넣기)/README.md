# BOJ 14888 연산자 끼워넣기 문제 자바(java)  풀이
- 랭크 : 실버1
- 백준 온라인 저지(BOJ) 14888 연산자 끼워넣기 문제 자바 풀이
- [백준 14888 연산자 끼워넣기](https://www.acmicpc.net/problem/14888)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 연산자 우선 순위 무시하고 앞에 부터 계산
2. 주어진 숫자는 순서 안 바뀜
3. 나눗셈은 몫만 취한다.
4. 음수를 양수로 나눌때 ex) -2 / 3
5. 양수로 바꾼 후 몫을 취하고 그 몫을 음수로 바꾼 것과 같다		

* 식의 결과의 최솟값과 최댓 값 구하기

# 문제풀이
1. 숫자들을 입력받는다.
2. 연산자의 개수 만큼 해당하는 연산자를 연산자 배열(opList)에 넣어준다.
    * 1010이면 '+' 1개 '*' 1개 씩 들어간다.
3. 그리고 permutation을 이용하여 가능한 순열을 모두 구하며 계산을 한다.
4. 계산된 모든 값은 list에 있으므로 Collections.max와 Collections.min을 이용해 최대, 최소 값을 구한다.

## Collections.sort vs Collections.max, min
최대 최소를 구하는 방법에는 2가지씩 존재한다.
결론 부터 말하자면 소팅을 하고 구하는 것보다 바로 max와 min을 이용하는게 훨씬 빠르다.
이 문제의 경우 max와 min으로 바꿔서 하니까 속도가 2배나 더 빨라졌다.
각 1.2와 2.2 방법을 사용해서 최대, 최소를 구하도록 하자.
1. 최대 값 구하기
    * 1.1
    ```java
    ArrayList<Integer> list = new ArrayList<>();
    Collections.sort();
    System.out.println(list.get(list.size()-1));
    ```

    * 1.2
    ```java
    ArrayList<Integer> list = new ArrayList<>();
    System.out.println(Collections.max(list));
    ```
2. 최소 값 구하기
    * 2.1
    ```java
    ArrayList<Integer> list = new ArrayList<>();
    Collections.sort();
    System.out.println(list.get(0);
    ```

    * 2.2
    ```java
    ArrayList<Integer> list = new ArrayList<>();
    System.out.println(Collections.min(list));
    ```
