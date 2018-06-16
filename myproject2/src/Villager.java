/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Villager extends Unit {
	
	protected static final int BARHEIGHT=20; 
	protected static final int MORE_WIDTH=20;
	protected static final int UP=50;

	protected int talk_time;
	protected static final int TALKCOOL=4000;
	public Villager(String image_path, double xpos, double ypos, Status status,String name)
			throws SlickException {
		super(image_path, xpos, ypos, status,name);
	}
	
	
	/**
     * things that the villager do to the player
     * @param player 
     * @param delta time per frame
     * @param distance distance between the monster and the player
     * @param push_t whether the player talk to the villager
     */
    public abstract void villager_action (Player player,int delta,double distance,boolean push_t);
	
    
    
    /**
     * render the dialogue of the villager and the player
     * @param camera camera of the player
     * @param g the graphic
     */
    public abstract void render_talk (Camera camera,Graphics g) throws SlickException;
    
    
    /**
     * render the format of the dialogue between the villager and the player
     * @param string of the talk
     * @param camera camera of the player
     * @param g the graphic
     */
    public void talke_bar(String talk,Camera camera,Graphics g){ 	
		int lineWidth;		
		int barWidth; 
		
    	lineWidth = g.getFont().getWidth(talk);
		barWidth = lineWidth+MORE_WIDTH;
		g.setColor(BAR_BG);
		g.fillRect((float)xpos-camera.getMinX() - barWidth / 2, (float)ypos-camera.getMinY() - UP - BARHEIGHT, barWidth, BARHEIGHT);
		g.setColor(VALUE);
		g.drawString(talk, (float)xpos-camera.getMinX() - lineWidth / 2, (float) ypos-camera.getMinY() - UP - BARHEIGHT);
    	
    }
    
	
	
}

