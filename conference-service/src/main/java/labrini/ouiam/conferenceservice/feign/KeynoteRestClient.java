package labrini.ouiam.conferenceservice.feign;

import labrini.ouiam.conferenceservice.model.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {
    @GetMapping("/keynotes/{id}")
    Keynote getKeynoteById(@PathVariable("id") Long id);

}
