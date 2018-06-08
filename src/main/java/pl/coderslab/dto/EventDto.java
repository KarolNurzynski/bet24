package pl.coderslab.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Data transfer object for communication between external event REST service delivering data in JSON
 * (represented by {@link pl.coderslab.event24.Event24Controller}) with {@link pl.coderslab.entity.Event}
 *  entities of this application
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("teamA")
    private String teamA;

    @JsonProperty("teamB")
    private String teamB;

    @JsonProperty("scoreA")
    private int scoreA;

    @JsonProperty("scoreB")
    private int scoreB;

    @JsonProperty("strengthA")
    private int strengthA;

    @JsonProperty("strengthB")
    private int strengthB;

    @JsonProperty("startDate")
    private LocalDateTime startDate;

    @JsonProperty("timeLeft")
    private LocalTime timeLeft;


}
