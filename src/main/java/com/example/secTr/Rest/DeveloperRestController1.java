package com.example.secTr.Rest;


import com.example.secTr.Model.Developer;
import com.example.secTr.Model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Controller
@RestController
@RequestMapping("/api/v1/developers")
@PreAuthorize("hasAuthority('developers:write')")
public class DeveloperRestController1 {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "qwe", "asd"),
            new Developer(2L, "asd", "zxc"),
            new Developer(3L, "zxc", "qwe")
    ).collect(Collectors.toList());


    @GetMapping
    public List<Developer> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream().filter(developer ->
                developer.getId()
                .equals(id))
                .findFirst()
                .orElse(null);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/delete/id")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
    this.DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
    }


}
