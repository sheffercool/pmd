package test.net.sourceforge.pmd.rules.junit;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.cpd.CPD;
import net.sourceforge.pmd.rules.XPathRule;
import test.net.sourceforge.pmd.rules.RuleTst;

public class JUnitSpellingRuleTest extends RuleTst {

    private static final String TEST1 =
    "public class JUnitSpelling1 {" + CPD.EOL +
    " // these should be 'setUp'" + CPD.EOL +
    " public void setup() {}" + CPD.EOL +
    " public void SetUp() {}" + CPD.EOL +
    "}";

    private static final String TEST2 =
    "public class JUnitSpelling2 {" + CPD.EOL +
    " // these should be 'tearDown'" + CPD.EOL +
    " public void TearDown() {}" + CPD.EOL +
    " public void teardown() {}" + CPD.EOL +
    "}";

    private static final String TEST3 =
    "public class JUnitSpelling3 {" + CPD.EOL +
    " // these are OK" + CPD.EOL +
    " public void setUp() {}" + CPD.EOL +
    " public void tearDown() {}" + CPD.EOL +
    "}";

    private static final String TEST4 =
    "public class JUnitSpelling4 {" + CPD.EOL +
    " // these are OK" + CPD.EOL +
    " public void utility() {}" + CPD.EOL +
    " public void foobr() {}" + CPD.EOL +
    "}";

    private static final String TEST5 =
    "public class JUnitSpelling5 {" + CPD.EOL +
    " public void setup(String x) {}" + CPD.EOL +
    "}";

    private Rule rule;

    public void setUp() {
        rule = new XPathRule();
        rule.addProperty("xpath", "//MethodDeclarator[(not(@Image = 'setUp') and translate(@Image, 'SETuP', 'setUp') = 'setUp') or (not(@Image = 'tearDown') and translate(@Image, 'TEARdOWN', 'tearDown') = 'tearDown')][FormalParameters[count(*) = 0]]");
    }

    public void testSetupMisspellings1() throws Throwable {
        runTestFromString(TEST1, 2, rule);
    }
    public void testTeardownMisspellings() throws Throwable {
        runTestFromString(TEST2, 2, rule);
    }
    public void testMethodsSpelledOK() throws Throwable {
        runTestFromString(TEST3, 0, rule);
    }
    public void testUnrelatedMethods() throws Throwable {
        runTestFromString(TEST4, 0, rule);
    }
    public void testMethodWithParams() throws Throwable {
        runTestFromString(TEST5, 0, rule);
    }
}
