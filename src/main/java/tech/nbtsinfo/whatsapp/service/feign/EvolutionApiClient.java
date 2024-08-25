package tech.nbtsinfo.whatsapp.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.nbtsinfo.whatsapp.model.request.EvolutionApiRequestBody;

@FeignClient(name = "evolutionApiClient", url = "https://api.nbtsinfo.tech")
public interface EvolutionApiClient {

    @PostMapping("/message/sendText/{instance}")
    void sendTextMessage(@PathVariable("instance") String instance,
                         @RequestBody EvolutionApiRequestBody message,
                         @RequestHeader("apikey") String apikey);
}