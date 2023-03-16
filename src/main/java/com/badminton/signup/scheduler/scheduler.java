package com.badminton.signup.scheduler;

import com.badminton.signup.service.FormSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class scheduler{

    @Autowired
    private FormSignup formSignup;

    private static final String Kevin = "Kevin Wu";
    private static final String Naz = "Naz Shah";
    private static final String Done = "Done";

    private static final String[] tuesday_names = new String[]{Kevin,Naz,Done};
    private int tuesdayProcess = 0;

    private static final String[] thursday_names = new String[]{Naz,Done};
    private int thursdayProcess = 0;

    @Scheduled(cron = "${cron.check}")
    public void tuesdayCheck(){

        if(!tuesday_names[tuesdayProcess].equals(Done)){
            if(formSignup.signup(tuesday_names[tuesdayProcess], "TUES")){
                tuesdayProcess++;
                tuesdayCheck();
            }
        }

        if(!thursday_names[thursdayProcess].equals(Done)){
            if(formSignup.signup(thursday_names[thursdayProcess], "THUR")){
                thursdayProcess++;
            }
        }
    }
}
