package shoes.model;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
	
	private List<ShoeCommand> commandList = new ArrayList<>();

    public void addCommand(ShoeCommand shoeCommand) {
        commandList.add(shoeCommand);
    }

    public void execute(){
        for (ShoeCommand shoeCommand :
                commandList) {
        	shoeCommand.execute();
        }
    }
}

