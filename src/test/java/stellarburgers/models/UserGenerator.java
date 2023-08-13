package stellarburgers.models;

import com.github.javafaker.Faker;

public class UserGenerator {


    public static CreateUserModel getRandomUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().firstName();
        return new CreateUserModel(email, password, name);

    }
}
