package com.example.fastcampusmysql.domain.member.serivce;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long id){
        var member =  memberRepository.findbyId(id).get();
        return toDto(member);
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId){
        return memberNicknameHistoryRepository.findAllByMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public MemberDto toDto(Member member){

        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    public MemberNicknameHistoryDto toDto(MemberNicknameHistory memberNicknameHistory){
        return new MemberNicknameHistoryDto(
                memberNicknameHistory.getId()
                , memberNicknameHistory.getMemberId()
                , memberNicknameHistory.getNickname()
                ,memberNicknameHistory.getCreatedAt()
        );
    }
}

