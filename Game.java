import hsa_ufa.Console;
import java.awt.*;
import java.util.Arrays;

public class Game {
	//By:Hasan.C 
	static Console c2 = new Console(500, 500,"Super Shooters");
	static Image finish = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/Finish.png"));
	static Image ammo = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/Ammo.png"));
	static String name = "";
	static int name_length;
	static int level;
	static int player_y;
    static int player_x;
    static int bullet;
    static int[] enemy_x;
    static int[] enemy_y;
    static int[] enemy_health;
    static int bullet_x;
    static int bullet_y;
    static boolean[] shoot = new boolean[6]; // 0 = shot boolean 1 is shot up 2 is is shot down 3 is shot left 4 is shot right 5 is hit
    static boolean[] enemy_alive = new boolean[3];
    static boolean[] enemy_movement = new boolean[3];
    //drop may not be need if you say while bullets < 100 in collision
    static boolean drop = true;
    static boolean player_alive = true;
    static boolean[] level_complete = new boolean[3];
    static String replay = "nothing";
    static int bullet_drop_x;
    static int bullet_drop_y;
    
	public static void main(String[] args) throws InterruptedException{
			RunGame();
	 }
	
	public static void MainMenu()
	{
		c2.clear();
		c2.setFont(new Font("SansSerif", Font.BOLD, 24));
		c2.drawString("Hello And Wellcome To Super Shooters", 22, 60);
		c2.drawString("What Is Your Name?", 115, 100);
		c2.setCursor(6, 7);
		name = c2.readLine();
		c2.setFont(new Font("SansSerif", Font.BOLD, 10));
		name_length = name.length();
	}
	public static void HowToPlay()
	{
		int x_pos = 3;
		int y_pos = 10;
		c2.clear();
		c2.setFont(new Font("SansSerif", Font.BOLD, 10));
		c2.drawString("Your Goal In Super Shooters Is To Collect Ammo From The Ammo Crates Spread Around On the Map", x_pos, y_pos);
		x_pos += 2;
		y_pos += 10;
		c2.drawString("And To Use That Ammo To Destroy The Robots On The Map Note That Some Robots Are Stronger Than", x_pos, y_pos);
		x_pos += 2;
		y_pos += 10;
		c2.drawString("Others You Can Move Around With The (w,a,s,d) Keys And Shoot With The Arrow Key The Key You", x_pos, y_pos);
		x_pos += 2;
		y_pos += 10;
		c2.drawString("Choose Is The Dirction You Will Shoot For Example If You Press The Up Arrow Key You Will ", x_pos, y_pos);
		x_pos += 2;
		y_pos += 10;
		c2.drawString("Shoot Upwards To Continue To The Game Press Enter Twice", x_pos, y_pos);
		c2.setCursor(-5,-5);
		c2.readLine();
	}
	public static void Movement()
	{
		int speed = 3;
		//Moves The Player 
		if(c2.getKeyCode() == 'A' && player_x > 0)
    		player_x -= speed;
		
    	if(c2.getKeyCode() == 'D' && player_x < (c2.getDrawWidth()-7))
    		player_x += speed;
    	
    	else if(c2.getKeyCode() == 'W' && player_y > 0)
    		player_y -= speed;
    	
    	else if(c2.getKeyCode() == 'S' && player_y < (c2.getDrawHeight()-7))
    		player_y += speed;
	}
	public static void Shot()
	{
		//Destroys Bullet
		if(bullet_y<=0 || bullet_y > (c2.getDrawHeight()-7) || bullet_x <= 0 || bullet_x > c2.getDrawWidth()-7||shoot[5] == true)
			Arrays.fill(shoot, false);
		//Checks Where Bullet Is Shot And If You Have Any Bullets 
		if(c2.getKeyCode() == Console.VK_UP && shoot[0] == false && bullet >= 1)
		{
			bullet_y=player_y;
			bullet_x=player_x;
			shoot[0] = true;
			shoot[1] = true;
			bullet--;
		}
		else if(c2.getKeyCode() == Console.VK_DOWN && shoot[0] == false && bullet >= 1)
		{
			bullet_y=player_y;
			bullet_x=player_x;
			shoot[0] = true;
			shoot[2] = true;
			bullet--;
		}
		else if(c2.getKeyCode() == Console.VK_LEFT && shoot[0] == false && bullet >= 1)
		{
			bullet_y=player_y;
			bullet_x=player_x;
			shoot[0] = true;
			shoot[3] = true;
			bullet--;
		}
		else if(c2.getKeyCode() == Console.VK_RIGHT && shoot[0] == false && bullet >= 1)
		{
			bullet_y=player_y;
			bullet_x=player_x;
			shoot[0] = true;
			shoot[4] = true;
			bullet--;
		}
	}
	public static void Lost()
	{
		while(true) 
		{
			c2.setBackgroundColor(Color.black);
			c2.clear();
			c2.setColor(Color.GRAY);
			c2.setFont(new Font("SansSerif", Font.BOLD, 24));
			c2.drawString("Nice Try Would You Like To Play Again?", 20, 100);
			c2.drawString("(YES/NO)", 190, 140);
			c2.setCursor(9, 7);
			replay = c2.readLine();
			c2.setBackgroundColor(Color.white);
			if(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no"))
				break;
		}
	}
	public static void LevelCutScene() throws InterruptedException
	{
		level++;
		c2.clear();
		c2.setFont(new Font("SansSerif", Font.BOLD, 24));
		c2.drawString(name+" You Are On Level "+level, 50, 100);
		c2.setFont(new Font("SansSerif", Font.BOLD, 10));
		Thread.sleep(1000);
	}
	public static void Win()
	{
		c2.setBackgroundColor(Color.GRAY);
		c2.clear();
		c2.setColor(Color.cyan);
		c2.setFont(new Font("SansSerif", Font.BOLD, 24));
		c2.drawString("Good Job "+name+ " You Won!!! ", 50, 100);
	}
	public static void RestartGame()
	{
		//Array Backups
		int[] enxBup = {40,20,10};
	    int[] enyBup = {201,60,30};
	    int[] enhpBup = {100, 100, 100};
	    
		//set Array size and values to 0
	    enemy_x = new int[enxBup.length];
	    enemy_y = new int[enyBup.length];
	    enemy_health = new int[enhpBup.length];
	    
		replay = "nothing";
		player_alive = true;
		Arrays.fill(level_complete, false);
		Arrays.fill(enemy_alive, true);
		Arrays.fill(shoot, false);
		Arrays.fill(enemy_movement, false);
		level = 0;
		bullet = 110;
		name = "";
		c2.setBackground(Color.white);
		c2.setColor(Color.black);
		player_x=0;
		player_y=0;
		bullet_x=-5;
		bullet_y=-5;
		
		//Reset Arrays
		
		//Sets Arrays = 0 Than
		//Resets Arrays To Proper Values
		for(int i = 0; i < enhpBup.length; i++)
	    	enemy_health[i] += enhpBup[i];
	    	
	    for(int i = 0; i < enxBup.length; i++)
	    	enemy_x[i] += enxBup[i];
	    
	    for(int i = 0; i < enyBup.length; i++)
	    	enemy_y[i] += enyBup[i];
	    
	}
	public static void Collision()
	{
		int collision_offset_x_1 = 21;
		int collision_offset_x_2 = 63;
		int collision_offset_y_1 = 3;
		int collision_offset_y_2 = 47;
		
		//Level1 Collision With Enemy
		if(level == 1)
		{
			
			if((player_x >= enemy_x[0] + collision_offset_x_1 && player_x <= enemy_x[0] + collision_offset_x_2 && player_y >= 40 + collision_offset_y_1 && player_y <= 40 + collision_offset_y_2 && enemy_alive[0] == true)||(player_x >= 300 + collision_offset_x_1 && player_x <= 300 + collision_offset_x_2 && player_y >= enemy_y[0] + collision_offset_y_1 && player_y <= enemy_y[0] + collision_offset_y_2 && enemy_alive[1]==true))
	    		player_alive = false;
			
			//Checks To See If A Bullet Hit The Enemy
	    	if(bullet_x >= enemy_x[0] + collision_offset_x_1 && bullet_x <= enemy_x[0] + collision_offset_x_2 && bullet_y >= 40 + collision_offset_y_1 && bullet_y <= 40 + collision_offset_y_2 && enemy_alive[0] == true)
	    		{
	    			enemy_alive[0] = false;
		    		shoot[5] = true;
		    		bullet_x=-5;
		    		bullet_y=-5;
	    		}
	    	if(bullet_x >= 300 + collision_offset_x_1 && bullet_x <= 300 + collision_offset_x_2 && bullet_y >= enemy_y[0] + collision_offset_y_1 && bullet_y <= enemy_y[0] + collision_offset_y_2 && enemy_alive[1] == true)
				{
		    		enemy_alive[1]=false;
		    		shoot[5]=true;
		    		bullet_x=-5;
		    		bullet_y=-5;
				}
	    	
	    	if(player_x >= 450 && player_x <= 500 && player_y >= 446 && player_y <= 500 && enemy_alive[0] == false && enemy_alive[1] == false)
	    		level_complete[0] = true;
		}
		
		//Level 2 & 3 Enemy Collision
		if(level == 2 || level == 3)
		{
			if(enemy_alive[0] || enemy_alive[1] || enemy_alive[2])
				{	
					for(int i = 0; i < 3; i++)
			    	{
			    		if(player_x >= enemy_x[i] + collision_offset_x_1 && player_x<= enemy_x[i] + collision_offset_x_2 && player_y >= enemy_y[i] + collision_offset_y_1 && player_y <= enemy_y[i] + collision_offset_y_2 && enemy_alive[i] == true)
			    			player_alive = false;
			    	}
				}
				//Checks To See If A Bullet Hit The Enemy
		
		    for(int i = 0; i < 3; i++)
	    	{
	    		if(bullet_x >= enemy_x[i] + collision_offset_x_1 && bullet_x<= enemy_x[i] + collision_offset_x_2 && bullet_y >= enemy_y[i] + collision_offset_y_1 && bullet_y <= enemy_y[i] + collision_offset_y_2 && enemy_alive[i] == true)
	    			{
		    			if(shoot[0] == true)
		    				{	
		    					if(level == 2)
		    						enemy_health[i] -= 30;
		    					else
		    						enemy_health[i] -= 20;
		    				}
			    		if(enemy_health[i] <= 0)
			    			enemy_alive[i] = false;
			    		
			    		shoot[5]=true;
	    			}
	    	}
		    
		    if(player_x >= 450 && player_x <= 500 && player_y >= 446 && player_y <= 500 && enemy_alive[0] == false && enemy_alive[1] == false && enemy_alive[2] == false)
	    		if(level == 2)
	    			level_complete[1] = true;
	    		else if(level == 3)
	    			level_complete[2] = true;
		}
		
    	//Checks To See If You Hit A Ammo Crate
    	if(player_x >= bullet_drop_x + 14 && player_x <= bullet_drop_x + 42 && player_y >= bullet_drop_y && player_y <= bullet_drop_y + 28)
		{
    		bullet++;
    		drop = true;
		}
	}
	public static void RunGame() throws InterruptedException
	{
		 while(true)
		    {
			 	//Makes Sure You Have A Name And All Variables Are Set Up Because Some Are boolean arrays 
			 
			 while(level_complete[0] == false && player_alive == true)
			   {
		    		if(name == "")
			    	{
			    		RestartGame();
			    		MainMenu();
			    		HowToPlay();
			    	}
			    	//Tells You You Are On Level 1
			    	if(level == 0)
			    		LevelCutScene();
			    	//Starts Level1 
			    	Level1();
			     }
		    	//Checks If You Past Level 1 And Runs Level 2 
			 while(level_complete[0] == true && player_alive == true && level_complete[1] == false)
		    	{
		    		if (level == 1)
			    	{
		    			String name1 = name;
			    		RestartGame();
			    		name = name1;
			    		level_complete[0] = true;
			    		level = 1;
			    		LevelCutScene();
			    	}
		    		
			    	Level2();
		    	}
			 //Checks If You Past Level 2 And Runs Level 3
		    	//Checks If You Are Alive
			 while(level_complete[1] == true && player_alive == true && level_complete[2] == false)
		    	{
		    		if (level == 2)
			    	{
		    			String name1 = name;
			    		RestartGame();
			    		name = name1;
			    		level_complete[1] = true;
			    		level = 2;
			    		LevelCutScene();
			    	}
		    		
			    	Level3();
		    	}
			 if(level_complete[2] == true)
				 {
				 	Win();
				 	break;
				 }
			 
		    	if(player_alive == false)
		    		Lost();
		    	
		    	if(replay.equalsIgnoreCase("no"))
		    		{
			    		c2.close();
			    		break;
		    		}
		    	
		    	else if(replay.equalsIgnoreCase("yes"))
		    		{
		    			RestartGame();
		    			continue;
		    		}
		    }
	}
	public static void Level1() throws InterruptedException
	{
		Image enemy1 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En1.png"));
    	synchronized (c2) {
    		c2.clear();
    		//Player + Name and Bullet Count
    		c2.drawString(name, player_x - (5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(finish, 426, 450, 100, 50);
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		drop = false;
    		}		
    		c2.drawImage(ammo, bullet_drop_x, bullet_drop_y, 70, 35);
    		
    		if(enemy_alive[0] == true)
    			c2.drawImage(enemy1, enemy_x[0], 40, 100, 50);
    		if(enemy_alive[1] == true)
    			c2.drawImage(enemy1, 300, enemy_y[0], 100, 50);
    		if (shoot[0] == true)
    		{
    			c2.drawRect(bullet_x, bullet_y, 5, 5);
    			if(shoot[1] == true)
    				bullet_y -= 7;
    			else if(shoot[2] == true)
    				bullet_y += 7;
    			else if(shoot[3] == true)
    				bullet_x -= 7;
    			else if(shoot[4] == true)
    				bullet_x += 7;
    		}
    		
    	}
    	Thread.sleep(25);
    	//EN Movement
    	EnMoveLevel1();
    	//Col
    	Collision();
    	//Movement
    	Movement();
    	//shoot
    	Shot();
	}
	public static void EnMoveLevel1()
	{
		//Moves The Enemies In Level1 
		if(enemy_movement[0] == true && enemy_x[0] <= 200)
			enemy_x[0]++;
		
    	else if(enemy_movement[0] == false && enemy_x[0] >= 20)
    		enemy_x[0]--;
		
    	if(enemy_x[0] == 201||enemy_x[0] == 19)
    		enemy_movement[0] = !enemy_movement[0];
    	
    	if(enemy_movement[1] == true && enemy_y[0] <= 300)
    		enemy_y[0]++;
    	
    	else if(enemy_movement[1] == false && enemy_y[0]>=30)
    		enemy_y[0]--;
    	
    	if(enemy_y[0] == 301 || enemy_y[0] == 29)
    		enemy_movement[1] = !enemy_movement[1];
	}
	public static void Level2() throws InterruptedException
	{
		Image enemy2 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En2.png"));
		synchronized (c2) {
    		c2.clear();
    		//Player + Name and Bullet Count
    		c2.drawString(name, player_x - (5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(finish, 426, 450, 100, 50);
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		drop = false;
    		}
    		
    		c2.drawImage(ammo, bullet_drop_x, bullet_drop_y, 70, 35);
    		
    		for(int i = 0; i < 3; i++)
    		{
    			if(enemy_alive[i] == true)
        		{
        			c2.drawString("Health: "+ enemy_health[i], enemy_x[i]+16, enemy_y[i]+56);
        			c2.drawImage(enemy2, enemy_x[i], enemy_y[i], 100, 50);
        		}
    		}
    		
    		if (shoot[0] == true)
    		{
    			c2.drawRect(bullet_x, bullet_y, 5, 5);
    			if(shoot[1] == true)
    				bullet_y -= 7;
    			else if(shoot[2] == true)
    				bullet_y += 7;
    			else if(shoot[3] == true)
    				bullet_x -= 7;
    			else if(shoot[4] == true)
    				bullet_x += 7;
    		}
    		
    	}
    	Thread.sleep(25);
    	//EN Movement
    	EnMoveLevel2();
    	//Col
    	Collision();
    	//Movement
    	Movement();
    	//shoot
    	Shot();
	}
	public static void EnMoveLevel2()
	{
		//Moves The Enemies In Level2
		int enemy_speed_x = 7;
		int enemy_speed_y = 5;
		//En1
		if(enemy_movement[0] == false && enemy_x[0] <= 200 && enemy_y[0] >= 201)
			enemy_x[0] += enemy_speed_x;
		
		else if(enemy_y[0] >= 20 && enemy_movement[0] == true && enemy_x[0] >= 200)
			enemy_y[0] -= enemy_speed_y;
		
    	else if(enemy_movement[0] == true && enemy_y[0] <= 19 && enemy_x[0] >= 20)
    		enemy_x[0] -= enemy_speed_x;
		
    	else if(enemy_y[0] <= 201 && enemy_x[0] <= 19 && enemy_movement[0] == false)
			enemy_y[0] += enemy_speed_y;
		
    	if(enemy_x[0] >= 201 || enemy_x[0] <= 19)
    		enemy_movement[0] = !enemy_movement[0];
    	//En1
    	
    	//En2
    	if(enemy_movement[1] == false && enemy_y[1] <= 300 && enemy_x[1] <= 100)
    		enemy_y[1] += enemy_speed_y;
    	
    	else if(enemy_x[1] <= 300 && enemy_movement[1] == true && enemy_y[1] >= 300)
    		enemy_x[1] += enemy_speed_x;
    	
    	else if(enemy_movement[1] == true && enemy_y[1] >= 30 && enemy_x[1] >= 300)
    		enemy_y[1]-= enemy_speed_y;
    	
    	else if(enemy_movement[1] == false && enemy_y[1] <= 30 && enemy_x[1] >= 100)
    		enemy_x[1]-= enemy_speed_x;
    	
    	if(enemy_y[1] >= 301 || enemy_y[1] <= 29)
    		enemy_movement[1] = !enemy_movement[1];
    	//En2
    	
    	//En3
    	if(enemy_movement[2] == false && enemy_y[2] <= 250 && enemy_x[2] <= 10)
    		enemy_y[2] += enemy_speed_y;
    	
    	else if(enemy_x[2] <= 400 && enemy_movement[2] == true && enemy_y[2] >= 250)
    		enemy_x[2] += enemy_speed_x;
    	
    	else if(enemy_movement[2] == true && enemy_y[2] >= 20 && enemy_x[2] >= 400)
    		enemy_y[2]-= enemy_speed_y;
    	
    	else if(enemy_movement[2] == false && enemy_y[2] <= 20 && enemy_x[2] >= 10)
    		enemy_x[2]-= enemy_speed_x;
    	
    	if(enemy_y[2] >= 251 || enemy_y[2] <= 21)
    		enemy_movement[2] = !enemy_movement[2];
    	//En3
	}
	public static void Level3() throws InterruptedException
	{
		Image enemy3 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En3.png"));
		synchronized (c2) {
    		c2.clear();
    		//Player + Name and Bullet Count
    		c2.drawString(name, player_x-(5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(finish, 426, 450, 100, 50);
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		drop = false;
    		}
    		
    		c2.drawImage(ammo, bullet_drop_x, bullet_drop_y, 70, 35);
    			
    		for(int i = 0; i < 3; i++)
    		{
    			if(enemy_alive[i] == true)
        		{
        			c2.drawString("Health: "+ enemy_health[i], enemy_x[i] + 16, enemy_y[i] + 56);
        			c2.drawImage(enemy3, enemy_x[i], enemy_y[i], 100, 50);
        			c2.setColor(Color.red);
        			c2.drawRect(27,35, 60, 70);
        			c2.drawString("Danger Zone",27,70);
        			c2.setColor(Color.black);
        		}
    		}
    		
    		if (shoot[0] == true)
    		{
    			c2.drawRect(bullet_x, bullet_y, 5, 5);
    			if(shoot[1] == true)
    				bullet_y -= 7;
    			else if(shoot[2] == true)
    				bullet_y += 7;
    			else if(shoot[3] == true)
    				bullet_x -= 7;
    			else if(shoot[4] == true)
    				bullet_x += 7;
    		}
    		
    	}
    	Thread.sleep(25);
    	//EN Movement
    	EnMoveLevel3();
    	//Col
    	Collision();
    	//Movement
    	Movement();
    	//shoot
    	Shot();
	}
	public static void EnMoveLevel3()
	{
		//Moves The Enemies In Level2
		int enemy_speed_x = 2;
		int enemy_speed_y = 2;
		
		//Enemy 
		for(int i = 0; i < 3; i++)
		{
			if(enemy_alive[i] == true)
			{
				if(player_x - enemy_x[i] < 40)
					enemy_x[i] -= enemy_speed_x;
				
				else if(player_x - enemy_x[i] > 46)
					enemy_x[i] += enemy_speed_x;
				
		    	else if(player_y - enemy_y[i] < 25)
		    		enemy_y[i] -= enemy_speed_y;
				
		    	else if(player_y - enemy_y[i] > 25)
					enemy_y[i] += enemy_speed_y;
			}
		}
    	//Enemy 
		
		//Sets enemies active
		if(enemy_alive[0] == true)
		{
			enemy_alive[1] = false;
			enemy_alive[2] = false;
		}
		else if(enemy_alive[1] == false && enemy_health[1] >=0)
		{
			enemy_alive[1] = true;
			enemy_alive[2] = false;
		}
		else if(enemy_alive[1] == false && enemy_alive[2] == false && enemy_health[2] >=0)
			enemy_alive[2] = true;
	}
}

