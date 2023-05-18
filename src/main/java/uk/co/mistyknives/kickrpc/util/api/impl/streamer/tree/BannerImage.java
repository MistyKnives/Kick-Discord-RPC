package uk.co.mistyknives.kickrpc.util.api.impl.streamer.tree;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

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
public class BannerImage {

    public BannerImage() {
        super();
    }

    private String responsive, url;
}
