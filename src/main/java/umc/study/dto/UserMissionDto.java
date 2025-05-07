package umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserMissionDto {
    private Long umId;
    private String missionTitle;
    private String storeName;
    private Integer pointsAwarded;
    private Boolean hasReview;
    private String status;
    private Long missionId;
    private Long storeId;
}
