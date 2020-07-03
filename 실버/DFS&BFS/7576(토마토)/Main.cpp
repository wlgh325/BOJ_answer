#include <iostream>
#include <queue>
using namespace std;

struct Pos {
	int x;
	int y;
	int day;
	Pos(int x, int y, int day) : x(x), y(y), day(day){

	}
};

int M, N;
int map[1000][1000];
int maxx = 0;
bool visited[1000][1000];
queue<Pos> q;

bool isValidPosition(int x, int y) {
	if (x < 0 || y < 0 || x >= N || y >= M) return false;
	return true;
}

void bfs() {
	// 위, 아래, 왼쪽, 오른쪽
	int xdir[4] = { -1, 1, 0, 0 };
	int ydir[4] = { 0, 0, -1, 1 };
	

	while (!q.empty()) {
		Pos a = q.front();
		q.pop();
		
		int x = a.x;
		int y = a.y;
		int day = a.day;

		maxx = maxx < day ? day : maxx;

		for (int i = 0; i < 4; i++) {
			int dx = x + xdir[i];
			int dy = y + ydir[i];

			if (isValidPosition(dx, dy) && map[dx][dy] == 0 && !visited[dx][dy]) {
				q.push(Pos(dx, dy,day+1));
				map[dx][dy] = 1;
				visited[dx][dy] = true;
			}
		}
	}
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> M >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1) q.push(Pos(i, j, 0));
		}
	}

	bfs();

	bool flag = true;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 0) {
				flag = false;
				break;
			}
		}
	}

	if (flag) cout << maxx << endl;
	else cout << -1 << endl;
	return 0;
}