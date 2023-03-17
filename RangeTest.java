package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range; import org.junit.*;
import org.junit.rules.ExpectedException;

public class RangeTest {

	private Range exampleRange;
    private Range exampleRange2;
    private Range exampleRange3;
    private Range exampleRange4;
	private Range exampleRange44;
	private Range exampleRange45;
	private Range exampleRange46;
	private Range exampleRange47;
	private Range exampleRange48;
	private Range exampleRange49;
	@BeforeClass public static void setUpBeforeCLass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception { 
		exampleRange = new Range(0, 10); 
        exampleRange2 = new Range(-1, -1);
        exampleRange3 = new Range(1, 1);
        exampleRange4 = new Range(-1, 5);
        exampleRange44 = new Range(1, 10); 
        exampleRange45 = new Range(1, 1);
        exampleRange46 = new Range(0, 0);
        exampleRange47 = new Range(-1, -1);
        exampleRange48 = new Range(-10, 10);
        exampleRange49 = new Range(-10, -1);
	}
	
	// Tests constrain() for value within range
	@Test
	public void validConstrainTest() {
		assertEquals("Value being tested is within the range and should return 5.0", 5, exampleRange.constrain(5), .000000001d);
	}
	
	// Tests constrain() for value less than lower bounds of the range
	@Test	// Bug found since -1 is closest to 0.0 but test returns 5.0
	public void lowerOutOfBoundsConstrainTest() {
		assertEquals("Value being tested is out of the lower bounds of the range and should return 0.0", 0, exampleRange.constrain(-1), .000000001d);
	}
	
	// Tests constrain() for value greater than upper bounds of the range
	@Test
	public void upperOutOfBoundsConstrainTest() {
		assertEquals("Value being tested is out of the upper bounds of the range and should return 10.0", 10, exampleRange.constrain(11), .000000001d);
	}
	
	// Tests constrain() for double value within range
	@Test
	public void DoubleConstrainTest() {
		assertEquals("Double value being tested is within the range and should return 5.99", 5.99, exampleRange.constrain(5.99), .000000001d);
	}	
	
	
	// Tests contains() for value less than lower bounds of range
    @Test
    public void lowerOutOfBoundsValueContains() {
        assertEquals("Value being tested is less than range and should return false", false, exampleRange.contains(-100));
    }

	// Tests contains() for value greater than upper bounds of range
    @Test
    public void upperOutOfBoundsValueContains() {
        assertEquals("Value being tested is greater than range and should return false", false, exampleRange.contains(100));
    }

    // Tests contains() for value within range
    @Test
    public void valueInRangeContains() {
        assertEquals("Value being tested lies within the range and should return true", true, exampleRange.contains(5));
    }

    // Tests contains() for value on the lower bounds
    @Test
    public void valueIsOnLowerBoundsContains() {
        assertEquals("Value being tested is on the lower bounds and should return true", true, exampleRange.contains(0));
    }

    // Tests contains() for value on the upper bounds
    @Test
    public void valueIsOnUpperBoundsContains() {
        assertEquals("Value being tested is on the upper bounds and should return true", true, exampleRange.contains(10));
    }
	
    // Tests getUpperBound() for same range values
	@Test
    public void getUpperBoundSameRangeValuesTest() {  	
    	assertEquals("Upper bound is the value of -1 (-1,-1)", -1, exampleRange2.getUpperBound(), .000000001d);
    }
    
    // Tests getUpperBound() for different range values with zero
    @Test
    public void getUpperBoundDifferentRangeValuesTestWithZero() {  	
    	assertEquals("Upper bound value is the value of 10 (0,10)", 10, exampleRange.getUpperBound(), .000000001d);
    }
    
    // Tests getUpperBound() for different range values
    @Test
    public void getUpperBoundDifferentRangeValuesTest() {  	
    	assertEquals("Upper bound value is the value of 5 (-1,5)", 5, exampleRange4.getUpperBound(), .000000001d);
    }
    
    // Tests getLowerBound() for same range values
	@Test
    public void getLowerBoundSameRangeValuesTest() {  	
    	assertEquals("Lower bound is the value of -1 (-1,-1)", -1, exampleRange2.getLowerBound(), .000000001d);
    }
    
    // Tests getLowerBound() for different range values with zero
    @Test
    public void getLowerBoundDifferentRangeValuesTestWithZero() {  	
    	assertEquals("Lower bound value is the value of 0 (0,10)", 0, exampleRange.getLowerBound(), .000000001d);
    }
    
    
    // Tests getLowerBound() for different range values
    @Test
    public void getLowerBoundDifferentRangeValuesTest() {  	
    	assertEquals("Lower bound value is the value of -1 (-1,5)", -1, exampleRange4.getLowerBound(), .000000001d);
    }
    
    //Tests getLength() with two different positive numbers in the range.
    @Test
    public void lengthTestWithDifferentRangesPositive() {
        assertEquals("The length of the range should be the total distance of the range",
        9, exampleRange44.getLength(), .000000001d);
    }
    
