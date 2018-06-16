/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 * student :Jiaqi Zhou
 * num:743304
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
    private static final int PLAYER_START_X = 756, PLAYER_START_Y =684 ;
    private static final int NUM_NON_PLAYER=4;
    private static final int NUM_GIANTBAT = 29;
    private static final int NUM_ZOMBIE=39;
    private static final int NUM_BANDIT=33;
    private static final int NUM_SKELETON=23;
    private static final int NUM_DRAELIC=1;
    
    private int[][] Position;
   
    public static final int SCREEN_WIDTH = 800;
    /** Screen height, in pixels. */
    public static final int SCREEN_HEIGHT = 600;
    
    private static final int TOTAL_NUM = 131;
	private static final int XY = 2;
	
    private Render_Panel panel;
    
    private Player player = null;
    private TiledMap map = null;
    private Camera camera = null;
    
    private ArrayList<Item> item = new ArrayList<Item>();
    private ArrayList<Villager> villager=new ArrayList<Villager>();   
    private ArrayList<Monster> monsters = new ArrayList<Monster>();   
   
    /** Map width, in pixels. */
    private int getMapWidth()
    {
        return map.getWidth() * getTileWidth();
    }

    /** Map height, in pixels. */
    private int getMapHeight()
    {
        return map.getHeight() * getTileHeight();
    }

    /** Tile width, in pixels. */
    private int getTileWidth()
    {
        return map.getTileWidth();
    }

    /** Tile height, in pixels. */
    private int getTileHeight()
    {
        return map.getTileHeight();
    }

    /** Create a new World object. 
     * @throws IOException */
    public World()
    throws SlickException, IOException
    {   int i;
        Position = new int [TOTAL_NUM][XY];
        map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);
        player = new Player(RPG.ASSETS_PATH + "/units/player.png", PLAYER_START_X, PLAYER_START_Y,new Status(100,100,26,600,0));
        panel= new Render_Panel(player);
        camera = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
        item.add(new Amulet_of_Vitality(965,3563));
        item.add(new Sword_of_Strength(546,6707));
        item.add(new Tome_of_Agility(4791,1253));
        item.add(new Elixir_of_Life(1976,402));
        
        
        //put the monsters in the arraylist
        
        readFile();     
        for (i=NUM_NON_PLAYER;i<NUM_GIANTBAT+NUM_NON_PLAYER;i++){
        	monsters.add(new Giant_Bat(RPG.ASSETS_PATH + "/units/dreadbat.png",Position[i][0],Position[i][1],new Status(40,40,0,0,0),"Giant Bat"));
        }
        
        for (i=NUM_NON_PLAYER+NUM_GIANTBAT;i<NUM_NON_PLAYER+NUM_ZOMBIE+NUM_ZOMBIE;i++){
        	monsters.add(new Zombie(RPG.ASSETS_PATH + "/units/zombie.png",Position[i][0],Position[i][1],new Status(60,60,10,800,0),"Zombie"));
        };
        
        for (i=NUM_NON_PLAYER+NUM_ZOMBIE+NUM_ZOMBIE;i<NUM_NON_PLAYER+NUM_GIANTBAT+NUM_ZOMBIE+NUM_BANDIT;i++){
        	monsters.add(new Bandit(RPG.ASSETS_PATH + "/units/bandit.png",Position[i][0],Position[i][1],new Status(40,40,8,200,0),"Bandit"));
        };
        
        for (i=NUM_NON_PLAYER+NUM_GIANTBAT+NUM_ZOMBIE+NUM_BANDIT;i<NUM_NON_PLAYER+NUM_GIANTBAT+NUM_ZOMBIE+NUM_BANDIT+NUM_SKELETON;i++){
        	monsters.add(new Skeleton(RPG.ASSETS_PATH + "/units/skeleton.png",Position[i][0],Position[i][1],new Status(100,100,16,500,0),"Skeleton"));
        };
        
        for (i=NUM_NON_PLAYER+NUM_GIANTBAT+NUM_ZOMBIE+NUM_BANDIT+NUM_SKELETON;i<NUM_NON_PLAYER+NUM_GIANTBAT+NUM_ZOMBIE+NUM_BANDIT+NUM_SKELETON+NUM_DRAELIC;i++){
        	monsters.add(new Draelic(RPG.ASSETS_PATH + "/units/necromancer.png",Position[i][0],Position[i][1],new Status(100,100,16,500,0),"Draelic"));
        };

        villager.add(new Aldric(467,679));
        villager.add(new Elvira(738,549));
        villager.add(new Garth(756,870));
        
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int dir_x, int dir_y, int delta, boolean push_a,boolean push_t)
    throws SlickException
    {
        player.move(this, dir_x, dir_y, delta);
        camera.update();
        pick_up();        
        for(Monster m: monsters){
    		m.move(this,player.getX(),player.getY(),delta,push_a,player.getStatus().getrest_Cooldown());
    		double distance = calculate_distance(player.getX(),player.getY(), m.getX(),m.getY());
    		if(m.isDead()==false){
    	        player.fight_monster(m,delta,push_a,PLAYER_START_X, PLAYER_START_Y,distance );
    	        m.fight_player(player,delta,PLAYER_START_X, PLAYER_START_Y,distance );    	        
    		}   	    
    	}        
        
        //update the status of the player when meet the villager
        for (Villager v:villager){
        	double distance=calculate_distance(player.getX(),player.getY(),v.getX(), v.getY());
        		v.villager_action(player,delta,distance,push_t);
        }
        
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        // Render the relevant section of the map
        int x = -(camera.getMinX() % getTileWidth());
        int y = -(camera.getMinY() % getTileHeight());
        int sx = camera.getMinX() / getTileWidth();
        int sy = camera.getMinY()/ getTileHeight();
        int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
        int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
        map.render(x, y, sx, sy, w, h);
        
        // Translate the Graphics object
        
        //g.translate(-camera.getMinX(), -camera.getMinY());        
        
        for (Item myitem:item){
        	if (myitem.isExist()==true){
        		myitem.render(camera,g);       		
       	} 
        	
        }
        //render monsters
        for(Monster m: monsters){
            if (m.isDead()==false){	
    		    m.render(camera,g);
            }
    	}        
        //render villager and dialogue
        for (Villager v:villager){
        	v.render(camera, g);
        	v.render_talk(camera, g);
        	
        }
        
        //render player and panel
        player.render(camera);               
        panel.renderPanel(g);
    
    }

    /** Determines whether a particular map coordinate blocks movement.
     * @param x Map x coordinate (in pixels).
     * @param y Map y coordinate (in pixels).
     * @return true if the coordinate blocks movement.
     */
    public boolean terrainBlocks(double x, double y)
    {
        // Check we are within the bounds of the map
        if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
            return true;
        }
        
        // Check the tile properties
        int tile_x = (int) x / getTileWidth();
        int tile_y = (int) y / getTileHeight();
        int tileid = map.getTileId(tile_x, tile_y, 0);
        String block = map.getTileProperty(tileid, "block", "0");
        return !block.equals("0");
    }
    
    
    /**
     * pickup items
     * change the status of player when pick up the item
     */
    public void pick_up(){
    	double itemX;   
    	double itemY;
    	double distance;
    	
    	for(Item myitem:item){
    		itemX=myitem.getX();
    		itemY=myitem.getY();
    		distance=calculate_distance(player.getX(),player.getY(),itemX,itemY);
    		if (distance<50 && myitem.isExist()){
    			myitem.update(player,distance);
    		}
    		
    	}
    }
    
    /**
     * calculate the distance of two entities
     * @param  playerx position of player
     * @param  playery position of player
     * @param position of entity in x
     * @param position of entity in y 
     * @return  distance of two position
     */
    public double calculate_distance(double playerx,double playery,double x, double y){
    	double distance;
    	distance=Math.sqrt(Math.pow((playerx - x), 2) + Math.pow((playery-y), 2)); 	
    	return distance;
    }   
    
    /**
     * read the file,put the data in list
     */
    
    public void readFile() throws IOException {
    	int line = 0;
		BufferedReader myfile;
		myfile = new BufferedReader(new FileReader("data/units.dat"));
		String now[];
		String thisrow;
		int x_pos=0;
		int y_pos=1;
		try {			
			while ((thisrow=myfile.readLine()) != null) {
				 now = thisrow.split(",");
				 Position[line][x_pos]=Integer.parseInt(now[1]);				 
				 Position[line][y_pos]=Integer.parseInt(now[2]);
				 line++;
			}		
			myfile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
    
    
    
    

}
