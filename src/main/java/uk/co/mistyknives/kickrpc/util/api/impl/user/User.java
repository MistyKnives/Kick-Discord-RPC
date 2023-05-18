package uk.co.mistyknives.kickrpc.util.api.impl.user;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@JsonClass(exposeAll = true)
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;

    private String username;

    @JsonProperty("agreed_to_terms")
    private boolean agreedToTerms;

    @JsonProperty("email_verified_at")
    private String emailVerifiedAt;

    private String bio, twitter, facebook, instagram, youtube, discord, tiktok;

    @JsonProperty("profile_pic")
    private String profilePicture;

    public User() {
        super();
    }
}
