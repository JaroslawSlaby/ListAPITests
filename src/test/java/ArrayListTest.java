import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    private List<String> testList;

    @BeforeMethod
    public void setUp() {
        testList = new ArrayList<String>();
    }

    @Test
    public void addOneElementTest() {
        // given
        // when
        boolean isAdded = testList.add("testString");
        // then
        assert isAdded : "isAdded == false";
    }

    @Test
    public void addOneNullElement() {
        // given
        // when
        boolean isAdded = testList.add(null);
        // then
        assert isAdded : "isAdded == false";
    }

    @Test
    public void addFewElementsWithNull() {
        // given

        // when
        boolean a = testList.add("a");
        boolean b = testList.add("b");
        boolean c = testList.add(null);
        boolean d = testList.add("c");
        // then
        assert a && b && c && d : "Null element not added";
    }

    @Test
    public void addOneElementTestSize() {
        // given
        // when
        testList.add("testString");
        int sizeBefore = testList.size();
        testList.add("testString");
        int sizeAfter = testList.size();
        // then
        assert (sizeBefore + 1) == sizeAfter : "Size after is wrong";
    }

    @Test
    public void addElementAtSecondPosition() {
        // given
        // when
        testList.add(0, "testString");
        int sizeBefore = testList.size();
        testList.add(1, "testString");
        int sizeAfter = testList.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
        assert testList.get(1) != null : "2nd element is null";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void addElementAtMinusPosition() {
        // given
        // when
        testList.add(-1, "testString");
        // then
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void addElementAtPositionBiggerThanSize() {
        // given
        // when
        testList.add(testList.size() + 2, "testString");
        // then
    }

    @Test
    public void addNullTwoTimes() {
        // given

        // when
        boolean a = testList.add(null);
        boolean b = testList.add(null);
        // then
        assert a && b : "Null element cannot be added two times";
    }

    @Test
    public void addElementAtZeroPosition() {
        // given
        // when
        int sizeBefore = 0;
        testList.add(0, "testString");
        int sizeAfter = testList.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
    }

    @Test
    public void addAnotherNotNullCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("testString");
        tempList.add("testString");
        tempList.add(null);
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert isAdded : "Another collection not added";
        assert tempList.size() == testList.size() : "Sizes are different";
    }

    @Test
    public void addAnotherEmptyCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert !isAdded : "Another empty collection not added";
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addAnotherNullCollection() {
        // given
        List<String> tempList = null;
        // when
        testList.addAll(tempList);
        // then
    }

    @Test
    public void addAnotherNotEmptyCollectionDifferentType() {
        // given
        List<String> tempList = new LinkedList<String>();
        tempList.add("testString");
        tempList.add("testString");
        tempList.add(null);
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert isAdded : "Different type collection not added";
    }

    @Test
    public void addAnotherEmptyCollectionDifferentType() {
        // given
        List<String> tempList = new LinkedList<String>();
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert !isAdded : "Different type empty collection not added";
    }

    @Test
    public void addAnotherCollectionAtSecondIndex() {
        // given
        testList.add("testString");
        List<String> tempList = new ArrayList<String>();
        tempList.add("testString");
        tempList.add("testString");
        tempList.add(null);
        // when
        boolean isAdded = testList.addAll(1, tempList);
        // then
        assert isAdded : "Another list not added";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void addAnotherCollectionAtMinusIndex() {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("testString");
        // when
        testList.addAll(-1, tempList);
        // then
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void addAnotherCollectionAtIndexBiggerThanSize() {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("testString");
        // when
        testList.addAll(testList.size() + 2, tempList);
        // then
    }

    @Test
    public void clearNotEmptyList() {
        // given
        testList.add("testString");
        // when
        testList.clear();
        // then
        assert testList.size() == 0 : "Not empty list after clearing";
    }

    @Test
    public void clearEmptyList() {
        // given

        // when
        testList.clear();
        // then
        assert testList.size() == 0 : "$MSG";
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void clearNullList() {
        // given
        testList = null;
        // when
        testList.clear();
        // then
    }

    @Test
    public void containsNotNullElement() {
        // given
        testList.add("test");
        // when
        boolean contains = testList.contains("test");
        // then
        assert contains : "List doesn't contain specified element";
    }

    @Test
    public void containsNullElement() {
        // given
        testList.add(null);
        // when
        boolean contains = testList.contains(null);
        // then
        assert contains : "List doesn't contain null element";
    }

    @Test
    public void containsNotExistingElement() {
        // given
        testList.add("non-test");
        // when
        boolean contains = testList.contains("test");
        // then
        assert !contains : "List doesn't contain not existing element";
    }

    @Test
    public void containsAllNotEmptyCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("one");
        tempList.add("two");
        testList.addAll(tempList);
        // when
        boolean containsAll = testList.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain non-empty temp list";
    }

    @Test
    public void containsAllEmptyCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        testList.addAll(tempList);
        // when
        boolean containsAll = testList.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain empty temp list";
    }

    @Test
    public void containsAllDifferentTypeNotEmptyCollection() {
        // given
        List<String> tempList = new LinkedList<String>();
        tempList.add("one");
        tempList.add("two");
        testList.addAll(tempList);
        // when
        boolean containsAll = testList.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain non-empty different type temp list";
    }

    @Test
    public void containsAllDifferentTypeEmptyCollection() {
        // given
        List<String> tempList = new LinkedList<String>();
        testList.addAll(tempList);
        // when
        boolean containsAll = testList.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain empty different type temp list";
    }

    @Test
    public void equalsTheSameCollection() {
        // given

        // when
        boolean equalsTheSame = testList.equals(testList);
        // then
        assert equalsTheSame : "Test list doesn't contain itself";
    }

    @Test
    public void equalsAnotherEmptyCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert equalsAnother : "Empty test list doesn't equals another empty list with the same type";
    }

    @Test
    public void equalsAnotherNonEmptyCollection() {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("one");
        tempList.add("two");
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert !equalsAnother : "Empty test list equals non-empty temp list";
    }

    @Test
    public void equalsEmptyCollection() {
        // given
        testList.add("one");
        testList.add("two");

        List<String> tempList = new ArrayList<String>();
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert !equalsAnother : "Non-empty test list equals empty temp list";
    }

    @Test
    public void equalsNonEmptyCollection() {
        // given
        testList.add("one");
        testList.add("two");

        List<String> tempList = new ArrayList<String>();
        tempList.add("one");
        tempList.add("two");
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert equalsAnother : "Non-empty test list doesn't equals another list with the same elements";
    }

    @Test
    public void equalsAnotherDifferentTypeEmptyCollection() {
        // given
        List<String> tempList = new LinkedList<String>();
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert equalsAnother : "Empty test list doesn't equals another empty list with the same type";
    }

    @Test
    public void equalsAnotherDifferentTypeNonEmptyCollection() {
        // given
        List<String> tempList = new LinkedList<String>();
        tempList.add("one");
        tempList.add("two");
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert !equalsAnother : "Empty test list equals non-empty temp list";
    }

    @Test
    public void equalsAnotherTypeEmptyCollection() {
        // given
        testList.add("one");
        testList.add("two");

        List<String> tempList = new LinkedList<String>();
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert !equalsAnother : "Non-empty test list equals empty temp list";
    }

    @Test
    public void equalsAnotherTypeNonEmptyCollection() {
        // given
        testList.add("one");
        testList.add("two");

        List<String> tempList = new LinkedList<String>();
        tempList.add("one");
        tempList.add("two");
        // when
        boolean equalsAnother = testList.equals(tempList);
        // then
        assert equalsAnother : "Non-empty test list doesn't equals another different type list with the same elements";
    }
}
