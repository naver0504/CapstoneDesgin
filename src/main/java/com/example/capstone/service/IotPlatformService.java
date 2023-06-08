package com.example.capstone.service;

import com.example.capstone.domain.IotPlatform;
import com.example.capstone.repository.IotPlatformRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IotPlatformService {

    private final IotPlatformRepository iotPlatformRepository;
    private final EntityManager em;


    @Transactional
    public void save(IotPlatform iotPlatform) {
        iotPlatformRepository.save(iotPlatform);
    }

    public IotPlatform findOne() {
        return iotPlatformRepository.findTop1ByOrderByDateTimeDesc();
    }

    public List<IotPlatform> findIotPlatformGraph() {
        IotPlatform iot = iotPlatformRepository.findTop1ByOrderByDateTimeDesc();
        Date now = iot.getDateTime();
        List<IotPlatform> iotPlatformList = new ArrayList<>();
        iotPlatformList.add(iot);
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        for (int i = 1; i <= 12; i++) {
            cal.add(Calendar.MINUTE, -60 -1);
            Date startTime = cal.getTime();
            cal.add(Calendar.MINUTE, +2);
            Date endTime = cal.getTime();
            IotPlatform foundIot = iotPlatformRepository.findByDateTimeBetween(startTime, endTime);
            iotPlatformList.add(foundIot);
            cal.add(Calendar.MINUTE, -1);

        }
        return iotPlatformList;

    }




}
