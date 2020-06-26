#include <iostream>
#include <string>	// string type
#include <vector>
#include <algorithm>
using namespace std;


int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	int N, M;
	cin >> N >> M;

	vector<string> vec;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		vec.push_back(str);
	}
	sort(vec.begin(), vec.end());

	vector<string> ans;
	for (int i = 0; i < M; i++) {
		string target;
		cin >> target;
		if (binary_search(vec.begin(), vec.end(), target)) {
			ans.push_back(target);
		}
	}

	if (ans.size() != 0)
		sort(ans.begin(), ans.end());
	cout << ans.size() << "\n";
	for(string s: ans)
		cout << s << "\n";
	return 0;
}