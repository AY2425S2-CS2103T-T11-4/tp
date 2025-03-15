package seedu.finclient.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.finclient.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.finclient.commons.core.index.Index;
import seedu.finclient.commons.exceptions.IllegalValueException;
import seedu.finclient.logic.commands.RemarkCommand;
import seedu.finclient.logic.parser.exceptions.ParseException;
import seedu.finclient.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, new Remark(remark));
    }
}
