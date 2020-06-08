package shoes.common.constants;

public enum ShoeBoxConstrant {
	A("BlueBox", 15, 10, 10),
    B("PinkBox", 15, 10, 10),
    C("RedBox", 15, 10, 10),
    D("GreenBox", 15, 10, 10),
    E("WhiteBox", 15, 10, 10),
    F("BlackBox", 15, 10, 10),
    G("YellowBox", 15, 10, 10);
	
    private String boxName;
    private Integer shoeBoxWidth;
    private Integer shoeBoxLength;
    private Integer shoeBoxHeight;
    
	
    private ShoeBoxConstrant(String boxName, Integer shoeBoxWidth, Integer shoeBoxLength, Integer shoeBoxHeight){
		this.boxName = boxName;
        this.shoeBoxWidth = shoeBoxWidth;
        this.shoeBoxLength = shoeBoxLength;
        this.shoeBoxHeight = shoeBoxHeight;
	}
	
	public static ShoeBoxConstrant getShoeBox(int i){
	    switch(i){
	      case 0:
	        return A;
	      case 1:
	        return B;
	      case 2:
	        return C;
	      case 3:
	        return D;
	      case 4:
		    return E;
	      case 5:
		    return F;
	      case 6:
		    return G;
	      default:
	        return null;
	    }
	  }
	
	public String getBoxName() {
        return boxName;
    }
	
	public int getShoeBoxWidth() {
        return shoeBoxWidth;
    }
	
	public int getShoeBoxLength() {
        return shoeBoxLength;
    }
	
	public int getShoeBoxHeight() {
        return shoeBoxHeight;
    }

    
}
