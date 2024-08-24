package tech.nbtsinfo.whatsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nbtsinfo.whatsapp.model.WebhookRequest;
import tech.nbtsinfo.whatsapp.service.WhatsAppService;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/messages-upsert")
    public ResponseEntity<?> handleMessage(@RequestBody WebhookRequest request) {
        return ResponseEntity.ok(whatsAppService.handleMessage(request));
    }
}