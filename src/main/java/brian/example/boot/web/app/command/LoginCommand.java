package brian.example.boot.web.app.command;

import lombok.Data;

@Data
public class LoginCommand {

    private String username;
    private String password;
}
