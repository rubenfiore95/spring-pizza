package jana60.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "pizza")
public class Pizza {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		
        private String nome;
		
		
		private String tipo;
		
		
		private String prezzo;
		
		@ManyToMany
		private List<Ingredienti> ListaIngredienti;

		public List<Ingredienti> getListaIngredienti() {
			return ListaIngredienti;
		}

		public void setListaIngredienti(List<Ingredienti> listaIngredienti) {
			ListaIngredienti = listaIngredienti;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getPrezzo() {
			if (prezzo == null) {
				return "";
			}
			return prezzo + " €";
		}

		public void setPrezzo(String prezzo) {
			this.prezzo = prezzo;
		}

		
}
