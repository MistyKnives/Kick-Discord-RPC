package uk.co.mistyknives.kickrpc.util.api.impl.livestream;

import co.casterlabs.rakurai.json.annotating.*;
import co.casterlabs.rakurai.json.element.JsonArray;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import co.casterlabs.rakurai.json.element.JsonObject;
import uk.co.mistyknives.kickrpc.util.api.impl.livestream.tree.LivestreamCategory;
import uk.co.mistyknives.kickrpc.util.api.impl.streamer.tree.BannerImage;

import java.util.Set;

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
public class Livestream {

    public Livestream() {
        super();
    }

    private Integer id;

    private String slug;

    @JsonField("channel_id")
    private Integer channelId;

    @JsonField("session_title")
    private String sessionTitle;

    @JsonField("is_live")
    private boolean isLive;

    private Integer viewers;

    private Set<LivestreamCategory> categories;

    private BannerImage thumbnail;
}
