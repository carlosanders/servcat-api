package mb.dabm.servcatapi.repository;

import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// import org.springframework.data.repository.CrudRepository;

public interface IdentificationRepository
    extends JpaRepository<Identification, Long> {

    /**
     * Example 1
     * @param pageable
     * @return
     */
    @Query("""
    select new mb.dabm.servcatapi.model.IdentificationDto(
        i.fsc as fsc,
        i.niin as niin,
        i.nsn as nsn,
        i.itemName as itemName,
        i.inc as inc,
        i.tiic as tiic,
        i.rpdmrc as rpdmrc,
        i.fmsn as fmsn,
        i.origem as origem
    )
    from Identification i
    order by i.codGen
    """)
    Page<IdentificationDto> findAllIdentificationDto(Pageable pageable);

    /**
     * Example 2
     * @param inc
     * @param pageable
     * @return
     */
    @Query("""
    SELECT new mb.dabm.servcatapi.model.IdentificationDto(
        i.fsc,
        i.niin,
        i.nsn,
        i.itemName,
        i.inc,
        i.tiic,
        i.rpdmrc,
        i.fmsn,
        i.origem
    )
    FROM Identification i
    WHERE i.inc = :inc
    ORDER BY i.codGen
    """)
    Page<IdentificationDto> findByInc(String inc, Pageable pageable);

    /**
     * Exampl 3 - consulta sem query
     * @param fsc
     * @param pageable
     * @return
     */
    Page<IdentificationDto> findByFsc(String fsc, Pageable pageable);
}
