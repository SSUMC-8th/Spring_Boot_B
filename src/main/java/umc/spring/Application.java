package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context){
		return args -> {
//			MemberRepository memberRepository = context.getBean(MemberRepository.class);
//			MemberMissionRepository memberMissionRepository = context.getBean(MemberMissionRepository.class);
//			String regionName = "서울";
//			Long userId = 2L;
//
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//
//			System.out.println(memberRepository.getMypageInfo(userId));
//			memberMissionRepository.findPossibleMissions(regionName, userId,10L,10).forEach(System.out::println);
//			Integer i = memberMissionRepository.regionMissionCount(regionName, userId);
//			System.out.println(i);
		};
	}
}
