package web.command;

import org.apache.log4j.Logger;
import web.command.admin.AdminCommand;
import web.command.admin.EditBookCommand;
import web.command.admin.NewBookCommand;
import web.command.admin.NewLibrarianCommand;
import web.command.librarian.ConfirmCommand;
import web.command.librarian.LibrarianCommand;
import web.command.librarian.NotConfirmedOrders;
import web.command.reader.ReaderCommand;
import web.command.reader.ReaderOrders;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("loginTranslate", new LoginTranslateCommand());

        commands.put("admin", new AdminCommand());
        commands.put("addBook", new NewBookCommand());
        commands.put("editBook", new EditBookCommand());
        commands.put("newLibrarian", new NewLibrarianCommand());

        commands.put("reader", new ReaderCommand());
        commands.put("reader_orders", new ReaderOrders());

        commands.put("librarian", new LibrarianCommand());
        commands.put("not_confirmed_orders", new NotConfirmedOrders());
        commands.put("confirmation", new ConfirmCommand());

        commands.put("newUser", new NewUserCommand());
        commands.put("forgetPassword", new ForgetPasswordCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());

    }

    /**
     *
     * @param commandName
     * @return {@link Command}
     */
    public static Command getCommand(String commandName) {
        if (commandName == null) {
            LOG.trace("Command name is null");
            return null;
        }
        if (!commands.containsKey(commandName)) {
            LOG.trace("Command name doesn't exist");
            return null;

        }
        return commands.get(commandName);
    }

}
