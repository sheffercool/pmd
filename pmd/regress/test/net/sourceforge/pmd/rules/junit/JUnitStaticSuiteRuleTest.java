package test.net.sourceforge.pmd.rules.junit;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.cpd.CPD;
import net.sourceforge.pmd.rules.XPathRule;
import test.net.sourceforge.pmd.rules.RuleTst;

public class JUnitStaticSuiteRuleTest extends RuleTst {

    private static final String TEST1 =
    "public class JUnitStaticSuite1 {" + CPD.EOL +
    " public TestSuite suite() {}" + CPD.EOL +
    "}";

    private static final String TEST2 =
    "public class JUnitStaticSuite2 {" + CPD.EOL +
    " public static TestSuite suite() {}" + CPD.EOL +
    "}";

    private static final String TEST3 =
    "public class JUnitStaticSuite3 {" + CPD.EOL +
    " private static TestSuite suite() {}" + CPD.EOL +
    "}";

    private Rule rule;

    public void setUp() {
        rule = new XPathRule();
        rule.addProperty("xpath", "//MethodDeclaration[not(@Static='true') or not(@Public='true')][MethodDeclarator/@Image='suite']");
    }
    public void testNonstatic() throws Throwable {
        runTestFromString(TEST1, 1, rule);
    }
    public void testGoodOK() throws Throwable {
        runTestFromString(TEST2, 0, rule);
    }
    public void testPrivateSuite() throws Throwable {
        runTestFromString(TEST3, 1, rule);
    }
}
