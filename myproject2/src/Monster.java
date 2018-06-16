/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Monster extends Unit {
    protected static final double SPEED=0;
    private boolean dead=false;

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Monster(String image_path, double xpos, double ypos,Status status,String name) throws SlickException {
		super(image_path, xpos, ypos,status,name);
		//constructor
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
	}
	
	
	

    /** Update the game state for a frame.
     * change the status of the player when fighting with the monster
     * @param player the monster to fight 
     * @param delta time per frame
     * @param start_x original position of player 
     * @param start_y original position of player 
     * @param distance distance between the monster and the player
     */
	public void fight_player(Player player,int delta,double start_x,double start_y,double distance ){
		
		int monster_attack= (int)(Math.random()*status.getDamage());
		
		if(status.getrest_Cooldown()>0){
    		status.setrest_Cooldown(status.getrest_Cooldown()-delta);
    	}else{
    		status.setrest_Cooldown(0);
    	}
		
		if (status.getrest_Cooldown()==0 && distance<50){
			status.setrest_Cooldown(status.getcooldown());
			if(player.getStatus().getHP()-monster_attack>0){
			    player.setStatus(new Status(player.getStatus().getHP()-monster_attack,player.getStatus().getMax_HP(), player.getStatus().getDamage(),player.getStatus().getcooldown(), player.getStatus().getrest_Cooldown()));
			}else{
				player.setterXY(start_x, start_y);
				player.setStatus(new Status(player.getStatus().getMax_HP(),player.getStatus().getMax_HP(), player.getStatus().getDamage(),player.getStatus().getcooldown(),0));
			}
			
		}
		
		
	}

	
	
}
