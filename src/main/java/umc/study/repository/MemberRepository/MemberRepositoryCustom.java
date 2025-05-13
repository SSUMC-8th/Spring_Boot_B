package umc.study.repository.MemberRepository;

import com.querydsl.core.Tuple;
import umc.study.domain.Member;
import umc.study.domain.Mission;

import java.util.List;

public interface MemberRepositoryCustom {
    Tuple myPage(Long memberId);
}
