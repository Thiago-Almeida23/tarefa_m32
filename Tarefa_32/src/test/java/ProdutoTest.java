import br.com.thiago.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProdutoTest {

    @Test
    public void testPersistirProduto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtoPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Produto produto = new Produto();
            produto.setNome("Caneta Azul");
            produto.setPreco(2.50);

            em.persist(produto);

            em.getTransaction().commit();

            assertNotNull(produto.getId(), "O ID do produto não deve ser nulo após persistência.");

            System.out.println("Produto salvo com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}