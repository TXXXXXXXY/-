package com.xinhua.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.xinhua.dao.MemberDao;
import com.xinhua.pojo.Member;
import com.xinhua.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao memberDao;
    //根据手机号查询会员
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }
    //新增会员
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }
    //根据月份统计会员数量
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for(String m : month){
            m = m + ".31";//格式：2022.01.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }
}
