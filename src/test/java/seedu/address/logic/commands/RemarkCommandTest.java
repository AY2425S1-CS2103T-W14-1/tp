package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RemarkCommandTest {
    private static final String MESSAGE_NOT_IMPLEMENTED_YET = "Command is not implemented yet" ;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(new RemarkCommand(), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }
}