package jana60.model;


	import java.util.List;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

	@Entity
	@Table(name = "ingredienti")
	public class Ingredienti {

	 
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;

	  @NotEmpty(message = "L' ingrediente non può essere vuoto.")
	  @Column(nullable = false)
	  private String name;

	  @ManyToMany(mappedBy = "ListaIngredienti")
	  private List<Pizza> pizza;

	  public List<Pizza> getPizza() {
		return pizza;
	}

	  public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}

	   public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	

	  
}
