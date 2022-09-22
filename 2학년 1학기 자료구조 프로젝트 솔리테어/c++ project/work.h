#pragma once
#include <ctime>
#include <string.h>

int stack[140];
int visible[140];
int top[7];
int st[7];

int stock[24];
int stp;
int point;
int pr[4];

int pe[24];
int peTop;
int outc=0;

int gamef = 0;

TCHAR name[4] = { 'D','S','H','C' };
TCHAR cardName[13] = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};

int isEmpty(int n)
{
	if (top[n] == st[n]-1) return 1;
	else return 0;
}

void stackPush(int n, int data)
{
	stack[++top[n]] = data;
	visible[top[n]] = true;
}

int stackPop(int n,bool vb)
{
	int temp;

	if (isEmpty(n) == 0)
	{
		temp= stack[top[n]--];
	}
	else temp = -1;
	if (vb == true) visible[top[n]] = true;

	return temp;
}

int stockPop()
{
	if(stp!=-1)return stock[stp--];
	return -1;
}

int pePop()
{
	return pe[peTop--];
}

void pePush(int n)
{
	pe[++peTop] = n;
}

int cardCode(int n)
{
	if (n % 13 == 0) return 13;
	else return n % 13;
}

int a()
{
	for (int i = 0; i < 7; i++)
	{
		if (cardCode(stack[top[i]]) == pr[(int)(stack[top[i]] / 13)]+1)
		{
			pr[(int)(stack[top[i]] / 13)]++;
			return stackPop(i,true);
		}
	}

	if (cardCode(pe[peTop]) == pr[(int)(pe[peTop] / 13)] + 1&&stp!=-1)
	{
		pr[(int)(pe[peTop] / 13)]++;
		return pePop();
	}
	return 0;
}

int p()
{
	int t=0;
	if (peTop == -1 && stp != -1)
	{
		t = stockPop();
		pePush(t);
	}
	return t;
}

int b()
{
	int peCard = pePop();
	int t;
	int flag = 0;

	if (peCard != -1)
	{
		for (int i = 0; i < 7; i++)
		{
			t = stackPop(i, false);
			if ((((int)(t) / 13) + ((int)peCard / 13)) % 2 == 1)
			{
				if (t % 13 == peCard % 13 + 1)
				{
					flag = 1;
				}
			}
			if (t == -1 && peCard % 13 == 0)flag = 1;
			else stackPush(i, t);

			if (flag == 1)
			{
				stackPush(i, peCard);
				return peCard;
			}
		}
	}

	pePush(peCard);
	return 0;
}

int c()
{
	int pCard;
	int t;
	int flag = 0;

	int s[20] = { 0 };
	int sa;

	for (int i = 1; i < 7; i++)
	{
		sa = 0;
		while (visible[top[i]] == 1)
		{
			s[sa++] = stackPop(i, false);
		}

		for (int j = 0; j < i; j++)
		{
			t = stackPop(j, false);
			
			if (t != -1) stackPush(j, t);
			for (int k = sa-1; k >=0; k--)
			{
				if ((((int)(t) / 13) + ((int)s[k] / 13)) % 2 == 1)
				{
					if (s[k]%13 + 1 == t % 13)
					{
						for (int n = sa - 1; n >= k; n--)
						{
							stackPush(j, s[n]);
						}
						for (int n = k - 1; n >= 0; n--)
						{
							stackPush(i, s[n]);
						}
						return j+1;
					}
				}
			}
			if (t == -1 && s[sa - 1] % 13 == 0) {
				for (int n = sa - 1; n >= 0; n--)
				{
					stackPush(j, s[n]);
				}
			}
		}
		for (int j = sa - 1; j >= 0; j--) stackPush(i, s[j]);
	}

	return 0;
}

int d()
{
	return 0;
}

int ps()
{
	int t = 0;

	t = stockPop();
	pePush(t);

	return t;
}

void setting()
{
	point = -52;
	gamef = 0;
	//outc = 0;
	std::fill_n(visible,140,0);
	std::fill_n(stack,140,0);
	std::fill_n(top,7,0);
	std::fill_n(st,7,0);
	std::fill_n(pr,4,0);
	std::fill_n(pe, 24, 0);
	std::fill_n(stock, 24, 0);
	stp = 23;
	peTop = 0;

	int card[53] = {};
	int t = 0;
	srand((unsigned int)time(NULL));

	for (int i = 0; i < 7; i++)
	{
		st[i] = 20*i;
		top[i] = 20*i+i;
		visible[top[i]] = 1;
		for (int j = 0; j <= i; j++)
		{
			while (true)
			{
				t = rand() % 52+1;
				if (card[t] != 1)
				{
					card[t] = 1;
					stack[st[i] + j] = t;
					break;
				}
			}
		}
	}
	for (int i = 0; i < 24; i++)
	{
		while (true)
		{
			t = rand() % 52+1;
			if (card[t] != 1)
			{
				card[t] = 1;
				stock[i] = t;
				break;
			}
		}
	}
	
}