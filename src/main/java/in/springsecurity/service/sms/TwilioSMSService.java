package in.springsecurity.service.sms;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSMSService {


        @Value("${twilio.phone_number}")
        private String fromPhoneNumber;

        public String sendSms(String toPhoneNumber, String messageBody) {
            try {
                Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber), // Receiver's phone number
                        new PhoneNumber(fromPhoneNumber), // Twilio phone number
                        messageBody
                ).create();

                return "SMS sent successfully! Message SID: " + message.getSid();
            } catch (Exception e) {
                return "Failed to send SMS: " + e.getMessage();
            }
        }
    }

