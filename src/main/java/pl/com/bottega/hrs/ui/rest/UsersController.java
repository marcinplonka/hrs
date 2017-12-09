package pl.com.bottega.hrs.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.hrs.application.CommandGateway;
import pl.com.bottega.hrs.model.commands.RegisterUserCommand;

@RestController
public class UsersController {
    private CommandGateway gateway;

    public UsersController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping("/users")
    public void registerNewUser(@RequestBody RegisterUserCommand cmd) {


    }
}
