package jana60.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pizza")
public class Pizza {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		
        private String nome;
		
		
		
		private String tipo;
		
		
		
		private String prezzo;
		
		private String ingredienti;
		
				
		
		public String getIngredienti() {
			return ingredienti;
		}

		public void setIngredienti(String ingredienti) {
			this.ingredienti = ingredienti;
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
			return prezzo + " €";
		}

		public void setPrezzo(String prezzo) {
			this.prezzo = prezzo;
		}

		
}
