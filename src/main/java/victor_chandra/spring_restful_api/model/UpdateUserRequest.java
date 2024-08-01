package victor_chandra.spring_restful_api.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateUserRequest {

    @Size(max = 100) //size tidak cek null atau tidak
    private String name;

    @Size(max = 100)
    private String password;
}
