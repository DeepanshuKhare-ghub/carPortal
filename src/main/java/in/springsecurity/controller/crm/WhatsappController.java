package in.springsecurity.controller.crm;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.service.sms.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.WHATSAPP_URL)
public class WhatsappController {


    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping(ApiEndpoints.WHATSAPP_SMS_URL)
    public String sendWhatsAppMessage(@RequestParam String to, @RequestParam String message) {
        return whatsAppService.sendWhatsAppMessage(to, message);
    }
}
