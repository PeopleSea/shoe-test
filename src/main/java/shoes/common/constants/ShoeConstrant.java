package shoes.common.constants;

public enum ShoeConstrant {
	A("BlueShoe", "Blue", "US8"),
    B("PinkShoe", "Pink", "US8"),
    C("RedShoe", "Red", "US8"),
    D("GreenShoe", "Green", "US8"),
    E("WhiteShoe", "White", "US8"),
    F("BlackShoe", "Black", "US8"),
    G("YellowShoe", "Yellow", "US8");
	
    private String shoeName;
    private String shoeColor;
    private String shoeSize;
    
	
    private ShoeConstrant(String shoeName, String shoeColor, String shoeSize){
		this.shoeName = shoeName;
        this.shoeColor = shoeColor;
        this.shoeSize = shoeSize;
	}
	
	public static ShoeConstrant getShoe(int i){
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
	
	public String getShoeName() {
        return shoeName;
    }
	
	public String getShoeColor() {
        return shoeColor;
    }
	
	public String getShoeSize() {
        return shoeSize;
    }

    
}
