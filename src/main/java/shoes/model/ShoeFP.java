package shoes.model;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import shoes.common.constants.ShoeBoxConstrant;

@Entity
@Table(name = "PR_SHOEFP")
public class ShoeFP extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private static ShoeFP shoeFP = new ShoeFP();
	
	public static ShoeFP returnShoeFP() {
		UUID uuid = UUID.randomUUID();
		shoeFP.setId(uuid.toString());		
        return shoeFP;
    }
	
	@Id
	@Column(name = "SHOEFP_ID", length = 36)
	protected String id;
	
	@Column(name = "SHOEFP_NUMBER")
	private String shoeFpNumber;
	
	@Column(name = "SHOEFP_NAME")
	private String shoeFpName;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOE_ID")
	private Shoe shoe;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOEBOX_ID")
	private ShoeBox shoeBox;
	
	public ShoeFP() {
	}

	public ShoeFP(String id, String shoeFpNumber, String shoeFpName, Shoe shoe, ShoeBox shoeBox) {
		super();
		this.id = id;
		this.shoeFpNumber = shoeFpNumber;
		this.shoeFpName = shoeFpName;
		this.shoe = shoe;
		this.shoeBox = shoeBox;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShoeFpNumber() {
		return shoeFpNumber;
	}

	public void setShoeFpNumber(String shoeFpNumber) {
		this.shoeFpNumber = shoeFpNumber;
	}

	public String getShoeFpName() {
		return shoeFpName;
	}

	public void setShoeFpName(String shoeFpName) {
		this.shoeFpName = shoeFpName;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public ShoeBox getShoeBox() {
		return shoeBox;
	}

	public void setShoeBox(ShoeBox shoeBox) {
		this.shoeBox = shoeBox;
	}

	@Override
	public String toString() {
		return "ShoeFP [id=" + id + ", shoeFpNumber=" + shoeFpNumber + ", shoeFpName=" + shoeFpName + "]";
	}
	
}
