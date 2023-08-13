package stellarburgers.models;

import lombok.Data;

@Data
public class CreateUserModel {

    String email, password, name;

    public CreateUserModel(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
