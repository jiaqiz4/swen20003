/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Unit extends Entity {
    protected Status status;
    
    protected Color VALUE = new Color(1.0f, 1.0f, 1.0f); 
    protected Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); 
 	protected Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); 
 	protected static final int UP=25;
 	protected static final int MORE_WIDTH=20;
 	protected static final int BARHEIGHT=20;
	
 	/** Creates a new unit.
     * @param image_path Path of unit's image file.
     * @param xpos x location in pixels.
     * @param ypos y location in pixels.
     * @name name of the entity
     * @param status of unit 
     */
 	
	public Unit(String image_path, double xpos, double ypos,Status status,String name) throws SlickException {
		super(image_path, xpos, ypos,name);
		this.status=status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
     * render the blood and the image of units
     * @param camera camera of the player
     * @param g the graphic
     */	
	public void render( Camera camera,Graphics g)throws SlickException
    {  		
		super.render(camera,g);
		int lineWidth ;
		int barWidth;
		float HP;
		
		lineWidth = g.getFont().getWidth(name);
		barWidth = lineWidth + MORE_WIDTH;		
		HP= (float) this.getStatus().getHP() / this.getStatus().getMax_HP() * barWidth;
		
		g.setColor(BAR_BG);
		g.fillRect((float)xpos-camera.getMinX() - barWidth / 2, (float)ypos-camera.getMinY() - UP - BARHEIGHT, barWidth, BARHEIGHT);
		g.setColor(BAR);
		g.fillRect((float)xpos-camera.getMinX() - barWidth / 2, (float)ypos-camera.getMinY() - UP - BARHEIGHT, HP, BARHEIGHT);
		g.setColor(VALUE);
		g.drawString(name, (float)xpos-camera.getMinX() - lineWidth / 2, (float) ypos-camera.getMinY() - UP - BARHEIGHT);
		
        
    }
	

}
