package mb.dabm.servcatapi.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.ServcatApiApplication;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.projection.IdentificationDTO;
import mb.dabm.servcatapi.service.IdentificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    /**
     * consulta Derived Query methods da Interface do JpaRepository - único objeto
     * @example: http://localhost:8080/identifications/
     * @param page
     * @param size
     * @return ResponseEntity<Page<Identification>>
     */
    @GetMapping("/")
    public ResponseEntity<Page<Identification>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {

        return ResponseEntity.ok(service.findAll(page, size));
    }

    /**
     * consulta Derived Query methods da Interface do JpaRepository - único objeto
     * @example: http://localhost:8080/identifications/37014
     * @param id
     * @return ResponseEntity<Identification>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retorna um único objeto do Seg A, de acordo com a chave passada no Path")
    public ResponseEntity<Identification> get(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Example 1 - Java DTO with JPQL
     * @example: http://localhost:8080/identifications/dto?page=2&size=50
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/dto")
    public ResponseEntity<Page<IdentificationDto>> listAllDto(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.getAllIdentificationDto(page, size));
    }

    /**
     * Example 2 - Java DTO with JPQL
     * @example: http://localhost:8080/identifications/inc/03988?page=1&size=30
     * @param inc
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/inc/{inc}")
    public ResponseEntity<Page<IdentificationDto>> listAllDtoByInc(
        @PathVariable("inc") String inc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.getAllIdentificationDtoByInc(inc, page, size));
    }

    /**
     * Example 3 - get IdentificationDto by FSC - projection em JAVA Record - sem SQL
     * @example: http://localhost:8080/identifications/fsc/5935
     * @param fsc
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/fsc/{fsc}")
    public ResponseEntity<Page<IdentificationDto>> listAllDtoByFsc(
        @PathVariable("fsc") String fsc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.getAllIdentificationDtoByFsc(fsc, page, size));
    }

    /**
     * Examplo 4 - consulta com query - Native e Projection Interface
     * @example: http://localhost:8080/identifications/niin/200003701
     * @param niin
     * @return ResponseEntity<IdentificationDTO>
     */
    @GetMapping("/niin/{niin}")
    @Operation(summary = "Retorna um único objeto do Seg A, de acordo com a chave passada no Path - NIIN")
    public ResponseEntity<IdentificationDTO> getNiin(
        @PathVariable("niin") String niin
    ) {
        return ResponseEntity.ok(service.findByNiin(niin));
    }

    /**
     * Example 5 - get IdentificationDto by FSC - projection em JAVA Record - sem SQL
     * @example: http://localhost:8080/identifications/niin/contains/00003701
     * @example: http://localhost:8080/identifications/niin/contains/*00003701*
     * @example: http://localhost:8080/identifications/niin/contains/00003701*
     * @example: http://localhost:8080/identifications/niin/contains/*00003701
     *
     * @param fsc
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/niin/contains/{niin}")
    public ResponseEntity<Page<IdentificationDto>> listIdentificationsDtoByNiin(
        @PathVariable("niin") String niin,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.getIdentificationsDtoByNiin(niin, page, size));
    }
}
