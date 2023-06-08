package com.example.capstone.controller;


import com.example.capstone.domain.IotPlatform;
import com.example.capstone.domain.IotPlatformDTO;
import com.example.capstone.service.IotPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IotPlatformController {

    private final IotPlatformService iotPlatformService;

    @GetMapping("/iot")
    public ResponseEntity<IotPlatformDTO> findOne() {
        return ResponseEntity.ok(IotPlatformDTO.EntityToDTO(iotPlatformService.findOne()));
    }

    @GetMapping("/iot/graph")
    public ResponseEntity<List<IotPlatformDTO>> findList() {

        List<IotPlatformDTO> iotPlatformList = iotPlatformService.findIotPlatformGraph().stream()
                .map(IotPlatformDTO::EntityToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(iotPlatformList);
    }






}
