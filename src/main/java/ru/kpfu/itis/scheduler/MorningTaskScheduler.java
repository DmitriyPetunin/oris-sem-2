package ru.kpfu.itis.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.config.MailConfig;
import ru.kpfu.itis.dto.RateDTO;
import ru.kpfu.itis.service.ExchangeRatesService;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class MorningTaskScheduler {
    private final UserService userService;
    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;
    private final ExchangeRatesService exchangeRatesService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void performMorningTask() throws JsonProcessingException {
        List<UserDto> userDtoList = userService.findAll();
        RateDTO rateDTO = exchangeRatesService.getPrice("USD");
        for (UserDto userDto : userDtoList) {
            if (userDto.isEnabled()) sendEmail(userDto.getEmail(), rateDTO);
        }
    }

    private void sendEmail(String mail, RateDTO rate) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String content = "today's dollar to ruble exchange rate - {rub}";
        try {
            mimeMessageHelper.setFrom(mailConfig.getFrom(), mailConfig.getSender());
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject("Exchange rate");

            content = content.replace("{rub}", rate.getRates().getRub());

            mimeMessageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
