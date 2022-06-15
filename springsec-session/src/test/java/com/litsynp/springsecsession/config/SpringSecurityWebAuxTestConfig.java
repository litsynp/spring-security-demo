package com.litsynp.springsecsession.config;

import com.litsynp.springsecsession.domain.auth.vo.UserDetailsVo;
import com.litsynp.springsecsession.domain.member.domain.Member;
import com.litsynp.springsecsession.domain.member.domain.RoleType;
import com.litsynp.springsecsession.util.FieldUtil;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    private static Member basicMember;
    private static Member adminMember;

    public static Member getBasicMember() {
        return basicMember;
    }

    public static Member getAdminMember() {
        return adminMember;
    }

    @PostConstruct
    void init() {
        basicMember = new Member("testuser@example.com", "12345678", RoleType.USER);
        FieldUtil.writeField(basicMember, "id", 1L);

        adminMember = new Member("admin@example.com", "12345678", RoleType.ADMIN);
        FieldUtil.writeField(basicMember, "id", 2L);
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetailsVo basicMemberDetails = new UserDetailsVo(basicMember);
        UserDetailsVo adminMemberDetails = new UserDetailsVo(adminMember);

        return new InMemoryUserDetailsManager(
                Arrays.asList(basicMemberDetails, adminMemberDetails));
    }
}
