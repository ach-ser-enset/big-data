package com.example.clickconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClickCountController {
    @Autowired
    private ClickCountService clickCountService;

    @GetMapping("/clicks/count")
    public long getClickCount() {
        return clickCountService.getTotalClicks();
    }
}
