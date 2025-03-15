package seedu.finclient.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.finclient.testutil.Assert.assertThrows;
import static seedu.finclient.testutil.TypicalPersons.ALICE;
import static seedu.finclient.testutil.TypicalPersons.BOB;
import static seedu.finclient.testutil.TypicalPersons.TOONOMBER;
import static seedu.finclient.testutil.TypicalPersons.TWEENOMBER;

import org.junit.jupiter.api.Test;

import seedu.finclient.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));

        assertFalse(TOONOMBER.isSamePerson(TWEENOMBER));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", phones=" + ALICE.getPhoneList() + ", email=" + ALICE.getEmail() + ", address="
                + ALICE.getAddress() + ", remark=" + ALICE.getRemark() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void testHiddenBehavior() {
        // Make a copy of ALICE with certain fields we can easily recognize
        Person hiddenAlice = new PersonBuilder(ALICE).build();
        assertFalse(hiddenAlice.getIsHidden(), "Newly created person should not be hidden by default.");

        // Hide the person
        hiddenAlice.setHidden();
        assertTrue(hiddenAlice.getIsHidden(), "Person should now be hidden.");

        // Now verify that the 'hidden' variants are returned
        assertEquals("00000000", hiddenAlice.getPhoneList().toString(),
                "PhoneList should be replaced with 00000000 when hidden.");
        assertEquals("hidden@example.com", hiddenAlice.getEmail().toString(),
                "Email should be replaced with hidden@example.com when hidden.");
        assertEquals("Hidden", hiddenAlice.getAddress().toString(),
                "Address should read 'Hidden' when hidden.");
        assertTrue(hiddenAlice.getTags().isEmpty(),
                "Tags should be empty when hidden.");

        // And the toString() method should reflect hidden details
        String hiddenString = hiddenAlice.toString();
        // e.g. "seedu.finclient.model.person.Person{name=Alice Pauline, details=Sensitive details are hidden}"
        assertTrue(hiddenString.contains("Sensitive details are hidden"),
                "Hidden person's toString() should say that details are hidden.");
    }

    @Test
    public void testUnhiddenBehavior() {
        // Start off with ALICE, hide, then unhide
        Person hiddenAlice = new PersonBuilder(ALICE).build();
        hiddenAlice.setHidden();
        assertTrue(hiddenAlice.getIsHidden());

        // Unhide
        hiddenAlice.setUnhidden();
        assertFalse(hiddenAlice.getIsHidden());

        // Should revert back to original data
        assertEquals(ALICE.getPhoneList(), hiddenAlice.getPhoneList(),
                "PhoneList should revert to the original once unhidden.");
        assertEquals(ALICE.getEmail(), hiddenAlice.getEmail(),
                "Email should revert to original once unhidden.");
        assertEquals(ALICE.getAddress(), hiddenAlice.getAddress(),
                "Address should revert to the original once unhidden.");
        assertEquals(ALICE.getTags(), hiddenAlice.getTags(),
                "Tags should revert to the original once unhidden.");
    }

    @Test
    public void testSetHiddenAndUnhidden() {
        Person p = new PersonBuilder().build(); // a generic person
        // by default, isHidden should be false
        assertFalse(p.getIsHidden());

        p.setHidden();
        assertTrue(p.getIsHidden(), "After setHidden(), person should be hidden.");

        p.setUnhidden();
        assertFalse(p.getIsHidden(), "After setUnhidden(), person should no longer be hidden.");
    }
}
