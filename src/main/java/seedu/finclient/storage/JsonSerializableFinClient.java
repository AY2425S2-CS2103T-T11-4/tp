package seedu.finclient.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.finclient.commons.exceptions.IllegalValueException;
import seedu.finclient.model.FinClient;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.person.Person;

/**
 * An Immutable FinClient that is serializable to JSON format.
 */
@JsonRootName(value = "finclient")
class JsonSerializableFinClient {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFinClient(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableFinClient(ReadOnlyFinClient source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code FinClient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FinClient toModelType() throws IllegalValueException {
        FinClient finClient = new FinClient();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (finClient.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            finClient.addPerson(person);
        }
        return finClient;
    }

}
