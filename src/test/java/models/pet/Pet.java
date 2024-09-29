package models.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @JsonProperty("name")
    @NotNull
    private String name;                // Required field

    @JsonProperty("photoUrls")
    @NotNull
    private List<String> photoUrls;

    @JsonProperty("id")
    private int id;                     // Optional field

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<Tag> tags;

    @JsonProperty("status")
    private Status status;
}