package in.springsecurity.service.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class WhatsAppService {

    @Value("${twilio.whatsapp_number}")
    private String fromWhatsAppNumber;

    public String sendWhatsAppMessage(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + toPhoneNumber),  // To number must include "whatsapp:"
                    new PhoneNumber(fromWhatsAppNumber),           // Twilio WhatsApp number
                    messageBody
            ).create();

            return "WhatsApp message sent successfully! Message SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send WhatsApp message: " + e.getMessage();
        }
    }
}
