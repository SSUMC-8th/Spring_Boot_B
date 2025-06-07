package umc.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDTO {
        @NotBlank
        String name;
        @NotBlank
        @Email
        String email; //이메일 필드 추가
        @NotBlank
        String password;  //비밀번호 필드 추가
        @NotNull
        Integer gender;
        @NotNull
        Integer age;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 2, max = 20)
        String address;
        @NotNull
        Role role;  //역할 필드 추가
        @ExistCategories
        List<Long> preferCategory;
    }
}
