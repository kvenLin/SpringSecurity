package net.javablog.service;

import net.javablog.security.JwtUser;
import net.javablog.security.LoginController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    //TODO,注入自己的dao进行user的查询
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//     User user = userRepository.findByUsername(username);
        //TODO,从数据库查询username,如果不存在则抛出异常
        if (!LoginController.loginname.equals(username)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            //TODO,若存在则返回userDetails对象
            return new JwtUser(username);
//            return JwtUserFactory.create(user);
        }

    }
}
