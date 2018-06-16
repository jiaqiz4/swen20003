/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 * student :Jiaqi Zhou
 * num:743304
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The character which the user plays as.
 */
public class Player
{
    private Image img = null;
    private Image img_flipped = null;
    private Status status=null;
    
    
    private boolean[] inventory={false,false,false,false};

	public boolean[] getInventory() {
		return inventory;
	}

	public void setInventory(boolean[] inventory) {
		this.inventory = inventory;
	}

	// In pixels
    private double x, y;
    private double width, height;
    private boolean face_left = false;

    // Pixels per millisecond
    private static final double SPEED = 0.25;

    /** The x coordinate of the player (pixels). */
    public double getX()
    {
        return x;
    }
    
    public void setterXY(double xpos,double ypos){
    	this.x = xpos;
        this.y = ypos;
    }

    /** The y coordinate of the player (pixels). */
    public double getY()
    {
        return y;
    }
    

    public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	


	/** Creates a new Player.
     * @param image_path Path of player's image file.
     * @param x The Player's starting x location in pixels.
     * @param y The Player's starting y location in pixels.
     */
    public Player(String image_path, double x, double y,Status status)
        throws SlickException
    {
        img = new Image(image_path);
        img_flipped = img.getFlippedCopy(true, false);
        this.x = x;
        this.y = y;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.status=status;
    }
	
    /** Move the player in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void move(World world, double dir_x, double dir_y, double delta)
    {
        // Update player facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;

        // Move the player by dir_x, dir_y, as a multiple of delta * speed
        double new_x = this.x + dir_x * delta * SPEED;
        double new_y = this.y + dir_y * delta * SPEED;

        // Move in x first
        double x_sign = Math.signum(dir_x);
        if(!world.terrainBlocks(new_x + x_sign * width / 4, this.y + height / 4) 
                && !world.terrainBlocks(new_x + x_sign * width / 4, this.y - height / 4)) {
            this.x = new_x;
        }
        
        // Then move in y
        double y_sign = Math.signum(dir_y);
        if(!world.terrainBlocks(this.x + width / 4, new_y + y_sign * height / 4) 
                && !world.terrainBlocks(this.x - width / 4, new_y + y_sign * height / 4)){
            this.y = new_y;
        }
                
        if(status.getrest_Cooldown()-delta>0){
    		status.setrest_Cooldown((int) (status.getrest_Cooldown()-delta));
    		
    	}else{
    		status.setrest_Cooldown(0);
    	}
   
    }
     
    /** Update the game state for a frame.
     * change the status of the monster when fighting with the player
     * @param monster the monster to fight 
     * @param delta time per frame
     * @param push_a whether player is attack
     * @param start_x original position of player 
     * @param start_y original position of player 
     * @param distance distance between the monster and the player
     */
public void fight_monster(Monster monster,int delta,boolean push_a,double start_x,double start_y,double distance ){
    	
    	int player_attack;
    	
    	//check whether the player attack the monster
    	if (push_a==true && status.getrest_Cooldown()==0 && distance<50){
    		status.setrest_Cooldown(status.getcooldown());
    		player_attack=(int)(Math.random()*status.getDamage());
    		
    		
    		if(monster!=null&&monster.getStatus().getHP()-player_attack>0){
    		    monster.setStatus(new Status(monster.getStatus().getHP()-player_attack,monster.getStatus().getMax_HP(), monster.getStatus().getDamage(),monster.getStatus().getcooldown(), monster.getStatus().getrest_Cooldown()));
    		    
    		//the monster is dead    
    		}else if (monster!=null&&monster.getStatus().getHP()-player_attack<=0){
    			monster.setStatus(new Status(0,monster.getStatus().getMax_HP(), monster.getStatus().getDamage(),monster.getStatus().getcooldown(), monster.getStatus().getrest_Cooldown()));
    			monster.setDead(true);
    		}
    	}   	
        	
    }
    

    /** Draw the player to the screen at the correct place.
     * @param g The current Graphics context.
     * @param cam_x Camera x position in pixels.
     * @param cam_y Camera y position in pixels.
     */
    public void render(Camera camera)
    {	
        Image which_img;
        which_img = this.face_left ? this.img_flipped : this.img;
        which_img.drawCentered((int) x - camera.getMinX(), (int) y-camera.getMinY());
    }
}
