import hsa_ufa.Console;
import java.awt.*;
import java.util.Arrays;

public class Game {
	
	//By:Hasan.C 
	static Console c2 = new Console(500, 500,"Super Shooters");
	static int name_length, level, player_y, player_x, bullet, bullet_x, bullet_y, bullet_drop_x, bullet_drop_y;
    static int[][] enemy_pos = {{40,20,10}, {201,60,30}, {100, 100, 100}}; //[0][n] enemy x [1][n] enemy y [2][n] enemy health
    static boolean[] shoot = new boolean[6]; // 0 = shot boolean 1 is shot up 2 is is shot down 3 is shot left 4 is shot right 5 is hit
    static boolean[] enemy_alive = new boolean[3];
    static boolean[] enemy_movement = new boolean[3];
    static boolean drop = true;
    
	public static void main(String[] args) throws InterruptedException{
		
		RunGame("", true);
			
	 }
	
	
	
	public static String MainMenu()
	{	
		String name = "";
		
		//Gets player name
		
		while(name == "")
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
		
		return name;
		
	}
	public static void HowToPlay()
	{
		int x_pos = 3;
		int y_pos = 10;
		
		c2.clear();
		c2.setFont(new Font("SansSerif", Font.BOLD, 10));
		
		//Displays the rules
		
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
		//Destroys Bullet If It Hits The Wall
		
		if(bullet_y < -1 || bullet_y > c2.getDrawHeight() || bullet_x < -1 || bullet_x > c2.getDrawWidth() || shoot[5] == true)
			Arrays.fill(shoot, false);
		
		//Checks If You Have Any Bullets And Where You Want To Shot It	

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
	public static boolean Lost()
	{
		while(true) 
		{
			//Sets Background To Black
			
			c2.setBackgroundColor(Color.black);
			c2.clear();
			
			//Displays Text And Gets Players Input
			
			c2.setColor(Color.GRAY);
			c2.setFont(new Font("SansSerif", Font.BOLD, 24));
			c2.drawString("Nice Try Would You Like To Play Again?", 20, 100);
			
			c2.drawString("(YES/NO)", 190, 140);
			c2.setCursor(9, 7);
			String replay = c2.readLine();
			
			c2.setBackgroundColor(Color.white);
			
			//Returns True If The Player Wants To Keep Playing And Returns False If They Don't
			
			if(replay.equalsIgnoreCase("yes"))
				return true;
			
			else if( replay.equalsIgnoreCase("no"))
				return false;
			
		}
		
	}
	public static void LevelCutScene(String name) throws InterruptedException
	{
		//Clears Screen And Sets Font Size
		
		level++;
		c2.clear();
		c2.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		c2.drawString(name+" You Are On Level "+ level, 50, 100);
		
		//Resets Font For Later Use
		
		c2.setFont(new Font("SansSerif", Font.BOLD, 10));
		Thread.sleep(1000);
		
	}
	public static void Win(String name)
	{
		c2.setBackgroundColor(Color.GRAY);
		c2.clear();
		
		c2.setColor(Color.cyan);
		c2.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		c2.drawString("Good Job " + name + " You Won!!! ", 50, 100);
		
	}
	public static String RestartGame(boolean[] level_complete, boolean player_alive)
	{
		//Array Backups
		
	    int[][] enemy_pos_back_up = {{40,20,10}, {201,60,30}, {100, 100, 100}};
	    
	    //Resets Variables
	    
		player_alive = true;
		Arrays.fill(level_complete, false);
		Arrays.fill(enemy_alive, true);
		
		Arrays.fill(shoot, false);
		Arrays.fill(enemy_movement, false);
		level = 0;
		
		bullet = 0;
		String name = "";
		c2.setBackground(Color.white);
		
		c2.setColor(Color.black);
		player_x=0;
		player_y=0;
		
		bullet_x=-5;
		bullet_y=-5;
		
		//Resets Enemy Position Array
		
		for(int i = 0; i < enemy_pos_back_up.length; i++)
		{
			for(int j = 0; j < enemy_pos_back_up[i].length; j++)
				enemy_pos[i][j] = enemy_pos_back_up[i][j];
		}
		
		return name;
		
	}
	public static boolean Collision(boolean[] level_complete)
	{
		//Sets Enemy Collision Offset x1 and x2
		
		int collision_offset_x_1 = 21;
		int collision_offset_x_2 = 63;
		
		//Sets Enemy Collision Offset y1 and y2
		
		int collision_offset_y_1 = 3;
		int collision_offset_y_2 = 47;
		
		//Level1 Collision With Enemy
		
		if(level == 1)
		{	
			//Checks If Player Hit Enemy And Kills The Player
			
			if((player_x >= enemy_pos[0][0] + collision_offset_x_1 && player_x <= enemy_pos[0][0] + collision_offset_x_2 && player_y >= 40 + collision_offset_y_1 && player_y <= 40 + collision_offset_y_2 && enemy_alive[0] == true)||(player_x >= 300 + collision_offset_x_1  && player_x <= 300 + collision_offset_x_2 && player_y >= enemy_pos[1][0] + collision_offset_y_1 && player_y <= enemy_pos[1][0] + collision_offset_y_2 && enemy_alive[1]==true))			
	    		return false;
			
			//Checks To See If A Bullet Hit The Enemy
			
	    	if(bullet_x >= enemy_pos[0][0] + collision_offset_x_1 && bullet_x <= enemy_pos[0][0] + collision_offset_x_2 && bullet_y >= 40 + collision_offset_y_1 && bullet_y <= 40 + collision_offset_y_2 && enemy_alive[0] == true)    		
	    	{
	    		enemy_alive[0] = false;
		    	shoot[5] = true;
		    	
		    	bullet_x=-5;
		    	bullet_y=-5;
	    	}
	    	
	    	if(bullet_x >= 300 + collision_offset_x_1 && bullet_x <= 300 + collision_offset_x_2 && bullet_y >= enemy_pos[1][0] + collision_offset_y_1 && bullet_y <= enemy_pos[1][0] + collision_offset_y_2 && enemy_alive[1] == true)	    		
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
			//Moves The Enemy In Level 2 And 3
			
			if(enemy_alive[0] || enemy_alive[1] || enemy_alive[2])
			{	
				
				for(int i = 0; i < 3; i++)
			    {
			    	if(player_x >= enemy_pos[0][i] + collision_offset_x_1 && player_x<= enemy_pos[0][i] + collision_offset_x_2 && player_y >= enemy_pos[1][i] + collision_offset_y_1 && player_y <= enemy_pos[1][i] + collision_offset_y_2 && enemy_alive[i] == true)
			    		return false;
			    	
			    }
					
			}
			
			//Checks To See If A Bullet Hit The Enemy
		
		    for(int i = 0; i < 3; i++)
	    	{
		    	
	    		if(bullet_x >= enemy_pos[0][i] + collision_offset_x_1 && bullet_x<= enemy_pos[0][i] + collision_offset_x_2 && bullet_y >= enemy_pos[1][i] + collision_offset_y_1 && bullet_y <= enemy_pos[1][i] + collision_offset_y_2 && enemy_alive[i] == true)	    			
	    		{
		    		if(shoot[0] == true)
		    		{
		    			if(level == 2)
		    				enemy_pos[2][i] -= 30;
		    				
		    			else
		    				enemy_pos[2][i] -= 20;
		    		}
			    		
			    	if(enemy_pos[2][i] <= 0)
			    		enemy_alive[i] = false;
			    		
			    	shoot[5]=true;
	    		}
	    		
	    	}
		    
		    //Checks To See If You Won Level 2 And 3
		    
		    if(player_x >= 450 && player_x <= 500 && player_y >= 446 && player_y <= 500 && enemy_alive[0] == false && enemy_alive[1] == false && enemy_alive[2] == false)		    	
		    	{
			    	if(level == 2)
		    			level_complete[1] = true;
			    
		    		else if(level == 3)
		    			level_complete[2] = true;
		    	}
		    
		}
		
    	//Checks To See If You Hit A Ammo Crate
		
    	if(player_x >= bullet_drop_x + 14 && player_x <= bullet_drop_x + 42 && player_y >= bullet_drop_y && player_y <= bullet_drop_y + 28)
		{
    		bullet++;
    		drop = true;
		}
    	
    	return true;
    	
	}
	public static void RunGame(String name, boolean player_alive) throws InterruptedException
	{
		boolean[] level_complete = new boolean[3];
		
		while(true)
		{ 			 
			while(level_complete[0] == false && player_alive == true)
			{
		    	if(name == "")
			    {
			    	RestartGame(level_complete, player_alive);
			    	name = MainMenu();
			    	HowToPlay();
			    }
		    		
			    //Tells You You Are On Level 1
		    	
			    if(level == 0)
			    	LevelCutScene(name);
			    	
			    //Starts Level1 
			    
			    Level1(name,level_complete, player_alive);
			    player_alive = Collision(level_complete);
			    
			}
			 
		    //Checks If You Past Level 1 And Runs Level 2 
			
			 while(level_complete[0] == true && player_alive == true && level_complete[1] == false)
		     {
		    	if (level == 1)
			    {
		    		RestartGame(level_complete, player_alive);
			    	level_complete[0] = true;
			    	level = 1;
			    	LevelCutScene(name);
			    }
		    		
			    Level2(name,level_complete, player_alive);
			    player_alive = Collision(level_complete);
			    
		    }
			 
			 //Checks If You Past Level 2 And Runs Level 3
			 
			 while(level_complete[1] == true && player_alive == true && level_complete[2] == false)
		    {
		    	if (level == 2)
			    {
		    		RestartGame(level_complete, player_alive);
			    	level_complete[1] = true;
			    	level = 2;
			    	LevelCutScene(name);
			    }
		    		
			    Level3(name, level_complete, player_alive);
			    player_alive = Collision(level_complete);
			    
		    }
			 
			 //Checks if you won the game
			 
			 if(level_complete[2] == true)
			 {
				 Win(name);
				 break;
			 }
			 
			//checks if the players alive 
			 
		    if(player_alive == false)
		    {
			    if(Lost())
			    {
			    	name = RestartGame(level_complete, player_alive);
			    	player_alive = true;
			    	continue;
			    }
			    	
			    else
			    {
			    	c2.close();
			    	break;
			    }
			    
		    }
		    
		}	
		 
	}
	public static void Level1(String name, boolean[] level_complete, boolean player_alive) throws InterruptedException
	{
    	synchronized (c2) {
    		
    		//Clears Screen
    		
    		c2.clear();
    		
    		//Displays Player Name, Bullet Count, And The Finish
    		
    		c2.drawString(name, player_x - (5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(DrawImage("finish"), 426, 450, 100, 50);
    		
    		//Randomly Drops The Ammo
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
    			bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
    			drop = false;
    		}
    		
    		c2.drawImage(DrawImage("ammo"), bullet_drop_x, bullet_drop_y, 70, 35);
    		
    		if(enemy_alive[0] == true)
    			c2.drawImage(DrawImage("enemy1"), enemy_pos[0][0], 40, 100, 50);
    		
    		if(enemy_alive[1] == true)
    			c2.drawImage(DrawImage("enemy1"), 300, enemy_pos[1][0], 100, 50);
    		
    		//Shoots The Bullet
    		
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
    	
    	//Enemy Movement
    	
    	EnMoveLevel1();   	
    	
    	//Movement
    	
    	Movement();
    	
    	//Shoot
    	
    	Shot();
    	
	}
	public static void EnMoveLevel1()
	{
		//Moves The Enemies In Level1 
		
		if(enemy_movement[0] == true && enemy_pos[0][0] <= 200)
			enemy_pos[0][0]++;
		
    	else if(enemy_movement[0] == false && enemy_pos[0][0] >= 20)
    		enemy_pos[0][0]--;
		
    	if(enemy_pos[0][0] == 201||enemy_pos[0][0] == 19)
    		enemy_movement[0] = !enemy_movement[0];
    	
    	if(enemy_movement[1] == true && enemy_pos[1][0] <= 300)
    		enemy_pos[1][0]++;
    	
    	else if(enemy_movement[1] == false && enemy_pos[1][0]>=30)
    		enemy_pos[1][0]--;
    	
    	if(enemy_pos[1][0] == 301 || enemy_pos[1][0] == 29)
    		enemy_movement[1] = !enemy_movement[1];
    	
	}
	public static void Level2(String name, boolean[] level_complete, boolean player_alive) throws InterruptedException
	{
		synchronized (c2) {
			
			//Clears Screen
			
    		c2.clear();
    		
    		//Displays Player Name, Bullet Count, And The Finish
    		
    		c2.drawString(name, player_x - (5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(DrawImage("finish"), 426, 450, 100, 50);
    		
    		//Randomly Drops The Ammo
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		drop = false;
    		}
    		
    		c2.drawImage(DrawImage("ammo"), bullet_drop_x, bullet_drop_y, 70, 35);
    		
    		for(int i = 0; i < 3; i++)
    		{
    			if(enemy_alive[i] == true)
        		{
        			c2.drawString("Health: "+ enemy_pos[2][i], enemy_pos[0][i]+16, enemy_pos[1][i]+56);
        			c2.drawImage(DrawImage("enemy2"), enemy_pos[0][i], enemy_pos[1][i], 100, 50);
        		}
    		}
    		
    		//Shoots The Bullet
    		
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
    	
    	//Enemy Movement
    	
    	EnMoveLevel2();
    	
    	//Movement
    	
    	Movement();
    	
    	//Shoot
    	
    	Shot();
    	
	}
	public static void EnMoveLevel2()
	{
		//Moves The Enemies In Level2
		
		int enemy_speed_x = 7;
		int enemy_speed_y = 5;
		
		//Enemy 1
		
		if(enemy_movement[0] == false && enemy_pos[0][0] <= 200 && enemy_pos[1][0] >= 201)
			enemy_pos[0][0] += enemy_speed_x;
		
		else if(enemy_pos[1][0] >= 20 && enemy_movement[0] == true && enemy_pos[0][0] >= 200)
			enemy_pos[1][0] -= enemy_speed_y;
		
    	else if(enemy_movement[0] == true && enemy_pos[1][0] <= 19 && enemy_pos[0][0] >= 20)
    		enemy_pos[0][0] -= enemy_speed_x;
		
    	else if(enemy_pos[1][0] <= 201 && enemy_pos[0][0] <= 19 && enemy_movement[0] == false)
    		enemy_pos[1][0] += enemy_speed_y;
		
    	if(enemy_pos[0][0] >= 201 || enemy_pos[0][0] <= 19)
    		enemy_movement[0] = !enemy_movement[0];
    	
    	//Enemy 1
    	
    	//Enemy 2
    	
    	if(enemy_movement[1] == false && enemy_pos[1][1] <= 300 && enemy_pos[0][1] <= 100)
    		enemy_pos[1][1] += enemy_speed_y;
    	
    	else if(enemy_pos[0][1] <= 300 && enemy_movement[1] == true && enemy_pos[1][1] >= 300)
    		enemy_pos[0][1] += enemy_speed_x;
    	
    	else if(enemy_movement[1] == true && enemy_pos[1][1] >= 30 && enemy_pos[0][1] >= 300)
    		enemy_pos[1][1]-= enemy_speed_y;
    	
    	else if(enemy_movement[1] == false && enemy_pos[1][1] <= 30 && enemy_pos[0][1] >= 100)
    		enemy_pos[0][1]-= enemy_speed_x;
    	
    	if(enemy_pos[1][1] >= 301 || enemy_pos[1][1] <= 29)
    		enemy_movement[1] = !enemy_movement[1];
    	
    	//Enemy 2
    	
    	//Enemy 3
    	
    	if(enemy_movement[2] == false && enemy_pos[1][2] <= 250 && enemy_pos[0][2] <= 10)
    		enemy_pos[1][2] += enemy_speed_y;
    	
    	else if(enemy_pos[0][2] <= 400 && enemy_movement[2] == true && enemy_pos[1][2] >= 250)
    		enemy_pos[0][2] += enemy_speed_x;
    	
    	else if(enemy_movement[2] == true && enemy_pos[1][2] >= 20 && enemy_pos[0][2] >= 400)
    		enemy_pos[1][2]-= enemy_speed_y;
    	
    	else if(enemy_movement[2] == false && enemy_pos[1][2] <= 20 && enemy_pos[0][2] >= 10)
    		enemy_pos[0][2]-= enemy_speed_x;
    	
    	if(enemy_pos[1][2] >= 251 || enemy_pos[1][2] <= 21)
    		enemy_movement[2] = !enemy_movement[2];
    	
    	//Enemy 3
    	
	}
	public static void Level3(String name, boolean[] level_complete, boolean player_alive) throws InterruptedException
	{
		synchronized (c2) {
			
			//Clears Screen
			
    		c2.clear();
    		
    		//Displays Player Name, Bullet Count, And The Finish
    		
    		c2.drawString(name, player_x-(5*(name_length/2)), player_y - 4);
    		c2.drawString("Bullets: "+bullet, player_x - 15, player_y + 15);
    		c2.drawRect(player_x, player_y, 5, 5);
    		c2.drawImage(DrawImage("finish"), 426, 450, 100, 50);
    		
    		//Randomly Drops The Ammo
    		
    		if(drop == true)
    		{
    			bullet_drop_x = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		bullet_drop_y = (int)(Math.random() * ((450 - 10) + 1)) + 10;
	    		drop = false;
    		}
    		
    		c2.drawImage(DrawImage("ammo"), bullet_drop_x, bullet_drop_y, 70, 35);
    			
    		for(int i = 0; i < 3; i++)
    		{
    			if(enemy_alive[i] == true)
        		{
        			c2.drawString("Health: "+ enemy_pos[2][i], enemy_pos[0][i] + 16, enemy_pos[1][i] + 56);
        			c2.drawImage(DrawImage("enemy3"), enemy_pos[0][i], enemy_pos[1][i], 100, 50);
        			c2.setColor(Color.red);
        			c2.drawRect(27,35, 60, 70);
        			c2.drawString("Danger Zone",27,70);
        			c2.setColor(Color.black);
        		}
    		}
    		
    		//Shoots The Bullet
    		
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
    	
    	//Enemy Movement
    	
    	EnMoveLevel3();
    	
    	//Movement
    	
    	Movement();
    	
    	//Shoot
    	
    	Shot();
    	
	}
	public static void EnMoveLevel3()
	{
		//Moves The Enemies In Level 3
		
		int enemy_speed_x = 2;
		int enemy_speed_y = 2;
		int enemy_speed_x_diagonal = 1;
		int enemy_speed_y_diagonal = 1;
		
		//Enemy 
		for(int i = 0; i < 3; i++)
		{
			if(enemy_alive[i] == true)
			{
				//enemy speed
				if(player_y - enemy_pos[1][i] < -60 || player_y - enemy_pos[1][i] > 100)
				{
					enemy_speed_y = 4;
					enemy_speed_y_diagonal = 2;
				}
				
				else
				{
					enemy_speed_y = 2;
					enemy_speed_y_diagonal = 1;
				}
				
				if(player_x - enemy_pos[0][i] < -21 || player_x - enemy_pos[0][i] > 110)
				{
					enemy_speed_x = 4;
					enemy_speed_x_diagonal = 2;
				}
				
				else
				{
					enemy_speed_x = 2;
					enemy_speed_x_diagonal = 1;
				}
				
				//enemy movement
				if(player_x - enemy_pos[0][i] < 40 && player_y - enemy_pos[1][i] < 20)
				{
					enemy_pos[0][i] -= enemy_speed_x_diagonal;
					enemy_pos[1][i] -= enemy_speed_y_diagonal;
				}
				
				
				if(player_x - enemy_pos[0][i] > 46 && player_y - enemy_pos[1][i] > 25)
				{
					enemy_pos[0][i] += enemy_speed_x_diagonal;
					enemy_pos[1][i] += enemy_speed_y_diagonal;
				}
				
				else if(player_x - enemy_pos[0][i] > 46)
					enemy_pos[0][i] += enemy_speed_x;
				
				else if(player_y - enemy_pos[1][i] < 20)
					enemy_pos[1][i] -= enemy_speed_y;
				
				else if(player_x - enemy_pos[0][i] < 40)
					enemy_pos[0][i] -= enemy_speed_x;
				
				else if(player_y - enemy_pos[1][i] > 25)
					enemy_pos[1][i] += enemy_speed_y;
			}
		}
    	//Enemy 
		
		//Sets enemies active
		
		if(enemy_alive[0] == true)
		{
			enemy_alive[1] = false;
			enemy_alive[2] = false;
		}
		
		else if(enemy_alive[1] == false && enemy_pos[2][1] >0)
			enemy_alive[1] = true;
		
		else if(enemy_alive[1] == false && enemy_alive[2] == false && enemy_pos[2][2] >0)
			enemy_alive[2] = true;
		
	}
	public static Image DrawImage(String image_name)
	{
		//define all the images
		
		Image finish = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/Finish.png"));
		Image enemy1 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En1.png"));
		Image enemy2 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En2.png"));
		
		Image enemy3 = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/En3.png"));
		Image ammo = Toolkit.getDefaultToolkit().getImage(c2.getClass().getClassLoader().getResource("Images/Ammo.png"));
		
		//see what image is needed and return the image
		
		if(image_name.equalsIgnoreCase("finish"))
			return finish;
		
		if(image_name.equalsIgnoreCase("ammo"))
			return ammo;
		
		if(image_name.equalsIgnoreCase("enemy1"))
			return enemy1;
		
		if(image_name.equalsIgnoreCase("enemy2"))
			return enemy2;
		
		if(image_name.equalsIgnoreCase("enemy3"))
			return enemy3;
		
		return null;
		
	}
	
}

