package umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AvailableMissionDto {
    private Long missionId;
    private String title;
    private String description;
    private String condition;
    private String rewardType;
    private BigDecimal rewardAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal minimumPurchaseAmount;
    private Long storeId;
    private String storeName;
    private String storeCategory;
}
