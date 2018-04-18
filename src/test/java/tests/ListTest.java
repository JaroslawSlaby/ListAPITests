package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListTest {

    Logger logger = Logger.getLogger(getClass().getName());

    @DataProvider(name = "collectionImpl")
    public Object[] collectionImpl() {
        return new Object[]{
                new ArrayList<String>(),
                new LinkedList<String>()
        };
    }

    @Test(dataProvider = "collectionImpl")
    public void addOneElementTest(List<String> collection) {
        // given
        // when
        boolean isAdded = collection.add("testString");
        logger.log(Level.INFO, "Element added to " + collection.getClass().getName());
        // then
        assert isAdded : "isAdded == false";
    }

    @Test(dataProvider = "collectionImpl")
    public void addOneNullElement(List<String> collection) {
        // given
        // when
        boolean isAdded = collection.add(null);
        // then
        assert isAdded : "isAdded == false";
    }

    @Test(dataProvider = "collectionImpl")
    public void addFewElementsWithNull(List<String> collection) {
        // given
        // when
        boolean a = collection.add("a");
        boolean b = collection.add("b");
        boolean c = collection.add(null);
        boolean d = collection.add("c");
        // then
        assert a && b && c && d : "Null element not added";
    }

    @Test(dataProvider = "collectionImpl")
    public void addOneElementTestSize(List<String> collection) {
        // given
        // when
        collection.add("testString");
        int sizeBefore = collection.size();
        collection.add("testString");
        int sizeAfter = collection.size();
        // then
        assert (sizeBefore + 1) == sizeAfter : "Size after is wrong";
    }

    @Test(dataProvider = "collectionImpl")
    public void addElementAtSecondPosition(List<String> collection) {
        // given
        // when
        collection.add(0, "testString");
        int sizeBefore = collection.size();
        collection.add(1, "testString");
        int sizeAfter = collection.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
        assert collection.get(1) != null : "2nd element is null";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void addElementAtMinusPosition(List<String> collection) {
        // given
        // when
        collection.add(-1, "testString");
        // then
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void addElementAtPositionBiggerThanSize(List<String> collection) {
        // given
        // when
        collection.add(collection.size() + 2, "testString");
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void addNullTwoTimes(List<String> collection) {
        // given
        // when
        boolean a = collection.add(null);
        boolean b = collection.add(null);
        // then
        assert a && b : "Null element cannot be added two times";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = UnsupportedOperationException.class)
    public void addToUnmodifialbeListThrowsUOE(List<String> collection) {
        // given
        List<String> temp = Collections.unmodifiableList(collection);
        temp.add("elo elo elo");
        // when
        collection.size();
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void addElementAtZeroPosition(List<String> collection) {
        // given
        // when
        int sizeBefore = 0;
        collection.add(0, "testString");
        int sizeAfter = collection.size();
        // then
        assert sizeAfter == sizeBefore + 1 : "Size after is wrong";
    }

    @Test(dataProvider = "collectionImpl")
    public void addAnotherNotNullCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>() {
            {
                add("testString");
                add("testString");
                add(null);
            }
        };
        // when
        boolean isAdded = collection.addAll(tempList);
        // then
        assert isAdded : "Another collection not added";
        assert tempList.size() == collection.size() : "Sizes are different";
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionTest() {
        // given
        List<Integer> temp = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};
        List temp2 = Arrays.asList("1a", "2a", "3a");
        // when
        boolean isAdded = temp2.addAll(temp);
        temp2.forEach(e -> logger.log(Level.INFO, e.toString()));
        // then
        assert isAdded : "";
    }

    @Test(dataProvider = "collectionImpl")
    public void addAnotherEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>();
        // when
        boolean isAdded = collection.addAll(tempList);
        // then
        assert !isAdded : "Another empty collection not added";
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "collectionImpl")
    public void addAnotherNullCollection(List<String> collection) {
        // given
        List<String> tempList = null;
        // when
        collection.addAll(tempList);
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void addAnotherNotEmptyCollectionDifferentType(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>() {
            {
                add("testString");
                add("testString");
                add(null);
            }
        };
        // when
        boolean isAdded = collection.addAll(tempList);
        // then
        assert isAdded : "Different type collection not added";
    }

    @Test(dataProvider = "collectionImpl")
    public void addAnotherEmptyCollectionDifferentType(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>();
        // when
        boolean isAdded = collection.addAll(tempList);
        // then
        assert !isAdded : "Different type empty collection not added";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void addAnotherCollectionAtMinusIndex(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>();
        tempList.add("testString");
        // when
        collection.addAll(-1, tempList);
        // then
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void addAnotherCollectionAtIndexBiggerThanSize(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>() {
            {
                add("testString");
            }
        };
        // when
        collection.addAll(collection.size() + 2, tempList);
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void clearNotEmptyList(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two");
        // when
        collection.clear();
        // then
        assert collection.size() == 0 : "Not empty list after clearing";
    }

    @Test(dataProvider = "collectionImpl")
    public void clearEmptyList(List<String> collection) {
        // given
        // when
        collection.clear();
        // then
        assert collection.size() == 0 : "$MSG";
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "collectionImpl")
    public void clearNullList(List<String> collection) {
        // given
        collection = null;
        // when
        collection.clear();
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void containsNotNullElement(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "test");
        // when
        boolean contains = collection.contains("test");
        // then
        assert contains : "List doesn't contain specified element";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsNullElement(List<String> collection) {
        // given
        collection.add(null);
        // when
        boolean contains = collection.contains(null);
        // then
        assert contains : "List doesn't contain null element";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsNotExistingElement(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two");
        // when
        boolean contains = collection.contains("test");
        // then
        assert !contains : "List doesn't contain not existing element";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsAllNotEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>() {
            {
                add("one");
                add("two");
            }
        };
        collection.addAll(tempList);
        // when
        boolean containsAll = collection.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain non-empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsAllEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>();
        collection.addAll(tempList);
        // when
        boolean containsAll = collection.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsAllDifferentTypeNotEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>() {
            {
                add("one");
                add("two");
            }
        };
        collection.addAll(tempList);
        // when
        boolean containsAll = collection.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain non-empty different type temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void containsAllDifferentTypeEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>();
        collection.addAll(tempList);
        // when
        boolean containsAll = collection.containsAll(tempList);
        // then
        assert containsAll : "Test list doesn't contain empty different type temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsNull(List<String> collection) {
        // given
        // when
        boolean equalsNull = collection.equals(null);
        // then
        assert !equalsNull : "Non-null test list equals null";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsTheSameCollection(List<String> collection) {
        // given
        // when
        boolean equalsTheSame = collection.equals(collection);
        // then
        assert equalsTheSame : "Test list doesn't contain itself";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>();
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert equalsAnother : "Empty test list doesn't equals another empty list with the same type";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherNonEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>() {
            {
                add("one");
                add("two");
            }
        };
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert !equalsAnother : "Empty test list equals non-empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsEmptyCollection(List<String> collection) {
        // given
        collection.add("one");
        collection.add("two");
        List<String> tempList = new ArrayList<String>();
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert !equalsAnother : "Non-empty test list equals empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsNonEmptyCollection(List<String> collection) {
        // given
        collection.add("one");
        collection.add("two");
        List<String> tempList = new ArrayList<String>() {{
            add("one");
            add("two");
        }};
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert equalsAnother : "Non-empty test list doesn't equals another list with the same elements";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherDifferentTypeEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>();
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert equalsAnother : "Empty test list doesn't equals another empty list with the same type";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherDifferentTypeNonEmptyCollection(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>() {{
            add("one");
            add("two");
        }};
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert !equalsAnother : "Empty test list equals non-empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherTypeEmptyCollection(List<String> collection) {
        // given
        collection.add("one");
        collection.add("two");
        List<String> tempList = new LinkedList<String>();
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert !equalsAnother : "Non-empty test list equals empty temp list";
    }

    @Test(dataProvider = "collectionImpl")
    public void equalsAnotherTypeNonEmptyCollection(List<String> collection) {
        // given
        collection.add("one");
        collection.add("two");
        List<String> tempList = new LinkedList<String>() {{
            add("one");
            add("two");
        }};
        // when
        boolean equalsAnother = collection.equals(tempList);
        // then
        assert equalsAnother : "Non-empty test list doesn't equals another different type list with the same elements";
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void getFirstFromEmptyCollection(List<String> collection) {
        // given
        // when
        collection.get(0);
        // then
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "collectionImpl")
    public void getElementFromNonEmptyCollectionMinusIndex(List<String> collection) {
        // given
        // when
        collection.get(-1);
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void getFirstElementFromNonEmptyCollection(List<String> collection) {
        // given
        collection.add("one");
        collection.add("two");
        // when
        String first = collection.get(0);
        // then
        assert first.equals("one") : "Non-empty test list doesn't contain element added before";
    }

    @Test(dataProvider = "collectionImpl")
    public void getFirstElementFromNonEmptyCollectionIncludingAnotherCollection(List<String> collection) {
        // given
        List<String> tempList = new ArrayList<String>() {{
            add("one");
            add("two");
        }};
        collection.addAll(tempList);
        // when
        String first = collection.get(0);
        // then
        assert first.equals("one") : "Non-empty test list doesn't contains element from another list added by addAll method";
    }

    @Test(dataProvider = "collectionImpl")
    public void getFirstElementFromNonEmptyCollectionIncludingAnotherDifferentTypeCollection(List<String> collection) {
        // given
        List<String> tempList = new LinkedList<String>() {{
            add("one");
            add("two");
        }};
        collection.addAll(tempList);
        // when
        String first = collection.get(0);
        // then
        assert first.equals("one") : "Non-empty test list doesn't contains element from another list with different type added by addAll method";
    }

    //FIXME: Hashcode will be here but I'm not sure it's necessary

    @Test(dataProvider = "collectionImpl")
    public void indexOfElementFromEmptyCollection(List<String> collection) {
        // given
        // when
        int index = collection.indexOf("one");
        // then
        assert index == -1 : "Empty collection contains specified element";
    }

    @Test(dataProvider = "collectionImpl")
    public void indexOfNullElementFromEmptyCollecion(List<String> collection) {
        // given
        // when
        int index = collection.indexOf(null);
        // then
        assert index == -1 : "Empty collection contains null element";
    }

    @Test(dataProvider = "collectionImpl")
    public void indexOfNullElementFromCollectionContainingNull(List<String> collection) {
        // given
        collection.add(null);
        // when
        int index = collection.indexOf(null);
        // then
        assert index == 0 : "Collection doesn't contain added null element";
    }

    @Test(dataProvider = "collectionImpl")
    public void indexOfNullElementAddedTwoTimes(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", null, null);
        // when
        int index = collection.indexOf(null);
        // then
        assert index == 1 : "Collection doesn't contain null element added two times";
    }

    @Test(dataProvider = "collectionImpl")
    public void indexOfNonNullElementAddedToCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        int index = collection.indexOf("two");
        // then
        assert index == 1 : "Collection doesn't contain non-null element added before one time";
    }

    @Test(dataProvider = "collectionImpl")
    public void isEmptyEmptyCollection(List<String> colleciton) {
        // given

        // when
        boolean isEmptyCollectionReallyEmpty = colleciton.isEmpty();
        // then
        assert isEmptyCollectionReallyEmpty : "Empty collection is not empty";
    }

    @Test(dataProvider = "collectionImpl")
    public void isEmptyNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        boolean isNonEmptyCollectionEmpty = collection.isEmpty();
        // then
        assert !isNonEmptyCollectionEmpty : "Non empty collection is empty";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = NullPointerException.class)
    public void isEmptyNullCollection(List<String> collection) {
        // given
        collection = null;
        // when
        collection.isEmpty();
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void iteratorOfEmptyCollection(List<String> collection) {
        // given

        // when
        Iterator<String> iter = collection.iterator();
        // then
        assert iter instanceof Iterator : "Iterator of collection is not an iterator";
    }

    @Test(dataProvider = "collectionImpl")
    public void iteratorOfNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        Iterator<String> iterator = collection.iterator();
        // then
        assert iterator instanceof Iterator : "$MSG";
    }

    @Test(dataProvider = "collectionImpl")
    public void lastIndexOfEmptyCollection(List<String> collection) {
        // given

        // when
        int index = collection.lastIndexOf("one");
        // then
        assert index == -1 : "$MSG";
    }

    @Test(dataProvider = "collectionImpl")
    public void lastIndexOfNonEmptyCollecion(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three", "one", "one");
        // when
        int index = collection.lastIndexOf("one");
        // then
        assert index == 4 : "$MSG";
    }

    @Test(dataProvider = "collectionImpl")
    public void checkingIfIndexOfOneElementEqualsLastIndexOfElement(List<String> collection) {
        // given
        addElementsToCollection(collection, "one");
        // when
        int indexOf = collection.indexOf("one");
        int lastIndexOf = collection.lastIndexOf("one");
        // then
        assert indexOf == lastIndexOf : "In one-element collection lastIndexOf doesn't equal indexOf";
    }

    @Test(dataProvider = "collectionImpl")
    public void listIteratorOfEmptyCollection(List<String> collection) {
        // given

        // when
        ListIterator<String> iterator = collection.listIterator();
        // then
        assert iterator instanceof ListIterator : "List iterator of empty collection is not a list iterator";
    }

    @Test(dataProvider = "collectionImpl")
    public void listIteratorOfNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        ListIterator<String> iterator = collection.listIterator();
        // then
        assert iterator instanceof ListIterator : "List iterator of non-empty collection is not a list iterator";
    }

    @Test(dataProvider = "collectionImpl")
    public void listIteratorOfNonEmptyCollectionWithIndex(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        ListIterator<String> iterator = collection.listIterator(2);
        // then
        assert collection.get(2).equals(iterator.next()) : "List iterator of empty collection is not a list iterator";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = IndexOutOfBoundsException.class)
    public void listIteratorOfEmptyCollectionWithIndex(List<String> collection) {
        // given
        // when
        collection.listIterator(2);
        // then
    }

    @Test(dataProvider = "collectionImpl")
    public void removeFromNonEmptyList(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        String removed = collection.remove(0);
        // then
        assert removed.equals("one") : "Cannot remove element from list";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = IndexOutOfBoundsException.class)
    public void removeFromEmptyList(List<String> collection) {
        //given
        //when
        collection.remove(0);
        //then
    }

    @Test(dataProvider = "collectionImpl")
    public void removeFromNonEmptyListUsingObject(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        boolean removed = collection.remove("one");
        // then
        assert removed : "Cannot remove element from collection using object";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeFromEmptyListUsingObject(List<String> collection) {
        // given
        // when
        boolean removed = collection.remove("one");
        // then
        assert !removed : "Removed non-existing element";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeNullFromCollectionUsingObject(List<String> collection) {
        // given

        // when
        boolean removed = collection.remove(null);
        // then
        assert !removed : "Removed null from empty collection";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeNullFromCollectionNotContainingNull(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        boolean removed = collection.remove(null);
        // then
        assert !removed : "Removed null from collection that is not containing null element";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeNullElementFromCollectionContainingNull(List<String> collection) {
        // given
        addElementsToCollection(collection, null, "one", "two", "three");
        // when
        boolean removed = collection.remove(null);
        // then
        assert removed : "Cannot remove null element from collecion containing null";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeNonNullElementAddedMoreThanOneTime(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three", "three");
        // when
        boolean removed = collection.remove("three");
        // then
        assert removed : "Cannot remove element added more than one time";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeAllFromEmptyCollecion(List<String> collection) {
        // given
        // when
        boolean removed = collection.removeAll(new ArrayList<String>());
        // then
        assert !removed : "Removed another collection from empty collection";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeAllFromNonEmptyCollection(List<String> collection) {
        // given
        List<String> temp = new ArrayList<>();
        addElementsToCollection(temp, "one", "two", "three");
        addElementsToCollection(collection, "one", "two", "two", "two", "three", "four");
        // when
        boolean removed = collection.removeAll(temp);
        collection.forEach(e -> logger.log(Level.INFO, e));
        // then
        assert removed : "Cannot remove elements added to temporary collection from tested collection";
    }

    @Test(dataProvider = "collectionImpl")
    public void removeEmptyCollectionFromNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        List<String> temp = new ArrayList<>();
        // when
        boolean removed = collection.removeAll(temp);
        // then
        assert !removed : "Cannot remove empty temp collection from another collection";
    }

    @Test(dataProvider = "collectionImpl")
    public void replaceAllElementsInNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        collection.replaceAll(getOperator());
        // then
        assert collection.contains("TEST_TEXT") : "Replace all doesn't work";
    }

    @Test(dataProvider = "collectionImpl")
    public void replaceAllElementsInCollectionContainingNulls(List<String> collection) {
        // given
        addElementsToCollection(collection, null, null, null);
        // when
        collection.replaceAll(getOperator());
        // then
        assert collection.contains("TEST_TEXT") : "Cannot replace nulls";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = NullPointerException.class)
    public void replaceAllElementsInNullCollection(List<String> collection) {
        // given
        collection = null;
        // when
        collection.replaceAll(getOperator());
    }

    @Test(dataProvider = "collectionImpl")
    public void replaceAllElementsInNonEmptyCollectionWithNulls(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three");
        // when
        collection.replaceAll(getNullOperator());
        // then
        assert collection.contains(null) : "Cannot replace elements with nulls";
    }

    @Test(dataProvider = "collectionImpl")
    public void retainAllFromEmptyCollection(List<String> collection) {
        // given

        // when
        boolean retains = collection.retainAll(new ArrayList<>());
        // then
        assert !retains : "Retains empty collection";
    }

    @Test(dataProvider = "collectionImpl")
    public void retainAllFromNonEmptyCollection(List<String> collection) {
        // given
        addElementsToCollection(collection, "one", "two", "three", "four");
        List<String> temp = Arrays.asList("four");
        // when
        boolean retains = collection.retainAll(temp);
        collection.forEach(e -> logger.log(Level.INFO, e));
        // then
        assert retains : "Cannot use retainsAll on non empty collection";
    }

    @Test(dataProvider = "collectionImpl", expectedExceptions = NullPointerException.class)
    public void retainAllNullCollection(List<String> collection) {
        //given
        List<String> tempList = null;
        //when
        collection.retainAll(tempList);
    }

    @Test(dataProvider = "collectionImpl")
    public void retainNullCollectionFromCollectionContainingNull(List<String> collection) {
        // given
        List tempList = new ArrayList() {{
            add(null);
        }};
        addElementsToCollection(collection, null, "one", "two", "three");
        // when
        boolean retains = collection.retainAll(tempList);
        // then
        assert retains : "Cannot retain null collection";
    }


    private void addElementsToCollection(List<String> collection, String... elements) {
        collection.addAll(Arrays.asList(elements));
    }

    private UnaryOperator<String> getOperator() {
        return s -> s = "TEST_TEXT";
    }

    private UnaryOperator<String> getNullOperator() {
        return s -> s = null;
    }

}
