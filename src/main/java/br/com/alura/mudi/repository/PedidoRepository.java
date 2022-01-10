package br.com.alura.mudi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;

@Repository //Com essa anotação o Spring identifica que a classe ou interface é um repositório e gerencia esse repositório pra você.
public interface PedidoRepository extends JpaRepository<Pedido, Long>{ //Ao extender JpaRepository o sobrescrito os métodos convencionais que realiza a manipulação no banco de dados (Recebe a classe e o tipo da PrimaryKey como argumento)

	List<Pedido> findByStatus(StatusPedido status);  //Método não herdado que faz a consulta dos pedidos conforme status e retorna uma lista
	/* simplificando o código acima: Basicamente o Spring DATA JPA está identificando automaticamente como um SQL
	 * Então está fazendo a seguinte query abaixo
	 * SELECT * FROM PEDIDO WHERE STATUS = "STATUSDOPARAMETRO" */
	
//	public List<Pedido> findAll(); //não precisa do método, pois JpaRepository já possui o método herdado
	
//	/* Pra se comunicar com o banco de dados utilizando JPA */
//	@PersistenceContext //Define uma anotação de persistência de conteúdo que será manipulada pelo hibernate
//	private EntityManager entityManager; //EntityManager é o serviço central para todas as ações de persistência no banco de dados.
//
//	public List<Pedido> recuperaTodosOsPedidos(){
//		
//		Query query = entityManager.createQuery("select p from Pedido p", Pedido.class); //Fazendo uma consulta no banco utilizando o método createQuery com o sql definido e na classe definida (retorna um Objeto Query)
//		return query.getResultList(); //Busca os dados retornados da query através do getResultList (retorna uma List)
//	}
}
