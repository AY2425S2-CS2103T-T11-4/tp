package seedu.finclient.testutil;

import seedu.finclient.model.FinClient;
import seedu.finclient.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code FinClient ab = new FinClientBuilder().withPerson("John", "Doe").build();}
 */
public class FinClientBuilder {

    private FinClient finClient;

    public FinClientBuilder() {
        finClient = new FinClient();
    }

    public FinClientBuilder(FinClient finClient) {
        this.finClient = finClient;
    }

    /**
     * Adds a new {@code Person} to the {@code FinClient} that we are building.
     */
    public FinClientBuilder withPerson(Person person) {
        finClient.addPerson(person);
        return this;
    }

    public FinClient build() {
        return finClient;
    }
}
