package umc.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.service.MissionService.MissionService;
import umc.study.service.ShopService.ShopQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			*//*ShopQueryService storeService = context.getBean(ShopQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findShopsByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findShopsByNameAndScore(name, score)
					.forEach(System.out::println);*//*

			*//*MissionService missionService = context.getBean(MissionService.class);

			Long memberId = 2L;

			System.out.println("Find Progress mission And Completed mission:");
			System.out.println("MemberId: " + memberId);

			missionService.getAllMissions(memberId)
					.forEach(System.out::println);*//*

			*//*MemberService memberService = context.getBean(MemberService.class);

			memberService.myPage(4L);*//*

			*//*
			MissionService missionService = context.getBean(MissionService.class);

			System.out.println("Find Missions From Selected Region");

			missionService.missionsByRegions("부산")
					.forEach(System.out::println);

			 *//*
		};
	}*/
}
