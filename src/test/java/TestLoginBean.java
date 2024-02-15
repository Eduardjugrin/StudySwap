import com.example.studyswap.bean.LoginBean;
import com.example.studyswap.exception.EmailFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestLoginBean {
    /* Verifichiamo se viene lanciata l'eccezione EmailFormatException quando
       viene creata un'istanza di LoginBean passando una mail con un formato sintatticamente errato */
    @Test
    void testValidEmail() {

        int validFormat;

        try {
            new LoginBean("seller1@.com", "seller1");
            validFormat = 1;
        } catch (EmailFormatException e) {
            validFormat = 0;
        }

        // Il test ha successo perché l'eccezione viene sollevata assegnando il valore 0 a validFormat
        assertEquals(0, validFormat);
    }

}
