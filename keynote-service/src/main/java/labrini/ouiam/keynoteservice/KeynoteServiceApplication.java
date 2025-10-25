package labrini.ouiam.keynoteservice;

import labrini.ouiam.keynoteservice.Repository.KeynoteRepository;
import labrini.ouiam.keynoteservice.entities.Keynote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeynoteServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(KeynoteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KeynoteRepository keynoteRepository){
		return args -> {
			keynoteRepository.save(Keynote.builder()
							.nom("LABRINI")
							.prenom("OUIAM")
							.email("labriniouiam2222@gmail.com")
							.fonction("Engineer")
					.build()
			);

			keynoteRepository.save(Keynote.builder()
					.nom("HAJAR")
					.prenom("ELMANSOURI")
							.email("hajar@gmail.com")
							.fonction("Developer")
					.build()
			);

			keynoteRepository.save(Keynote.builder()
					.prenom("WISSAL")
					.nom("karimi")
					.email("karimi@gmail.com")
					.fonction("Architect")
					.build()
			);
		};
	}


}
