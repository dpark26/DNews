import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class DNews {
    public static String pre = "#";
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault("OTQ3MTkzODcxNjI3MDc1NTg0.YhptCQ.suSkS9rB5GrvtXNfcid5b6qur30").build();
        jda.getPresence().setActivity(Activity.watching("BBC News"));

        jda.addEventListener(new Commands());
    }
}