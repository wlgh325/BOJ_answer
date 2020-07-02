#include <iostream>
#include <string>
#include <stack>
using namespace std;

int n;
int arr[100000];
string res;

void solve() {
	int idx = 0;
	int num = 1;
	stack<int> st;

	while(1) {
        // stack이 비어있지 않을 경우에만 탐색
		if (st.size() != 0) {
            // stack의 맨 위에 있는 수가 수열의 idx번째 수와 같은 경우 pop
			if (st.top() == arr[idx]) {
				idx++;
				st.pop();
				res += "-\n";
				if (idx == n) break;
				continue;
			}
            // stack의 맨 위에 있는 수가 n 이상인 경우 break
            // stack에 들어갈 수 있는 수는 최대 n이기 때문에
            // 들어갔다는 것은 수열을 만들 수 없어서 숫자가 계속 들어감을 의미
			else if (st.top() > n)
				break;
		}

        // stack이 빈 경우 무조건 stack에 push 해야함
		st.push(num);
		num++;
		res += "+\n";
	}

	if (st.size() != 0)
		cout << "NO" << "\n";
	else
		cout << res;
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin >> n;

	for (int i = 0; i < n; i++)
		cin >> arr[i];

	solve();
	return 0;
}