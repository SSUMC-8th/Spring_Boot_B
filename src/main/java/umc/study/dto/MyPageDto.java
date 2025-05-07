package umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MyPageDto {
    private Long userId;
    private String email;
    private String username;
    private String nickname;
    private String gender;
    private String phoneNumber;
    private Boolean phoneVerified;
    private BigDecimal availablePoints;
    private String profileImageUrl;
}
