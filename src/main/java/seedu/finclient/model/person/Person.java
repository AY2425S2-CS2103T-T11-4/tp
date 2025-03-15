package seedu.finclient.model.person;

import static seedu.finclient.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.finclient.commons.util.ToStringBuilder;
import seedu.finclient.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final PhoneList phoneList;
    private final Email email;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    // Hidden detail flag
    private boolean isHidden = false;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, PhoneList phoneList, Email email, Address address, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, phoneList, email, address, tags);
        this.name = name;
        this.phoneList = phoneList;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    /**
     * Alternate constructor to allow hiding of details.
     */
    public Person(Name name, PhoneList phoneList, Email email,
                  Address address, Remark remark, Set<Tag> tags, boolean isHidden) {
        requireAllNonNull(name, phoneList, email, address, tags);
        this.name = name;
        this.phoneList = phoneList;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.isHidden = isHidden;
    }

    public void setHidden() {
        this.isHidden = true;
    }

    public void setUnhidden() {
        this.isHidden = false;
    }

    public Name getName() {
        return name;
    }

    public PhoneList getPhoneList() {
        return isHidden ? new PhoneList(new ArrayList<>(Arrays.asList(new Phone("00000000")))) : phoneList;
    }

    public Email getEmail() {
        return isHidden ? new Email("hidden@example.com") : email;
    }

    public Address getAddress() {
        return isHidden ? new Address("Hidden") : address;
    }

    public Remark getRemark() {
        return remark; }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return isHidden ? Collections.emptySet() : Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if the person is hidden.
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phoneList.equals(otherPerson.phoneList)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && remark.equals(otherPerson.remark)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phoneList, email, address, remark, tags, isHidden);
    }

    @Override
    public String toString() {
        // If the person is hidden, return only non-sensitive details.
        if (isHidden) {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("details", "Sensitive details are hidden")
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phones", phoneList)
                    .add("email", email)
                    .add("address", address)
                    .add("remark", remark)
                    .add("tags", tags)
                    .toString();
        }
    }
}
