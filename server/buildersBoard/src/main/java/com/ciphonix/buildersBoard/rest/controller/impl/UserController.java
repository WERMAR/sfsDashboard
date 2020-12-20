package com.ciphonix.buildersBoard.rest.controller.impl;

import com.ciphonix.buildersBoard.data.entity.User;
import com.ciphonix.buildersBoard.rest.data.UserRestData;
import com.ciphonix.buildersBoard.services.UserService;
import com.ciphonix.buildersBoard.util.ExceptionMessages;
import com.ciphonix.buildersBoard.util.StreamUtilFactory;
import com.ciphonix.buildersBoard.util.converter.UserConverter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public List<User> loadAll() {
        return StreamUtilFactory.generateStream(this.userService.loadAll()).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User loadForId(@PathVariable("id") long id) throws NotFoundException {
        Optional<User> user = this.userService.loadForId(id);
        if (!user.isPresent())
            throw new NotFoundException(String.format(ExceptionMessages.USER_NOT_FOUND_EXEC, id));
        return user.get();
    }

    @GetMapping("/name")
    @ResponseBody
    public User loadForName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return this.userService.loadForName(firstName, lastName);
    }

    @PostMapping
    @ResponseBody
    public UserRestData create(@RequestBody UserRestData userRestData) {
        return UserConverter.toRest(this.userService.saveUser(UserConverter.toDB(userRestData)));
    }

    @PutMapping
    @ResponseBody
    public UserRestData update(@RequestBody UserRestData userRestData) {
        return UserConverter.toRest(this.userService.saveUser(UserConverter.toDB(userRestData)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        this.userService.delete(Long.parseLong(id));
    }
}
