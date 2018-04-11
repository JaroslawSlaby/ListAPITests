import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    private List<Object> testList;

    @BeforeMethod
    public void setUp() {
        testList = new ArrayList<Object>();
    }

    @Test
    public void addOneElementTest() {
        // given
        // when
        boolean isAdded = testList.add(new Object());
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
    public void addOneElementTestSize() {
        // given
        // when
        testList.add(new Object());
        int sizeBefore = testList.size();
        testList.add(new Object());
        int sizeAfter = testList.size();
        // then
        assert (sizeBefore + 1) == sizeAfter : "Size after is wrong";
    }

    @Test
    public void addElementAtSecondPosition() {
        // given
        // when
        testList.add(0, new Object());
        int sizeBefore = testList.size();
        testList.add(1, new Object());
        int sizeAfter = testList.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
        assert testList.get(1) != null : "2nd element is null";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void addElementAtMinusPosition() {
        // given
        // when
        testList.add(-1, new Object());
        // then
    }

    @Test
    public void addElementAtZeroPosition() {
        // given
        // when
        int sizeBefore = 0;
        testList.add(0, new Object());
        int sizeAfter = testList.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
    }

    @Test
    public void addAnotherNotNullCollection() {
        // given
        List<Object> tempList = new ArrayList<Object>();
        tempList.add(new Object());
        tempList.add(new Object());
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert isAdded : "Another collection not added";
        assert tempList.size() == testList.size() : "Sizes are different";
    }

    @Test
    public void addAnotherEmptyCollection() {
        // given
        List<Object> tempList = new ArrayList<Object>();
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert !isAdded : "Another empty collection not added";
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addAnotherNullCollection() {
        // given
        List<Object> tempList = null;
        // when
        testList.addAll(tempList);
        // then
    }

    @Test
    public void addAnotherNotEmptyCollectionDifferentType() {
        // given
        List<Object> tempList = new LinkedList<Object>();
        tempList.add(new Object());
        tempList.add(new Object());
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert isAdded : "Different type collection not added";
    }

    @Test
    public void addAnotherEmptyCollectionDifferentType() {
        // given
        List<Object> tempList = new LinkedList<Object>();
        // when
        boolean isAdded = testList.addAll(tempList);
        // then
        assert !isAdded : "Different type empty collection not added";
    }

    @Test
    public void addAnotherCollectionAtSecondIndex() {
        // given
        testList.add(new Object());
        List<Object> tempList = new ArrayList<Object>();
        tempList.add(new Object());
        tempList.add(new Object());
        // when
        boolean isAdded = testList.addAll(1, tempList);
        // then
        assert isAdded : "Another list not added";
    }
}
