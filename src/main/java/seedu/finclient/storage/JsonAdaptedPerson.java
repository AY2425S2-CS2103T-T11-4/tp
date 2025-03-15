package seedu.finclient.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.finclient.commons.exceptions.IllegalValueException;
import seedu.finclient.model.person.Address;
import seedu.finclient.model.person.Email;
import seedu.finclient.model.person.Name;
import seedu.finclient.model.person.Person;
import seedu.finclient.model.person.Phone;
import seedu.finclient.model.person.PhoneList;
import seedu.finclient.model.person.Remark;
import seedu.finclient.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String email;
    private final String address;
    private final String remark;
    private final List<String> phones;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final boolean isHidden;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phones") List<String> phones,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("remark") String remark, @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("isHidden") boolean isHidden) {
        this.name = name;
        this.phones = (phones != null) ? new ArrayList<>(phones) : new ArrayList<>();
        this.email = email;
        this.address = address;
        this.remark = remark;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.isHidden = isHidden;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        isHidden = source.getIsHidden();
        source.setUnhidden();
        name = source.getName().fullName;
        phones = source.getPhoneList().phoneList.stream()
                .map(Phone::toString)
                .collect(Collectors.toList());
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        if (isHidden) {
            source.setHidden();
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phones == null || phones.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PhoneList"));
        }

        PhoneList modelPhoneList = new PhoneList();
        for (String phone : phones) {
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            modelPhoneList.addPhone(new Phone(phone));
        }

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);
        final boolean modelIsHidden = isHidden;

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelPhoneList, modelEmail, modelAddress, modelRemark, modelTags, modelIsHidden);

    }

}
