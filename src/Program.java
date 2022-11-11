import Testers.AlgebraTester;
import Testers.GeometryTester;
import Testers.PolymorphismTester;
import Testers.StandardTester;
import Interfaces.ITestable;
public class Program
{
    public static void main( String[] args )
    {
        var testers = new ITestable[]
        {
            new AlgebraTester(),
            new GeometryTester(),
            new StandardTester(),
            new PolymorphismTester()
        };

        for ( var tester : testers )
        {
            tester.evaluate();
        }
    }
}
