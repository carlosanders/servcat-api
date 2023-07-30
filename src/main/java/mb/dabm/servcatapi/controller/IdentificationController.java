package mb.dabm.servcatapi.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identifications")
@Data
@AllArgsConstructor
public class IdentificationController {

    @Autowired
    IdentificationService service;

    @GetMapping("/")
    public ResponseEntity<Page<Identification>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/dto")
    public ResponseEntity<Page<IdentificationDto>> listAllDto(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findAllDto(page, size));
    }
}
