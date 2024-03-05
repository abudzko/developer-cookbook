package budzko.zoo.action.service;

import budzko.zoo.action.mapper.UserActionMapper;
import budzko.zoo.action.repo.UserActionRepo;
import budzko.zoo.action.service.dto.UserActionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserActionService {

    private final UserActionRepo userActionRepo;
    private final UserActionMapper userActionMapper;

    public UserActionDto get(String id) {
        return userActionMapper.convert(userActionRepo.findById(id).orElseThrow());
    }

    public UserActionDto save(UserActionDto userActionDto) {
        return userActionMapper.convert(userActionRepo.save(userActionMapper.convert(userActionDto)));
    }
}
