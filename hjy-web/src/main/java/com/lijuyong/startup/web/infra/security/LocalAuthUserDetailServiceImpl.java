package com.lijuyong.startup.web.infra.security;
import com.lijuyong.startup.entity.AdminDO;
import com.lijuyong.startup.entity.MemberDO;
import com.lijuyong.startup.repository.AdminRespository;
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
    AdminRespository adminRespository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AdminDO adminDO = adminRespository.findByName(username);
        if( adminDO != null ){
            LocalAuthUser localAuthUser =  new LocalAuthUser(adminDO.getName(),
                    adminDO.getPassword(),
                    0);
            localAuthUser.setCompanyName(adminDO.getCompanyName());
            localAuthUser.setCompanyCode(adminDO.getCompanyCode());
            return  localAuthUser;


        }
        throw new UsernameNotFoundException(String.format("No user found with username '%s'.",
                    username));

    }
}
