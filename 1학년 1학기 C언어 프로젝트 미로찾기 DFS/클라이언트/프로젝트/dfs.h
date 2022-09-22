#pragma once

#include "variable.h";

int v[100][100] = { 0 };

void dfs(int x, int y, int c)
{
	if (map[x][y] == 3 && checkCount > c)
	{
		checkCount = c;
		return;
	}

	if (map[x-1][y] != 1 && v[x-1][y] == 0)
	{
		v[x][y] = 1;
		dfs(x-1, y, ++c);
		v[x][y] = 0;
	}

	if (map[x+1][y] != 1 && v[x+1][y] == 0)
	{
		v[x][y] = 1;
		dfs(x+1, y, ++c);
		v[x][y] = 0;
	}

	if (map[x][y+1] != 1 && v[x][y + 1] == 0)
	{
		v[x][y] = 1;
		dfs(x, y + 1, ++c);
		v[x][y] = 0;
	}

	if (map[x][y-1] != 1 && v[x][y - 1] == 0)
	{
		v[x][y] = 1;
		dfs(x, y - 1, ++c);
		v[x][y] = 0;
	}
}