#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int N, M;
bool visited[1001];
vector<vector<int>> graph;

void bfs(int start) {
	queue<int> q;

	q.push(start);
	visited[start] = true;

	while (!q.empty()) {
		int a = q.front();	// q의 맨 앞에서 꺼낼 값
		q.pop();	// 실제 꺼내기

		vector<int>::iterator it;
		for (it = graph[a].begin(); it != graph[a].end(); it++) {
			if (!visited[*it]) {
				visited[*it] = true;
				q.push(*it);
			}
		}
	}
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin >> N >> M;
	// 2차원 벡터 초기화
	for (int i = 0; i <= N; i++) {
		vector<int> temp;
		graph.push_back(temp);
	}

	// 값 입력 받기
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		// 그래프 저장
		graph.at(a).push_back(b);
		graph.at(b).push_back(a);
	}
	
	// 연결 요소 탐색
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		if (!visited[i]) {
			bfs(i);
			cnt++;
		}
	}
	
	cout << cnt << endl;
	return 0;
}