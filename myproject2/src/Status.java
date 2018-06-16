/* student :Jiaqi Zhou
 * num:743304
*/
public class Status {

	private int HP;
	private int Max_HP;
	private int Damage;	
	private int cooldown;
	private int rest_cooldown;
	
	
	public int getcooldown() {
		return cooldown;
	}

	public void setcooldown(int cooldown) {
		this.cooldown =cooldown;
	}		

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMax_HP() {
		return Max_HP;
	}

	public void setMax_HP(int max_HP) {
		Max_HP += max_HP;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage += damage;
	}

	

    //back to max cooldown	
    public void reset_cooldown(){
    	this.rest_cooldown=cooldown;
    }
    

	
    public int getrest_Cooldown() {
		return rest_cooldown;
	}
    
	public void setrest_Cooldown(int rest_cooldown){
		this.rest_cooldown=rest_cooldown;
		
	}
	
	public void max_hp(){
		HP=Max_HP;
	}



	public Status(int HP, int Max_HP, int Damage, int cooldown, int rest_cooldown) {
		this.HP = HP;
		this.Max_HP = Max_HP;
		this.Damage = Damage;
		this.cooldown = cooldown;
		this.rest_cooldown=rest_cooldown;
	}
	
}
