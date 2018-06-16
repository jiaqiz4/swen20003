/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public class Elixir_of_Life extends Item {

	private static final String name="Elixir of Life";
    private static final String image_path="assets/items/elixir.png";

	public Elixir_of_Life(double xpos, double ypos)
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
			inventory[3]=true;
			player.setInventory(inventory);
		}
	}

}
