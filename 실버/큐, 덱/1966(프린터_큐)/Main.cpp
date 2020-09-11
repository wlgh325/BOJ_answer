#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;

struct Print {
	int idx;
	int priority;

	Print(int idx, int priority) : idx(idx), priority(priority) {

	}
};


int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	// 현재 q의 가장 앞에 있는 문서의 중요도 확인
	// 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 q의 가장 뒤에 배치
	// 그렇지 않다면 바로 인쇄
	int T;
	int N, target;

	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		cin >> N >> target;
		queue<Print> q;
		vector<int> vec;
		for (int i = 0; i < N; i++) {
			int a;
			cin >> a;
			vec.push_back(a);
			q.push(Print(i, a));
		}
		
		sort(vec.begin(), vec.end(), greater<int>());	// vector 내림차순
		int aa = 0;
		while (1) {
			if (q.front().priority < vec[aa]) {
				q.push(q.front());	// 우선순위가 더 높은게 있으므로 뒤에 넣고 나중에 처리
				q.pop();
			}
			else if (q.front().priority == vec[aa]) {
				if (q.front().idx == target) {
					cout << aa+1 << endl;
					break;
				}
				else {
					q.pop();
					aa++;
				}
			}
		}
	}

	return 0;
}