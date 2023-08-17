package mb.dabm.servcatapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class IdentificationService {

    @Autowired
    IdentificationRepository repository;

    public Page<Identification> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Identification findById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * Example 1 - projection em JAVA Record - with JPQL
     * @param page
     * @param size
     * @return
     * @referencia
     * https://thorben-janssen.com/spring-data-jpa-query-projections/
     * https://thorben-janssen.com/jpql/
     */
    public Page<IdentificationDto> getAllIdentificationDto(int page, int size) {
        return repository.findAllIdentificationDto(PageRequest.of(page, size));
    }

    /**
     * Example 2 - get IdentificationDto by INC - projection em JAVA Record - with JPQL
     * @param inc
     * @param page
     * @param size
     * @return
     * @referencia
     * https://thorben-janssen.com/spring-data-jpa-query-projections/
     * https://thorben-janssen.com/jpql/
     */
    public Page<IdentificationDto> getAllIdentificationDtoByInc(String inc, int page, int size) {
        return repository.findByInc(inc, PageRequest.of(page, size));
    }

    /**
     * Example 2 - get IdentificationDto by FSC - projection em JAVA Record - with SQL Native
     * @param fsc
     * @param page
     * @param size
     * @return
     */
    public Page<IdentificationDto> getAllIdentificationDtoByFsc(String fsc, int page, int size) {
        return repository.findByFsc(fsc, PageRequest.of(page, size));
    }

}
