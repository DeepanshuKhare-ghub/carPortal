package in.springsecurity.controller.crm;


import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.service.sms.TwilioSMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.SMS_URL)
public class SMSController {


        @Autowired
        private TwilioSMSService twilioSMSService;

        @PostMapping(ApiEndpoints.SMS_USER_URL)
        public String sendSms(@RequestParam String to, @RequestParam String message) {
            return twilioSMSService.sendSms(to, message);
        }
    }
