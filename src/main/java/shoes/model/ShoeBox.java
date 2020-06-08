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

import shoes.common.constants.ShoeBoxConstrant;
import shoes.utils.GenerateShoeBoxNumberUtils;

@Entity
@Table(name = "PR_SHOEBOX")
public class ShoeBox extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private static ShoeBox shoeBox = new ShoeBox();
	
	public static ShoeBox returnShoeBox() {
		Random ran = new Random();		
		ShoeBoxConstrant shoeBoxConstrant = ShoeBoxConstrant.getShoeBox(ran.nextInt(6));
		UUID uuid = UUID.randomUUID();
		shoeBox.setId(uuid.toString());
		shoeBox.setShoeBoxNumber(GenerateShoeBoxNumberUtils.genNumber());
		shoeBox.setShoeBoxName(shoeBoxConstrant.getBoxName());
		shoeBox.setShoeBoxLength(shoeBoxConstrant.getShoeBoxLength());
		shoeBox.setShoeBoxWidth(shoeBoxConstrant.getShoeBoxWidth());
		shoeBox.setShoeBoxHeight(shoeBoxConstrant.getShoeBoxHeight());
        return shoeBox;
    }

	@Id
	@Column(name = "SHOEBOX_ID", length = 36)
	protected String id;
	
	@Column(name = "SHOEBOX_NUMBER")
	protected String shoeBoxNumber;
	
	@Column(name = "SHOEBOX_NAME")
	protected String shoeBoxName;
	
	@Column(name = "SHOEBOX_WIDTH")
	protected Integer shoeBoxWidth;
	
	@Column(name = "SHOEBOX_LENGTH")
	protected Integer shoeBoxLength;

	@Column(name = "SHOEBOX_HEIGHT")
	protected Integer shoeBoxHeight;
	
	@JsonIgnore
	@OneToOne(mappedBy = "shoeBox", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	protected ShoeFP shoeFP;
	
	public ShoeBox() {
	}

	public ShoeBox(String id, String shoeBoxNumber, String shoeBoxName, Integer shoeBoxWidth, Integer shoeBoxLength, Integer shoeBoxHeight) {
		super();
		this.id = id;
		this.shoeBoxNumber = shoeBoxNumber;
		this.shoeBoxName = shoeBoxName;
		this.shoeBoxWidth = shoeBoxWidth;
		this.shoeBoxLength = shoeBoxLength;
		this.shoeBoxHeight = shoeBoxHeight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShoeBoxNumber() {
		return shoeBoxNumber;
	}

	public void setShoeBoxNumber(String shoeBoxNumber) {
		this.shoeBoxNumber = shoeBoxNumber;
	}

	public String getShoeBoxName() {
		return shoeBoxName;
	}

	public void setShoeBoxName(String shoeBoxName) {
		this.shoeBoxName = shoeBoxName;
	}

	public Integer getShoeBoxWidth() {
		return shoeBoxWidth;
	}

	public void setShoeBoxWidth(Integer shoeBoxWidth) {
		this.shoeBoxWidth = shoeBoxWidth;
	}

	public Integer getShoeBoxLength() {
		return shoeBoxLength;
	}

	public void setShoeBoxLength(Integer shoeBoxLength) {
		this.shoeBoxLength = shoeBoxLength;
	}

	public ShoeFP getShoeFP() {
		return shoeFP;
	}

	public void setShoeFP(ShoeFP shoeFP) {
		this.shoeFP = shoeFP;
	}

	public Integer getShoeBoxHeight() {
		return shoeBoxHeight;
	}

	public void setShoeBoxHeight(Integer shoeBoxHeight) {
		this.shoeBoxHeight = shoeBoxHeight;
	}

	@Override
	public String toString() {
		return "ShoeBox [id=" + id + ", shoeBoxNumber=" + shoeBoxNumber + ", shoeBoxName=" + shoeBoxName
				+ ", shoeBoxWidth=" + shoeBoxWidth + ", shoeBoxLength=" + shoeBoxLength + ", shoeBoxHeight="
				+ shoeBoxHeight + "]";
	}	
}
