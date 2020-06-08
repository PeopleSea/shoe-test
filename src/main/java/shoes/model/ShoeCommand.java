package shoes.model;

public abstract class ShoeCommand {
    
    Shoe shoe;

    public ShoeCommand(Shoe shoe){
        this.shoe = shoe;
    }

    public abstract void execute();
}
