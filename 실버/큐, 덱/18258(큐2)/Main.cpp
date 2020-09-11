#include <iostream>
#include <queue>
#include <string>
#include <cstring>

#define endl '\n'
using namespace std;


int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int N;
	queue<int> q;
	string ans;

	cin >> N;

	for (int i = 0; i < N; i++) {
		char s[10];
		cin >> s;
		
		if (!strcmp(s, "push")) {
			int a;
			cin >> a;
			q.push(a);
		}
		else if (!strcmp(s, "pop")) {
			if (q.empty()) ans += "-1\n";
			else {
				ans += to_string(q.front()) + "\n";
				q.pop();
			}
		}
		else if (!strcmp(s, "size")) {
			ans += to_string(q.size()) + "\n";
		}
		else if (!strcmp(s, "empty")) {
			if (q.empty()) ans += "1\n";
			else ans += "0\n";
		}
		else if (!strcmp(s, "front")) {
			if (q.empty()) ans += "-1\n";
			else ans += to_string(q.front()) + "\n";
		}
		else {
			if (q.empty()) ans += "-1\n";
			else ans += to_string(q.back()) + "\n";
		}
	}

	cout << ans << "\n";
	return 0;
}