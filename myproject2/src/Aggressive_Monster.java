/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Aggressive_Monster extends Monster {
	
	protected static final double SPEED=0.25;
	protected static final double CHASING=150;
	protected static final double ATTACK_DIS=50;

	public Aggressive_Monster(String image_path, double xpos, double ypos, Status status,String name) throws SlickException {
		super(image_path, xpos, ypos, status,name);
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
	public void move(World world,double playerx,double playery,double delta,boolean push_a,int player_cooldown){
        double xdistance;
        double ydistance;
        double distance;
        double new_x;
        double new_y;
        
        distance=Math.sqrt(Math.pow((playerx - xpos), 2) + Math.pow((playery-ypos), 2));
		
        //chase the player when distance<150
        if (distance<CHASING && distance>=ATTACK_DIS){
            xdistance=xpos-playerx;
            ydistance=ypos-playery;
            new_x=xpos+(-1)*(xdistance / distance) * SPEED * delta;
            if(!world.terrainBlocks(new_x + (-1)* img.getWidth() / 4, this.ypos + img.getHeight() / 4) 
                    && !world.terrainBlocks(new_x + (-1)* img.getWidth() / 4, this.ypos - img.getHeight() / 4)) {
                this.xpos = new_x;
            }
            new_y=ypos+(-1)*(ydistance / distance) * SPEED * delta;
            if(!world.terrainBlocks(this.xpos + img.getWidth() / 4, new_y + (-1) * img.getHeight() / 4) 
                    && !world.terrainBlocks(this.xpos - img.getWidth() / 4, new_y + (-1)* img.getHeight() / 4)){
   	            this.ypos = new_y;
            }        	
        }
	}
}