    //Tests getLength() with two of the same numbers in the range positive.
    @Test
    public void lengthTestWithTheSameRangePositive() {
        assertEquals("The length of the range should be Zero",
        0, exampleRange45.getLength(), .000000001d);
    }
    
    //Tests getLength() with two of the same numbers in the range this time 0.
    @Test
    public void lengthTestWithTheSameRangeZero() {
        assertEquals("The length of the range should be Zero",
        0, exampleRange46.getLength(), .000000001d);
    }
    
    //Tests getLength() with two of the same numbers in the range negative.
    @Test
    public void lengthTestWithTheSameRangeNegative() {
        assertEquals("The length of the range should be Zero",
        0, exampleRange47.getLength(), .000000001d);
    }
    
    //Tests getLength() with two different numbers in the range negative and positive.
    @Test
    public void lengthTestWithDifferentRangesNegativePositive() {
        assertEquals("The length of the range should be the total distance of the range",
        20, exampleRange48.getLength(), .000000001d);
    }
    
    //Tests getLength() with two different numbers in the range negative.
    @Test 
    public void lengthTestWithDifferentRangesNegative() {
        assertEquals("The length of the range should be the total distance of the range",
        9, exampleRange49.getLength(), .000000001d);
    }
    
    //NEW COVERAGE TESTS START HERE
    
	@Rule // Testing InvalidParameterException 
	public ExpectedException invalidArgException = ExpectedException.none();
	
//    @Test(expected = IllegalArgumentException.class)
//    public void getLengthWithNull() {
//    	invalidArgException.expect(InvalidParameterException.class);
//    	invalidArgException.expectMessage("Range(double, double): require lower (\" + 2.0 + \") <= upper (\" + 5.0 + \").");
//    	//final double num1 = null;
//    	exampleRange = new Range(5.0, 2.0);
//    	exampleRange.getLength();
//        //assertNull("The length should not exist.", exampleRange.getCentralValue());
//    }
    
    //max test
    @Test
    public void maxTest() {
    	double d2 = Math.sqrt(-1);
    	Math.max(1.0, d2);
    }
        
    //hashCode
    @Test
    public void hashCodeCoverageTest() {
    	exampleRange = new Range(1, 10);
        assertEquals(2119434240, exampleRange.hashCode(), 0.000000001d);
    }
    
    @Test
    public void hashCodeEqualTest() {
    	assertEquals(exampleRange.hashCode(), exampleRange.hashCode());
    }
    
    @Test
    public void hashCodeCoverageTest2() {
    	assertFalse(exampleRange.hashCode() == exampleRange2.hashCode());
    }
    
    //scale
   
    @Test
    public void scaleCoverageTest1() {
    	exampleRange = new Range (0, 0);
    	Range correctRange = new Range (0, 0);
    	assertEquals(correctRange, Range.scale(exampleRange, 8));
    }
    
    @Test
    public void scaleCoverageTest2() {
    	Range correctRange = new Range (0, 0);
    	assertEquals(correctRange, Range.scale(exampleRange, 0));
    }
    
    @Test
    public void scaleCoverageTest3() {
    	exampleRange = new Range(-1, 1);
    	Range correctRange = new Range(-10, 10);
    	assertEquals(correctRange, Range.scale(exampleRange, 10));
    }
    
    //combine
    @Test
    public void combineCoverageTest() {
    	exampleRange = new Range(6, 8);
    	Range.combine(null, exampleRange);
    }
    
    @Test
    public void combineCoverageTest2() {
    	exampleRange = new Range(6, 8);
    	Range.combine(exampleRange, null);
    }
    
    @Test
    public void combineCoverageTest3() {
    	exampleRange = new Range(6, 8);
    	Range testRange = new Range (4, 7);
    	Range.combine(exampleRange, testRange);
    }
        
    //combineIgnoringNaN
    @Test
    public void combineIgnoringNaNCoverageTest() {
    	exampleRange = new Range(2, 6);
    	Range.combineIgnoringNaN(null, exampleRange);
    }
    
    @Test
    public void combineIgnoringNaNCoverageTest2() {
    	exampleRange = new Range(2, 6);
    	Range.combineIgnoringNaN(exampleRange, null);
    }
    
    @Test
    public void combineIgnoringNaNCoverageTest3() {
    	exampleRange = new Range(2, 6);
    	Range.combineIgnoringNaN(exampleRange, null);
    }
    
