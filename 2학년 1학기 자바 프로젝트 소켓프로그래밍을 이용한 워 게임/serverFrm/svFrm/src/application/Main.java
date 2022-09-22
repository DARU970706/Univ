package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main extends Application {
	
	static ListView chat = new ListView();
	static int port;
	static serverClass loginServ;
	static int cgv;
	static serverClass[] gameServ = new serverClass[100];
	static int gameReady=0;
	static int maxPlayer=4;
	static int[] t1={0,0};
	static int[] t2={0,0};
	
	static int[] tv1={0,0,0,0,0,0,0,0};
	static int[] tv2={0,0,0,0,0,0,0,0};
	
	static int ready=0;
	static int turn=0;
	
	//Platform.runLater(()->name.getItems().add(getRealResponse(n)));
	
	public void serverStart() throws IOException
	{
		String msg = JOptionPane.showInputDialog("서버 최대 인원수");
		maxPlayer = Integer.parseInt(msg);
		
		new Thread(){
			public void run(){
				t1[0]=maxPlayer*50;
				t1[1]=maxPlayer*25;
				t2[0]=maxPlayer*50;
				t2[1]=maxPlayer*25;
				
				String Temp;
				cgv=-1;
				port=8584;
				try {
					loginServ = new serverClass("로그인 서버에 접속하였습니다.",cb);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=0;i<maxPlayer;i++)
				{
					port++;
					try {
						loginServ = new serverClass("로그인 서버에 접속하였습니다.",cb);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						loginServ.on(8484,-1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						loginServ.Send(String.valueOf(port));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						loginServ.Send(String.valueOf("MAX:"+maxPlayer));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					try {
						loginServ.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					cgv++;
					try {
						gameServ[cgv] = new serverClass("게임 서버에 접속하였습니다.",cb);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gameServ[cgv].cgv=cgv;
					try {
						gameServ[cgv].on(port,cgv);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gameReady++;
				}
				
				insert("Server is full");
				insert("No on start game");
			}
		}.start();

	}
	
	public void start(Stage primaryStage) {	
				
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        // Update UI here.
		    }
		});
		
		Pane root = new Pane();
		Scene scene = new Scene(root,900,520);
		
        chat.setPrefSize(900, 520);
        chat.setLayoutX(0);
        chat.setLayoutY(0);
        chat.setEditable(true);
        chat.getItems().add("Server On");
        
		root.getChildren().addAll(chat);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		try {
			serverStart();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
	private static String getRealResponse(String a)
	{
		return a.split(":")[1];
	}
	
	private static void insert(String n)
	{
		Platform.runLater(()->chat.getItems().add(n));
	}
	private static void setName(String a, int b) throws IOException
	{
		gameServ[b].nic=getRealResponse(a);
		insert("New plyer : " +gameServ[b].nic);
		
		String n;
		if(gameReady==maxPlayer)
		{
			for(int i=0;i<maxPlayer;i++)
			{
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
		insert(gameServ[x].nic+"'s turn");
		insert(gameServ[y].nic+"'s turn");
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
			insert("Team1 win");
			tx1=0;
			tx2=1;
		}
		
		else if(tv1[n]<tv2[n])
		{
			t1[1]=t1[1]-10;
			t2[1]=t2[1]+10;
			
			t1[0]=t1[0]-Math.abs(tv1[0]-tv2[0]);
			insert("Team2 win");
			tx1=1;
			tx2=0;
		}
		else
		{
			insert("draw");
			tx1=2;
			tx2=2;
		}
		
		if(turn==maxPlayer/2)
		{
			insert("New round");
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

	private static void sendWinl(String a, String b)
	{
		for(int i=0;i<maxPlayer/2;i++)
		{
			try {
				gameServ[i].Send(a);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=maxPlayer/2;i<maxPlayer;i++)
		{
			try {
				gameServ[i].Send(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<maxPlayer;i++)
		{
			try {
				gameServ[i].close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void readyPlayer(String n, int cgv)
	{
		if(cgv<maxPlayer/2)
		{
			for(int i=0;i<8;i++) tv1[i]=Integer.parseInt(n.split(":")[i+1]);
			insert("Team1 Total Power :"+tv1[0]+", hp :"+tv1[2]+", money :"+tv1[3]+", x :"+ tv1[4]+", y :"+tv1[5]);
			insert("Laser :"+tv1[1]+", Gun:"+tv1[6]+ ", bomb:"+tv1[7]);
		}
		else
		{
			for(int i=0;i<8;i++) tv2[i]=Integer.parseInt(n.split(":")[i+1]);
			insert("Team 2 Total Power :"+tv2[0]+", hp :"+tv2[2]+", money :"+tv2[3]+", x :"+ tv2[4]+", y :"+tv2[5]);
			insert("Laser : "+tv2[1]+", Gun :"+tv2[6]+ ", bomb :"+tv2[7]);
		}
		ready++;
		
		if(ready==2)
		{
			insert("Range : "+(Math.abs(tv1[4]-tv2[4])+Math.abs(tv1[5]-tv2[5])));
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
			if(t1[0]<=0)sendWinl("FL:","FW:");
			if(t2[0]<=0)sendWinl("FW:","FL:");
			ready=0;
		}
	}
	
    private static CallBack cb = new CallBack() {
        @Override
        public void getResponse(Object obj, Object cgv) {
            String result = (String) obj;
            
            int c=(int) cgv;
            if(result.indexOf("msg:")!=-1)
            {
            	msgSend(result, c);
            	insert(gameServ[c].nic+"'s " + result);
            }
            else if(result.indexOf("Name:")!=-1)
            {
				try {
					setName(result, c);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            else if(result.indexOf("ready:")!=-1) readyPlayer(result,c);
        }
    };
}
