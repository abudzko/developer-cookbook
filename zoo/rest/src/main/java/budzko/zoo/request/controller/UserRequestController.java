package budzko.zoo.request.controller;

import budzko.zoo.request.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserRequestController {
    private final UserRequestService userRequestService;

    @GetMapping("/sendRequest")
    void sendRequest(@RequestParam String userId) {
        userRequestService.sendRequest(userId);
    }
}
