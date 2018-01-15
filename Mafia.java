public class Mafia extends Character
{
    public Mafia(String name)
    {
	super(name);
	type = "Mafia";
    }
    public Character kill(Character person){
	person.die();
	return person;
    }

}
