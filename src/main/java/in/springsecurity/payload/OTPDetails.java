package in.springsecurity.payload;

public class OTPDetails {
    private final String Otp;
    private final long timeStamp;

    public OTPDetails(String otp, long timeStamp) {
        Otp = otp;
        this.timeStamp = timeStamp;
    }

    public String getOtp() {
        return Otp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
