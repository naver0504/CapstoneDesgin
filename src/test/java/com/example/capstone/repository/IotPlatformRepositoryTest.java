package com.example.capstone.repository;

import com.example.capstone.domain.IotPlatform;
import com.example.capstone.service.IotPlatformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@Transactional
class IotPlatformRepositoryTest {

    @Autowired
    IotPlatformRepository iotPlatformRepository;
    @Autowired
    IotPlatformService iotPlatformService;

    @Test
    public void test() {
        IotPlatform iotPlatform1 = new IotPlatform();
        iotPlatform1.setDO(10);
        iotPlatform1.setDateTime(new Date());
        iotPlatform1.setTemp(20);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 5);
        Date time = cal.getTime();

        IotPlatform iotPlatform2 = new IotPlatform();
        iotPlatform2.setDO(15);
        iotPlatform2.setDateTime(time);
        iotPlatform2.setTemp(23);

        iotPlatformRepository.save(iotPlatform1);
        iotPlatformRepository.save(iotPlatform2);

        IotPlatform recentIotPlatform = iotPlatformRepository.findTop1ByOrderByDateTimeDesc();
        System.out.println("recentIotPlatform = " + recentIotPlatform);
    }

    @Test
    public void between() {
        IotPlatform iotPlatform1 = new IotPlatform();
        iotPlatform1.setDO(10);
        iotPlatform1.setDateTime(new Date());
        iotPlatform1.setTemp(20);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 5);
        Date time = cal.getTime();

        cal.add(Calendar.MINUTE, -6);
        Date startTime = cal.getTime();
        cal.add(Calendar.MINUTE, 5);
        Date endTIme = cal.getTime();

        IotPlatform iotPlatform2 = new IotPlatform();
        iotPlatform2.setDO(15);
        iotPlatform2.setDateTime(time);
        iotPlatform2.setTemp(23);

        iotPlatformRepository.save(iotPlatform1);
        iotPlatformRepository.save(iotPlatform2);

        IotPlatform byDateTimeBetween = iotPlatformRepository.findByDateTimeBetween(startTime, endTIme);
        System.out.println("byDateTimeBetween = " + byDateTimeBetween);

    }


}