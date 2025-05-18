package umc.study.converter;

import umc.study.domain.Foods;
import umc.study.domain.mapping.MemberSelectFoods;

import java.util.List;
import java.util.stream.Collectors;

public class MemberSelectFoodsConverter {

    public static List<MemberSelectFoods> toMemberSelectFoodsList(List<Foods> foodsList) {
        return foodsList.stream()
                .map(foods ->
                        MemberSelectFoods.builder()
                                .foods(foods)
                                .build()
                ).collect(Collectors.toList());
    }
}
