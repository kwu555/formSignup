package com.badminton.signup.scheduler;

import com.badminton.signup.service.FormSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class scheduler{

    @Autowired
    private FormSignup formSignup;

    private String Kevin = "Kevin Wu";
    private String Naz = "Naz Shah";
    private String Done = "Done";

    private String[] tuesday_names = new String[]{Kevin,Naz,Done};
    private int tuesday_process = 0;

    @Scheduled(cron = "${cron.tuesday.check}")
    public void tuesdayCheck(){

        if(!tuesday_names[tuesday_process].equals(Done)){
            if(formSignup.signup(tuesday_names[tuesday_process])){
                tuesday_process++;
                tuesdayCheck();
            }
        }
    }
}
