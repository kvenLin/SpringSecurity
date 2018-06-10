package net.javablog.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    private String tokenHeader = "Authorization";


    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public static final String loginname = "abcefg";
    public static final String loginpwd_raw = "111";
    public static final String loginpwd = "$2a$10$qsYvMwvld7FMGKp45AQjpun6otC8b.eFN7Be5KAr0vuEQWgT.uvgm";

    /**
     * 登录接口
     * @param username
     * @param password
     * @param device
     * @return
     */
    @PostMapping("/login")
    public Map login( String username,String password, Device device) {

        //TODO,查询用户名是否存在,若不存在则抛异常
        if (!LoginController.loginname.equals(username)) {
            //定义自己的返回用户不存在的json
            log.info("用户不存在");
            Map map = new HashMap();
            map.put("code",0);
            map.put("data","用户不存在");
            return map;
        }

        log.info("进行验证用户密码..");
        //验证根据参数username和password生成的token是否有效若有效则生成authentication
        Authentication token1 = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token1);
        log.info("验证通过..");
        //将认证通过的authentication放入容器,没有弄懂这样做有什么好处
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //重新加载security来生成userDetails
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info("加载userDetails:"+userDetails.getUsername());
        //生成token
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        // return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        HashMap<String, String> r = new HashMap<>();
        r.put("token", token);

        return r;
    }

    @RequestMapping("/test")
    public String test(){
        return "test success";
    }
}
