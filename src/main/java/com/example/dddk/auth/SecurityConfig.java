package com.example.dddk.auth;

import com.example.dddk.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", // 권한 관리 대상을 지정하는 옵션
                        "/js/**", "/h2-console/**").permitAll()
                // "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.
                        USER.name())
                // /api/v1/**"주소를 가진 API는 USER 권한을 가진 사람만 가능하도록
                .anyRequest().authenticated()
                // 설정된 값들 이외 나머지 URL들을 나타냅니다.
                // 여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공 시 이동하게 되는 주소
                .and()
                .oauth2Login() // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService)
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                .and()
                .defaultSuccessUrl("/inputDetail",true);
    }
}