#define _CRT_SECURE_NO_WARNINGS

#include <string.h>
#include <conio.h>
#include <time.h>

//생성한 헤더파일들
#include "cursor.h";
#include "map.h";
#include "variable.h"
#include "character.h"
#include "color.h"
#include "cinfo.h"
#include "dfs.h"
//move_cursor(3, 3);

void start(char *t)
{
	pX = 1;
	pY = 1;
	printf("                                                                 %s", t);
	checkCount = 99999;
	get_map(t);
	dfs(1, 1, 0);
	moveCount = checkCount;
	draw_map();
	drawInfo();
	set_first();
	move();
	system("cls");
	printf_s("Misson Clear\n");
	Sleep(2000);
}
int main(void)
{
	//char* temp=t();
	//printf("%s", temp);

	cursor_hide();

	start("map1.txt");
	start("map2.txt");
	start("map3.txt");
	start("map4.txt");
	start("map5.txt");

	printf_s("All Misson Clear");
	Sleep(20000);

	return 0;
}

