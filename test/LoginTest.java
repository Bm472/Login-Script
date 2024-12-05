import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LoginTest {

    User testUser = new User("username1234", "password", "Bradley", "Morris", (byte)25);

    //Test for SHA-256
    @Test
    void passwordHashShouldBeCorrect() {
        assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", User.hashPassword("password"));
    }

    //Test for SHA-256
    @Test
    void test1234HashShouldBeCorrect() {
        assertEquals("937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244",User.hashPassword("test1234"));
    }

    //Test to show if user is authenticated if both username and password are correct
    @Test
    void correctUserNameAndPasswordShouldLogin() {
        assertEquals(true,testUser.authenticate("username1234", "password"));
    }

    //Test to show if user is authenticated if username is incorrect but password is correct
    @Test
    void incorrectUserNameShouldNotLogin() {
        assertEquals(false,testUser.authenticate("username123", "password"));
    }

    //Test to show if user is authenticated if username is correct but password is incorrect
    @Test
    void incorrectPasswordShouldNotLogin() {
        assertEquals(false,testUser.authenticate("username1234", "passord"));
    }

    //Test to show if user is authenticated if both username and password are incorrect
    @Test
    void incorrectUsernameAndPasswordShouldNotLogin() {
        assertEquals(false,testUser.authenticate("usernam234", "paord"));
    }

}
