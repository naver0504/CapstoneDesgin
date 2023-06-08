package com.example.capstone.controller;


import com.example.capstone.domain.IotPlatform;
import com.example.capstone.service.IotPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IotPlatformController {

    private final IotPlatformService iotPlatformService;

    @GetMapping("/iot")
    public ResponseEntity<IotPlatform> findOne() {
        return ResponseEntity.ok(iotPlatformService.findOne());
    }

    @GetMapping("/iot/graph")
    public ResponseEntity<List<IotPlatform>> findList() {

        List<IotPlatform> iotPlatformList = iotPlatformService.findIotPlatformGraph();
        return ResponseEntity.ok(iotPlatformList);
    }






}
