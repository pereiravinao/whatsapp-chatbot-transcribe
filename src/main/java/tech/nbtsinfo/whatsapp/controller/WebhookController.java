package tech.nbtsinfo.whatsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nbtsinfo.whatsapp.model.WebhookEvolution;
import tech.nbtsinfo.whatsapp.parameter.WebhookEvolutionParameter;
import tech.nbtsinfo.whatsapp.service.WhatsAppService;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/messages-upsert")
    public ResponseEntity<?> handleMessage(@RequestBody WebhookEvolutionParameter request) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        WebhookEvolution webhookEvolution = request.toModel();
        this.whatsAppService.handleMessage(webhookEvolution);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}