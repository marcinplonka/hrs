package pl.com.bottega.hrs.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.hrs.application.CommandGateway;
import pl.com.bottega.hrs.application.users.RegisterUserCommand;
import pl.com.bottega.hrs.application.users.UpdateUserProfileCommand;

@RestController
@RequestMapping("/users")
public class UsersController {

    private CommandGateway commandGateway;

    public UsersController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public void register(@RequestBody RegisterUserCommand cmd) {
        commandGateway.execute(cmd);
    }

    @PutMapping("/{id}")
    public void updateProfile(@PathVariable Integer id, @RequestBody UpdateUserProfileCommand cmd) {
        cmd.setUserId(id);
        commandGateway.execute(cmd);
    }

}
