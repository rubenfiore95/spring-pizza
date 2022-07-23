package jana60.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.Pizza;
import jana60.repository.PizzaRepository;


@Controller
@RequestMapping("/")
public class PizzaController {
    
	@Autowired 
	private PizzaRepository pizza;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Pizza> ListaAggiunta =(List<Pizza>)pizza.findAll();
		model.addAttribute("pizza" , ListaAggiunta);
		return "index";
	}
	
	@GetMapping("/add")
	  public String pizzaForm(Model model) {
	    model.addAttribute("pizza", new Pizza());
	    return "add";
	  }
	
	
	  @GetMapping("/add/{id}")
	  public String edit(@PathVariable("id") Integer PizzaId, Model model) {
	    Optional <Pizza> result = pizza.findById(PizzaId);
	    // controllo se il Book con quell'id è presente
	    if (result.isPresent()) {
	      // preparo il template con al form passandogli il book trovato su db
	      model.addAttribute("pizza", result.get());
	      return "/add";
	    } else {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Questa pizza non e' presente nel mio database");
	    }
	  }



	  
	  @PostMapping("/add")
	    public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult br, Model model) {
	    // testo se ci sono errori di validazione
	    boolean hasErrors = br.hasErrors();
	    boolean validateNome = true;
	    if (formPizza.getId() != null) { // sono in edit non in create
	      Pizza pizzaBeforeUpdate = pizza.findById(formPizza.getId()).get();
	      if (pizzaBeforeUpdate.getTipo().equals(formPizza.getTipo())) {
	        validateNome = false;
	      }
	     }
	     // Controlliamo che il nome sia univoco
	     if (validateNome && pizza.countByNome(formPizza.getTipo()) > 0) {
	      br.addError(new FieldError("pizza", "nome", "Il nome e' gia presente"));
	      hasErrors = true;
	     }

	      if (hasErrors) {
	      // se ci sono errori non salvo il book su database ma ritorno alla form precaricata
	      return "/add";
	    } else {
	      // se non ci sono errori salvo il book che arriva dalla form
	      try {
	        pizza.save(formPizza);
	      } catch (Exception e) { // gestisco eventuali eccezioni sql
	        model.addAttribute("errorMessage", "Impossibile salvare la Pizza!");
	        return "/add";
	      }
	        return "redirect:/"; // non cercare un template, ma fai la HTTP redirect a quel path
	    }
	  }
	  
	  

	
	  @GetMapping("/delete/{id}")
	  public String delete(@PathVariable("id") Integer PizzaId, RedirectAttributes ra) {
	    Optional <Pizza> result = pizza.findById(PizzaId);
	    if (result.isPresent()) {
	      pizza.delete(result.get());
	      ra.addFlashAttribute("successMessage", "Pizza" + result.get().getTipo() + " Cancellata!");
	      return "redirect:/";
	    } else {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non presente nel database");
	    }
	  }
}
