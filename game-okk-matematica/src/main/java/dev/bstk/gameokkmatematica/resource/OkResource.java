package dev.bstk.gameokkmatematica.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matematica/ok")
public class OkResource {

    @GetMapping
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("OK");
    }

}
