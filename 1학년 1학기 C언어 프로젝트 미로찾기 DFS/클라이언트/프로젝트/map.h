#pragma once

#include <string.h>
#include <stdio.h>
#include <direct.h>

#include "variable.h";

#ifndef _MAX_PATH
#define _MAX_PATH 260
#endif

void draw_map()
{
	for (int i = 0; i < mX; i++)
	{
		move_cursor(0+2, i+1);
		for (int j = 0; j < mY; j++)
		{
			if (map[i][j] == 0) printf("  ");
			else if (map[i][j] == 1) printf("бс");
			else if (map[i][j] == 3) printf("б┌");
		}
	}
}

void get_map(char*a)
{
	char strBuffer[_MAX_PATH] = { 0, };
	char *pstrBuffer = NULL;

	pstrBuffer = _getcwd(strBuffer, _MAX_PATH);
	strcat(pstrBuffer, "\\");
	strcat(pstrBuffer, a);

	int n;

	map[100][100] = { -1 };

	FILE *f = fopen(pstrBuffer, "r");

	if (f == NULL)
	{
		printf("Cannot read Mapdata");
	}

	fscanf(f, "%d %d", &mX, &mY);

	for (int i = 0; i < mX; i++)
	{
		for (int j = 0; j < mY; j++)
		{
			fscanf(f, "%d", &map[i][j]);
		}
	}
}

/*
map code
empty 0
block 1
player 2
*/