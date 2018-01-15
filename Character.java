public class Character{
    protected String name;
    protected boolean alive;
    protected String type;
    public Character(String givenName){
	type = "Citizen";
	alive = true;
	name = givenName;
    }
    public String setName(String newName){
	String oldName = name;
	name = newName;
	return oldName;
    }
    public String getType(){
	return type;
    }
    public String getName(){
	return name;
    }
    public String toString()
    {
	return name + " -- " + type;
    }
    public boolean isAlive(){
	return alive;
    }
    public void die(){
	alive = false;
    }
    
}