    @Test
    public void combineIgnoringNaNCoverageTest4() {
    	exampleRange = new Range (2, 4);
    	Range exampleRange2 = new Range(1, 8);
    	Range.combineIgnoringNaN(exampleRange2, exampleRange);
    }
    
    
	@Rule // Testing InvalidParameterException
	public ExpectedException invalidArithmeticException = ExpectedException.none();
	
//    @Test(expected = IllegalArgumentException.class)
//    public void combineIgnoringNanCoverageTest5() {
//    	exampleRange = new Range (1,1);
//     	invalidArithmeticException.expect(InvalidParameterException.class);
//     	invalidArithmeticException.expectMessage("Expected invalidArithmeticException");
//    	exampleRange2 = new Range (Math.sqrt(-1), 0);
//    	Range.combineIgnoringNaN(exampleRange, exampleRange2);
//    }
    
	
//    @Test(expected = IllegalArgumentException.class)
//    public void combineIgnoringNanCoverageTest6() {
//    	exampleRange = new Range (1,1);
//     	invalidArithmeticException.expect(InvalidParameterException.class);
//     	invalidArithmeticException.expectMessage("Expected invalidArithmeticException");
//    	exampleRange2 = new Range (Math.sqrt(-1), Math.sqrt(-1));
//    	Range.combineIgnoringNaN(null, exampleRange2);
//    	Range.combineIgnoringNaN(exampleRange2, exampleRange2);
//    	Range.combineIgnoringNaN(exampleRange2, null);
//    }
    
    
    //equals
    @Test
    public void equalsCoverageTest1() {
    	exampleRange = new Range (4, 6);
    	Range testRange = new Range (4, 6);
    	assertTrue(exampleRange.equals(testRange));
    }
    
    
    @Test
    public void equalsCoverageTest2() {
    	exampleRange = new Range (4, 6);
    	Range testRange = new Range (4, 8);
    	assertFalse(exampleRange.equals(testRange));
    }
    
    @Test
    public void equalsCoverageTest3() {
        assertFalse(exampleRange.equals(null));
    }
    
    @Test
    public void equalsCoverageTest4() {
    	exampleRange = new Range (4, 5);
    	Range testRange = new Range (4, 6);
    	assertFalse(exampleRange.equals(testRange));
    }
    
    
    
    //expandToInclude
    @Test
    public void expandToIncludeCoverageTest1() {
    	exampleRange = new Range (4, 5);
    	Range.expandToInclude(exampleRange, 6);
    	
    }
    
    @Test
    public void expandToIncludeCoverageTest2() {
    	exampleRange = new Range (4, 6);
    	Range.expandToInclude(exampleRange, 5);
    }
    
    @Test
    public void expandToIncludeCoverageTest3() {
    	exampleRange = new Range (3, 5);
    	Range.expandToInclude(exampleRange, 2);
    }
    
    @Test
    public void expandToIncludeCoverageTest4() {
    	exampleRange = null;
    	Range.expandToInclude(exampleRange, 1);
    }
        
    //expand
    @Test
    public void expandCoverageTest1() {
    	exampleRange = new Range (4, 6);
    	Range.expand(exampleRange, 2, 7);
    }
    
    @Test
    public void expandCoverageTest2() {
    	exampleRange = new Range (4, 6);
    	Range.expand(exampleRange, 2, -4);
    }
    
    //intersects
    @Test
    public void intersectsCoverageTest1() {
    	assertTrue(exampleRange.intersects(-2.0, 1.0));
   }
    @Test
    public void intersectsCoverageTest2() {
    	assertTrue(exampleRange.intersects(9.0, 20.0));
    }
    
    @Test
    public void intersectsCoverageTest3() {
    	assertFalse(exampleRange.intersects(-20.0, -10.0));
    }
  
    
    @Test
    public void intersectsCoverageTest4() {
    	assertTrue(exampleRange.intersects(5, 15));
    }
    
    
    //toString
   @Test
   public void toStringCoverageTest() {
       exampleRange = new Range (4, 6);
       assertEquals(exampleRange.toString(), "Range[4.0,6.0]");
   }
    

   //getCentralValue
//    @Test
//    public void centralValueCoverageTest() {
//        assertEquals("The central value of -1 and 1 should be 0", 0, exampleRange.getCentralValue(), .000000001d);
//    }
// 
    //shift
    @Test
    public void shiftCoverageTest1() {
        exampleRange = new Range(-1.0, 1.0);
        Range shiftSmall = Range.shift(exampleRange, 0.5);
        assertEquals(-0.5, shiftSmall.getLowerBound(), .000000001d);
        assertEquals(1.5, shiftSmall.getUpperBound(), .000000001d);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void shiftWithNull() {
    	//Range exampleRange2 = new Range(-.5, .5);
    	Range.shift(null, 20);
    }
    
    @Test
    public void shiftCoverageTest3() {
        exampleRange = new Range(-1.0, 1.0);
        Range shiftNegative = Range.shift(exampleRange, -10.0);
        assertEquals(-11.0, shiftNegative.getLowerBound(), .000000001d);
        assertEquals(0, shiftNegative.getUpperBound(), .000000001d);
    }
    
    @Test
    public void shiftCoverageTest4() {
        exampleRange = new Range(-1.0, 1.0);
        Range shifted = Range.shift(exampleRange, 0, true);
        assertEquals(-1.0, shifted.getLowerBound(), .000000001d);
        assertEquals(1.0, shifted.getUpperBound(), .000000001d);
    }
    
    //isNaNRange
    
    @Test
    public void isNaNRangeTest1() {
    	assertFalse("Range of 0 and 10 should not be NaN", exampleRange.isNaNRange());
    }
    
    
//    @Test
//    public void shiftWithNoZeroCrossingCoverageTest() {
//    	exampleRange = new Range (1, 4);
//    	Range.shiftWithNoZeroCrossing(1.0,5.0);
//    }
    
    
    //ENDS HERE
    
    @After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
