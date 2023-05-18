package uk.co.mistyknives.kickrpc.util.api.impl.livestream.tree;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class LivestreamCategory {

    public LivestreamCategory() {
        super();
    }

    private Integer id;

    @JsonProperty("category_id")
    private Integer categoryId;

    private String name, slug;

    private String[] tags;

    private String description;

    @JsonProperty("deleted_at")
    private String deletedAt;

    private Integer viewers;
}
