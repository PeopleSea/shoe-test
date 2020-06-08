package shoes.model;

public class CreateShoe extends  ShoeCommand{

	public CreateShoe(Shoe shoe) {
		super(shoe);
	}

	@Override
    public void execute() {
		shoe.createShoe();
    }
}
