package br.com.alura.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.alura.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido") //Mapeando todas as requisições que forem pro diretório pedido
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository; //Instanciando (através de injeção de dependência) a conexão com o banco de dados
	
	@GetMapping("formulario") //Definindo um mapeamento com método GET exclusivo para formulário
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	
	/**
	 * O método novo é utilizado para receber dados da requisição e atribuir a um objeto pedido (Preenchendo previamente apenas os dados que foram especificados no FormData)
	 * @Valid é uma anotation que força o Spring a realizar as validações do Bean Validation no parametro do método do objeto RequisicaoNovoPedido (pois além da anotation que existe na classe, precisamos indicar a execução do Bean ao Spring)
	 * Objeto BindingResult trás o resultado da validação da anotação acima.
	 */
	@PostMapping("novo") //Definindo um mapeamento com método POST exclusivo para novo
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if(result.hasErrors()) { //Compara se a validação apresentou erro.
			return "pedido/formulario"; //Retorna o formulário novamente para preenchimento
		}
		
		Pedido pedido = requisicao.toPedido(); //Comparando os atributos do FormData (que vieram da requisição) com os atributos da classe pedido.
		pedidoRepository.save(pedido); //Salvando o pedido no Banco de Dados
		return "redirect:/home"; //Redirecionando para a página home
	}
}
