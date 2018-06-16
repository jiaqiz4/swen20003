/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Passive_Monster extends Monster {
	
	
	
	private int change_direction=3000;
	private int move_time=0;
	private int safe_wait=0;
	
	protected static final double SPEED=0.20;
	
	private int dir_x=0;
	private int dir_y=0;
	

	public Passive_Monster(String image_path, double xpos, double ypos, Status status,String name) throws SlickException {
		super(image_path, xpos, ypos, status ,name);
		// TODO Auto-generated constructor stub
	}	
	
	
	
	
	/** Update the game state for a frame.
     * the movement of the player
     * @param world the world 
     * @param delta time per frame
     * @param playerx position of player 
     * @param playery position of player 
     * @param push_a whether player is attack
     * @param distance distance between the monster and the player
     */
	public void move(World world,double playerx,double playery,double delta,boolean push_a, int player_cooldown){
        double xdistance;
        double ydistance;
        double distance;
        double new_x;
        double new_y;
 

        distance=Math.sqrt(Math.pow((playerx - xpos), 2) + Math.pow((playery-ypos), 2));
        
        if (push_a==true && distance<50 && player_cooldown<=0){
        	safe_wait=5000;
        }
        
        //when attacked by the player, moving around
        if (safe_wait>0){
        	safe_wait-=delta;      	
        	xdistance=xpos-playerx;
            ydistance=ypos-playery;
      		dir_x=(int)(xdistance/Math.abs(xdistance));
      		new_x=xpos+(xdistance / distance) * SPEED * delta;
      		if(!world.terrainBlocks(new_x + dir_x * img.getWidth() / 4, this.ypos + img.getHeight() / 4) 
                       && !world.terrainBlocks(new_x + dir_x * img.getWidth() / 4, this.ypos - img.getHeight() / 4)) {
                this.xpos = new_x;
            }        		
      		dir_y=(int)(ydistance/Math.abs(ydistance));
      		new_y=ypos+(ydistance / distance) * SPEED * delta;
      		if(!world.terrainBlocks(this.xpos + img.getWidth() / 4, new_y + dir_y * img.getHeight() / 4) 
      	                && !world.terrainBlocks(this.xpos - img.getWidth() / 4, new_y + dir_y * img.getHeight() / 4)){
      	         this.ypos = new_y;
      	    }
        	
        }
        
        move_time+=delta;
        
        //randly move on the map
        if(move_time >= change_direction && safe_wait<= 0){
        	dir_x=(int)(1+Math.random()*(3))-2;
    		dir_y=(int)(1+Math.random()*(3))-2;
    		move_time=0;
        }
        if (move_time>=0 && safe_wait<= 0){
    		new_x=xpos+delta*SPEED*dir_x;
        	new_y=ypos+delta*SPEED*dir_y;

        	if(!world.terrainBlocks(new_x + dir_x * img.getWidth() / 4, this.ypos + img.getHeight() / 4) 
                    && !world.terrainBlocks(new_x + dir_x * img.getWidth() / 4, this.ypos - img.getHeight() / 4)){
                this.xpos = new_x;
        	} 
        	if(!world.terrainBlocks(this.xpos + img.getWidth() / 4, new_y + dir_y * img.getHeight() / 4) 
 	            && !world.terrainBlocks(this.xpos - img.getWidth() / 4, new_y + dir_y * img.getHeight() / 4)){
 	            this.ypos = new_y;
  	        }
        }
    
	}		
	
}
