#include <iostream>
#include <cstring> // strcmp
#include <string>	// string type
#include <map>
using namespace std;

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	int M=0, num=0, check=0;
	long set = 0;
	char cmd[7];
	map<string, int> m;
	m["add"] = 0; m["remove"] = 1; m["check"] = 2;
	m["toggle"] = 3; m["all"] = 4; m["empty"] = 5;

	cin >> M;
	for (int i = 0; i < M; i++) {
		cin >> cmd;
		if (strcmp(cmd, "all") && strcmp(cmd, "empty")) {
			cin >> num;
		}
		check = 1 << (num - 1);
		switch (m[cmd]) {
			case 0:
				set |= check;
				break;
			case 1:
				set &= ~check;
				break;
			case 2:
				if ((set & check) == check)
					cout << 1 << "\n";
				else
					cout << 0 << "\n";
				break;
			case 3:
				if ((set & check) == check)
					set &= ~check;
				else
					set |= check;
				break;
			case 4:
				set = (1 << 20) - 1;
				break;
			case 5:
				set = 0;
				break;
		}
	}
	return 0;
}