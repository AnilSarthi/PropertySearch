package com.property.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
   private String userName;
    private String passWord;
    private String emailAddress;

    public static User buildUser() {
        String firstName = "FirstName";
        return User.builder()
                .userName(firstName)
                .passWord("password1234")
                .emailAddress(firstName + "a" + System.currentTimeMillis() + "@sas.com").build();
    }
    public static User buildMyUser() {
        return User.builder()
                .userName("Anil")
                .passWord("properySearchtest")
                .emailAddress("anilsarthi@gmail.com").build();

    }

}



