package com.shuangyangad.service.admin;

import com.shuangyangad.service.admin.utils.GenerateUserIdUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ServiceAdminApplication.class)
class ServiceAdminApplicationTests {


    String createUser = "mutation {\n" +
            "  addSystemUser(\n" +
            "    input: {\n" +
            "      userId: \"ddc8cbc78922ac63af10abd2a4a15339\"\n" +
            "      username: \"system\"\n" +
            "      password: \"$2a$10$fUkxk9JgMrCa/3krYZjhLO3NsGGu.SEmyAVeTL/vEVbn99atcKTUC\"\n" +
            "      nickname: \"系统用户0\"\n" +
            "      avatar: \"https://xxx.png\"\n" +
            "    }\n" +
            "  ) {\n" +
            "    numUids\n" +
            "  }\n" +
            "}\n";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String system = passwordEncoder.encode("system");
        System.out.println(system);

    }

    @Test
    public void passwordEquals() {
        String userId = GenerateUserIdUtils.getUserId();

        System.out.println(userId);
        String system1 = passwordEncoder.encode("system");
        String system2 = passwordEncoder.encode("system");
        System.out.println(system1);
        System.out.println(system2);
        System.out.println(passwordEncoder.matches(system2, "system"));
    }
}
