package com.example.capstone.domain;


import lombok.Data;

import java.util.Date;

@Data
public class IotPlatformDTO {

    private double temp;
    private double DO;
    private Date dateTime;

    public static IotPlatformDTO EntityToDTO(IotPlatform iotPlatform) {
        IotPlatformDTO iotPlatformDTO = new IotPlatformDTO();
        iotPlatformDTO.setDO(iotPlatform.getDO());
        iotPlatformDTO.setTemp(iotPlatform.getTemp());
        iotPlatformDTO.setDateTime(iotPlatform.getDateTime());
        return iotPlatformDTO;

    }

}
