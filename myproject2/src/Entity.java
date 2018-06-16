
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity {	
	protected double xpos;
	protected double ypos;
	protected Image img=null;
	protected double distance;
	protected String name;
	
	/** Creates a new entity.
     * @param image_path Path of entity's image file.
     * @param xpos x location in pixels.
     * @param ypos y location in pixels.
     * @name name of the entity
     */
	public Entity(String image_path, double xpos, double ypos,String name)
	    throws SlickException
	{
		this.img = new Image(image_path);
		this.xpos = xpos;
		this.ypos = ypos;
		this.name=name;
	}
	
	public double getX() {
		return xpos;
	}

	public double getY() {
		return ypos;
	}
	


	/** render the image of the item
	 * @param camera the camera of the player
     * @param g The current Graphics context.
     */
	public void render( Camera camera,Graphics g)throws SlickException
    {
        img.drawCentered((int) xpos-camera.getMinX(), (int) ypos-camera.getMinY());
        
    }
    
	
	
	public Image getImg() {
		return img;
	}

	


}
