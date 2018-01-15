public class Doctor extends Character{
    public Doctor(String name){
	super(name);
	type = "Doctor";
    }
    public Character save (Character person){
	return person;
    }
}
