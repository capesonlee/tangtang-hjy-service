package com.lijuyong.startup.web.infra.security;

import com.fasterxml.classmate.MemberResolver;
import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/4/18.
 */
@Service
public class LocalAuthUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        MemberDO memberDo = memberRepository.findByLoginName(username);
        if( memberDo != null ){
            return new LocalAuthUser(memberDo.getLoginName(),
                    memberDo.getLoginPassword(),
                    memberDo.getId());

        }
        throw new UsernameNotFoundException(String.format("No user found with username '%s'.",
                    username));

    }
}
