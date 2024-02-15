import com.example.studyswap.DAO.NoteDAOJDBC;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRebuyNotes {

    @Test
    void testBuyNotes() throws SQLException {
        // Riprova ad acquistare lo stesso appunto
        boolean secondPurchaseResult = NoteDAOJDBC.isNotePurchased("eduard@sample.com", 30);

        //file di appunti già acquistato
        assertTrue(secondPurchaseResult); // Verifica che il secondo acquisto non sia andato a buon fine
    }
}
