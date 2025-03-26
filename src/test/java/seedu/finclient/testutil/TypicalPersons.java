package seedu.finclient.testutil;

import static seedu.finclient.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.finclient.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.finclient.model.FinClient;
import seedu.finclient.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withOrder("BUY 30 @ $5.50").withPhone("94351253").withTags("friends")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withRemark("").withCompany().withJob().withStockPlatform().withNetworth()
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added (Phone List)
    public static final Person TOONOMBER = new PersonBuilder().withName("Too Nomber").withPhone("8482131", "81112222")
            .withEmail("too@example.com").withAddress("chicago").build();

    public static final Person TOONOMBER_DIFF_NUMBER = new PersonBuilder().withName("Too Nomber")
            .withPhone("8482131").withEmail("too@example.com").withAddress("chicago").build();

    public static final Person TWEENOMBER = new PersonBuilder().withName("Twee Nomber")
            .withPhone("8482131", "81112222", "99990000").withEmail("too@example.com")
            .withAddress("chicago").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code FinClient} with all the typical persons.
     */
    public static FinClient getTypicalFinClient() {
        FinClient fc = new FinClient();
        for (Person person : getTypicalPersons()) {
            fc.addPerson(person);
        }
        return fc;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
