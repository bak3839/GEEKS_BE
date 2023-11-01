package com.example.geeks.service;

import com.example.geeks.domain.Member;
import com.example.geeks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean availableEmail(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }
}
