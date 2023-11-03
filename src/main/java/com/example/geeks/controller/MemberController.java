package com.example.geeks.controller;

import com.example.geeks.domain.Member;
import com.example.geeks.requestDto.PasswordDto;
import com.example.geeks.requestDto.RegisterDto;
import com.example.geeks.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MemberController {
    private final MemberService memberService;

    private final BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public String register(HttpSession session) {
        Member member = Member.builder()
                .nickname((String) session.getAttribute("nickname"))
                .email((String) session.getAttribute("email"))
                .password((String) session.getAttribute("password"))
                .major((String) session.getAttribute("major"))
                .gender((int) session.getAttribute("gender"))
                .exp((int) session.getAttribute("exp"))
                .smoking((boolean) session.getAttribute("smoking"))
                .build();
        memberService.join(member);
        return "";
    }

    @GetMapping("/check/nickname")
    public String checkNickname(@RequestParam String nickname) {
        if(!memberService.availableNickname(nickname)) return "duplicate";
        return "available";
    }

    @PostMapping("/password")
    public String password(@RequestBody PasswordDto dto, HttpSession session) {
        String encodePassword = encoder.encode(dto.getPassword());
        session.setAttribute("password", encodePassword);
        return "success";
    }

    @GetMapping("/nickname")
    public String nickname(@RequestParam String nickname, HttpSession session) {
        session.setAttribute("nickname", nickname);
        return "success";
    }

    @GetMapping("/major")
    public String major(@RequestParam("major") String major,
                        @RequestParam("studentID") int studentID, HttpSession session) {
        session.setAttribute("major", major);
        session.setAttribute("studentID", studentID);
        return "success";
    }

    @GetMapping("/gender")
    public String gender(@RequestParam int gender, HttpSession session) {
        session.setAttribute("gender", gender);
        return "success";
    }

    @GetMapping("/smoking")
    public String smoking(@RequestParam boolean smoking, HttpSession session) {
        session.setAttribute("smoking", smoking);
        return "success";
    }
}
