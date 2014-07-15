package ru.todo100.cube3d.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;



import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="cube3d_model3d_mark")
@SuppressWarnings(value="all")
public class MarkItem extends Item {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="model3d_id",referencedColumnName="id")
	private Model3dItem model3d;

	@Column(name = "mark_name")
	private String name;



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public Model3dItem getModel3d() {
		return model3d;
	}

	public void setModel3d(Model3dItem model3d) {
		this.model3d = model3d;
	}


}
