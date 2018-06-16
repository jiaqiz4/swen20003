/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.SlickException;

public abstract class Item extends Entity {
	
	protected boolean exist=true;

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	/** Creates a new Item.
     * @param image_path Path of image file.
     * @param xpos x location in pixels.
     * @param ypos y location in pixels.
     * @name name of the item
     */
	public Item(String image_path, double xpos, double ypos,String name) throws SlickException {
		super(image_path, xpos, ypos,name);
		
	}
	
	/** update the status of player when pick up the item
     * @param player
     * @param distance distance between the player and the item
     */
	 public abstract void update(Player player, double distance);
	 
}
