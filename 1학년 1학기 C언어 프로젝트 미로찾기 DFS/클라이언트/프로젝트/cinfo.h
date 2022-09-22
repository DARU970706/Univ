#pragma once
#include <stdio.h>
#include "variable.h"
#include "cursor.h"
#include "color.h"
#include "character.h"

void drawInfo(void)
{
	int temp = get_Pos(1+1, mY);

	for (int i = 0; i < 15; i++)
	{
		temp = get_Pos(1 + 1, mY+i);
		move_cursor(temp + 2, 1);
		printf_s("■");
	}

	for (int i = 0; i < mX; i++)
	{
		temp = get_Pos(mX +1+i, mY);
		move_cursor(temp + 2+28, 1+i);
		printf_s("■");
	}
	for (int i = 0; i < 15; i++)
	{
		temp = get_Pos(mX + 1, mY + i);
		move_cursor(temp + 2, mX);
		printf_s("■");
	}

	temp = get_Pos(mX + 2, mY);
	move_cursor(temp + 4, 3);
	printf_s("남은 이동 횟수 : %d", checkCount);

}