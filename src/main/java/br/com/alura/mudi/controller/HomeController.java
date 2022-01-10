package br.com.alura.mudi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.repository.PedidoRepository;

@Controller //anotation para ser gerenciada pelo próprio spring
@RequestMapping("/home") //Agrupando a rota home para bater nesse controller através dessa anotação.
public class HomeController {
	
	@Autowired //Anotation indica ao Spring que o objeto é um componente que retorna uma instância por meio de Injeção dependencia.
	private PedidoRepository pedidoRepository; //Injetando a classe de repositórios criada manualmente e definindo como atributo
	
	@GetMapping
	public String home(Model model) { //Utilizando o serviço de request, response da classe Model (nativa do spring)
		List<Pedido> pedidos = pedidoRepository.findAll(); //método herdado de JpaRepository realiza a busca de todos os dados da tabela no banco de dados e retorna uma lista
		model.addAttribute("pedidos", pedidos);	 //envia pra view uma variável contendo a lista de pedidos com o nome de pedidos
		return "home";
	}
	
	@GetMapping("/{status}") //Recebendo o nome da rota como uma variável
	public String porStatus(@PathVariable("status") String status, Model model) { //Anotation @PathVariable diz ao Spring para pegar uma variável que foi definida no link (dentro da anotation) e retorne em forma de uma string
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase())); //StatusPedido.valueOf(status.toUpperCase()) está solicitando pra buscar um Status de Pedido que tenha valor igual ao Status passado (convertido em maiusculo)
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class) //Indicando o erro mapeando a excessão.
	private String onError() { //Método criado para garantir que qualquer url depois de /home que não estiver mapeado vai retornar a página para /home
		return "redirect:/home";
	}
}
