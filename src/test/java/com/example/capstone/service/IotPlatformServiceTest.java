package com.example.capstone.service;

import com.example.capstone.domain.IotPlatform;
import com.example.capstone.domain.IotPlatformDTO;
import com.example.capstone.repository.IotPlatformRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IotPlatformServiceTest {

    @Autowired
    IotPlatformService iotPlatformService;
    @Autowired
    IotPlatformRepository iotPlatformRepository;

    @Test
    public void serviceBetween() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        for (int i = 0; i <= 12; i++) {
            Date time = cal.getTime();
            IotPlatform iotPlatform = new IotPlatform();

            iotPlatform.setTemp(i);
            iotPlatform.setDO(i);
            iotPlatform.setDateTime(time);

            iotPlatformRepository.save(iotPlatform);
            cal.add(Calendar.MINUTE, -60);
        }

        List<IotPlatform> iotPlatformList = iotPlatformService.findIotPlatformGraph();
        System.out.println("iotPlatformList = " + iotPlatformList);
    }
    @Test
    public void serviceBetweenV2() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        for (int i = 0; i <= 48; i++) {
            Date time = cal.getTime();
            IotPlatform iotPlatform = new IotPlatform();

            iotPlatform.setTemp(i);
            iotPlatform.setDO(i);
            iotPlatform.setDateTime(time);

            iotPlatformRepository.save(iotPlatform);
            cal.add(Calendar.MINUTE, -15);
        }

        List<IotPlatform> iotPlatformList = iotPlatformService.findIotPlatformGraph();
        Assertions.assertThat(iotPlatformList.size()).isEqualTo(13);

    }

    @Test
    public void CanlenderTest() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        for (int i = 0; i <= 48; i++) {
            Date time = cal.getTime();
            IotPlatform iotPlatform = new IotPlatform();

            iotPlatform.setTemp(i);
            iotPlatform.setDO(i);
            iotPlatform.setDateTime(time);

            iotPlatformRepository.save(iotPlatform);
            cal.add(Calendar.MINUTE, -15);
        }


        List<IotPlatformDTO> iotPlatformList = iotPlatformService.findIotPlatformGraph().stream()
                .map(IotPlatformDTO::EntityToDTO)
                .collect(Collectors.toList());

        for (IotPlatformDTO iotPlatformDTO : iotPlatformList) {
            System.out.println("iotPlatformDTO = " + iotPlatformDTO);
        }

    }
}