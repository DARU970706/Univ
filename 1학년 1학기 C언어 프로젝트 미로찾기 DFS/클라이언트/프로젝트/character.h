#pragma once

#include <stdio.h>
#include "variable.h"
#include "cursor.h"
#include "color.h"

int get_Pos(int x, int y)
{
	int c = 0;
	for (int i = 0; i < y; i++)
	{
		if (map[x][i] == 0)c = c + 2;
		else c=c+2;
	}
	return c;
}

void set_first(void)
{
	//set player position
	color(4);
	int temp = get_Pos(pX, pY);
	map[pX][pY] = 2;
	move_cursor(temp + 2, pY + 1);
	printf_s("■");
	color(7);
}

int move_char(int n)
{
	int tX, tY;
	tX = pX;
	tY = pY;

	if (pf == 0) return 0;
	pf = 0;

	if (n == 72)tY--;
	if (n == 80)tY++;
	if (n == 75)tX--;
	if (n == 77)tX++;

	if (map[tY][tX] == 0)
	{
		;
		

		int temp = get_Pos(mX + 2, mY);

		if (moveCount == 0)
		{
			tX = 1;
			tY = 1;
			moveCount = checkCount;
		}
		moveCount--;

		move_cursor(temp + 4, 3);
		printf_s("                      ");
		move_cursor(temp + 4, 3);
		printf_s("남은 이동 횟수 : %d", moveCount);

		color(4);

		temp = get_Pos(pY, pX);
		map[pY][pX] = 0;
		move_cursor(temp+2, pY+1);
		printf_s("  ");

		temp = get_Pos(tY, tX);
		map[tY][tX] = 2;
		move_cursor(temp+2, tY+1);
		printf_s("■");
		color(7);
		pX = tX;
		pY = tY;
	}
	if (map[tY][tX] == 3)
	{
		return 1;
	}
	return 0;

}

void move(void)
{
	int button = 0;
	time_t temp;
	time(&temp);

	while (true)
	{
		Sleep(50);
		time(&temp);
		pf = 1;
		if (_kbhit())
		{
			button = _getch();
			fflush(stdin);
			if (move_char(button) == 1)
			{
				return;
			}
		}
	}
}
