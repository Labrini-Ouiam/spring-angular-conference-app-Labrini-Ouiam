package labrini.ouiam.conferenceservice;

import labrini.ouiam.conferenceservice.feign.KeynoteRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import labrini.ouiam.conferenceservice.Repository.ConferenceRepository;
import labrini.ouiam.conferenceservice.Repository.ReviewRepository;
import labrini.ouiam.conferenceservice.entities.Conference;
import labrini.ouiam.conferenceservice.entities.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class ConferenceServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConferenceServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ConferenceRepository conferenceRepository, ReviewRepository reviewRepository, KeynoteRestClient keynoteRestClient){
		return args -> {



			//Insertion de quelques conférences
			Conference c1 = Conference.builder()
					.titre("Conférence sur l'Intelligence Artificielle")
					.type("académique")
					.date(new Date())
					.duree(120)
					.nbInscrits(150)
					.score(4.7)
					.keynoteId(1L) // correspond à un keynote du keynote-service
					.build();

			Conference c2 = Conference.builder()
					.titre("Cloud Computing et Sécurité")
					.type("commerciale")
					.date(new Date())
					.duree(90)
					.nbInscrits(200)
					.score(4.3)
					.keynoteId(2L)
					.build();

			Conference c3 = Conference.builder()
					.titre("Architecture Microservices avec Spring Boot")
					.type("académique")
					.date(new Date())
					.duree(100)
					.nbInscrits(180)
					.score(4.9)
					.keynoteId(3L)
					.build();

			conferenceRepository.save(c1);
			conferenceRepository.save(c2);
			conferenceRepository.save(c3);

			// Insertion de quelques reviews pour chaque conférence
			reviewRepository.save(Review.builder()
					.date(new Date())
					.texte("Excellente présentation, très claire !")
					.stars(5)
					.conference(c1)
					.build());

			reviewRepository.save(Review.builder()
					.date(new Date())
					.texte("Très intéressant mais un peu long.")
					.stars(4)
					.conference(c1)
					.build());

			reviewRepository.save(Review.builder()
					.date(new Date())
					.texte("Bon contenu sur la sécurité dans le cloud.")
					.stars(4)
					.conference(c2)
					.build());

			reviewRepository.save(Review.builder()
					.date(new Date())
					.texte("Présentation technique et bien expliquée.")
					.stars(5)
					.conference(c3)
					.build());
		};
	}
}

