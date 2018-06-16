/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Tome_of_Agility extends Item {
	
	
	private static final String name="Tome of Agility";
    private static final String image_path="assets/items/tome.png";
    private static final int MINUS_COOL=300;

	public Tome_of_Agility(double xpos, double ypos)
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
		    inventory[2]=true;
		    player.setInventory(inventory);
		    player.setStatus(new Status(player.getStatus().getHP(),player.getStatus().getMax_HP(), player.getStatus().getDamage(),player.getStatus().getcooldown()-MINUS_COOL, player.getStatus().getrest_Cooldown()));
		}		
	}
	
	

}
