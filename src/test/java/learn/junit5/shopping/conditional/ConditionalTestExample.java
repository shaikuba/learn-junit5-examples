package learn.junit5.shopping.conditional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTestExample {

    //**********************
    //@Disabled
    //**********************
    @Disabled("Disabled until bug #12 has been resolved.")
    @Test
    void disable_one() {
    }

    //**********************
    //OS condition
    //**********************
    @EnabledOnOs(OS.AIX)
    @Test
    void os_one() {
    }

    @DisabledOnOs(OS.LINUX)
    @Test
    void os_two() {
    }

    //**********************
    //JRE condition
    //**********************
    @EnabledOnJre(value = JRE.JAVA_8, disabledReason = "lambda expression should be supported")
    @Test
    void jre_one() {
    }

    @DisabledOnJre(value = JRE.JAVA_8)
    @Test
    void jre_two() {
    }

    //**********************
    //System properties condition
    //**********************
    @EnabledIfSystemProperty(named = "os.arch", matches = "amd64")
    @Test
    void sys_props_one() {
    }

    @EnabledIfSystemProperties(value = {
            @EnabledIfSystemProperty(named = "os.arch", matches = "amd64")
            , @EnabledIfSystemProperty(named = "java.version", matches = "1\\.8.*")
    })
    @Test
    void sys_props_two() {
    }


    //**********************
    //Environment variables condition
    //**********************
    @EnabledIfEnvironmentVariable(named = "os.arch", matches = "amd64")
    @Test
    void env_var_one() {
    }

    @EnabledIfEnvironmentVariables(value = {
            @EnabledIfEnvironmentVariable(named = "os.arch", matches = "amd64")
            , @EnabledIfEnvironmentVariable(named = "java.version", matches = "1\\.8.*")
    })
    @Test
    void env_var_two() {
    }

    //**********************
    //custom condition
    //**********************
    @EnabledIf("userOffline")
    @Test
    void custom_one() {
    }
    boolean userOffline() {
        return false;
    }

    @DisabledIf("learn.junit5.shopping.conditional.DisabledIfs#userOffline")
    @Test
    void custom_two() {
    }

}

class DisabledIfs {
    public static boolean userOffline() {
        return true;
    }
}
