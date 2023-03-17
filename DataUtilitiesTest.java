package org.jfree.data;

import static org.junit.Assert.*; import org.junit.*;
import org.junit.rules.ExpectedException;
import org.jmock.*; import org.jfree.data.*;
import java.security.InvalidParameterException;

public class DataUtilitiesTest {
    // setup of keyed value mocking (put in setup)
	// Making a KeyedValue mockery
    Mockery KeyedValuesMock = new Mockery();
    // Creating a mock object of KeyedValues class
    final KeyedValues KeyedValuesMockTable = KeyedValuesMock.mock(KeyedValues.class); 
    
	@BeforeClass
	public static void setUpBeforeCLass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception { 
        
        //Creating expectations of what the values are supposed to be
        KeyedValuesMock.checking(new Expectations() {
            {
            	// Setting up arrays, and testing the methods in KeyedValues
            	double[] keyValues = {10,40,30,20};
            	String[] keys = {"0","1","2","3"};
            	
            	//Loop in order to setup mock values
            	for(int i = 0; i < keyValues.length; i++) {
            		atMost(keys.length).of(KeyedValuesMockTable).getKey(i); 
            		will(returnValue(keys[i].toString()));
            		
            		atMost(keyValues.length).of(KeyedValuesMockTable).getValue(i);
            		will(returnValue(keyValues[i]));
            		
            		atMost(keys.length).of(KeyedValuesMockTable).getIndex(i);
            		will(returnValue(i));
            		
            	}
            	
            	allowing(KeyedValuesMockTable).getKeys();
            	will(returnValue(keys));
            	
            	allowing(KeyedValuesMockTable).getItemCount();
            	will(returnValue(keys.length));
          		
            }
        });
        
	}
	
	// Tests calculateColumnTotal() for two values in a column
	 @Test
	 public void calculateColumnTotalForTwoValues() {
	     // setup
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });

	     assertEquals("Should return column total of 10.0", 10.0, DataUtilities.calculateColumnTotal(values, 0), .000000001d);

	 }
	
	// Tests for InvalidParameterException when an invalid data object is passed in
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	 
//	@Test(expected = InvalidParameterException.class)
//	public void calculateColunmTotalForNull() {
//		thrown.expect(InvalidParameterException.class);
//		thrown.expectMessage("Expected InvalidParameterException");
//		final Values2D nullValue = null;
//		DataUtilities.calculateColumnTotal(nullValue, 0);
//		
//	}
	
	// Tests calculateColumnTotal() for a column index that does not exist
	@Test
	public void calculateColumnTotalForInvalidColumn() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(3));
	             one(values).getValue(0, -1);
	             will(returnValue(null));
	             one(values).getValue(1, -1);
	             will(returnValue(null));
	             one(values).getValue(2, -1);
	             will(returnValue(null));
	         }
	     });
	     
	     assertEquals("Should return column total of 0.0", 0.0, DataUtilities.calculateColumnTotal(values, -1), .000000001d);
	}
	
	// Tests calculateColumnTotal() for table with multiple columns
	@Test
	public void calculateColumnTotalForTable() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	             one(values).getValue(0, 1);
	             will(returnValue(1.0));
	             one(values).getValue(1, 1);
	             will(returnValue(2.0));
	         }
	     });
	     
	     assertEquals("Should return column total of 3.0", 3.0, DataUtilities.calculateColumnTotal(values, 1), .000000001d);
	}
	
	@Test
	 public void calcColumnsTotalWithRow() {
		 Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(3));
	             one(values).getValue(0, 0); //row column
	             will(returnValue(7.5));     //7.5 2.5
	             one(values).getValue(1, 0); //only 7.5
	             will(returnValue(2.5));
	             one(values).getValue(2, 0); 
	             will(returnValue(1.0));
	         }
	     });
	     int[] rows = {0,1,2};
	     //double outcome = DataUtilities.calculateColumnTotal(values, 0, rows);
	     //assertEquals(outcome, 10.0, .000000001d);
	     assertEquals("Should return column total of 11.0", 11.0, DataUtilities.calculateColumnTotal(values, 0, rows), .000000001d);
	 }
	
	
