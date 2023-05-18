package uk.co.mistyknives.kickrpc.util.api.impl.streamer;

import co.casterlabs.rakurai.json.annotating.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import uk.co.mistyknives.kickrpc.util.api.impl.livestream.Livestream;

/**
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
@Data
@JsonClass(exposeAll = true)
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Streamer {

    public Streamer() {
        super();
    }

    private Integer id;

    private String slug;

    @JsonField("livestream")
    private Livestream livestream;
}
