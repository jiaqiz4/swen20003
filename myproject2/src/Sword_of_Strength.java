/* student :Jiaqi Zhou
 * num:743304
*/

import org.newdawn.slick.SlickException;

public class Sword_of_Strength extends Item {
	
	
    private static final String name="Sword of Strength";
    private static final String image_path="assets/items/sword.png";
    private static final int ADD_DAMAGE=30;
    //private boolean exist=true;

	public Sword_of_Strength(double xpos, double ypos)
			throws SlickException {
		super(image_path, xpos, ypos,name);
		// TODO Auto-generated constructor stub
	}
    
	
	/** update the status of player when pick up the item
     * @param player
     * @param distance distance between the player and the item
     */
	public void update(Player player,double distance){
		
		boolean[] inventory=player.getInventory();
		if (distance<50){
			exist=false;
		    inventory[1]=true;
		    player.setInventory(inventory);
		    player.setStatus(new Status(player.getStatus().getHP(),player.getStatus().getMax_HP(), player.getStatus().getDamage()+ADD_DAMAGE,player.getStatus().getcooldown(), player.getStatus().getrest_Cooldown()));
		}		
	}
}
