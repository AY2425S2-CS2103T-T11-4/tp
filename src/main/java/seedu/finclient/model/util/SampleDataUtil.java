package seedu.finclient.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.finclient.model.FinClient;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.person.Address;
import seedu.finclient.model.person.Email;
import seedu.finclient.model.person.Name;
import seedu.finclient.model.person.Person;
import seedu.finclient.model.person.Phone;
import seedu.finclient.model.person.PhoneList;
import seedu.finclient.model.person.Remark;
import seedu.finclient.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FinClient} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), getPhoneList("87438807", "91234567"),
                        new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                        getTagSet("friends")),

            new Person(new Name("Bernice Yu"), getPhoneList("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                        getTagSet("colleagues", "friends")),

            new Person(new Name("Charlotte Oliveiro"), getPhoneList("93210283", "81234567"),
                        new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                        getTagSet("neighbours")),

            new Person(new Name("David Li"), getPhoneList("91031282"),
                        new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                        getTagSet("family")),

            new Person(new Name("Irfan Ibrahim"), getPhoneList("92492021", "82345678"),
                        new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                        getTagSet("classmates")),

            new Person(new Name("Roy Balakrishnan"), getPhoneList("92624417"),
                        new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyFinClient getSampleFinClient() {
        FinClient sampleFC = new FinClient();
        for (Person samplePerson : getSamplePersons()) {
            sampleFC.addPerson(samplePerson);
        }
        return sampleFC;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a {@code PhoneList} containing the list of phone numbers given.
     */
    public static PhoneList getPhoneList(String... phoneNumbers) {
        ArrayList<Phone> phoneArrayList = new ArrayList<>();
        for (String phone : phoneNumbers) {
            phoneArrayList.add(new Phone(phone));
        }
        return new PhoneList(phoneArrayList);
    }
}
