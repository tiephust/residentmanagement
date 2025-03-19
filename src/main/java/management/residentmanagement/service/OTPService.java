package management.residentmanagement.service;

import management.residentmanagement.entity.OTP;
import management.residentmanagement.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    // Tạo OTP và lưu vào database
    public String generateOTP(String phone) {
        // Tạo mã OTP ngẫu nhiên (6 chữ số)
        Random random = new Random();
        String otpCode = String.format("%06d", random.nextInt(999999));

        // Tính thời gian hết hạn (ví dụ: 5 phút sau)
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        // Lưu OTP vào database
        OTP otp = new OTP();
        otp.setPhone(phone);
        otp.setCode(otpCode);
        otp.setExpiryTime(expiryTime);
        otpRepository.save(otp);

        // Gửi OTP đến số điện thoại (giả lập)
        sendOTPToPhone(phone, otpCode);

        return otpCode;
    }

    // Xác thực OTP
    public boolean validateOTP(String Phone, String otpCode) {
        Optional<OTP> otpOptional = otpRepository.findByPhoneAndCode(Phone, otpCode);
        if (otpOptional.isPresent()) {
            OTP otp = otpOptional.get();
            // Kiểm tra xem OTP còn hiệu lực không
            if (LocalDateTime.now().isBefore(otp.getExpiryTime())) {
                return true;
            }
        }
        return false;
    }

    // Giả lập gửi OTP đến số điện thoại
    private void sendOTPToPhone(String Phone, String otpCode) {
        System.out.println("Gửi OTP đến số điện thoại " + Phone + ": " + otpCode);
        // Ở đây bạn có thể tích hợp với dịch vụ SMS như Twilio, Nexmo, v.v.
    }
}