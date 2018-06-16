import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Aldric extends Villager {

	private String talk1="Please seek out the Elixir of Life to cure the king.";
    private String talk2="The elixir! My father is cured! Thank you!";
    boolean check=false;
	
    private static final Status status=new Status(1,1,0,0,0);
    private static final String name="Aldric";
    private static final String image_path="assets/units/prince.png"; 
    
	public Aldric(double xpos, double ypos)
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
	public void villager_action(Player player,int delta,double distance,boolean push_t){
		boolean[] inventory=player.getInventory();
		
		//check the elixir of life
		if (distance<50 && push_t==true){
			talk_time=TALKCOOL;
			if (inventory[3]==true){
			    check=true;
			    inventory[3]=false;
			    player.setInventory(inventory);
		    }
		}
		talk_time-=delta;
	}
    
	
	
	/**
     * render the dialogue of the villager and the player
     * @param camera camera of the player
     * @param g the graphic
     */
    public void render_talk( Camera camera,Graphics g)throws SlickException{
		
		if(talk_time>0){
			if (check==true){
				super.talke_bar(talk2, camera, g);			
			}else {

				super.talke_bar(talk1, camera, g);
			}	
		}
    }	
}

