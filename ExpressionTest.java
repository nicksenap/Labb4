import junit.framework.TestCase;

public class ExpressionTest extends TestCase {

    public void testMain() throws Exception {
        assertEquals("1+2+3+4 = 10.0",new Expression("1+2+3+4").getResult());
        assertEquals("1+2*(3+4) = 15.0", new Expression("1+2*(3+4)").getResult());
        assertEquals("3+2*4 = 11.0", new Expression("3+2*4").getResult());
        }
}