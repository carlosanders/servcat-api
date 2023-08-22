package mb.dabm.servcatapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.projection.IdentificationDTO;
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

    /**
     * consulta Derived Query methods da Interface do JpaRepository com paginação
     * @param page
     * @param size
     * @return
     */
    public Page<Identification> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    /**
     * consulta Derived Query methods da Interface do JpaRepository - único objeto
     * @param id
     * @return
     */
    public Identification findById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * Example 1 - projection em JAVA Record - with JPQL com paginação
     *
     * @param page
     * @param size
     * @return
     * @referencia https://thorben-janssen.com/spring-data-jpa-query-projections/
     * https://thorben-janssen.com/jpql/
     */
    public Page<IdentificationDto> getAllIdentificationDto(int page, int size) {
        return repository.findAllIdentificationDto(PageRequest.of(page, size));
    }

    /**
     * Example 2 - get IdentificationDto by INC - projection em JAVA Record - with JPQL com paginação
     * @param inc
     * @param page
     * @param size
     * @return
     * @referencia https://thorben-janssen.com/spring-data-jpa-query-projections/
     * https://thorben-janssen.com/jpql/
     */
    public Page<IdentificationDto> getAllIdentificationDtoByInc(String inc, int page, int size) {
        return repository.findByInc(inc, PageRequest.of(page, size));
    }

    /**
     * Example 3 - get IdentificationDto by FSC - projection em JAVA Record - sem SQL com paginação
     * @param fsc
     * @param page
     * @param size
     * @return
     */
    public Page<IdentificationDto> getAllIdentificationDtoByFsc(String fsc, int page, int size) {
        return repository.findByFsc(fsc, PageRequest.of(page, size));
    }

    /**
     * Examplo 4 - consulta com query - Native e Projection Interface - único objeto
     * @param niin
     * @return
     */
    public IdentificationDTO findByNiin(String niin) {
        return repository.getByNiin(niin);
    }

    /**
     * Examplo 5 - consulta Derived Query methods - com paginação
     * @url https://www.bezkoder.com/jpa-repository-query/
     * @param niin
     * @param page
     * @param size
     * @return
     */
    public Page<IdentificationDto> getIdentificationsDtoByNiin(String niin, int page, int size) {

        // Finding string length
        int n = niin.length();
        // First character of a string
        char first = niin.charAt(0);
        //System.out.println("first: "+first);
        // Last character of a string
        char last = niin.charAt(n - 1);
        //System.out.println("last: "+last);
        long count = niin.chars().filter(ch -> ch == '*').count();

        // VRF (*) - se existir mais do que 2 * ex: *00003701*
        if(count >= 2){
            //System.out.println(">= 2 = findByNiinContaining");
            //System.out.println(niin.replace("*", ""));
            return repository.findByNiinContaining(niin.replace("*", ""), PageRequest.of(page, size));
        }

        // VRF (*) - se existir 1 * no início do niin: ex: *00003701
        if(first=='*'){
            //System.out.println("compFirst > 0 = findByNiinEndingWith");
            //System.out.println(niin.replace("*", ""));
            return repository.findByNiinEndingWith(niin.replace("*", ""), PageRequest.of(page, size));
        }

        // VRF (*) - se existir 1 * no final do niin: ex: 00003701*
        if(last=='*'){
            //System.out.println("compLast > 0 = findByNiinStartingWith");
            //System.out.println(niin.replace("*", ""));
            return repository.findByNiinStartingWith(niin.replace("*", ""), PageRequest.of(page, size));
        }

        // VRF (*) - se nao houver * no niin: ex: 00003701
        //System.out.println("nenhuma das opcoes = findByNiinContaining");
        return repository.findByNiinContaining(niin, PageRequest.of(page, size));
    }

}
