/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Amulet_of_Vitality extends Item {

	
	
	private static final String name="Amulet of Vitality";
    private static final String image_path="assets/items/amulet.png";
    private static final int ADDHP=80;

	public Amulet_of_Vitality(double xpos, double ypos)
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
		    inventory[0]=true;
		    player.setInventory(inventory);
		    player.setStatus(new Status(player.getStatus().getHP(),player.getStatus().getMax_HP()+ADDHP, player.getStatus().getDamage(),player.getStatus().getcooldown(), player.getStatus().getrest_Cooldown()));
		}		
	}



	
}
