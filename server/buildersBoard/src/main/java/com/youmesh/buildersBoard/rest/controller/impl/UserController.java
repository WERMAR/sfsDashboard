package com.youmesh.buildersBoard.rest.controller.impl;

import com.youmesh.buildersBoard.data.entity.User;
import com.youmesh.buildersBoard.rest.data.UserRestData;
import com.youmesh.buildersBoard.services.UserService;
import com.youmesh.buildersBoard.util.ExceptionMessages;
import com.youmesh.buildersBoard.util.StreamUtilFactory;
import com.youmesh.buildersBoard.util.converter.UserConverter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h3>Description</h3>
 * This UserController is the specific Rest-Controller for the Entity {@link User}
 *
 * @author wermar
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * <h3>Description</h3>
     * Default Constructor, which is used from the Spring-Context. Both parameter will be set from the Spring-Context.
     *
     * @param userService - {@link UserService} instance to handle logical calls to the underlying tier
     * @see UserService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <h3>Description</h3>
     * returns a List of {@link UserRestData}. This method call the logical tier ({@link UserService}) to load all
     * existing {@link User} from the datasource. For an easier processing of the
     * json on the client side, the base object ({@link User}) will be converted to the TransferObject {@link UserRestData}
     *
     * @return List of {@link UserRestData}
     * @see User
     * @see UserRestData
     * @see UserService#loadAll()
     * @see UserConverter#toRest(User)
     * @see StreamUtilFactory#generateStream(Iterable)
     */
    @GetMapping
    @ResponseBody
    public List<UserRestData> loadAll() {
        return StreamUtilFactory.generateStream(this.userService.loadAll()).map(UserConverter::toRest).collect(Collectors.toList());
    }

    /**
     * <h3>Description</h3>
     * returns the concrete {@link UserRestData} Object fits to the submitted id.
     * <p/>
     * The logical tier try to find a object in the datasource for the submitted id.
     * Is this finished, the {@link User} Object will be converted to the {@link UserRestData}
     *
     * @param id - submitted id of the searched item
     * @return Converted {@link UserRestData} match to the searched item
     * @throws NotFoundException - If the System were not able to find a data record for the given id
     * @see User
     * @see UserRestData@see UserService#loadForId(Long)
     * @see UserConverter#toRest(User)
     */
    @GetMapping("/{id}")
    @ResponseBody
    public UserRestData loadForId(@PathVariable("id") long id) throws NotFoundException {
        Optional<User> user = this.userService.loadForId(id);
        if (!user.isPresent())
            throw new NotFoundException(String.format(ExceptionMessages.USER_NOT_FOUND_EXEC, id));
        return UserConverter.toRest(user.get());
    }

    /**
     * <h3>Description</h3>
     * returns the concrete {@link UserRestData} Object fits to the submitted <strong>firstName-Param</strong> & <strong>lastName-Param</strong>
     * <p/>
     * The logical tier try to find a object in the datasource for the submitted firstName and lastName. Did the System find a match,
     * it converted from the {@link User} Object to the {@link UserRestData}.
     *
     * @param firstName - submitted firstName of the searched user
     * @param lastName  - submitted lastName of the searched user
     * @return Converted {@link UserRestData} match to the submitted firstName-Param and lastName-Param
     * @see User
     * @see UserRestData
     * @see UserService#loadForName(String, String)
     * @see UserConverter#toRest(User)
     */
    @GetMapping("/name")
    @ResponseBody
    public UserRestData loadForName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return UserConverter.toRest(this.userService.loadForName(firstName, lastName));
    }

    /**
     * <h3>Description</h3>
     * returns the created object as {@link UserRestData}
     * <p/>
     * This Method save the submitted data in the datasource. First step before the logical tier ist called for the system, the
     * submitted data will be validate. If this okay, the logical tier will be called and save the data. Afterwards, the returned Object is
     * converted to {@link UserRestData}
     *
     * @param userRestData - submitted data which should be saved from the system.
     * @return saved object after converted to the TransferObject {@link UserRestData}
     * @see User
     * @see UserRestData
     * @see UserService#saveUser(User)
     * @see UserConverter#toRest(User)
     */
    @PostMapping
    @ResponseBody
    public UserRestData create(@RequestBody UserRestData userRestData) {
        return UserConverter.toRest(this.userService.saveUser(UserConverter.toDB(userRestData)));
    }

    /**
     * <h3>Description</h3>
     * returns the updated object after processing.
     * <p/>
     * Logical tier update the submitted object. Before calling the logical tier method, the submitted data is converted from
     * {@link UserRestData} to {@link User}.
     * After performing the update, the returned entity is converted back again to the TransferObject and returned to the requested client.
     *
     * @param userRestData - submitted data, which should updated
     * @return updated {@link UserRestData} object
     * @see User
     * @see UserRestData
     * @see UserConverter#toDB(UserRestData)
     * @see UserConverter#toRest(User)
     * @see UserService#saveUser(User)
     */
    @PutMapping
    @ResponseBody
    public UserRestData update(@RequestBody UserRestData userRestData) {
        return UserConverter.toRest(this.userService.saveUser(UserConverter.toDB(userRestData)));
    }

    /**
     * <h3>Description</h3>
     * Performing the delete operation for the submitted id. If an data record for the submitted id is not founded, a {@link NotFoundException} will be thrown.
     *
     * @param id - id of the data record which should be deleted
     * @throws NotFoundException - if the data record for the submitted id is not founded.
     * @see UserService#delete(long)
     * @see NotFoundException
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        this.userService.delete(Long.parseLong(id));
    }
}