//	@Test
//	public void calculateColTotalWithNegRow() {
//	     // setup
//	     Mockery mockingContext = new Mockery();
//	     final Values2D values = mockingContext.mock(Values2D.class);
//	     mockingContext.checking(new Expectations() {
//	         {
//	             one(values).getRowCount();
//	             will(returnValue(-1));
//	             one(values).getValue(0, 0);
//	             will(returnValue(7.5));
//	             one(values).getValue(1, 0);
//	             will(returnValue(2.5));
//	         }
//	     });
//
//	     assertEquals("Should return column total of 10.0", 10.0, DataUtilities.calculateColumnTotal(values, 0), .000000001d);
//	}
	
	// Tests calculateRowTotal() for two values in a row
	 @Test
	 public void calculateRowTotalForTwoValues() {
	     // setup
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0); //row column
	             will(returnValue(7.5));     //7.5 2.5
	             one(values).getValue(0, 1); //only 7.5
	             will(returnValue(2.5));
	         }
	     });

	     assertEquals("Should return row total of 10.0", 10.0, DataUtilities.calculateRowTotal(values, 0), .000000001d);

	 }
	 
//	 @Test
//	 public void calcRowTotalWithColumns() {
//		 Mockery mockingContext = new Mockery();
//	     final Values2D values = mockingContext.mock(Values2D.class);
//	     mockingContext.checking(new Expectations() {
//	         {
//	             one(values).getColumnCount();
//	             will(returnValue(2));
//	             one(values).getValue(0, 0); //row column
//	             will(returnValue(7.5));     //7.5 2.5
//	             one(values).getValue(0, 1); //only 7.5
//	             will(returnValue(2.5));
//	         }
//	     });
//	     int[] columns = {1,2,3};
//	     double outcome = DataUtilities.calculateRowTotal(values, 0, columns);
//	     assertEquals(outcome, 10.0, .000000001d);
//	 }
	
	// Tests for InvalidParameterException when an invalid data object is passed in
	@Rule
	public ExpectedException thrown2 = ExpectedException.none();
//	 
//	@Test(expected = InvalidParameterException.class)
//	public void calculateRowTotalForNull() {
//		thrown.expect(InvalidParameterException.class);
//		thrown.expectMessage("Expected InvalidParameterException");
//		final Values2D nullValue = null;
//		DataUtilities.calculateRowTotal(nullValue, 0);
//		
//	}
	
	
	
	// Tests calculateRowTotal() for a row index that does not exist
	@Test
	public void calculateRowTotalForInvalidRow() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(3));
	             one(values).getValue(-1, 0);
	             will(returnValue(null));
	             one(values).getValue(-1, 1);
	             will(returnValue(null));
	             one(values).getValue(-1, 2);
	             will(returnValue(null));
	         }
	     });
	     
	     assertEquals("Should return row total of 0.0", 0.0, DataUtilities.calculateRowTotal(values, -1), .000000001d);
	}
	
	// Tests calculateRowTotal() for table with multiple rows
	@Test
	public void calculateRowTotalForTable() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0); // 7.5 2.5
	             will(returnValue(7.5));     // 1.0 2.0
	             one(values).getValue(0, 1); //only 1.0
	             will(returnValue(2.5));
	             one(values).getValue(1, 0);
	             will(returnValue(1.0));
	             one(values).getValue(1, 1);
	             will(returnValue(2.0));
	         }
	     });
	     
	     assertEquals("Should return row total of 3.0", 3.0, DataUtilities.calculateRowTotal(values, 1), .000000001d);
	}
	
		@Test // Tests the first value for the getCumulativePercentages() method.
		public void getCumulativePercetagesTestValue1() {
		KeyedValues finalTest = DataUtilities.getCumulativePercentages(KeyedValuesMockTable);
		assertEquals("The first percentage is 10/100, it should be 0.10"
		, 0.1, (double) finalTest.getValue(0), .000000001d);
}
		
		@Test // Tests the second value for the getCumulativePercentages() method.
		public void getCumulativePercetagesTestValue2() {
			KeyedValues finalTest = DataUtilities.getCumulativePercentages(KeyedValuesMockTable);
			assertEquals("The second percentage is 50/100, it should be 0.50"
			, 0.5, (double) finalTest.getValue(1), .000000001d);
}
	
 		@Test // Tests the third value for the getCumulativePercentages() method.
 		public void getCumulativePercetagesTestValue3() {
 			KeyedValues finalTest = DataUtilities.getCumulativePercentages(KeyedValuesMockTable);
 			assertEquals("The third percentage is 80/100, it should be 0.80"
 			, 0.8, (double) finalTest.getValue(2), .000000001d);
    }
	
	 	@Test // Tests the fourth value for the getCumulativePercentages() method.
	    public void getCumulativePercetagesTestValue4() {
	    	KeyedValues finalTest = DataUtilities.getCumulativePercentages(KeyedValuesMockTable);
	        assertEquals("The fourth percentage is 100/100, it should be 1.0"
	        , 1.0, (double) finalTest.getValue(3), .000000001d);
	    }

	 	@Rule // Testing InvalidParameterException for getCumulativePercentages with an invalid input
		public ExpectedException invalidException = ExpectedException.none();
		
