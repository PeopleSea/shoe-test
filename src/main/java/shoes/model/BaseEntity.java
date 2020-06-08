package shoes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "VERSION", columnDefinition = "Number(12,0)")
	protected Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", updatable = false)
	protected Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	protected Date updateTime;

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@PrePersist
	public void onCreate() {
		Date now = new Date();
		this.createTime = (this.createTime == null) ? now : this.createTime;
		this.updateTime = (this.updateTime == null) ? now : this.updateTime;
		this.version = Long.valueOf("0");
	}

	@PreUpdate
	public void onUpdate() {
		this.updateTime = new Date();
		this.version = Long.valueOf(this.version.longValue() + Long.valueOf("1").longValue());
	}
}