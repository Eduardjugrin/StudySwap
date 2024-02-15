import com.example.studyswap.DAO.LoginDAO;
import com.example.studyswap.engineering.factory.LoginDAOFactory;
import com.example.studyswap.exception.UserNotFoundException;
import com.example.studyswap.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestLoginDAO {

    /* Verifichiamo che venga ritornato il tipo di ruolo corretto quando
       viene effettuato il login con il profilo di un seller */
    @Test
    void testCheckUser() throws UserNotFoundException {
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();
        User user = loginDAO.checkUser("eduard@sample.com", "test456");

        // Il test ha successo in quanto restituisce il ruolo 2, corrispondente al passeggero
        assertEquals(1, user.getRole());
    }
}
