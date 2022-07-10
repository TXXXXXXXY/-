package com.xinhua.service;

import com.xinhua.pojo.Member;

import java.util.List;

public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);
    public List<Integer> findMemberCountByMonth(List<String> month);
}
