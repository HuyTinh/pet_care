package com.pet_care.identity_service.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacebookUserInfor {
    String id;
    String name;
    String firstName;
    String lastName;
    String email;
    String picture;
    String gender;
}
