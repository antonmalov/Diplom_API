package burgers.models;

import com.github.javafaker.Faker;

public class UserGenerator {
    public static Faker faker = new Faker();

    public static CreateUserModel getRandomUser() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().firstName();
        return new CreateUserModel(email, password, name);
    }

    public static CreateUserModel getUserWithoutPassword() {
        String email = faker.internet().emailAddress();
        String password = "";
        String name = faker.name().firstName();
        return new CreateUserModel(email, password, name);
    }

    public static CreateUserModel getUserWithoutName() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = "";
        return new CreateUserModel(email, password, name);
    }

    public static CreateUserModel getUserWithoutEmail() {
        String email = "";
        String password = faker.internet().password();
        String name = faker.name().firstName();
        return new CreateUserModel(email, password, name);
    }
}
