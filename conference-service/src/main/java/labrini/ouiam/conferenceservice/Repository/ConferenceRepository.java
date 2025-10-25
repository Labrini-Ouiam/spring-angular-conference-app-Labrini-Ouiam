package labrini.ouiam.conferenceservice.Repository;

import labrini.ouiam.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
