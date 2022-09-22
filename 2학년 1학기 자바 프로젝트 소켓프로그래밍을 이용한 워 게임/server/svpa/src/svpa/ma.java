package svpa;

import java.io.IOException;

public class ma extends Thread{
	
	static int port;
	static serverClass loginServ;
	static int cgv;
	static serverClass[] gameServ = new serverClass[100];
	static int gameReady=0;
	static int maxPlayer=4;
	static int[] t1={0,0};
	static int[] t2={0,0};
	
	static int[] tv1={0,0,0,0,0,0,0};
	static int[] tv2={0,0,0,0,0,0,0};
	
	static int ready=0;
	static int turn=0;
	
	public static void main(String[] args) throws Exception{
		
		t1[0]=maxPlayer*50;
		t1[1]=maxPlayer*25;
		t2[0]=maxPlayer*50;
		t2[1]=maxPlayer*25;
		
		String Temp;
		cgv=-1;
		port=8584;
		loginServ = new serverClass("로그인 서버에 접속하였습니다.",cb);
		
		for(int i=0;i<maxPlayer;i++)
		{
			port++;
			loginServ = new serverClass("로그인 서버에 접속하였습니다.",cb);
			loginServ.on(8484,-1);
			loginServ.Send(String.valueOf(port));
			loginServ.close();

			cgv++;
			gameServ[cgv] = new serverClass("게임 서버에 접속하였습니다.",cb);
			gameServ[cgv].cgv=cgv;
			gameServ[cgv].on(port,cgv);
			gameReady++;
		}

		System.out.println("Server is full");
		System.out.println("No on start game");

	}
	
	private static String getRealResponse(String a)
	{
		System.out.println(a);
		return a.split(":")[1];
	}
	
	private static void setName(String a, int b) throws IOException
	{
		gameServ[b].nic=getRealResponse(a);
		System.out.println("Hello Plyer :" +gameServ[b].nic);
		
		if(gameReady==maxPlayer)
		{
			for(int i=0;i<maxPlayer;i++)
			{
				System.out.println(gameServ[i].nic);
				try {
					gameServ[i].Send("data:"+(maxPlayer*50)+":"+(maxPlayer*25)+":100:100");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for(int j=0;j<maxPlayer;j++)
				{
					try {
						if(j==0)gameServ[i].Send("name:Team1");
						if(j==maxPlayer/2)gameServ[i].Send("name:Team2");
						gameServ[i].Send("name:"+gameServ[j].nic);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			sendTurn();
		}
		
		for(int i=0;i<=cgv;i++)
		{
			try {
				gameServ[i].Send("WaitP:"+(cgv+1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void sendTurn() throws IOException
	{
		int x=turn;
		int y=turn+maxPlayer/2;
		
		gameServ[x].Send("Turn:");
		gameServ[y].Send("Turn:");
		turn++;
	}
	
	private static void msgSend(String n, int cgv)
	{
		int x,y;
		
		if(cgv<maxPlayer/2)
		{
			x=0;
			y=maxPlayer/2;
		}
		else
		{
			x=maxPlayer/2;
			y=maxPlayer;
		}
		for(int i=x;i<y;i++)
		{
			try {
				gameServ[i].Send("msg:"+gameServ[cgv].nic+" "+getRealResponse(n));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void findWinner(int n)
	{
		String[] x={"win:","lose:","draw:"};
		int tx1,tx2;
		
		t1[1]=tv1[3];
		t2[1]=tv2[3];
		
		if(tv1[n]>tv2[n])
		{
			t2[1]=t2[1]-10;
			t1[1]=t1[1]+10;
			
			t2[0]=t2[0]-Math.abs(tv1[0]-tv2[0]);
			System.out.println("T1 win");
			tx1=0;
			tx2=1;
		}
		
		else if(tv1[n]<tv2[n])
		{
			t1[1]=t1[1]-10;
			t2[1]=t2[1]+10;
			
			t1[0]=t1[0]-Math.abs(tv1[0]-tv2[0]);
			System.out.println("T2 win");
			tx1=1;
			tx2=0;
		}
		else
		{
			System.out.println("Draw");
			tx1=2;
			tx2=2;
		}
		
		if(turn==maxPlayer/2)
		{
			turn=0;
			t1[1]=maxPlayer*25;
			t2[1]=maxPlayer*25;
			
		}
		
		for(int i=0;i<maxPlayer/2;i++)
		{
			try {
				gameServ[i].Send("data:"+t1[0]+":"+t1[1]+":"+tv2[4]+":"+tv2[5]);
				gameServ[i].Send(x[tx1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=maxPlayer/2;i<maxPlayer;i++)
		{
			try {
				gameServ[i].Send("data:"+t2[0]+":"+t2[1]+":"+tv1[4]+":"+tv1[5]);
				gameServ[i].Send(x[tx2]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void readyPlayer(String n, int cgv)
	{
		if(cgv<maxPlayer/2)
		{
			for(int i=0;i<7;i++) tv1[i]=Integer.parseInt(n.split(":")[i+1]);
		}
		else
		{
			for(int i=0;i<7;i++) tv2[i]=Integer.parseInt(n.split(":")[i+1]);
		}
		ready++;
		
		if(ready==2)
		{

			
			if(Math.abs(tv1[4]-tv2[4])+Math.abs(tv1[5]-tv2[5])>=150)
			{
				findWinner(1);
			}
			else if(Math.abs(tv1[4]-tv2[4])+Math.abs(tv1[5]-tv2[5])<=50)
			{
				findWinner(6);
			}
			else
			{
				findWinner(0);
			}
			try {
				sendTurn();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ready=0;
		}
	}
	
    private static CallBack cb = new CallBack() {
        @Override
        public void getResponse(Object obj, Object cgv) {
            String result = (String) obj;
            System.out.println(result);
            int c=(int) cgv;
            if(result.indexOf("Name:")!=-1)
				try {
					setName(result, c);
				} catch (IOException e) {
					e.printStackTrace();
				}
            if(result.indexOf("msg:")!=-1) msgSend(result, c);
            if(result.indexOf("ready:")!=-1) readyPlayer(result,c);
        }
    };
}
