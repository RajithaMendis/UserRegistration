package com.example.UserRegistration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository repo;

  @Test
  public void testCreateUser() {
    User user = new User();
    user.setEmail("rajitha@gmail.com");

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String mobileNo = "0777888999";
    String encodedMobileNo = encoder.encode(mobileNo);
    user.setMobileNo(encodedMobileNo);

    user.setFirstName("Rajitha");
    user.setLastName("mendis");

    User savedUser = repo.save(user);

    User existUser = entityManager.find(User.class, savedUser.getId());

    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

  }

   @Test
   public void testFindUserByEmail() {
     String email = "ravikumar@gmail.com";
     User user = repo.findByEmail(email);
     assertThat(user).isNotNull();
   }
}