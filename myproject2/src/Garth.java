/* student :Jiaqi Zhou
 * num:743304
*/
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Garth extends Villager {
	
	private String talk1="Find the Amulet of Vitality, across the river to the west.";
	private String talk2="Find the Sword of Strength - cross the bridge to the east, then head south.";
	private String talk3="Find the Tome of Agility, in the Land of Shadows.";
	private String talk4= "You have found all the treasure I know of.";
	
    private static boolean[] player_inventory={false,false,false,false};
	
    private static final Status status=new Status(1,1,0,0,0);
    private static final String name="Garth";
    private static final String image_path="assets/units/peasant.png"; 

	public Garth(double xpos, double ypos)
			throws SlickException {
		super(image_path, xpos, ypos, status,name);
		// TODO Auto-generated constructor stub
	}	

	
	/**
     * things that the villager do to the player
     * @param player 
     * @param delta time per frame
     * @param distance distance between the monster and the player
     * @param push_t whether the player talk to the villager
     */
    public void villager_action (Player player,int delta,double distance,boolean push_t){

    	if (distance<50 && push_t==true){
    		player_inventory = player.getInventory();	
    		talk_time=TALKCOOL;   		
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
		    if (player_inventory[0]==false){
			    super.talke_bar(talk1, camera, g);
		    }else if(player_inventory[1]==false) {
		    	super.talke_bar(talk2, camera, g);
		    }else if(player_inventory[2]==false) {
		    	super.talke_bar(talk3, camera, g);
		    }else {
		    	super.talke_bar(talk4, camera, g);
		    }
	
	    }

    }
	
}

