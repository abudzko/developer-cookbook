package budzko.zoo.action.controller;

import budzko.zoo.action.service.UserActionService;
import budzko.zoo.action.service.dto.UserActionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
/**
 * Data is stored into partitioned table - see v2_changes_partitioned_table.sql
 */
public class UserActionController {

    private final UserActionService userActionService;

    @GetMapping("/action")
    UserActionDto getById(@RequestParam String id) {
        return userActionService.get(id);
    }

    @PostMapping("/action")
    UserActionDto save(@RequestBody UserActionDto userActionDto) {
        return userActionService.save(userActionDto);
    }
}
