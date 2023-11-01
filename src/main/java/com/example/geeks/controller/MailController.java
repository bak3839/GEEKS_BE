package com.example.geeks.controller;

import com.example.geeks.service.MailAuthService;
import com.example.geeks.service.MailService;
import com.example.geeks.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MailController {
    private final MailService mailService;

    private final MemberService memberService;

    private final MailAuthService emailAuthService;

    @GetMapping("/send")
    public String mailConfirm(@RequestParam String email) throws Exception{
        // 비어있으면 true 있다면 false
        boolean pass = memberService.availableEmail(email);

        if(!pass) return "duplicate";

        String code = mailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);

        emailAuthService.saveDataWithExpiration(email, code, 300);
        return code;
    }
}
