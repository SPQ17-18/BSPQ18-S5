package SPQ;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.internal.stubbing.answers.ThrowsException;

import SPQ.data.User;
import SPQ.gui.VentanaPrincipal;

//import junit.framework.TestCase;

public class CoberturaTest {

	User u = new User ("alvaro", "alvaro@gmail.com", "alv12", "Facebook");
	
	@Test
    public void testApp() {
        final VentanaPrincipal vp = new VentanaPrincipal(null);
    }
}
