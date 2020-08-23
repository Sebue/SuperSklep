package pl.sebastian.model;

import lombok.Value;

@Value
public class User {
    String nickname;
    String password;
    Privilege privilege;
}
