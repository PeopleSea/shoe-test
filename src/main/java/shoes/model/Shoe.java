package shoes.model;

import java.util.Random;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import shoes.common.constants.ShoeConstrant;
import shoes.utils.GenerateShoeBoxNumberUtils;

@Entity
@Table(name = "PR_SHOE")
public class Shoe extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private static Shoe shoe = new Shoe();
	
	public Shoe createShoe(){
		Random ran = new Random();		
		ShoeConstrant shoeConstrant = ShoeConstrant.getShoe(ran.nextInt(6));
		UUID uuid = UUID.randomUUID();
		shoe.setId(uuid.toString());
		shoe.setShoeNumber(GenerateShoeBoxNumberUtils.genNumber());
		shoe.setShoeName(shoeConstrant.getShoeName());
		shoe.setShoeColor(shoeConstrant.getShoeColor());
		shoe.setShoeSize(shoeConstrant.getShoeSize());
		return shoe;
    }
	
	public static Shoe returnShoe() {
		Random ran = new Random();		
		ShoeConstrant shoeConstrant = ShoeConstrant.getShoe(ran.nextInt(6));
		UUID uuid = UUID.randomUUID();
		shoe.setId(uuid.toString());
		shoe.setShoeNumber(GenerateShoeBoxNumberUtils.genNumber());
		shoe.setShoeName(shoeConstrant.getShoeName());
		shoe.setShoeColor(shoeConstrant.getShoeColor());
		shoe.setShoeSize(shoeConstrant.getShoeSize());
		return shoe;
    }
	
	@Id
	@Column(name = "SHOE_ID", length = 36)
	protected String id;
	
	@Column(name = "SHOE_NUMBER")
	protected String shoeNumber;
	
	@Column(name = "SHOE_Name")
	protected String shoeName;
	
	@Column(name = "SHOE_COLOR")
	protected String shoeColor;
	
	@Column(name = "SHOE_SIZE")
	protected String shoeSize;
	
	@JsonIgnore
	@OneToOne(mappedBy = "shoe", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	protected ShoeFP shoeFP;
	
	public Shoe() {
	}

	public Shoe(String id, String shoeNumber, String shoeName, String shoeColor, String shoeSize) {
		super();
		this.id = id;
		this.shoeNumber = shoeNumber;
		this.shoeName = shoeName;
		this.shoeColor = shoeColor;
		this.shoeSize = shoeSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(String shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public String getShoeName() {
		return shoeName;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}

	public String getShoeColor() {
		return shoeColor;
	}

	public void setShoeColor(String shoeColor) {
		this.shoeColor = shoeColor;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public ShoeFP getShoeFP() {
		return shoeFP;
	}

	public void setShoeFP(ShoeFP shoeFP) {
		this.shoeFP = shoeFP;
	}

	@Override
	public String toString() {
		return "Shoe [id=" + id + ", shoeNumber=" + shoeNumber + ", shoeName=" + shoeName + ", shoeColor=" + shoeColor
				+ ", shoeSize=" + shoeSize + "]";
	}
}