//		@Test (expected = InvalidParameterException.class)
//		public void getCumulativePercentagesInvalid() {
//			invalidException.expect(InvalidParameterException.class);
//			invalidException.expectMessage("Expected InvalidParameterException");
//			final KeyedValues KeyedNullValue = null;
//			DataUtilities.getCumulativePercentages(KeyedNullValue);
//		}
	//createNumberArray
		@Test
		//testing positive decimals values
		public void testCreateNumberArrayPositive() {
			double[] testData = {1.1, 2.2, 3.3, 4.4, 5.5, 7.7, 8.8};
			Number[] result = DataUtilities.createNumberArray(testData);
			assertTrue(result instanceof Number[]);
		}
		@Test
		//testing negative decimals values
		public void testCreateNumberArrayNegative() {
			double[] testData = {-1.1, -2.2, -3.3, -4.4, -5.5, -7.7, -8.8};
			Number[] result = DataUtilities.createNumberArray(testData);
			assertTrue(result instanceof Number[]);
		}
		@Test
		//testing both positive and negative values
		public void testCreateNumberArrayAll() {
			double[] testData = {-4.4, -3.3, -2.2, -1.1, 1.1, 2.2, 3.3};
			Number[] result = DataUtilities.createNumberArray(testData);
			assertTrue(result instanceof Number[]);
		}
		@Rule // Testing InvalidParameterException for createNumberArray with an invalid input
		public ExpectedException invalidNumArrayException = ExpectedException.none();
		
		// Testing InvalidParameterException for createNumberArray with an invalid input
//		@Test(expected = IllegalArgumentException.class)
//		public void createNumberArrayInvalid() {
//			invalidNumArrayException.expect(InvalidParameterException.class);
//			invalidNumArrayException.expectMessage("Expected InvalidParameterException");
//			final double[] numArray = null;
//			DataUtilities.createNumberArray(numArray);
//		}
//		
		//createNumberArray2D
		@Test
		//testing positive decimals values
		public void testCreateNumberArray2DPositive() {
			double[][] testData = {{1.1, 2.2}, {3.3, 4.4}};
			Number[][] result = DataUtilities.createNumberArray2D(testData);
			assertTrue(result instanceof Number[][]);
		}
		@Test
		//testing negative decimals values
		public void testCreateNumberArray2DNegative() {
			double[][] testData = {{-1.1, -2.2}, { -3.3, -4.4}};
			Number[][] result = DataUtilities.createNumberArray2D(testData);
			assertTrue(result instanceof Number[][]);
		}
		@Test
		//testing both positive and negative values
		public void testCreateNumberArray2DAll() {
			double[][] testData = {{ -2.2, -1.1}, {1.1, 2.2}};
			Number[][] result = DataUtilities.createNumberArray2D(testData);
			assertTrue(result instanceof Number[][]);
		}
		
		@Rule // Testing InvalidParameterException for createNumberArray with an invalid input
		public ExpectedException invalidNumArray2DException = ExpectedException.none();
		
