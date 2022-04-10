package com.example.UserRegistration;

import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User,Long> {

 /**
  * Responsible for finding respective user by the given email address.
  * @param email String
  * @return User
  */
 @Query("SELECT u FROM User u where u.email=?1")
 User findByEmail(String email);

}