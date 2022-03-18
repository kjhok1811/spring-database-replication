package com.example.replication.controller;

import com.example.replication.service.ReplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplicationController {
    private final ReplicationService replicationService;

    @GetMapping("/read")
    public void read() {
        replicationService.read();
    }

    @GetMapping("/write")
    public void write() {
        replicationService.write();
    }
}