//		// Testing InvalidParameterException for createNumberArray with an invalid input
//		@Test(expected = IllegalArgumentException.class)
//		public void createNumberArray2DInvalid() {
//			invalidNumArray2DException.expect(InvalidParameterException.class);
//			invalidNumArray2DException.expectMessage("Expected InvalidParameterException");
//			final double[][] numArray2D = null;
//			DataUtilities.createNumberArray2D(numArray2D);
//		}
//		
		//clone(double[][])
		@Test
		//testing clone method with 2D array
		public void testClone() {
			double[][] originalArray = {{1.1, 2.2}, {3.3, 4.4}};
			double[][] copy = DataUtilities.clone(originalArray);
			boolean outcome = DataUtilities.equal(originalArray, copy);
			assertTrue("Copy should be the same as orginal", outcome);
		}
		
		//non equal arrays negative
		@Test
		public void nonEqualsNeg() {
			double[][] one = {{-1.1, 2.2}, {3.3, 4.4}};
			double[][] two = {{1.1, 2.2}, {3.3, 4.4}};
			boolean outcome = DataUtilities.equal(one, two);
			assertFalse("no match", outcome);
		}
		
		//both null arrays
		@Test
		public void NULLEquals() {
			double[][] one = null;
			double[][] two = null;
			boolean outcome = DataUtilities.equal(one, two);
			assertTrue("match", outcome);
		}
		//b = null
		@Test
		public void bNullEquals() {
			double[][] one = {{1}, {2}};
			double[][] two = null;
			boolean outcome = DataUtilities.equal(one, two);
			assertFalse("no match", outcome);
		}
		//different lengths
		@Test
		public void nonEqualLengths() {
			double[][] one = {{1.1, 2.2}, {3.3, 4.4}};
			double[][] two = new double[4][2];
			boolean outcome = DataUtilities.equal(one, two);
			assertFalse("no match", outcome);
		}
				 
		//Mutation testing

		@Test
		public void nullArraysEqual() {
		    assertTrue(DataUtilities.equal(null, null));
		}

		@Test
		public void differentLengthArraysNotEqual() {
		    double[][] firstArray = new double[4][];
		    double[][] secondArray = new double[5][];
		    assertFalse(DataUtilities.equal(firstArray, secondArray));
		}

		@Test
		public void sameLengthArraysEqual() {
		    double[][] firstArray = new double[5][];
		    double[][] secondArray = new double[5][];
		    assertTrue(DataUtilities.equal(firstArray, secondArray));
		}

		@Test
		public void arraysWithDifferentSubarraysNotEqual() {
		    double[][] firstArray = new double[4][];
		    double[][] secondArray = new double[4][];
		    firstArray[0] = new double[6];
		    secondArray[0] = new double[5];
		    assertFalse(DataUtilities.equal(firstArray, secondArray));
		}

		@Test
		public void arraysWithSameSubarraysEqual() {
		    double[][] firstArray = new double[4][];
		    double[][] secondArray = new double[4][];
		    firstArray[0] = new double[6];
		    secondArray[0] = new double[6];
		    assertTrue(DataUtilities.equal(firstArray, secondArray));
		}

		@Test
		public void arraysWithSameElementsEqual() {
		    double[][] firstArray = new double[4][];
		    double[][] secondArray = new double[4][];
		    firstArray[0] = new double[6];
		    secondArray[0] = new double[6];
		    firstArray[0][0] = 1.0;
		    secondArray[0][0] = 1.0;
		    firstArray[0][1] = Double.NaN;
		    secondArray[0][1] = Double.NaN;
		    firstArray[0][2] = Double.NEGATIVE_INFINITY;
		    secondArray[0][2] = Double.NEGATIVE_INFINITY;
		    firstArray[0][3] = Double.POSITIVE_INFINITY;
		    secondArray[0][3] = Double.POSITIVE_INFINITY;
		    firstArray[0][4] = Double.POSITIVE_INFINITY;
		    secondArray[0][4] = Double.POSITIVE_INFINITY;
		    assertTrue(DataUtilities.equal(firstArray, secondArray));
		}

		@Test
		public void arraysWithDifferentElementsNotEqual() {
		    double[][] firstArray = new double[4][];
		    double[][] secondArray = new double[4][];
		    firstArray[0] = new double[6];
		    secondArray[0] = new double[6];
		    firstArray[0][0] = 1.0;
		    secondArray[0][0] = 2.0;
		    assertFalse(DataUtilities.equal(firstArray, secondArray));
		}

	    @Test
	    public void createNumberArray2DMutationTest() {
	        double[][] d = {{1.1, 2.2, 3.3, 4.4}, {1.1, 2.2, 3.3, 4.4, 5.5}};
	        Number[][] n = DataUtilities.createNumberArray2D(d);
	        assertEquals(2, n.length);
	        assertEquals(4, n[0].length);
	        assertEquals(5, n[1].length);
	    }
	    
	    @Test
	    public void createNumberArray2DMutationTest2() {
	        double[][] d = new double[4][];
	        d[0] = new double[] {1.5, 2.8, 3.2};
	        d[1] = new double[] {4.0, 5.2, 6.7};
	        d[2] = new double[] {7.4, 8.1, 9.6, 10.0};
	        d[3] = new double[] {11.3, 12.6, 13.9, 14.2, 15.5};
	        
	        Number[][] n = DataUtilities.createNumberArray2D(d);
	        
	        assertEquals(4, n.length);
	        assertEquals(3, n[0].length);
	        assertEquals(3, n[1].length);
	        assertEquals(4, n[2].length);
	        assertEquals(5, n[3].length);
	    }	    
	    
	    @Test
	    public void calculateRowTotalMutationTest() {
	    	Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(4));
	                one(values).getValue(0, 0);
	                will(returnValue(5.4));
	                one(values).getValue(0, 1);
	                will(returnValue(2.1));
	                one(values).getValue(0, 2);
	                will(returnValue(null));
	    			one(values).getValue(0, 3);
	    			will(returnValue(3.3));
	            }
	        });
	        int[] zeroArray = {0};
	        double result = DataUtilities.calculateRowTotal(values, 0, zeroArray);
	        assertEquals(5.4, result, 0.00001);
	    } 
				
}