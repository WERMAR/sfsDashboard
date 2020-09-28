package com.wpmtec.buildersBoard.rest.controller.user;

import com.wpmtec.buildersBoard.entity.data.User;
import com.wpmtec.buildersBoard.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseBody()
    public List<User> loadAll() {
        return this.userService.loadAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User loadForId(@PathVariable("id") long id) {
        return this.userService.loadForId(id);
    }

    @GetMapping("/forName")
    @ResponseBody
    public List<User> loadForName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return this.userService.loadForName(firstName, lastName);
    }

    @PostMapping
    @ResponseBody
    public User create(User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping
    @ResponseBody
    public User update(User user) {
        return this.userService.saveUser(user);
    }
}
