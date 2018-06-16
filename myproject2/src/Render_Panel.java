import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Render_Panel {
	private Image panel;
	//private RPG RPG;
	private Player player;
	
	
	private Amulet_of_Vitality amulet_of_vitality=new Amulet_of_Vitality(965,1200);
    private Sword_of_Strength sword_of_strength=new Sword_of_Strength(955,1200);
    private Tome_of_Agility tome_of_agility=new Tome_of_Agility(900,1200);
    private Elixir_of_Life elixir_of_life=new Elixir_of_Life(900,1200);
	
    private Image[] total_panel={amulet_of_vitality.getImg(),sword_of_strength.getImg(),tome_of_agility.getImg(),elixir_of_life.img};
    
	public Render_Panel(Player player) throws SlickException {
		// TODO Auto-generated constructor stub
		panel= new Image ("assets/panel.png");
		//RPG = new RPG ();
		this.player=player;
		
	}
    
	public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.SCREEN_HEIGHT - RPG.panelheight);

        // Display the player's health
        text_x = 15;
        text_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        int HP=player.getStatus().getHP();
        int Max_HP=player.getStatus().getMax_HP();
        
        text = ""+HP+"/"+Max_HP;                                 // TODO: HP / Max-HP

        bar_x = 90;
        bar_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = 0.75f;                         // TODO: HP / Max-HP
        hp_bar_width = (int) (bar_width * HP/Max_HP);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;                         // TODO: Damage
        int damage=player.getStatus().getDamage();
        text=""+damage;
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        int cooldown=player.getStatus().getcooldown();
        text = ""+cooldown;                                    // TODO: Cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        
        
        inv_x = 490;
        inv_y = RPG.SCREEN_HEIGHT - RPG.panelheight
            + ((RPG.panelheight - 72) / 2);
        boolean[] inventory=player.getInventory();
        for (int i=0;i<4;i++)                // TODO
        {    if (inventory[i]==true){
        	total_panel[i].draw(inv_x, inv_y);
        }
            inv_x += 72;
        }
    }
}
