import org.newdawn.slick.Color;
//import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Elvira extends Villager {
    
	private String talk1="Return to me if you ever need healing.";
    private String talk2="You're looking much healthier now.";
    
    Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
    Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
    
    int player_hp;
	int player_maxhp;
	boolean not_good=false;
	
	private static final Status status=new Status(1,1,0,0,0);
    private static final String name="Elvira";
    private static final String image_path="assets/units/shaman.png";
	
	public Elvira(double xpos, double ypos)
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
	public void villager_action (Player player,int delta,double distance,boolean push_t) {
		
		//max the hp of the player
		if (distance<50 && push_t==true){ 		
    		talk_time=TALKCOOL;  
    		player_hp=player.getStatus().getHP();
		    player_maxhp=player.getStatus().getMax_HP();
		    if (player.getStatus().getHP()< player.getStatus().getMax_HP()){
		    	not_good=true;
		    	player.setStatus(new Status(player.getStatus().getMax_HP(),player.getStatus().getMax_HP(), player.getStatus().getDamage(),player.getStatus().getcooldown(), player.getStatus().getrest_Cooldown()));
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
			if (not_good==true){
				super.talke_bar(talk2, camera, g);
			}else if (not_good==false){
				super.talke_bar(talk1, camera, g);
			}
		
		}		
	}
	

	

}
