package stellarburgers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateResponseModel {
    User user;
    String accessToken, refreshToken;
}
