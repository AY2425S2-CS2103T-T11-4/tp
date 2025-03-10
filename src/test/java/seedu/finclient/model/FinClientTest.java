package seedu.finclient.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.finclient.testutil.Assert.assertThrows;
import static seedu.finclient.testutil.TypicalPersons.ALICE;
import static seedu.finclient.testutil.TypicalPersons.getTypicalFinClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.finclient.model.person.Person;
import seedu.finclient.model.person.exceptions.DuplicatePersonException;
import seedu.finclient.testutil.PersonBuilder;

public class FinClientTest {

    private final FinClient finClient = new FinClient();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), finClient.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> finClient.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFinClient_replacesData() {
        FinClient newData = getTypicalFinClient();
        finClient.resetData(newData);
        assertEquals(newData, finClient);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> finClient.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> finClient.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(finClient.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        finClient.addPerson(ALICE);
        assertTrue(finClient.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        finClient.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(finClient.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> finClient.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = FinClient.class.getCanonicalName() + "{persons=" + finClient.getPersonList() + "}";
        assertEquals(expected, finClient.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyFinClient {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
