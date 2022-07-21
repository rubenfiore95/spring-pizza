package jana60.controller;

import java.awt.print.Book;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	 @PostMapping("/add")
	  public String save(@Valid @ModelAttribute("book") Pizza formBook, BindingResult br) {
	    // testo se ci sono errori di validazione
	    if (br.hasErrors()) {
	      // se ci sono errori non salvo il book su database ma ritorno alla form precaricata
	      return "/book/edit";
	    } else {
	      // se non ci sono errori salvo il book che arriva dalla form
	      pizza.save(formBook);
	      return "redirect:/"; // non cercare un template, ma fai la HTTP redirect a quel path
	    }
	  }
	
}
