package com.goorm.devlink.matchingservice.controller;


import com.goorm.devlink.matchingservice.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping
    public ResponseEntity<List<String>> getAddressList(@RequestParam String location){
        if( location.isEmpty() ){ throw new NoSuchElementException(); }
        List<String> addressList = matchingService.getAddressList(location);
    }






}
