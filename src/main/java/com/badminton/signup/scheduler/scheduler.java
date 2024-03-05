package com.badminton.signup.scheduler;

import com.badminton.signup.service.FormSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@EnableAsync
public class scheduler{

    @Autowired
    private FormSignup formSignup;

    private static final String Kevin = "Kevin Wu";
    private static final String Naz = "Naz Shah";
    private static final String Done = "Done";

    private static final String[] tuesday_names = new String[]{Naz,Done};
    private int tuesdayProcess = 0;

    private static final String[] thursday_names = new String[]{Naz,Done};
//    private int thursdayProcess = 1;

//    @Scheduled(cron = "* 0/2 * * * *")
    @Scheduled(fixedRate = 120000)
    public void tuesdayCheck() throws InterruptedException {

        if(!tuesday_names[tuesdayProcess].equals(Done)){
            if(formSignup.signup(tuesday_names[tuesdayProcess], "TUES")){
                tuesdayProcess++;
            }
        }

//        if(!thursday_names[thursdayProcess].equals(Done)){
//            if(formSignup.signup(thursday_names[thursdayProcess], "THUR")){
//                thursdayProcess++;
//            }
//        }
        System.out.println("Work done, next run will be " + LocalDateTime.now().plusMinutes(2).toString()
                + " Tuesday - " + tuesday_names[tuesdayProcess]);
    }
}
